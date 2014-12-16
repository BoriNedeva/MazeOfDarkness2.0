<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MazeOfDarkness Login</title>
<style>
body{
background-image: url("img\\mazeWallpaper.jpg");
background-repeat:no-repeat;
background-size:cover;

color: white;

}
</style>
</head>
<body>
<div align="center" style = "margin-top: 50px">
	<form action="/MazeOfDarkness2.0/Login" method="POST">
		Username:<br> <input type="text" name=username required
			maxlength="20">
		<%
			if (!session.isNew()
					&& request.getAttribute("wrongUsername") != null
					&& ((Boolean) request.getAttribute("wrongUsername"))) {
				out.print("Wrong username!");
			}
		%>
		<br> Password:<br> <input type="password" name=password
			required maxlength="32">
		<%
			if (!session.isNew()
					&& request.getAttribute("wrongPassword") != null
					&& (Boolean) request.getAttribute("wrongPassword")) {
				out.print("Wrong password!");
			}
		%>
		<br> <input type="submit" value="Login"><br> <a
			href="/MazeOfDarkness2.0/Register" style="color: #A7F9FF">Not a member?</a>
	</form>
	</div>
</body>
</html>

