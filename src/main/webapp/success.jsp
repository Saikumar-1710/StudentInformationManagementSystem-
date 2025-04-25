<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% String message  = (String)request.getAttribute("success"); %>
	<% if(message.toLowerCase().contains("created")){ %>
		<h3 style ="color:green" align="left">
		<%= message %><br>
		</h3>
		<jsp:include page="./login.jsp"/>
	<% }else if(message.toLowerCase().contains("update")){ %>
		<jsp:include page="./home.jsp"/>
		<h3 style = "color:blue" align="left">
		<%= message %><br>
		</h3>
	<%} else if(message.toLowerCase().contains("delete")){ %>
		<jsp:include page="./home.jsp"></jsp:include>
		<h3 style="color:red" align="left"><br>
		<%= message %>/<br>
		</h3>
	<%} %>
</body>
</html>