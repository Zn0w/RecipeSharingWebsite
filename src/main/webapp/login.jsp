<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%> 

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Recipe sharing</title>
	<link href = "styles/stylesheet.css" type = "text/css" rel = "stylesheet">
</head>

<body>
	Recipe sharing web-site by Zn0w
		
	<div id = "header">
		<a href = "http://localhost:8080/recipe-sharing-site/">Home</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/recipesMain.jsp">Recipes</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/">Share recipe</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/">About</a>
	</div>
		
	Login
	<br><br><br><br>
	<div align = "center">
		<form action="LoginServlet" method = "post">
			Username: <input type = "text" name = "username">
			<br>
			Password: <input type = "password" name = "password">
			<br>
			<input type = "submit" value = "Submit">
			<br><br>
			If you don't have an account you can register right now.
			<br>
			<a href = "http://localhost:8080/recipe-sharing-site/register.jsp">Register</a>
		</form>
	</div>
</body>

</html>