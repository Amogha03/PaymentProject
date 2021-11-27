package com.dbs.payment.model;

public class TopCustomer {
	
	private String name;
	private String amount;
	
	public TopCustomer() {
		
	}

	public TopCustomer(String name, String amount) {
		super();
		this.name = name;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "TopCustomer [name=" + name + ", amount=" + amount + "]";
	}
	
	
	
	

}
