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
		
	Home
	<br><br><br><br>
	<div align = "center">
		<h1>Web-site where you can find other people's recipes or share your own recipes.</h1>
		
		<h3>Welcome, <c:out value="${login}"></c:out>!</h3>
		
		<c:if test="${login == 'Guest'}">
			<h1>Please, </h1>
			<form action="login.jsp">
				<input type = "submit" value = "Login">
				<br>
				<h1>to be able to share, comment and manage your favorites recipes.</h1>
			</form>
		</c:if>
	</div>
</body>

</html>
