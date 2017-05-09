<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Recipe sharing</title>
</head>

<body>
	<div>
		<strong>Recipe sharing web-site by Zn0w</strong>
		
		<center>
			<strong>
				<a href = "http://localhost:8080/recipe-sharing-site/">Home</a> &nbsp;
				<a href = "http://localhost:8080/recipe-sharing-site/ResipeListServlet">Recipes</a> &nbsp;
				<a href = "http://localhost:8080/recipe-sharing-site/">Share recipe</a> &nbsp;
				<a href = "http://localhost:8080/recipe-sharing-site/">About</a>
			</strong>
		</center>
		
		<strong>Recipe</strong>
		
		<%
			String recipeName = (String) request.getAttribute("recipeName");
			String author = (String) request.getAttribute("author");
			String[] ingredients = (String[]) request.getAttribute("ingredients");
			String description = (String) request.getAttribute("description");
		%>
		
		<div align = "center">
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
		</div>
		
		<h1><%=description%></h1>
</body>

</html>