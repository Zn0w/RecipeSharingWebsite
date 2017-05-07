

<html>

<head>
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
		
		<strong>Recipes</strong>
	</div>
	
	<%
		String[] recipes = (String[]) request.getAttribute("recipes");
		for (int i = 0; i < 10; i++) {
			out.println(recipes[i]);
		}
	%>
</body>

</html>
