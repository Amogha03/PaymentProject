package com.dbs.payment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.payment.model.Message;
import com.dbs.payment.service.MessageService;

@RestController
@CrossOrigin
@RequestMapping("/message")
public class MessageController {

	
	@Autowired
	private MessageService messageservice;
	
	@GetMapping
	public ResponseEntity<List<Message>> findAllMessages(){
		
		List<Message> list =  this.messageservice.findAllMessageID();
		
		return ResponseEntity.status(HttpStatus.OK).body(list);

	}
}
