package com.sidgoyal.bonuspayout.results.api.errorHandling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public final class IllegalStateExceptionHandler implements ExceptionMapper<IllegalStateException>{

	public Response toResponse(IllegalStateException exception) {

		return  Response
				.status(Response.Status.CONFLICT)
				.type(MediaType.APPLICATION_JSON)
				.entity(exception.getMessage())
				.build();
	}
}
