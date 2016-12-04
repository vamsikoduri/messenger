package org.learning.javabrains.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.learning.javabrains.messenger.model.ExceptionMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		
		ExceptionMessage msg = new ExceptionMessage();
		msg.setCode(500);
		msg.setMessage(exception.getMessage());
		msg.setDocumentation("Generic Error Message");

		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(msg).build();
	}

}
