package com.sidgoyal.bonuspayout.results.api.model;

import java.util.List;

public class AccountsList {
	
	private List<AccountModel> accounts ;
	
	public AccountsList(List<AccountModel> accounts) {
		this.accounts = accounts;
	}
	
	public AccountsList(){}

	public void setAccounts(List<AccountModel> accounts) {
		this.accounts = accounts;
	}
	

	public List<AccountModel> getAccounts() {
		return accounts;
	}
	
	@Override
	public String toString() {
		return "AccountsList [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
