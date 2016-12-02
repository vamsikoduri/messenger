package org.learning.javabrains.messenger;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.learning.javabrains.messenger.model.Message;
import org.learning.javabrains.messenger.service.MessengerService;

@Path("messages")
public class MessagesResources {

	MessengerService service = new MessengerService();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages() {
		return service.getAllMessages();
	}

}
