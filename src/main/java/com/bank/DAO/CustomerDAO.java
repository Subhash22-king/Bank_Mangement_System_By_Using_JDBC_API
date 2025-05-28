package com.bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.DTO.CustomerDetailsDTO;

public class CustomerDAO {
	
	private static final String url="jdbc:mysql://localhost:3306/bank_management_system_db?user=root&password=root";
	private static final String fqcn="com.mysql.cj.jdbc.Driver";
	private static final String insert_details="insert into customer_details"
			+ "(name, emailid, address, gender, pancard, mobilenumber, aadhaarnumber, dateofbirth, amount) "
			+ "values(?,?,?,?,?,?,?,?,?)";
	
	private static final String login_check="select * from customer_details where emailid=? and pin=?";	
	private static final String all_customer_details="select * from customer_details";
	private static final String all_registration_pending_customer_details="select * from customer_details where accountnumber is null and pin is null";
	
	private static final String all_account_closing_customer_details="select * from customer_details where remark=?";
	
	private static final String new_accountno_and_pin="update customer_details set accountnumber=?,pin=? where emailid=? ";
	
	private static final String account_closing_request="update customer_details set remark=? where emailid=?";
	
	private static final String account_closing_based_on_name="delete from customer_details where name=?";
	
	
	public boolean existsMobileNumber(long mobileNumber)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement("select * from customer_details where mobilenumber=?");
			ps.setLong(1, mobileNumber);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				return false;
			}
					
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return true;
		
	}
	
	public boolean existsEmailid(String emailid)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement("select * from customer_details where emailid=?");
			ps.setString(1, emailid);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				return false;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}
	
	public boolean existsAadhaar(long aadhaarNo)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement("select * from customer_details where aadhaarnumber=?");
			ps.setLong(1, aadhaarNo);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				return false;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean existsPanNumber(String pancardNo)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement("select * from customer_details where pancard=?");
			ps.setString(1, pancardNo);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				return false;
			}
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return true;
	}
	
	public int customerInsertingOperation(CustomerDetailsDTO cd)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement(insert_details);
