package com.bank.DTO;

import java.sql.Date;

public class CustomerDetailsDTO {
	
	private String name,emailid,address,gender,pancard;
	private long mobilenumber,aadhaarnumber;
	private Date dateofbirth;
	private double amount;
	private long accountNo;
	
	
	public CustomerDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CustomerDetailsDTO(String name, String emailid, String address, String gender, String pancard,
			long mobilenumber, long aadhaarnumber, Date dateofbirth, double amount) {
		super();
		this.name = name;
		this.emailid = emailid;
		this.address = address;
		this.gender = gender;
		this.pancard = pancard;
		this.mobilenumber = mobilenumber;
		this.aadhaarnumber = aadhaarnumber;
		this.dateofbirth = dateofbirth;
		this.amount = amount;
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmailid() {
		return emailid;
	}


	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPancard() {
		return pancard;
	}


	public void setPancard(String pancard) {
		this.pancard = pancard;
	}


	public long getMobilenumber() {
		return mobilenumber;
	}


	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}


	public long getAadhaarnumber() {
		return aadhaarnumber;
	}


	public void setAadhaarnumber(long aadhaarnumber) {
		this.aadhaarnumber = aadhaarnumber;
	}


	public Date getDateofbirth() {
		return dateofbirth;
	}


	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public long getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}


	@Override
	public String toString() {
		return "CustomerDetailsDTO [name=" + name + ", emailid=" + emailid + ", address=" + address + ", gender="
				+ gender + ", pancard=" + pancard + ", mobilenumber=" + mobilenumber + ", aadhaarnumber="
				+ aadhaarnumber + ", dateofbirth=" + dateofbirth + ", amount=" + amount + ", accountNo=" + accountNo
				+ "]";
	}
	
	
	



		
	
	
	
	
	
	

}
