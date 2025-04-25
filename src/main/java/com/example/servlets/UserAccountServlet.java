package com.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.example.dao.UserAccountDAO;
import com.example.pojo.UserAccount;


@WebServlet("/UserAccountServlet")
public class UserAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			processRequest(request, response);
		
	}


	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//create HttpSession obj its comes from listener
		HttpSession session = request.getSession();
		UserAccountDAO accountDao =  (UserAccountDAO)session.getAttribute("accountDao");
		
		
		String button = request.getParameter("button");
		switch(button.toLowerCase()) { //switch(button.toLowerCase()){  or switch(button)
		case "register" :{
			//create new Instance of UserAccount
			UserAccount account = new UserAccount();
			
			//initializing new account  with current request parameter
			//set values to UserAccount clas from req parameter<html form>
			
			account.setUserId(Long.parseLong(request.getParameter("userId")));
			account.setUname(request.getParameter("uname"));
			account.setPassword(request.getParameter("password"));
			account.setEmail(request.getParameter("email"));
			account.setMobile(Long.parseLong(request.getParameter("mobile")));
			
			//inserting or creating Values into account with this request details
			boolean created = accountDao.create(account);
			if(created) {
				//success page return 
				//converting servlet to jsp
				request.setAttribute("success", "Account created succcesfully"); // success undey mundhu
				//go to login.jsp
				request.getRequestDispatcher("./success.jsp").forward(request, response);
			}else {
				request.setAttribute("error", "account creation failed");
				request.getRequestDispatcher("./error.jsp").forward(request, response);
			}
			
			break;
		}//case register close
		
		case "login" :{
			//call DAO cls MEthod 
			Long userId = accountDao.login(request.getParameter("uname"),request.getParameter("password"));
			if(userId != -1 ) {
				session.setAttribute("userId", userId);
				request.getRequestDispatcher("./home.jsp").forward(request, response);
				
			}else {
				request.setAttribute("failed","Invalid UserName or Password");
				//request.setAttribute("uname", uname);  //asking to create local variable
				request.getRequestDispatcher("./login.jsp").forward(request, response);
			}
			break;
		}//case login close
		
		case "get user details" :{
			long userId = (long)session.getAttribute("userId");
			UserAccount account = accountDao.getAccount(userId);
			System.out.println(account);
			if(account != null) {
				request.setAttribute("account", account);
				request.getRequestDispatcher("./accountdisplay.jsp").forward(request, response);
			}else {
				request.setAttribute("error","Account is not found with the given userid");
				request.getRequestDispatcher("./error.jsp").forward(request, response);
			}
			break;
		}//case get user details close
		
		case "get all users" :{
			ArrayList<UserAccount> accountsList = accountDao.getAccounts();
			if(accountsList != null && !accountsList.isEmpty()) {
				//display page
				request.setAttribute("accountsList", accountsList);
				request.getRequestDispatcher("./accountdisplay.jsp").forward(request, response);
			}else {
				//error page
				request.setAttribute("error", "No Users found");
				request.getRequestDispatcher("./error.jsp").forward(request, response);
			}
			break;
		}//case get all users close
		
		case "update" :{
			UserAccount account = accountDao.update((long)session.getAttribute("userId"));
			if( account != null) {
				request.setAttribute("update", account);
				request.getRequestDispatcher("./update.jsp").forward(request, response);
			}else {
				request.setAttribute("error", "User not found , login again");
				request.getRequestDispatcher("./error.jsp").forward(request, response);//update.jsp
			}
			break;
		}//case update close
		
		case "save" :{ //save
			//updating user details
			UserAccount account = new UserAccount();
			
			//initializing new account with current rquest parameter
			account.setUserId((long)session.getAttribute("userId"));
			account.setPassword(request.getParameter("password"));
			account.setEmail(request.getParameter("email"));
			account.setMobile(Long.parseLong(request.getParameter("mobile")));
			
			//inserting or creating an account with this request details
			boolean updated = accountDao.save(account);
			
			if(updated) {
				//sucess page
				request.setAttribute("success", "Account is updated sucessfully");
				request.getRequestDispatcher("./success.jsp").forward(request, response);
			}else {
				//error page
				request.setAttribute("error","Account is not updated");
				request.getRequestDispatcher("./error.jsp").forward(request, response);
			}
			break;	
		}//case save close
		
		case "delete" :{
//			UserAccount account = accountDao.delete((long)session.getAttribute("userId"));
				
			boolean  account = accountDao.delete((long)session.getAttribute("userId"));
		
			if(account ) {
				//sucess page
				request.setAttribute("success", "Account is deleted sucessfully");
				request.getRequestDispatcher("./success.jsp").forward(request, response);
			}else {
				//error page
				request.setAttribute("error","Account is not deleted");
				request.getRequestDispatcher("./error.jsp").forward(request, response);
			}
		break;
		}//case delete
		
		}//switch close
	}

}
