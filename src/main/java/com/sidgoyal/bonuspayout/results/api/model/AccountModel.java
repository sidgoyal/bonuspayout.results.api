package com.sidgoyal.bonuspayout.results.api.model;

public class AccountModel {
	
	String accountNumber ; 
	String amount;
	String accountType ;
	
	public AccountModel(String accountNumber, String amount, String accountType) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.accountType = accountType;
	}
	
	public AccountModel(){}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "AccountModel [accountNumber=" + accountNumber + ", amount=" + amount + ", accountType=" + accountType
				+ "]";
	}
	
	

}
