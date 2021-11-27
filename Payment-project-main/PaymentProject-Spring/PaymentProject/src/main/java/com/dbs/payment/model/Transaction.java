package com.dbs.payment.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	private int transactionid;
	
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customer;
	
//	@ManyToOne
//	@JoinColumn(name="currencycode")
//	private Currency currency;
	
	@ManyToOne
	@JoinColumn(name="senderBIC")
	private Bank senderbic;
	
	@ManyToOne
	@JoinColumn(name="receiverBIC")
	private Bank receiverbic;
	
	@JoinColumn(name="receiveraccountholdernumber", columnDefinition = "char(11)")
	@ManyToOne
	private Customer receiveraccounholdernumber;
	
	
	private String receiveraccountholdername;
	
	@ManyToOne
	@JoinColumn(name="transfertypecode")
	private TransferTypes transfertypecode;
	
	@ManyToOne
	@JoinColumn(name="messagecode")
	private Message message;
	
	
	private double currencyamount;
	
	private double transferfees;
	
//	private double inramount;
	
	@Column(name="transferdate")
	private LocalDate transferDate;
	
	
	
	public Transaction() {
		
	}



	public Transaction(int transactionid, Customer customer, Bank senderbic, Bank receiverbic,
			Customer receiveraccounholdernumber, String receiveraccountholdername, TransferTypes transfertypecode,
			Message message, double currencyamount, double transferfees, LocalDate transferDate) {
		super();
		this.transactionid = transactionid;
		this.customer = customer;
		this.senderbic = senderbic;
		this.receiverbic = receiverbic;
		this.receiveraccounholdernumber = receiveraccounholdernumber;
		this.receiveraccountholdername = receiveraccountholdername;
		this.transfertypecode = transfertypecode;
		this.message = message;
		this.currencyamount = currencyamount;
		this.transferfees = transferfees;
		this.transferDate = transferDate;
	}



	public int getTransactionid() {
		return transactionid;
	}



	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Bank getSenderbic() {
		return senderbic;
	}



	public void setSenderbic(Bank senderbic) {
		this.senderbic = senderbic;
	}



	public Bank getReceiverbic() {
		return receiverbic;
	}



	public void setReceiverbic(Bank receiverbic) {
		this.receiverbic = receiverbic;
	}



	public Customer getReceiveraccounholdernumber() {
		return receiveraccounholdernumber;
	}



	public void setReceiveraccounholdernumber(Customer receiveraccounholdernumber) {
		this.receiveraccounholdernumber = receiveraccounholdernumber;
	}



	public String getReceiveraccountholdername() {
		return receiveraccountholdername;
	}



	public void setReceiveraccountholdername(String receiveraccountholdername) {
		this.receiveraccountholdername = receiveraccountholdername;
	}



	public TransferTypes getTransfertypecode() {
		return transfertypecode;
	}



	public void setTransfertypecode(TransferTypes transfertypecode) {
		this.transfertypecode = transfertypecode;
	}



	public Message getMessage() {
		return message;
	}



	public void setMessage(Message message) {
		this.message = message;
	}



	public double getCurrencyamount() {
		return currencyamount;
	}



	public void setCurrencyamount(double currencyamount) {
		this.currencyamount = currencyamount;
	}



	public double getTransferfees() {
		return transferfees;
	}



	public void setTransferfees(double transferfees) {
		this.transferfees = transferfees;
	}



	public LocalDate getTransferDate() {
		return transferDate;
	}



	public void setTransferDate(LocalDate transferDate) {
		this.transferDate = transferDate;
	}



	@Override
	public String toString() {
		return "Transaction [transactionid=" + transactionid + ", customer=" + customer + ", senderbic=" + senderbic
				+ ", receiverbic=" + receiverbic + ", receiveraccounholdernumber=" + receiveraccounholdernumber
				+ ", receiveraccountholdername=" + receiveraccountholdername + ", transfertypecode=" + transfertypecode
				+ ", message=" + message + ", currencyamount=" + currencyamount + ", transferfees=" + transferfees
				+ ", transferDate=" + transferDate + "]";
	}






	
	
}
