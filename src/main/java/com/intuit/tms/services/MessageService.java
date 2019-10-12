package com.intuit.tms.services;

import java.util.List;

import com.intuit.tms.entities.Message;

public interface MessageService {
	void saveMessage(Message message);

	List<Message> getAllMessages();
}
