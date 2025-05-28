package com.bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bank.DTO.TransactionDetailsDTO;


public class TransactionDAO {                    
	private static final String url="jdbc:mysql://localhost:3306/bank_management_system_db?user=root&password=root";
	private static final String fqcn="com.mysql.cj.jdbc.Driver";
	private static final String insert_tranaction_details="insert into transaction_details(transaction_amount, transaction_date, transaction_time, transaction_balance, customer_account_number, transaction_type)"
			+ " values (?,?,?,?,?,?)" ; 
	private static final String check_statement="select * from transaction_details where customer_account_number=?";
	public int transactionDetails(TransactionDetailsDTO dto)
	{
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement(insert_tranaction_details);
			
			ps.setDouble(1, dto.getAmount());
			ps.setString(2, dto.getDate());
			ps.setString(3, dto.getTime());
			ps.setDouble(4,dto.getBalance());
			ps.setLong(5, dto.getAccountnumber());
			ps.setString(6, dto.getType());
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
	
	public List<TransactionDetailsDTO> checkStatement(long accountNo) {
		
		List<TransactionDetailsDTO> allTransactionList=new ArrayList<TransactionDetailsDTO>();
		
		try {
			Class.forName(fqcn);
			Connection conn=DriverManager.getConnection(url);
			PreparedStatement ps=conn.prepareStatement(check_statement);
			ps.setLong(1, accountNo);
			ResultSet rs=ps.executeQuery();
			if(rs.isBeforeFirst())
			{
//transaction_id, transaction_amount, transaction_date, transaction_time,
				//transaction_balance, customer_account_number, transaction_type				
				while(rs.next()) 
				{
					TransactionDetailsDTO tddto=new TransactionDetailsDTO();
					tddto.setId(rs.getInt("transaction_id"));
					tddto.setAmount(rs.getDouble("transaction_amount"));
					tddto.setDate(rs.getString("transaction_date"));
					tddto.setTime(rs.getString("transaction_time"));
					tddto.setBalance(rs.getDouble("transaction_balance"));
					tddto.setAccountnumber(rs.getLong("customer_account_number"));
					tddto.setType(rs.getString("transaction_type"));
					allTransactionList.add(tddto);
					
				}
			}
			else
			{
				return null;
			}

		} catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allTransactionList;
			
	}

}
