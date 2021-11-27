package com.dbs.payment.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.payment.model.Message;
import com.dbs.payment.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messagerepo;
	
	
	public List<Message> findAllMessageID(){
		
		List<Message> list = (List<Message>) messagerepo.findAll();
		
		return list;
		
	}
	
	public Message findMessageByID(String msgcode) {
		
		try {
		Optional<Message> opt = messagerepo.findById(msgcode);
		
		return opt.orElseThrow(()->{
			return new EntityNotFoundException("Message Instruction with Message Code "+msgcode+" Not Found");
		});
		}catch(IllegalArgumentException e) {
			
			return null;
			
		}
		
	}

}