//(name, emailid, address, gender, pancard, mobilenumber, aadhaarnumber, dateofbirth, amount
			ps.setString(1, cd.getName());
			ps.setString(2, cd.getEmailid());
			ps.setString(3, cd.getAddress());
			ps.setString(4, cd.getGender());
			ps.setString(5, cd.getPancard());
			ps.setLong(6, cd.getMobilenumber());
			ps.setLong(7, cd.getAadhaarnumber());
			ps.setDate(8, cd.getDateofbirth());
			ps.setDouble(9, cd.getAmount());
			
			int rowAffected=ps.executeUpdate();
			return rowAffected;
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public CustomerDetailsDTO checkCustomerEmailAndPin(String email,int pin)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement(login_check);
			ps.setString(1, email);
			ps.setInt(2, pin);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				
				CustomerDetailsDTO cdto= new CustomerDetailsDTO();
				cdto.setName(rs.getString("name"));
				cdto.setGender(rs.getString("gender"));
				cdto.setAccountNo(rs.getLong("accountnumber"));
				return cdto;
				
			}	
			return null;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public CustomerDetailsDTO getBalanceAmount(String mail)
	{
		String select="select * from customer_details where emailid=?";
		
		try {
			Class.forName(fqcn);
			
			Connection conn=DriverManager.getConnection(url);
			CustomerDetailsDTO cdto=new CustomerDetailsDTO();
			PreparedStatement psSel=conn.prepareStatement(select);
			psSel.setString(1, mail);
			ResultSet rs=psSel.executeQuery();
			if(rs.next())
			{
				cdto.setAmount(rs.getDouble("amount"));
				
				cdto.setName(rs.getString("name"));
				
				return cdto;

			}


		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
    public int insertNewAmount(double newAmount,String email)
    {
    	String update="update customer_details set amount=? where emailid=?";
    	
    	try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement(update);
			
			ps.setDouble(1,newAmount);
			ps.setString(2, email);
			
			int rowAffected=ps.executeUpdate();
			return rowAffected;
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
    }
	
	
	
	
	public CustomerDetailsDTO checkBalance(String mail)
	{
		String select="select * from customer_details where emailid=?";
		try {
			Class.forName(fqcn);
			
			Connection conn=DriverManager.getConnection(url);
			
			PreparedStatement psSel=conn.prepareStatement(select);
			psSel.setString(1, mail);
			ResultSet rs=psSel.executeQuery();
			
			CustomerDetailsDTO cdto=new CustomerDetailsDTO();
			
			if(rs.next()) {
				
				cdto.setAmount(rs.getDouble("amount"));
				cdto.setName(rs.getString("name"));
				return cdto;
			}
		            
		
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	public int changePin(String email,int newPin)
	{
		try {
			
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement("update customer_details set pin=? where emailid=?");
			ps.setInt(1, newPin);
			ps.setString(2, email);
			int rowAffected=ps.executeUpdate();
			return rowAffected;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int accountClosingRequest(String email)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement(account_closing_request);
			ps.setString(1, "close");
			ps.setString(2, email);
			int rowAffected=ps.executeUpdate();
			return rowAffected;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int updateName(String name ,String email)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			
			PreparedStatement ps1 =conn.prepareStatement("select name from customer_details where emailid=?");
			ps1.setString(1, email);
			ResultSet rs=ps1.executeQuery();
			
			if(rs.next())
			{
				if(rs.getString("name").equals(name))
				{
					return 0;
				}
			}
			
			PreparedStatement ps2=conn.prepareStatement("update  customer_details set name=? where emailid=?");
			
			ps2.setString(1, name);
			ps2.setString(2, email);
			
			int rowAffected=ps2.executeUpdate();
			return rowAffected;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateEmail(String newEamil ,String email)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			
			PreparedStatement ps1 =conn.prepareStatement("select emailid from customer_details where emailid=?");
			ps1.setString(1, email);
			ResultSet rs=ps1.executeQuery();
			
			if(rs.next())
			{
				if(rs.getString("emailid").equals(newEamil))
				{
					return 0;
				}
			}
			
			PreparedStatement ps2=conn.prepareStatement("update  customer_details set emailid=? where emailid=?");
			
			ps2.setString(1, newEamil);
			ps2.setString(2, email);
			
			int rowAffected=ps2.executeUpdate();
			return rowAffected;
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateAddress(String newAddress ,String email)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			
			PreparedStatement ps=conn.prepareStatement("update  customer_details set address=? where emailid=?");
			
			ps.setString(1, newAddress);
			ps.setString(2, email);
			
			int rowAffected=ps.executeUpdate();
			return rowAffected;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateMobileNumber(long newMobileNumber,String email)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			
			PreparedStatement ps=conn.prepareStatement("update  customer_details set mobilenumber=? where emailid=?");
			
			ps.setLong(1, newMobileNumber);
			ps.setString(2, email);
			
			int rowAffected=ps.executeUpdate();
			return rowAffected;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateDateOfBirth(String newDOB,String email)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			
			PreparedStatement ps=conn.prepareStatement("update  customer_details set dateofbirth=? where emailid=?");
			
			ps.setString(1, newDOB);
			ps.setString(2, email);
			
			int rowAffected=ps.executeUpdate();
			return rowAffected;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<CustomerDetailsDTO> getAllCustomerDetails()
	{
		
		List<CustomerDetailsDTO> allCustomerList=new ArrayList<CustomerDetailsDTO>();
		
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement(all_customer_details);
			ResultSet rs=ps.executeQuery();
			
			if(rs.isBeforeFirst())
			{//id, name, emailid, address, gender, pancard, mobilenumber, aadhaarnumber, dateofbirth, accountnumber, pin, amount
				while(rs.next())
				{   
					CustomerDetailsDTO customerDetailsDTO=new CustomerDetailsDTO();
					customerDetailsDTO.setName(rs.getString("name"));
					customerDetailsDTO.setEmailid(rs.getString("emailid"));
					customerDetailsDTO.setAddress(rs.getString("address"));
					customerDetailsDTO.setGender(rs.getString("gender"));

					customerDetailsDTO.setPancard(rs.getString("pancard"));

					customerDetailsDTO.setMobilenumber(rs.getLong("mobilenumber"));

					customerDetailsDTO.setAadhaarnumber(rs.getLong("aadhaarnumber"));
					customerDetailsDTO.setDateofbirth(rs.getDate("dateofbirth"));
					customerDetailsDTO.setAccountNo(rs.getLong("accountnumber"));
					customerDetailsDTO.setAmount(rs.getDouble("amount"));
					allCustomerList.add(customerDetailsDTO);
				}
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allCustomerList;
		
		
	}
	
	
	public List<CustomerDetailsDTO> getAllPendingCustomerDetails()
	{
		
		List<CustomerDetailsDTO> allCustomerPendingList=new ArrayList<CustomerDetailsDTO>();
		
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement(all_registration_pending_customer_details);
			ResultSet rs=ps.executeQuery();
			
			if(rs.isBeforeFirst())
			{//id, name, emailid, address, gender, pancard, mobilenumber, aadhaarnumber, dateofbirth, accountnumber, pin, amount
				while(rs.next())
				{   
					CustomerDetailsDTO customerDetailsDTO=new CustomerDetailsDTO();
					customerDetailsDTO.setName(rs.getString("name"));
					customerDetailsDTO.setEmailid(rs.getString("emailid"));
					customerDetailsDTO.setAddress(rs.getString("address"));
					customerDetailsDTO.setGender(rs.getString("gender"));

					customerDetailsDTO.setPancard(rs.getString("pancard"));

					customerDetailsDTO.setMobilenumber(rs.getLong("mobilenumber"));

					customerDetailsDTO.setAadhaarnumber(rs.getLong("aadhaarnumber"));
					customerDetailsDTO.setDateofbirth(rs.getDate("dateofbirth"));
					
					allCustomerPendingList.add(customerDetailsDTO);
				}
				
			}
			else
			{
				
				return null;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allCustomerPendingList;
		
		
	}
	
	
	
	public boolean newAccountNumberAndPinBasesOnEmail(long accountNo,int pin,String email)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement(new_accountno_and_pin);
			ps.setLong(1, accountNo);
			ps.setInt(2, pin);
			ps.setString(3, email);
			int rowAffected=ps.executeUpdate();
			
			if(rowAffected>0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	public List<CustomerDetailsDTO> getClosingCustomerDetails()
	{
		
		List<CustomerDetailsDTO> allCustomerClosingList=new ArrayList<CustomerDetailsDTO>();
		
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement(all_account_closing_customer_details);
			ps.setString(1, "close");
			ResultSet rs=ps.executeQuery();
			
			if(rs.isBeforeFirst())
			{//id, name, emailid, address, gender, pancard, mobilenumber, aadhaarnumber, dateofbirth, accountnumber, pin, amount
				while(rs.next())
				{   
					CustomerDetailsDTO customerDetailsDTO=new CustomerDetailsDTO();
					customerDetailsDTO.setName(rs.getString("name"));
					customerDetailsDTO.setEmailid(rs.getString("emailid"));
					customerDetailsDTO.setAddress(rs.getString("address"));
					customerDetailsDTO.setGender(rs.getString("gender"));

					customerDetailsDTO.setPancard(rs.getString("pancard"));

					customerDetailsDTO.setMobilenumber(rs.getLong("mobilenumber"));

					customerDetailsDTO.setAadhaarnumber(rs.getLong("aadhaarnumber"));
					customerDetailsDTO.setDateofbirth(rs.getDate("dateofbirth"));
					
					allCustomerClosingList.add(customerDetailsDTO);
				}
				
			}
			else
			{
				
				return null;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allCustomerClosingList;
		
		
	}
	public boolean closingAccountBasedOnSelectedObject(CustomerDetailsDTO closeObject)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement(account_closing_based_on_name);
			ps.setString(1, closeObject.getName());
			int rowAffected=ps.executeUpdate();
			if(rowAffected>0)
			{
				return true;
			}
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
