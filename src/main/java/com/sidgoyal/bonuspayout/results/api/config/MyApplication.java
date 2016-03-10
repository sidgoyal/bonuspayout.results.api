package com.sidgoyal.bonuspayout.results.api.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig{

	    public MyApplication() {
	    	super();
	        register(JacksonFeature.class);    // <----- Jackson Support
	        packages("com.sidgoyal.bonuspayout.results.api");
	}
}
