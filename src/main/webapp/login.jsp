<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<%
	String uname = (String)request.getAttribute("uname");
	String errorMessage = (String)request.getAttribute("failed");
	
	if(uname == null){
		uname = "";
	}
	if(errorMessage == null){
		errorMessage = "&nbsp"; //&nbsp is for line gap space
	}
%>
<form action="./UserAccountServlet" METHOD="post">
	<table>
		<tr>
			<td>UserName</td>
			<td><input type="text" name = "uname" value="<%=uname%>"/></td>
		</tr>
		
		<tr>
			<td>password></td>
			<td><input type="password" name="password" value=""/></td>
		</tr>
		<tr>
			<td colspan="2" style="color:red"><%=errorMessage %></td>
		</tr>
		
		<tr>
			<td><input type="submit" name="button" value="Login"/></td>
		</tr>
	</table>
	<br>
	Don't you have an account? <a href="register.html">Create an Account</a>
</form>

</body>
</html>