<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%> 

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Recipe Sharing</title>
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
		
	Register result
	<br><br><br><br>
	
	<div align = "center">
		<c:set var = "message" value = "${requestScope.message}"/>
		
		<h1><c:out value="${message}"/></h1>
		
		<c:choose>
			<c:when test = "${message == 'User has been succesfully created!'}">
				<h1><a href = "http://localhost:8080/recipe-sharing-site/login.jsp">Log in</a></h1>
			</c:when>
			
			<c:otherwise>
				<h1><a href = "http://localhost:8080/recipe-sharing-site/register.jsp">Try again</a></h1>
			</c:otherwise>
		</c:choose>
	</div>
</body>

</html>