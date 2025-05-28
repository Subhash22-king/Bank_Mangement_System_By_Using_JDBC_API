package com.bank.service;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

import com.bank.DAO.CustomerDAO;
import com.bank.DAO.TransactionDAO;
import com.bank.DTO.CustomerDetailsDTO;
import com.bank.DTO.TransactionDetailsDTO;


public class CustomerService
{
    CustomerDAO cdao=new CustomerDAO();
    CustomerDetailsDTO cdto;
	Scanner sc=new Scanner(System.in);
	TransactionDAO tdao=new TransactionDAO();
	TransactionService ts=new TransactionService();
	
	
	Predicate<String> checkName=(name)->name.length()>=3;
	public String validationName()
	{
		while(true)
		{
			System.out.println("Enter the Customer name");
            String  name=sc.next().trim();
			if(checkName.test(name))
			{
				return name;
			}
			else
			{
				System.out.println("Invalid  name ");
				continue;
			}
		}
		
	}
	
	Predicate<String> checkMail=(email)->email.contains("@gmail.com");
	public String validationEmailid()
	{
		while(true)
		{
			System.out.println("Enter the Customer emailid");
	        String emailid=sc.next().trim();
			if(checkMail.test(emailid))
			{
				if(cdao.existsEmailid(emailid))
				{
					return emailid;
				}
				else
				{
					System.out.println("Emailid already existed ");
					continue;
				}
			}
			else
			{
				System.out.println("Invalid Email id");
				continue;
			}
		}
		
	}
	
	
    Predicate<String> checkAddress=address->address.length()>=5;
	public String validationAddress()
	{
		while(true)
		{
			System.out.println("Enter the Customer address");
	        String address=sc.next().trim();
			if(checkAddress.test(address))
			{
				
				return address;
				
			}
			else
			{
				System.out.println("Invalid address");
				continue;
			}
		}
		
	}
	
	Predicate<String> checkGender=gender->gender.equalsIgnoreCase("male")||gender.equalsIgnoreCase("female");
	public String validationGender()
	{
		while(true)
		{
			System.out.println("Enter the Customer gender");
	        String gender=sc.next().trim();
			if(checkGender.test(gender))
			{
				
					return gender ;
				
			}
			else
			{
				System.out.println("Invalid gender it should be either male or female");
				continue;
			}
		}
		
	}
	
	
	
	public String validationPancardNumber()
	{
		while(true)
		{
			System.out.println("Enter the Customer Pancard number");
	        String pancardNo=sc.next().trim().toUpperCase();
	        String template="^[A-Z]{5}\\d{4}[A-Z]{1}$";
	        
			if(pancardNo.matches(template))
			{
				if(cdao.existsPanNumber(pancardNo))
				{
					return pancardNo;
				}
				else
				{
					System.out.println("Duplicate Pancard number, Already existed");
					continue;
				}
			}
			else
			{
				System.out.println("Invalid pancard number");
				continue;
			}
		}
		
	}
	
	public long validationMobileNumber()
	{
		while(true)
		{
			System.out.println("Enter the customer mobile number");
	        long mobilenumber=sc.nextLong();
	        String mnStr=""+mobilenumber;
			String pattern="^[6-9]\\d{9}$";
			if(mnStr.matches(pattern))
			{
				if(cdao.existsMobileNumber(mobilenumber))
				{
					return mobilenumber;
				}
				else
				{
					System.out.println("Duplicate mobilenumber,Already existed");
				}
			}
			else
			{
				System.out.println("Invalid mobile number");
				continue;
			}
		}
		
	}
	
	public boolean isNumberic(String s)
	{
		char[] str=s.toCharArray();
		for(char ch:str)
		{
			if(Character.isAlphabetic(ch))
			{
				return false;
			}
			
		}
		return true;
	}
	
	
	public long validationAadhaarNumber()
	{
		while(true)
		{
			System.out.println("Enter the customer aadhaar number");
	        long aadhaar=sc.nextLong();
	        String adhStr=""+aadhaar;
			
			if(adhStr.length()==12 && isNumberic(adhStr))
			{
				if(cdao.existsAadhaar(aadhaar))
				{
					return aadhaar;
				}
				else
				{
					System.out.println("Aadhaar number already existed");
					continue;
				}
			}
			else
			{
				System.out.println("Invalid adhaar number");
				continue;
			}
		}
		
	}
	
