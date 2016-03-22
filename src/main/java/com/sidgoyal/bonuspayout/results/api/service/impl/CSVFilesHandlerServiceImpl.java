package com.sidgoyal.bonuspayout.results.api.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import javax.ws.rs.NotFoundException;

import jersey.repackaged.com.google.common.collect.Lists;

import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVReader;

import com.sidgoyal.bonuspayout.results.api.model.AccountModel;
import com.sidgoyal.bonuspayout.results.api.model.AccountsList;
import com.sidgoyal.bonuspayout.results.api.model.InstanceIdList;
import com.sidgoyal.bonuspayout.results.api.service.CSVFilesHandlerService;

@Service
public class CSVFilesHandlerServiceImpl implements CSVFilesHandlerService {

	Logger logger = Logger.getLogger(this.getClass().getName());

	String logsDir;

	public CSVFilesHandlerServiceImpl() {
		
		//Set the logs directory either by:
		// system property : server.output.dir (For WAS LibertyProfile, its wlp/usr/servers/{srever.name}/) 
		// or environment variable " LOG_DIR
		this.logsDir = System.getenv("CSV_DIR") == null ? System.getProperty("server.output.dir") + "logs"
				: System.getenv("CSV_DIR");

		logger.fine("LogsDir : " + logsDir);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sidgoyal.bonuspayout.results.api.service.CSVFilesHandlerService#getJobInstancesList()
	 */
	public InstanceIdList getJobInstancesList() {
		Set<Long> jobInstances = new TreeSet<Long>();

		for (File file : getFiles(".csv")) {

			logger.fine("Filename matched : " + file.getName());
			jobInstances.add(getInstanceIdForFile(file));
		}
		return new InstanceIdList(Lists.newArrayList(jobInstances));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sidgoyal.bonuspayout.results.api.service.CSVFilesHandlerService#getAccountsForInstance(java.lang.String)
	 */
	public AccountsList getAccountsForInstance(String instanceId) throws IOException {

		List<AccountModel> accountsList = new ArrayList<AccountModel>();

		File[] files =  getFiles("." + instanceId + ".csv");
		
		if(files.length > 1){
			String errorMessage = "More than one file found for instancerId " + instanceId;
			logger.warning(errorMessage);
			throw new IllegalStateException(errorMessage);
		}
		if(files.length == 0){
			String errorMessage = "No files found for instanceId " + instanceId;
			logger.warning(errorMessage);
			throw new NotFoundException(errorMessage);
		}
		
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(files[0]));
		} catch (FileNotFoundException e) {
			logger.warning(e.toString());
			//Do nothing. If there was no NotFoundException, chances are that this won't be thrown
		}
		
		// Read CSV line by line and use the string array
		String[] nextLine;
		try{
			while ((nextLine = reader.readNext()) != null) {
				if (nextLine != null) {
					accountsList.add(new AccountModel(nextLine[0], nextLine[1], nextLine[2]));
				}
			}
		}
		catch(IOException e){
			//We'll let the jersey/the container to deal with this. 
			//Don't want to swallow the exception
			throw e;
		}
		finally{
			//Close the reader first
			try{
				reader.close();
			}
			catch(Exception e){
				//DO Nothing
			}
		}
		
		logger.fine("Returning accoutns : " + accountsList) ;
		return new AccountsList(accountsList);
	}
	
	/*
	 * @param the pattern used to search the directories
	 * @returns list of files that ends with the supplied pattern
	 */
	private File[] getFiles(final String pattern) {
		File dir = new File(logsDir);
		File[] files = dir.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(pattern);
			}

		});

		return files;
	}

	/*
	 * returns instance id for the provided file.
	 * 
	 * Ex: for file name ****.1.csv, returns 1
	 * 
	 * @param file instance
	 * @returns instanceId 
	 */
	private long getInstanceIdForFile(File file) {
		return Long.parseLong(file.getName().split("\\.")[1]);
	}
}
