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
		
	Recipe
	<br><br><br><br>
		
	<c:set var = "relationshipStatus" value = "${requestScope.relationshipStatus}"/>
	<c:set var = "recipeName" value = "${requestScope.recipeName}"/>
	<c:set var = "author" value = "${requestScope.author}"/>
	<c:set var = "ingredients" value = "${requestScope.ingredients}"/>
	<c:set var = "description" value = "${requestScope.description}"/>	
		
	<form action="RecipeManagerServlet">
		<input type = "hidden" name = "recipeName" value = "${recipeName}">
		<input type = "hidden" name = "author" value = "${author}">
		<input type = "hidden" name = "userLogin" value = "${login}">
		
		<c:choose>
			<c:when test = "${relationshipStatus == 'favourited'}">
				<c:set var = "command" value = "remove"/>
				<c:set var = "buttonValue" value = "Remove from favorites"/>
			</c:when>
			
			<c:when test = "${relationshipStatus == 'not favourited'}">
				<c:set var = "command" value = "add"/>
				<c:set var = "buttonValue" value = "Add to favorites"/>
			</c:when>
			
			<c:when test = "${relationshipStatus == 'owner'}">
				<c:set var = "command" value = "destroy"/>
				<c:set var = "buttonValue" value = "Delete recipe"/>
			</c:when>
		</c:choose>
			
		<input type = "hidden" name = "command" value = "${command}">
		<input type = "submit" value = "${buttonValue}">
	</form>
		
	<form action="UserRecipesServlet">
		<input type = hidden name = "login" value = "${author}">
		<input type = "submit" value = "See author's profile">
	</form>
		
	<h1><c:out value="${recipeName}"/></h1>
	<h2>By <c:out value="${author}"/>'s recipe</h2>
	<h3>Ingredients</h3>
			
	<ul>
		<c:forEach var = "ingredient" items = "${ingredients}">
			<li><c:out value="${ingredient}"/></li>
		</c:forEach>
	</ul>
		
	<h3>Description</h3>
		
	<p><c:out value="${description}"/></p>
	
	<br><br>
	<h3>Comments</h3>
	
	<div align = "center">
		<form action="SendCommentServlet" method = "post">
			<textarea rows="7" cols="80" name = "commentContent">You can leave a comment here</textarea>
			<input type = "hidden" name = "commentAuthor" value = "${login}">
			<input type = "hidden" name = "recipeName" value = "${recipeName}">
			<input type = "hidden" name = "recipeAuthor" value = "${author}">
			<input type = "submit" value = "Send">
		</form>
		
		<br><br><br>
		
		<c:set var = "commentsInfo" value = "${requestScope.commentsInfo}"/>
		
		<c:if test="${not empty commentsInfo}">
			<c:forEach var = "comment" items = "${commentsInfo}">
				<c:out value="${comment[0]}"/>
				<br>
				<textarea rows="5" cols="85" readonly = "readonly"><c:out value="${comment[1]}"/></textarea>
				<br><br>
			</c:forEach>
		</c:if>
	</div>
</body>

</html>