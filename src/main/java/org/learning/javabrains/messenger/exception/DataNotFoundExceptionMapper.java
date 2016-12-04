package org.learning.javabrains.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.learning.javabrains.messenger.model.ExceptionMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException exception) {

		ExceptionMessage msg = new ExceptionMessage();
		msg.setCode(404);
		msg.setMessage(exception.getMessage());
		msg.setDocumentation("Not yet available");

		return Response.status(Status.NOT_FOUND).entity(msg).build();

	}

}
