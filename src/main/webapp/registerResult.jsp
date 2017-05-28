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
		
	<div id = "header">
		<a href = "http://localhost:8080/recipe-sharing-site/">Home</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/recipesMain.jsp">Recipes</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/">Share recipe</a> &nbsp;
		<a href = "http://localhost:8080/recipe-sharing-site/">About</a>
	</div>
		
	Register result
	<br><br><br><br>
	
	<div align = "center">
		<%
			String message = (String) request.getAttribute("message");
		%>
		
		<h1><%=message%></h1>
		
		<%
			if (message.equals("User has been succesfully created!")) {
				out.println("<h1><a href = 'http://localhost:8080/recipe-sharing-site/login.jsp'>Log in</a></h1>");
			}
			else {
				out.println("<h1><a href = http://localhost:8080/recipe-sharing-site/register.jsp>Try again</a></h1>");
			}
		%>
	</div>
</body>

</html>