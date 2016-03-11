package com.sidgoyal.bonuspayout.results.api.resources.test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sidgoyal.bonuspayout.results.api.config.MyApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml") //classes = {Config.class})
public class JobInstancesTest extends JerseyTest {
	
    @Before
    public void before() {
    }


    @Override
	protected Application configure() {
		return new MyApplication();
    }

	@Test
    public void testGetJobInstances() throws Exception {
    	
		Response response = target("/results/jobinstances").request().get();
    	
    	assertEquals(Status.OK, response.getStatusInfo());
    	assertEquals(MediaType.APPLICATION_JSON,response.getHeaderString("Content-Type"));
    	
    	String expected = "{\"instanceIds\":[3,43]}";
    	
    	//This also asserts the order in the array
    	JSONAssert.assertEquals(expected,response.readEntity(String.class), true );
    }
	
	@Test
	public void testGetAccounts() throws Exception{
		
    	Response response = target("/results/jobinstances/3").request().get();
    	
    	assertEquals(Status.OK, response.getStatusInfo());
    	assertEquals(MediaType.APPLICATION_JSON,response.getHeaderString("Content-Type"));
    	
    	String expected = "{\"accounts\":["
    			+ "{\"accountNumber\":\"0\",\"amount\":\"372\",\"accountType\":\"PREF\"},"
    			+ "{\"accountNumber\":\"1\",\"amount\":\"243\",\"accountType\":\"PREF\"},"
    			+ "{\"accountNumber\":\"2\",\"amount\":\"912\",\"accountType\":\"PREF\"},"
    			+ "{\"accountNumber\":\"3\",\"amount\":\"958\",\"accountType\":\"PREF\"}"
    			+ "]}";
    			
    	JSONAssert.assertEquals(expected, response.readEntity(String.class), true);
	}
	
	@Test
	public void testGetAccountsNotFound(){
		
		Response response = target("/results/jobinstances/4").request().get();
    	
    	assertEquals(Status.NOT_FOUND, response.getStatusInfo());
    	assertEquals(MediaType.APPLICATION_JSON,response.getHeaderString("Content-Type"));
    	assertEquals("No files found for instanceId 4",(response.readEntity(String.class)));
	}
	
	@Test//(expected=javax.ws.rs.ProcessingException.class)
	public void testGetAccountsDuplicateFiles(){

		Response response = target("/results/jobinstances/43").request().get();
    	
		assertEquals(Status.CONFLICT, response.getStatusInfo());
    	assertEquals(MediaType.APPLICATION_JSON,response.getHeaderString("Content-Type"));
    	assertEquals("More than one file found for instancerId 43",(response.readEntity(String.class)));
	}

}
