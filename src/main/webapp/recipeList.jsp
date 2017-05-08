<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
		String[][] recipes = (String[][]) request.getAttribute("recipes");
	%>
	
	<form action="RecipeServlet">
		<%
			for (int i = 0; i < recipes.length; i++) {
				out.println("<input type = 'submit' value = '" + recipes[i][0] + " by " + recipes[i][3] + "' name = 'button name'>");
			}
		%>
	</form>
	
</body>

</html>
