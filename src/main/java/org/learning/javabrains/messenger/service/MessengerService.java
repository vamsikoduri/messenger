package org.learning.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
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
		message.setId((long) (messages.size() + 1));
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(long id) {
		return messages.remove(id);
	}

	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}

	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if (start + size > list.size())
			return new ArrayList<Message>();
		return list.subList(start, start + size);
	}

}
