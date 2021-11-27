package com.dbs.payment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class Message {

	@Id
	@Column(columnDefinition = "char(4)")
	private String messagecode;
	
	private String instruction;
	
	public Message() {
		
	}

	public Message(String messagecode, String instruction) {
		super();
		this.messagecode = messagecode;
		this.instruction = instruction;
	}

	public String getMessagecode() {
		return messagecode;
	}

	public void setMessagecode(String messagecode) {
		this.messagecode = messagecode;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	@Override
	public String toString() {
		return "Message [messagecode=" + messagecode + ", instruction=" + instruction + "]";
	}
	
	
}
