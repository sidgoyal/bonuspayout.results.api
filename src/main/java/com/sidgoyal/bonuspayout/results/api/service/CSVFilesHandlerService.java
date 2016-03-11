package com.sidgoyal.bonuspayout.results.api.service;

import java.io.IOException;

import com.sidgoyal.bonuspayout.results.api.model.AccountsList;
import com.sidgoyal.bonuspayout.results.api.model.InstanceIdList;

/*
 * @author sidgoyal
 */
public interface CSVFilesHandlerService {
	
	/*
	 * @returns List of InstaceIds that have associated csv file stored in the logs directory 
	 */
	public InstanceIdList getJobInstancesList() ;

	/*
	 * @returns List of Accounts
	 * @param instanceId
	 * @throws IOException
	 */
	public AccountsList getAccountsForInstance(String instanceId) throws IOException;

}
