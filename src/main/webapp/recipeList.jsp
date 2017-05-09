
<html>

<head>
	<title>Recipe sharing</title>
	<link href = "styles/stylesheet.css" type = "text/css" rel = "stylesheet">
</head>

<body>
	Recipe sharing web-site by Zn0w
		
	<div id = "header">
		<a href = "http://localhost:8080/recipe-sharing-site/">Home</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/ResipeListServlet">Recipes</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/">Share recipe</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/">About</a>
	</div>
		
	Home
	
	<%
		String[][] recipes = (String[][]) request.getAttribute("recipes");
	%>
	
	<form action="RecipeServlet" method = "post">	
		<input type = "hidden" value = "<%=recipes%>" name = "recipes">
		
		<%
			for (int i = 0; i < recipes.length; i++) {
				out.println("<input type = 'submit' value = '" + recipes[i][0] + " by " + recipes[i][3] + "' name = 'button name'> <br>");
			}
		%>
	</form>
	
</body>

</html>
