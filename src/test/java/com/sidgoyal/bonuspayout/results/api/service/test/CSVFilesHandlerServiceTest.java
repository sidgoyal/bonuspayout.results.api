package com.sidgoyal.bonuspayout.results.api.service.test;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sidgoyal.bonuspayout.results.api.config.Config;
import com.sidgoyal.bonuspayout.results.api.service.CSVFilesHandlerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Config.class})
public class CSVFilesHandlerServiceTest {
	
	@Autowired 
	private CSVFilesHandlerService csvService;

	@BeforeClass
	public static void before(){
		//csvSer
	}
	
	@AfterClass
	public static void after(){
		//TODO
	}
	
	@Test
	public void getJobInstancesTest() throws Exception{
		
		Set<String> instanceIds = csvService.getJobInstancesList().getInstanceIds();
		
		assertEquals(2, instanceIds.size());
//		asssertTrue(list.c)
	}

	@Override
	public String toString() {
		return "CSVFilesHandlerServiceTest [csvService=" + csvService + "]";
	}
}
