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

	<c:choose >
		<c:when test = "${cookie.containsKey('username')}">
			<c:set var = "login" value = "${cookie['username'].value}"></c:set>
		</c:when>
		
		<c:otherwise>
			<c:set var = "login" value = "Guest"></c:set>
		</c:otherwise>
	</c:choose>
	
	<div id = "header">
		<a href = "http://localhost:8080/recipe-sharing-site/">Home</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/recipesMain.jsp">Recipes</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/shareRecipe.jsp">Share recipe</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/about.jsp">About</a> &nbsp;
	</div>
	
	<div id = "loginSection">
		Logged as <c:out value = "${login}"></c:out>
		
		<c:choose>
			<c:when test = "${login == 'Guest'}">
				<form action = 'login.jsp' method = 'post'>
					<input type = 'submit' value = 'Login' align = 'right'>
				</form>
			</c:when>
			
			<c:otherwise>
				<form action = 'LogoutServlet' method = 'post'>
					<input type = 'submit' value = 'Logout' align = 'right'>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
		
	Share recipe
	<br><br><br><br>
	<div align = "center">
		<form action="ShareRecipeServlet" method  = "post" id = "shareRecipeForm">
			<h4>Recipe name</h4>
			<input type = "text" name = "name"> <br><br>
			<h4>Ingredients (for normal display of ingredients type as in example: Sugar,salt,pepper.)</h4>
			<textarea rows="4" cols="150" name = "ingredients" ></textarea> <br><br>
			<h4>Description</h4>
			<textarea rows="15" cols="150" name = "description"></textarea> <br><br><br><br>
			<input type = "submit" value = "Submit">
			
			<input type = "hidden" name = "user" value = "${login}">
		</form>
	</div>
</body>

</html>