package org.learning.javabrains.messenger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class CommentResource {

	@GET
	public String testMethod() {
		return "sub resourcing";
	}

	@GET
	@Path("/{commentid}")
	public String getparameters(@PathParam("commentid") long commentId, @PathParam("messageId") long mesgid) {
		return "sub resourcing with message id: " + mesgid + " Comment Id: " + commentId;

	}

}
