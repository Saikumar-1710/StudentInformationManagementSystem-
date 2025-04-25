package com.example.listner;

import com.example.dao.UserAccountDAO;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class HttpSessionListnerHandler implements HttpSessionListener {

	//private UserAccountDAo accountDao; 
    public void sessionCreated(HttpSessionEvent se)  { 
        UserAccountDAO accountDao = new UserAccountDAO();
        //opening db connection
        accountDao.openConnection();
        
        //create PrepareStatements 
        accountDao.createPrepareStatements();
        
        //adding acccountDao object to session as attribute
        se.getSession().setAttribute("accountDao", accountDao);
        System.out.println("Account object is stored in session : "+se.getSession());
    	
    }

	
    public void sessionDestroyed(HttpSessionEvent se)  { 
         UserAccountDAO accountDao = (UserAccountDAO)se.getSession().getAttribute("accountDao");
         accountDao.closePrepareStatement();
         accountDao.closeConnection();
         accountDao = null;
         System.out.println("Account object is unrefered and destroyed in session : "+se.getSession());
         
         
    }
	
}
