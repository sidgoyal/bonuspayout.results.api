package com.sidgoyal.bonuspayout.results.api.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.sidgoyal.bonuspayout.results.api.model.AccountsList;
import com.sidgoyal.bonuspayout.results.api.model.InstanceIdList;

public interface CSVFilesHandlerService {
	
	public InstanceIdList getJobInstancesList() ;

	public AccountsList getAccountsForInstance(String instanceId) throws FileNotFoundException, IOException;

}
