package com.dbs.payment.constants;

public class BankContants {
	
	private static String Bank_Customer_IDS[]= {"69652133523248","45002608912874","42895235807723"};
	
	 static double TransferCharges = 0.25;
	
	public static double calculateTransferFee(double TransferAmount)
	{
		double fee = (double)(TransferAmount * TransferCharges)/(100*1.0);
		
		return fee;
	}

	public static double getTransferCharges() {
		return TransferCharges;
	}

	public static void setTransferCharges(double transferCharges) {
		TransferCharges = transferCharges;
	}

	public static String[] getBank_Customer_IDS() {
		return Bank_Customer_IDS;
	}

	public static void setBank_Customer_IDS(String[] bank_Customer_IDS) {
		Bank_Customer_IDS = bank_Customer_IDS;
	}
	
	
	
	
	

}
