<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>You lose!</title>
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
<font size="1000">
You lose!
</font>
<form action="goBackToLobby" method = "GET">
<input type="submit" value = "Go to Lobby">
</form>
</div>
</body>
</html>