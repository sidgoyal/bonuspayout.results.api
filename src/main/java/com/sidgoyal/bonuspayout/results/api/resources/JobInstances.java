package com.sidgoyal.bonuspayout.results.api.resources;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sidgoyal.bonuspayout.results.api.model.AccountsList;
import com.sidgoyal.bonuspayout.results.api.model.InstanceIdList;
import com.sidgoyal.bonuspayout.results.api.service.CSVFilesHandlerService;

@Component
@Path("/results/jobinstances")
public class JobInstances {
	
	@Autowired
	public JobInstances(CSVFilesHandlerService fileHandler){
		this.fileHandler = fileHandler;
	}
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	private CSVFilesHandlerService fileHandler;

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public InstanceIdList getJobInstances(){
		
		logger.fine("Getting jobInstances");
		
		return fileHandler.getJobInstancesList();
	}
	
	@GET
	@Path("{instanceId}")
	@Produces({MediaType.APPLICATION_JSON})
	public AccountsList getAccounts(@PathParam("instanceId") String instanceId) throws IOException{
	
		logger.fine("Getting accounts for jobInstance :" + instanceId);
		
		return fileHandler.getAccountsForInstance(instanceId);
	}
	
	@GET
	@Path("exception")
	@Produces({MediaType.APPLICATION_JSON})
	public String getException(){
		
		throwException();
		return "Sent exception";
	}
	
	private void throwException(){
		throw new RuntimeException();
	}
}