	Predicate<String> checkDate=dateofbirth->  dateofbirth.matches("^\\d{4}\\-\\d{2}\\-\\d{2}$");
	public String validateDate()
	{
		while(true)
		{
			System.out.println("Enter the Customer date of birth\nFollow order like:YYYY-MM-DD");
		     String dateofbirth=sc.next();
		     
		     if(checkDate.test(dateofbirth))
		     {
		    	 return dateofbirth;
		     }
		     else
		     {
		    	 System.out.println("Invalid date order");
		    	 continue;
		     }
		}
	}
	
	public double validateAmount()
	{
		while(true)
		{
			System.out.println("Enter the Customer amount");
		     double amount=sc.nextDouble();
		     if(amount<=0)
		     {
		    	 System.out.println("Amount should be more than zero(0)");
		    	 continue;
		     }
		     else
		     {
		    	 return amount;
		     }
		}
	}

	
	public long validateAccountNo()
	{
		while(true)
		{
			System.out.println("Enter the customer account number");
	        long accountNo=sc.nextLong();
	        
			
			if((accountNo+"").matches("^\\d{11}$"))
			{
				if(cdao.existsAadhaar(accountNo))
				{
					return accountNo;
				}
				else
				{
					System.out.println("Account number already existed");
					continue;
				}
			}
			else
			{
				System.out.println("Invalid Account Number");
				continue;
			}
		}
	}
	
	
	public void customerRegistration()
	{
		String name=validationName();
		
		String emailid=validationEmailid();
		
		String address=validationAddress();
		
		String gender=validationGender();
		
		String pancard=validationPancardNumber();
		
		long mobileNo=validationMobileNumber();
		
		long aadhaarNo=validationAadhaarNumber();
		
	    String dateofbirth=validateDate();
	     
	    double amount=validateAmount();
	    
	    

	    CustomerDetailsDTO csdto= new CustomerDetailsDTO(name, emailid, address, gender, pancard, mobileNo,
	    		aadhaarNo,Date.valueOf(dateofbirth),amount);
		System.out.println(csdto);
		
		cdao.customerInsertingOperation(csdto);
		
		
	}
	
	
	Function<String,String> gender=(gender)->
	{
		if(gender.equalsIgnoreCase("male")) return "Mr";
		else return "Miss";
	};
	
