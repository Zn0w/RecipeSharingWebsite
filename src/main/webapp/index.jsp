<html>

<head>
	<title>Recipe sharing</title>
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
		<a href = "http://localhost:8080/recipe-sharing-site/recipesMain.jsp">Recipes</a> &nbsp;
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
		
	Home
	<br><br><br><br>
	<div align = "center">
		<h1>Web-site where you can find other people's recipes or share your own recipes.</h1>
		
		<%
			if (!name.equals("Guest"))
				out.println("<h3>Welome, " + name + "</h3>");
			else
				out.println("<h3>Welome, " + name + "</h3>" +
						"<h1>Please, </h1>" +
						"<form action='login.jsp'>" +
						"<input type = 'submit' value = 'Login'>" +
						"<br>" +
						"<h1>to comment other's recipes, share recipes or add recipes in favorites.</h1>" +
					"</form> ");
		%>
	</div>
</body>

</html>
