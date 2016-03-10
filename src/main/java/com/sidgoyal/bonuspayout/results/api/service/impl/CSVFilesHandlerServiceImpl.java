package com.sidgoyal.bonuspayout.results.api.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ws.rs.NotFoundException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sidgoyal.bonuspayout.results.api.model.AccountModel;
import com.sidgoyal.bonuspayout.results.api.model.AccountsList;
import com.sidgoyal.bonuspayout.results.api.model.InstanceIdList;
import com.sidgoyal.bonuspayout.results.api.service.CSVFilesHandlerService;

import au.com.bytecode.opencsv.CSVReader;

@Service
public class CSVFilesHandlerServiceImpl implements CSVFilesHandlerService {

	Logger logger = Logger.getLogger(this.getClass().getName());

	String logsDir;

	public CSVFilesHandlerServiceImpl() {
		
		this.logsDir = System.getenv("LOG_DIR") == null ? System.getProperty("server.output.dir") + "logs"
				: System.getenv("LOG_DIR");

		logger.fine("LogsDir : " + logsDir);
	}

	@JsonCreator()
	public InstanceIdList getJobInstancesList() {
		Set<String> jobInstances = new HashSet<String>();

		for (File file : getFiles(".csv")) {

			logger.fine("Filename matched : " + file.getName());
			jobInstances.add((getInstanceIdForFile(file)));
		}
		return new InstanceIdList(jobInstances);
	}

	public AccountsList getAccountsForInstance(String instanceId) throws IOException {

		List<AccountModel> accountsList = new ArrayList<AccountModel>();

		File[] files =  getFiles("." + instanceId + ".csv");
		
		if(files.length > 1){
			logger.severe(" More than one file found for instancerId " + instanceId );
			throw new IllegalStateException();
		}
		if(files.length == 0){
			String errorMessage = " No files found for instanceId " + instanceId;
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
		while ((nextLine = reader.readNext()) != null) {
			if (nextLine != null) {
				accountsList.add(new AccountModel(nextLine[0], nextLine[1], nextLine[2]));
			}
		}
		
		logger.fine("Returning accoutns : " + accountsList) ;
		return new AccountsList(accountsList);
	}

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

	private String getInstanceIdForFile(File file) {
		return file.getName().split("\\.")[1];
	}
}
