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
		
	<%
		Cookie cookies[] = request.getCookies();
	
		String name = "Guest";
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("username")) {
					name = cookies[i].getValue();
					break;
				}
			}
		}
	%>
	
	<div id = "header">
		<a href = "http://localhost:8080/recipe-sharing-site/">Home</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/ResipeListServlet">Recipes</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/">Share recipe</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/">About</a> &nbsp;
	</div>
	
	<div id = "loginSection">
		Logged as <%=name%>
		
		<%
			if (!name.equals("Guest")) {
				out.println("<form action = 'LogoutServlet' method = 'post'><input type = 'submit' value = 'Logout' align = 'right'></form>");
			}
			else
				out.println("<form action = 'login.jsp' method = 'post'><input type = 'submit' value = 'Login' align = 'right'></form>");
		%>
	</div>
		
	Recipes
	<br><br><br><br>
	
	<h3><a href = "http://localhost:8080/recipe-sharing-site/ResipeListServlet">Find new recipes!</a></h3>
	<p>Here you can find recipes that is shared by other users of this web-site.</p>
	
	<h3><a href = "http://localhost:8080/recipe-sharing-site/MyRecipesServlet">My recipes</a></h3>
	<p>Here you can see recipes you have already shared.</p>
	
	<h3><a href = "http://localhost:8080/recipe-sharing-site/FavouritedRecipesServlet">Favourited recipes</a></h3>
	<p>Here you can see recipes that you have favorited.</p>
</body>

</html>