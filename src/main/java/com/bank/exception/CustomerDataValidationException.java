package com.bank.exception;

public class CustomerDataValidationException extends RuntimeException {

	private String exceptionMeassage;
	public CustomerDataValidationException(String message) {
		
		super(message);
		this.exceptionMeassage=message;
	}
	public String getExceptionMeassage() {
		return exceptionMeassage;
	}
	public void setExceptionMeassage(String exceptionMeassage) {
		this.exceptionMeassage = exceptionMeassage;
	}
	
	
	
	

}
