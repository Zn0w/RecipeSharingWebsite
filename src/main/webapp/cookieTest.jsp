<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recipe sharing</title>

</head>

<body>

	<%
		String name = "Guest";
		
		Cookie cookies[] = request.getCookies();
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("username")) {
					name = cookies[i].getValue();
					break;
				}
			}
		}
	%>
	
	<h1>Welcome, <%=name%></h1>

</body>

</html>