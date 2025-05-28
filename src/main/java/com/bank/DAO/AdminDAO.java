package com.bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
	
	private static final String url="jdbc:mysql://localhost:3306/bank_management_system_db?user=root&password=root";
	private static final String fqcn="com.mysql.cj.jdbc.Driver";
	private static final String adminLogin="select * from admin_details where admin_mailid=? and admin_password=?";
	
	
	public boolean getAdminDetailsUsingEMailAndPassword(String email,String password)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement(adminLogin);
			ps.setString(1, email);

			ps.setString(2, password);
			
			 ResultSet rs=ps.executeQuery();
			 
			 if(rs.next())
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
