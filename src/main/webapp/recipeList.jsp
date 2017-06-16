<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%> 

<html>

<head>
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
		
	Recipe list
	<br><br><br><br>
	
	<c:set var = "recipes" value = "${requestScope.recipes}"/>
	
	<form action="RecipeServlet" method = "post">	
		<input type = "hidden" value = "${recipes}" name = "recipes">
		
		<c:forEach var = "recipe" items = "${recipes}">
			<input type = "submit" value = "${recipe[0]} by ${recipe[3]}" name  = "button name"> <br>
		</c:forEach>
	</form>
	
</body>

</html>
