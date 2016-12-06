package org.learning.javabrains.messenger;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.learning.javabrains.messenger.model.ExceptionMessage;
import org.learning.javabrains.messenger.model.Message;
import org.learning.javabrains.messenger.service.MessageFilterBean;
import org.learning.javabrains.messenger.service.MessengerService;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessagesResources {

	MessengerService service = new MessengerService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@BeanParam MessageFilterBean filter) {

		if (filter.getYear() > 0) {
			return service.getAllMessagesForYear(filter.getYear());
		}
		if (filter.getStart() >= 0 && filter.getSize() > 0) {
			return service.getAllMessagesPaginated(filter.getStart(), filter.getSize());
		}
		return service.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		Message newMessage = service.getMessage(id);
		if (null == newMessage) {
			ExceptionMessage msg = new ExceptionMessage();
			msg.setCode(499);
			msg.setMessage("message not found");
			msg.setDocumentation("Not yet available");
			Response response = Response.status(Status.NOT_FOUND).entity(msg).build();
			throw new NotFoundException(response);
		}

		newMessage.addLink(getUrlForSelf(uriInfo, newMessage), "self");
		newMessage.addLink(getUrlForProfile(uriInfo, newMessage), "profile");
		newMessage.addLink(getUrlForComment(uriInfo, newMessage), "messages");
		return newMessage;
	}

	private String getUrlForComment(UriInfo uriInfo, Message newMessage) {
		String url = uriInfo.getBaseUriBuilder().path(MessagesResources.class)
				.path(MessagesResources.class, "getCommentReource").path(CommentResource.class)
				.resolveTemplate("messageId", newMessage.getId()).toString();
		return url;
	}

	private String getUrlForProfile(UriInfo uriInfo, Message newMessage) {
		String url = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(newMessage.getAuthor()).build()
				.toString();
		return url;
	}

	private String getUrlForSelf(UriInfo uriInfo, Message newMessage) {
		String url = uriInfo.getBaseUriBuilder().path(MessagesResources.class).path(Long.toString(newMessage.getId()))
				.build().toString();
		return url;
	}

	@POST
	public Response addMessage(@Context UriInfo uriInfo, Message mesg) {
		Message newMessage = service.addMessage(mesg);
		String messgId = String.valueOf(mesg.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(messgId).build();
		// return Response.status(Status.CREATED).entity(newMessage).build();
		return Response.created(uri).entity(newMessage).build();

	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message mesg) {
		mesg.setId(id);
		return service.updateMessage(mesg);

	}

	@DELETE
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id) {

		return service.removeMessage(id);

	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentReource() {

		return new CommentResource();
	}

}
