package org.learning.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;

import org.learning.javabrains.messenger.model.Message;

public class MessengerService {

	public List<Message> getMessage() {
		Message m1 = new Message(1, "Hello world!", "Vamsi");
		Message m2 = new Message(2, "Hello RestFull Service", "Krishna");
		ArrayList<Message> list = new ArrayList<Message>();
		list.add(m1);
		list.add(m2);
		return list;

	}

}