	public void customerLogin()
	{
		System.out.println("enter customer email");
		String email=sc.next();
		System.out.println("Enter customer pin");
		int pin=sc.nextInt();
		cdto=cdao.checkCustomerEmailAndPin(email,pin);
		long accountNo=validateAccountNo();
		
		if(cdto!=null && (accountNo==cdto.getAccountNo()) )
		{
		    System.out.println("Hello "+gender.apply(cdto.getGender())+"."+cdto.getName());
			
			
			System.out.println("Enter\n"
					+"1.Deposit\n"
					+"2.Withdraw\n"
					+"3.Check Balance\n"
					+"4.Check Statement\n"
					+"5.Change Pin\n"
					+"6.Close Account\n"
					+"7.Update Details");
			
			switch (sc.nextInt()) {
			
			case 1:{
				
				Double amount=validateAmount();
				
				//	//transaction_amount, transaction_date, transaction_time, transaction_balance, customer_account_number,
				//transaction_type
				CustomerDetailsDTO customerDetailsDTO= cdao.getBalanceAmount(email);
                    double updatedAmount=calculatedAmount(customerDetailsDTO.getAmount(), amount ,"deposit");
			    
			        int status=cdao.insertNewAmount(updatedAmount, email);
			        if(status==1)
			        {
			        	System.out.println("Deposit completed");
			        	
			        	ts.addingTransactionDetailsIntoDTO(amount, updatedAmount, accountNo,"credit");
			        	
			        	
			        }
			        else
			        {
			        	System.out.println("Deposit incomplete");
			        }
			}
				
				break;
			
			case 2:{
				
				Double amount=validateAmount();
				CustomerDetailsDTO customerDetailsDTO= cdao.getBalanceAmount(email);
			    double updatedAmount=calculatedAmount(customerDetailsDTO.getAmount(), amount ,"withdraw");
			    
			    	System.out.println("Withdraw started");
			    	
			    	if(updatedAmount!=0)
			    	{
			    	
			        int status=cdao.insertNewAmount(updatedAmount, email);
				        if(status==1)
				        {
				        	System.out.println("Withdraw successfully");
	
				        	ts.addingTransactionDetailsIntoDTO(amount, updatedAmount, accountNo,"debit");
				        }
				        else {
				        	System.out.println("Withdraw incompleted");
				        }
			    	}
			    	else {
			    		System.out.println("insufficient balance");
			    	}
			        
			  
			}

			
			break;
			case 3:{
				
				System.out.println("Checking balance");
				cdao.checkBalance(email);
			}
			
			break;
			case 4:{
				System.out.println("checking statement");
				
				List<TransactionDetailsDTO> customerTransactionList=tdao.checkStatement(accountNo);
				
				try {
					
						customerTransactionList.forEach(eachTrans->{
							System.out.println(eachTrans);
						});
						
				}
				catch(NullPointerException e)
				{
					System.out.println("No Transaction made");
				}
				
			}
			
			break;
			case 5:{
				
				System.out.println("Changing pin");
				System.out.println("Enter 4 digits pin");
				int newPin=sc.nextInt();
				if((newPin+"").matches("^\\d{4}$"))
				{
					int status=cdao.changePin(email, newPin);	
					
					if(status==1)
					{
						System.out.println("New Pin Updated");
					}
				}
				else
				{
				 System.out.println("Invalid pin");
				}
			}
			
			break;
			case 6:{
				System.out.println("Closing account");
				System.out.println("Enter 1 to confirm 0 to cancel");
				int confirm=sc.nextInt();
				
				
				
				
				if(confirm==1)
				{
					int status=cdao.accountClosingRequest(email);
					
					if(status==1)
					{
						System.out.println("Account closing request has been generated");
					}
					
					
				}
				else return;
				
			}
			
			break;
			case 7:{
				System.out.println("Update details");
				
				boolean continuing=true;
				
				while(continuing)
				{
					System.out.println("Enter\n"
							+ "1.To update name\n"
							+ "2.To update email\n"
							+ "3.To update address\n"
							+ "4.To update mobile number\n"
							+ "5.Update date of birth"
							+ "6.To exit");
					
					int request=sc.nextInt();
					
					switch (request) {
					case 1:{
						System.out.println("Updating name");
						String name=validationName();
						int status=cdao.updateName(name, email);
						if(status==1)
						{
							System.out.println("Name is Updated.");
						}
						else
						{
							System.out.println("Name is not updated");
						}
						
					}
						
						break;
					case 2:{
						System.out.println("Updating email");

						String newEmail=validationEmailid();
						int status=cdao.updateEmail(newEmail, email);
						if(status==1)
						{
							System.out.println("New mail is Updated.");
						}
						else
						{
							System.out.println("Mail is not updated");
						}
						
					}
					break;
					
					case 3:{
						System.out.println("Updating address");
						String newAddress=validationAddress();
						
						int status=cdao.updateAddress(newAddress, email);
						if(status==1)
						{
							System.out.println("New address is Updated.");
						}
						else
						{
							System.out.println("Address is not updated");
						}
						
					}
					break;
					
					case 4:{
						long newMobileNo=validationMobileNumber();
						
							int status=cdao.updateMobileNumber(newMobileNo, email);
							
							if(status==1)
							{
								System.out.println("New mobile number is updated");
							}
							else
							{
								System.out.println("Mobile number is not updated");
							}
					}
					break;
					
					case 5:{
						String newDateOfBirth=validateDate();
						
							int status=cdao.updateDateOfBirth(newDateOfBirth, email);
							
							if(status==1)
							{
								System.out.println("New DOB is updated");
							}
							else
							{
								System.out.println("New DOB is not updated");
							}
					}
					break;
					
					
					case 6:{
						continuing=false;
						System.out.println("Thankyou goodBye!");
						break;
					}
						
						

					default:{
						System.out.println("Invalid choice");
					}
						break;
					}
				}
			}
			
			break;

			default:{
				System.out.println("Invalid request");
			}
				break;
			}
		}
		else
		{
			System.out.println("Invalid MailId or Pin or account number");
		}
	}
	
	public double calculatedAmount(double databaseAmount,double amount ,String type)
	{
		double newAmount=0;
		if(type.equalsIgnoreCase("deposit")) {
		newAmount=databaseAmount+amount;
		return newAmount;
				}
		else if(type.equalsIgnoreCase("withdraw"))
		{
			if(databaseAmount>=amount)
			{
				newAmount=databaseAmount-amount;
				return newAmount;
			}
			else {
				return 0;
			}
			
		}
		return 0;
	}

}
