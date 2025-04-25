package com.example.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.example.pojo.UserAccount;

public class UserAccountDAO {
	private Connection connection;
	
	private PreparedStatement insertPstmt;
	private PreparedStatement selectPstmt;
	private PreparedStatement updatePstmt;
	private PreparedStatement deletePstmt;
	private PreparedStatement loginPstmt;
	
	//create open connection return type boolean true/false
	public boolean openConnection() {
		
		/*
			Properties props = new Properties();
			String filepath = "driverinfo.properties";
			try(FileInputStream input = new FileInputStream(filepath)) {
				props.load(input);
				
				String dbURL = props.getProperty("DB_URL"); //jdbc:oracle:thin:@localhost:1521:XE
				String dbUSN = props.getProperty("DB_USN");  //saistudent
				String dbPWD = props.getProperty("DB_PWD");  //saikumar
				*/
		try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "saistudent", "saikumar");
				//System.out.println(dbUSN);
				//System.out.println(dbPWD);
				
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		return false;
	}
//----------------------------------------------------------------------------------------------------
	
	public void closeConnection(){
		try {
			insertPstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try { loginPstmt.close();} 
		catch(SQLException e) { e.printStackTrace(); }
		
		try { selectPstmt.close();} 
		catch(SQLException e) { e.printStackTrace(); }

		try { updatePstmt.close();} 
		catch(SQLException e) { e.printStackTrace(); }

		try { deletePstmt.close();} 
		catch(SQLException e) { e.printStackTrace(); }
		
		
	}
//-----------------------------------------------------------------------------------------------------------------
	public boolean createPrepareStatements() {
		try {
			StringBuilder insertQueryBuilder = new StringBuilder();
			insertQueryBuilder.append("INSERT INTO USER_ACCOUNT(USER_ID, UNAME, PASSWORD, EMAIL, MOBILE) ");
			insertQueryBuilder.append("VALUES (?, ?, ?, ?, ?)");
			insertPstmt = connection.prepareStatement(insertQueryBuilder.toString());
			
			StringBuilder loginQueryBuilder = new StringBuilder();
			loginQueryBuilder.append("SELECT USER_ID FROM user_account ");			
			loginQueryBuilder.append("WHERE UNAME=? ");			
			loginQueryBuilder.append("AND PASSWORD=? ");			
			loginPstmt = connection.prepareStatement(loginQueryBuilder.toString());
			
			StringBuilder selectQueryBuilder = new StringBuilder();
			selectQueryBuilder.append("SELECT * FROM USER_ACCOUNT ");
			selectQueryBuilder.append("WHERE USER_ID = ?");
			selectPstmt = connection.prepareStatement(selectQueryBuilder.toString());
			
			StringBuilder updateQueryBuilder = new StringBuilder();
			updateQueryBuilder.append("UPDATE USER_ACCOUNT ");
			updateQueryBuilder.append("SET password=?, email=?, mobile=? ");
			updateQueryBuilder.append("WHERE USER_ID = ?");
			updatePstmt = connection.prepareStatement(updateQueryBuilder.toString());
			
			StringBuilder deleteQueryBuilder = new StringBuilder();
			deleteQueryBuilder.append("DELETE FROM USER_ACCOUNT ");
			deleteQueryBuilder.append("WHERE USER_ID = ?");
			deletePstmt = connection.prepareStatement(deleteQueryBuilder.toString());
			
		return true;
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//-------------------------------------------------------------------------------------------
	
	public void closePrepareStatement() {
		try {
			insertPstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}

		try { loginPstmt.close(); } 
		catch(SQLException e) { e.printStackTrace(); }
		
		try { selectPstmt.close();} 
		catch(SQLException e) { e.printStackTrace(); }

		try { updatePstmt.close();} 
		catch(SQLException e) { e.printStackTrace(); }

		try { deletePstmt.close();} 
		catch(SQLException e) { e.printStackTrace(); }
		
	}
//-------------------------------------------------------------------------------------------------
	
	//create the POJO Object  for set values in class meaning taking 
	public boolean create(UserAccount account) {
		try {
			insertPstmt.setLong(1, account.getUserId());
			insertPstmt.setString(2, account.getUname());
			insertPstmt.setString(3, account.getPassword());
			insertPstmt.setString(4, account.getEmail());
			insertPstmt.setLong(5, account.getMobile());
			
			insertPstmt.executeUpdate();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//-------------------------------------------------------------------------------
	
	public long login(String uname, String password) {
		try {
			loginPstmt.setString(1, uname);
			loginPstmt.setString(2, password);
			
			ResultSet rs = loginPstmt.executeQuery();
			if(rs.next())
				return rs.getLong(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
//----------------------------------------------------------------------------------------
	
	public UserAccount getAccount(long userId) {
		UserAccount account = new UserAccount();
		ResultSet rs = null;
		
		try {
		selectPstmt.setLong(1, userId);	
		rs = selectPstmt.executeQuery();
		
		if(rs.next()) {
			account.setUserId(userId);
			account.setUname(rs.getString(2));
			account.setPassword(rs.getString(3));
			account.setEmail(rs.getString(4));
			account.setMobile(rs.getLong(5));
			
			return account;
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();				//closing the connection
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	//------------------------------------------------------------------------------------------
	public ArrayList<UserAccount> getAccounts(){
		
		ArrayList<UserAccount> accountsList = new ArrayList<>();
		try(	// Java 7 try with resource feature no need to close the connection
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM user_account ORDER BY user_Id");
			){
			while(rs.next()) {
				UserAccount account = new UserAccount();
				
				account.setUserId(rs.getLong(1));
				account.setUname(rs.getString(2));
				account.setPassword(rs.getString(3));
				account.setEmail(rs.getString(4));
				account.setMobile(rs.getLong(5));
				
				accountsList.add(account);
			}//while end
			}catch(SQLException e) {
				e.printStackTrace();
			}
		
		return null; 
	}
	//-------------------------------------------------------------------------------------------------
	
	public UserAccount update(long userId) {
		return getAccount(userId);
	}
	
	//------------------------------------------------------------------------------------------------------
	
	
	public boolean save(UserAccount account) {
		try {
			updatePstmt.setString(1, account.getPassword());
			updatePstmt.setString(2, account.getEmail());
			updatePstmt.setLong(3, account.getMobile());
			updatePstmt.setLong(4, account.getUserId());
			
			int updatedCount = updatePstmt.executeUpdate();
			if(updatedCount ==0)
				return false;
			else
				return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
//------------------------------------------------------------------------------------------------------------
	

	public boolean delete(Long userId) {
		try {
			//selectPstmt.setLong(1, userId);	
			//rs = selectPstmt.executeQuery();
		int deleteCount = deletePstmt.executeUpdate();
		if(deleteCount == 0)
			return false;
		else
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
//----------------------------------------------


}