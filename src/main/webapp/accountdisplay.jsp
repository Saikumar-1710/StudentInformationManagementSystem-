<%@ page import="com.example.dao.UserAccountDAO"  import="java.io.IOException" import="com.example.pojo.UserAccount" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account details</title>
</head>
<body>
	<jsp:include page="./home.jsp"/>
	<%
	UserAccount account = (UserAccount)request.getAttribute("account");
	ArrayList<UserAccount> accountsList = (ArrayList)request.getAttribute("accountsList");
	%>
	
	<% if(account != null){	%>
		<h2><%= account.getUname() %> details</h2>
		<table>
			<tr>
				<td>UserId</td>
				<td>UserName</td>
				<td>Password</td>
				<td>Email</td>
				<td>Mobile</td>
			</tr>
			
			<tr>
				<td><%= account.getUserId() %></td>
				<td><%= account.getUname() %></td>
				<td><%= account.getPassword() %></td>
				<td><%= account.getEmail() %></td>
				<td><%= account.getMobile() %></td>
			</tr>
		</table>
	<%}else if(accountsList!=null && !accountsList.isEmpty()) { %>
		<h2>All User Details</h2>
		<table>
			<tr>
				<td>UserId</td>
				<td>UserName</td>
				<td>Password</td>
				<td>Email</td>
				<td>Mobile</td>
			</tr>
			<%for (UserAccount userAccount : accountsList) { %>
			<tr>
				<td><%= userAccount.getUserId() %></td>
				<td><%= userAccount.getUname() %></td>
				<td><%= userAccount.getPassword() %></td>
				<td><%= userAccount.getEmail() %></td>
				<td><%= userAccount.getMobile() %></td>
			</tr>
			<%} %>
		</table>
	<%} %>
</body>
</html>