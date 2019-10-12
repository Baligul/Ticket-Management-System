package com.intuit.tms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.tms.entities.Message;
import com.intuit.tms.repositories.MessageRepository;
import com.intuit.tms.services.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageRepository repoMessage;

	@Override
	public void saveMessage(Message message) {
		repoMessage.save(message);
	}

	@Override
	public List<Message> getAllMessages() {
		return repoMessage.findAll();
	}
}
