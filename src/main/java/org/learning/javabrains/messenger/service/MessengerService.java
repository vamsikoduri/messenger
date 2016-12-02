package org.learning.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.learning.javabrains.messenger.database.DatabaseClass;
import org.learning.javabrains.messenger.model.Message;

public class MessengerService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessengerService() {
		messages.put(1L, new Message(1L, "Hello World", "koushik"));
		messages.put(2L, new Message(2L, "Hello Jersey", "koushik"));
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	public Message getMessage(long id) {
		Message message = messages.get(id);

		return message;
	}

	public List<Message> getMessages(int id) {
		return (List<Message>) messages.values();

	}

	public Message addMessage(Message message) {
		message.setMessageId((long) (messages.size() + 1));
		messages.put(message.getMessageId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getMessageId() <= 0) {
			return null;
		}
		messages.put(message.getMessageId(), message);
		return message;
	}

	public Message removeMessage(long id) {
		return messages.remove(id);
	}

}
