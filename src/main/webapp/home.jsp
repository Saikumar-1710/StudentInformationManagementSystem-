<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h3>User Actions</h3>
	<form action="./UserAccountServlet" method="post">
		<input type="submit" name="button" value="Get User Details" style="background:cyan;">
		<input type="submit" name="button" value="Get All Users" style="background:gray;">
		<input type="submit" name="button" value="Update" style="background:yellow;">
		<input type="submit" name="button" value="Delete" style="background:red;">
	
	
	</form>

</body>
</html>