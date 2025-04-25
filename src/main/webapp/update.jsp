<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="./UserAccountServlet" method="post"> 

<h4>Update the values</h4>
		User Id<input type="number" name="userId" value="" ><br>
		User Name<input type="text" name="uname" value=""><br>
		Password<input type="password" name="password" value=""><br>
		Email <input type="email" name="email" value=""><br>
		Mobile <input type="tel" name="mobile" value=""><br>
		<input type="submit" name="button" value="save">

</form>
</body>
</html>