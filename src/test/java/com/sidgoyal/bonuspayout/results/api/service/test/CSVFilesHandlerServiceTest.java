package com.sidgoyal.bonuspayout.results.api.service.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sidgoyal.bonuspayout.results.api.config.Config;
import com.sidgoyal.bonuspayout.results.api.model.AccountModel;
import com.sidgoyal.bonuspayout.results.api.service.CSVFilesHandlerService;

/*
 * I think all code paths are already being tested by JobInstancesTest
 * Just adding one test to save the boilerplate to add tests later if needed
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Config.class})
public class CSVFilesHandlerServiceTest {
	
	@Autowired 
	private CSVFilesHandlerService csvService;

	@Test
	public void getJobInstancesTest() throws Exception{
		
		List<Long> instanceIds = csvService.getJobInstancesList();
		
		assertEquals(2, instanceIds.size());
	}
	
	@Test
	public void getAccountsTest() throws Exception{
		
		List<AccountModel> accounts = csvService.getAccountsForInstance("3").getAccounts();
		
		assertEquals(4, accounts.size());
	}

}
