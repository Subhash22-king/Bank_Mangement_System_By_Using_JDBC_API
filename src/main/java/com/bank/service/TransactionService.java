package com.bank.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.bank.DAO.TransactionDAO;
import com.bank.DTO.TransactionDetailsDTO;

public class TransactionService {
	
	private final static LocalDate date=LocalDate.now();
	private final static LocalTime time=LocalTime.now();
	TransactionDetailsDTO transactionDetailsDTO=new TransactionDetailsDTO();
	TransactionDAO tdao=new TransactionDAO();
	public void addingTransactionDetailsIntoDTO(double amount,double updatedAmount,long accountNo,String type)
	{
		
    	DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
    	transactionDetailsDTO.setAmount(amount);

    	transactionDetailsDTO.setDate(date.toString());

    	transactionDetailsDTO.setTime(time.format(formatter));

    	transactionDetailsDTO.setBalance(updatedAmount);

    	transactionDetailsDTO.setAccountnumber(accountNo);

    	transactionDetailsDTO.setType(type);
    	System.out.println(transactionDetailsDTO);
    	int status=tdao.transactionDetails(transactionDetailsDTO);
    	
    	if(status==1)
    	{
    		System.out.println("Tranaction details are inserted.");
    	}
    	
	}

}
