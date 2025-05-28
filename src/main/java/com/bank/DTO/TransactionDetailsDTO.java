package com.bank.DTO;

public class TransactionDetailsDTO {
	
	//transaction_amount, transaction_date, transaction_time, transaction_balance, customer_account_number, transaction_type
	
	private int id;
	private double amount;
	private String date;
	private String time;
	private double balance;
	private long accountnumber;
	private String type;
	
	
	public TransactionDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TransactionDetailsDTO(int id, double amount, String date, String time, double balance, long accountnumber,
			String type) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.time = time;
		this.balance = balance;
		this.accountnumber = accountnumber;
		this.type = type;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public long getAccountnumber() {
		return accountnumber;
	}


	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "TransactionDetailsDTO [id=" + id + 
				", amount=" + amount +
				", date=" + date + 
				", time=" + time+
				", balance=" + balance + 
				", accountnumber=" + accountnumber + 
				", type=" + type + "]";
	}


	
	
	
	
	
	

}
