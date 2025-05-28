package com.bank;

import java.util.Scanner;

import com.bank.service.AdminService;
import com.bank.service.CustomerService;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {//after adding dependency we need to save , otherwise it will not appear in maven dependencies.
    	Scanner sc=new Scanner(System.in);
    	
        CustomerService cs=new CustomerService();
        AdminService adminService=new AdminService();
        String wel="--------------Welcome to SUMO BANK Pvt Ltd!----------------------";
        
        for(char ch:wel.toCharArray())
        {
        	System.out.print(ch);
        	try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block2
				e.printStackTrace();
			}
        }
        System.out.println();
        System.out.println("Enter\n1.To customer registration\n2.To customer login\n3.To admin login");
        
        
        
        switch(sc.nextInt())
        {
        case 1:cs.customerRegistration();
        break;
        case 2:cs.customerLogin();
        break;
        case 3:{
        	System.out.println("Admin login");
        	adminService.adminLogin();
        }
        break;
        
        default:System.out.println("Invalid choice");
        }
        sc.close();
        

        
        
    }
}
