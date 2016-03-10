package com.sidgoyal.bonuspayout.results.api.resources.test;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sidgoyal.bonuspayout.results.api.config.MyApplication;
import com.sidgoyal.bonuspayout.results.api.model.AccountsList;
import com.sidgoyal.bonuspayout.results.api.model.InstanceIdList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml") //classes = {Config.class})
public class JobInstancesTest extends JerseyTest {
	
    @Before
    public void before() {
    }


    @Override
	protected Application configure() {
		// TODO Auto-generated method stub
		return new MyApplication();
    }

	@Test
    public void test() {
    	InstanceIdList list = target("/results/jobinstances").request().get(InstanceIdList.class);
    }

}
