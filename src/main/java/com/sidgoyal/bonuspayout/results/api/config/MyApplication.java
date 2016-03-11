package com.sidgoyal.bonuspayout.results.api.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/*
 * Configures the jersey context
 */
public class MyApplication extends ResourceConfig{

	    public MyApplication() {
	    	super();
	        register(JacksonFeature.class); 
	        //Lists which packages to scan for providers
	        packages("com.sidgoyal.bonuspayout.results.api");
	}
}
