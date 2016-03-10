package com.sidgoyal.bonuspayout.results.api.errorHandling;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException arg0) {
		return  Response
				.status(Response.Status.NOT_FOUND)
				.type(MediaType.APPLICATION_JSON)
				.entity(arg0.getMessage())
				.build();
	}

}
