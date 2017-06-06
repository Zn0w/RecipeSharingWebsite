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
		
	<%
		Cookie cookies[] = request.getCookies();
	
		String login = "Guest";
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("username")) {
					login = cookies[i].getValue();
					break;
				}
			}
		}
	%>
	
	<div id = "header">
		<a href = "http://localhost:8080/recipe-sharing-site/">Home</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/recipesMain.jsp">Recipes</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/shareRecipe.jsp">Share recipe</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/about.jsp">About</a> &nbsp;
	</div>
	
	<div id = "loginSection">
		Logged as <%=login%>
		
		<%
			if (!login.equals("Guest")) {
				out.println("<form action = 'LogoutServlet' method = 'post'><input type = 'submit' value = 'Logout' align = 'right'></form>");
			}
			else
				out.println("<form action = 'login.jsp' method = 'post'><input type = 'submit' value = 'Login' align = 'right'></form>");
		%>
	</div>
		
	Recipe
	<br><br><br><br>
		
		<%
			String relationshipStatus = (String) request.getAttribute("relationshipStatus");
			
			String recipeName = (String) request.getAttribute("recipeName");
			String author = (String) request.getAttribute("author");
			String[] ingredients = (String[]) request.getAttribute("ingredients");
			String description = (String) request.getAttribute("description");
		%>
		
		<form action="RecipeManagerServlet">
			<input type = "hidden" name = "recipeName" value = "<%=recipeName%>">
			<input type = "hidden" name = "author" value = "<%=author%>">
			<input type = "hidden" name = "userLogin" value = "<%=login%>">
			
			<%
				String command = null;
				String buttonValue = null;
				
				if (relationshipStatus.equals("favourited")) {
					command = "remove";
					buttonValue = "Remove from favorites";
				}
				else if (relationshipStatus.equals("not favourited")) {
					command = "add";
					buttonValue = "Add to favorites";
				}
				else if (relationshipStatus.equals("owner")) {
					command = "destroy";
					buttonValue = "Delete recipe";
				}
			%>
			
			<input type = "hidden" name = "command" value = "<%=command%>">
			<input type = "submit" value = "<%=buttonValue%>">
		</form>
		
		<h1><%=recipeName%></h1>
		<h2>By <%=author%>'s recipe</h2>
		<h3>Ingredients</h3>
			
		<ul>
		<%
			for (int i = 0; i < ingredients.length; i++) {
				out.println("<li>" + ingredients[i] + "</li>");
			}
		%>
		</ul>
		
		<h3>Description</h3>
		
		<p><%=description%></p>
</body>

</html>