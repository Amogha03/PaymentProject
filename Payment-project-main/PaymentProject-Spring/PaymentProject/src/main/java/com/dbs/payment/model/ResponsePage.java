package com.dbs.payment.model;

public class ResponsePage {

	private String message;
	private String Description;
	
	public ResponsePage() {
		
	}

	public ResponsePage(String message, String description) {
		super();
		this.message = message;
		Description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public String toString() {
		return "ResponsePage [message=" + message + ", Description=" + Description + "]";
	}
	
    
}
