package com.bank.service;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.bank.DAO.AdminDAO;
import com.bank.DAO.CustomerDAO;
import com.bank.DTO.CustomerDetailsDTO;

public class AdminService {
	
	Scanner sc=new Scanner(System.in);
	AdminDAO adminDAO=new AdminDAO();
	CustomerDAO customerDAO=new CustomerDAO();
	
	public void adminLogin()
	{
		System.out.println("Enter admin Email id");
		String emailid=sc.next();
		System.out.println("Enter Admin passsword");
		String password=sc.next();
		adminDAO.getAdminDetailsUsingEMailAndPassword(emailid, password);
		if(adminDAO.getAdminDetailsUsingEMailAndPassword(emailid, password))
		{
			System.out.println("Enter\n"
					+ "1.To accept account request\n"
					+ "2.To accept account closing request\n"
					+ "3.To get all customer details");
			
			switch (sc.nextInt()) {
			case 1:{
				System.out.println("Pending customer registration");
				List<CustomerDetailsDTO> allPendingDetails=customerDAO.getAllPendingCustomerDetails();
				
				if(allPendingDetails!=null)
				{
				   allPendingDetails.forEach(pendingRegistration->{
					   
					   

					   int indexOf=allPendingDetails.indexOf(pendingRegistration);
					   int sNo=indexOf+1;
					   System.out.println("S.no: "+sNo);
					   System.out.println(pendingRegistration);
					   System.out.println("**********-----------**************");
					   					   
					   
				   });
				   System.out.println("Enter SerialNo of Object which wanted to update");
				   int serialNo=sc.nextInt();
				   CustomerDetailsDTO cddto=allPendingDetails.get(serialNo-1);
				   Random random=new Random();
				   long accountNo=random.nextLong(100000000000l);
				   if(accountNo<1000000000)
				   {
					   accountNo+=1000000000;
				   }
				   int pin=random.nextInt(10000);
				   if(pin<1000) {
					   pin+=1000;
				   }
				   boolean status=customerDAO.newAccountNumberAndPinBasesOnEmail(accountNo, pin, cddto.getEmailid());
				   if(status)
				   {
					   System.out.println("Account is created");
					   System.out.println(cddto.getName()+" this is your new account number:"+accountNo);
					   System.out.print("and this is your new pin number:"+pin);
				   }
				   else
				   {
					   System.out.println(" Not Updated");
				   }
				   
				}
				else
				{
					System.out.println("No pending registration");
				}
			}
				
				break;
			case 2:{
				System.out.println("Accept account closing request");
				List<CustomerDetailsDTO> closingCustomerList=customerDAO.getClosingCustomerDetails();
				
				closingCustomerList.forEach(eachClosingCustomer->{
					int closingCusIndex=closingCustomerList.indexOf(eachClosingCustomer);
					int serialNo=1+closingCusIndex;
					System.out.println("SerialNo: "+serialNo);
					System.out.println(eachClosingCustomer);
					System.out.println("********----------------****************");
					
				});
				System.out.println("Enter SerailNo of Customer which wanted to close");
				int sNo=sc.nextInt();
				CustomerDetailsDTO selectedObject=closingCustomerList.get(sNo-1);
				
				if(customerDAO.closingAccountBasedOnSelectedObject(selectedObject))
				{
					System.out.println(selectedObject.getName()+"'s account closed succesfully");
				}
				else
				{
					System.out.println(selectedObject.getName()+"'s account deleting not completed");
				}
				
			}
				
				break;
			case 3:{
				System.out.println("Get all customer details");
				List<CustomerDetailsDTO> allDetails=customerDAO.getAllCustomerDetails();
				
				allDetails.forEach((eachCustomer)->{
					System.out.println(eachCustomer);
					System.out.println("****---------------*****");
				});
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
			System.out.println("Invalid email or password");
		}
		
		
	}

}
