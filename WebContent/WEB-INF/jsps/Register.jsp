<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.PrintWriter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Form</title>
</head>
<body>
<form action="/MazeOfDarkness2.0/Register" method="POST">
		Username:<br> <input type="text" name=username required maxlength="20">
		<% 
			if(!session.isNew() && ((Boolean)request.getAttribute("wrongUsername")))
			{
				out.print("Wrong username!");
			}
			if(!session.isNew() && ((Boolean)request.getAttribute("takenUsername")))
			{
				out.print("The current username exists!");
			}
		%>
		<br>
		E-mail:<br> <input type="text" name=email required maxlength="255">
		<% 
			if(!session.isNew() && ((Boolean)request.getAttribute("wrongEmail")))
			{
				out.print("Wrong e-mail!");
			}
			if(!session.isNew() && ((Boolean)request.getAttribute("existEmail")))
			{
				out.print("The current e-mail exists!");
			}
		%>
		<br>
		Password:<br> <input type="password" name=password required maxlength="32">
		<% 
			if(!session.isNew() && (Boolean)request.getAttribute("wrongPassword"))
			{
				out.print("Wrong password!");
			}
		%>
		<br>
		
		Repeat Password:<br> <input type="password" name=repeatPassword required maxlength="32">
		<% 
			if(!session.isNew() && (Boolean)request.getAttribute("equalsPasswords"))
			{
				out.print("The password doesn't match!");
			}
		%>
		<br>
		<br>
		<input type="submit" value="Register"><br>
	</form>
	<%  if(request.getAttribute("successfulRegistration") != null){
		if(!session.isNew() && (Boolean)request.getAttribute("successfulRegistration"))
		{
			response.setContentType("text/html"); 
			out.print("<html><head>"); 
			out.print("<script type=\"text/javascript\">alert('Successful Registration!'); location.href = '/MazeOfDarkness2.0/Login'</script>"); 
			out.print("</head><body></body></html>"); 
		}
		else if(!session.isNew() && !(Boolean)request.getAttribute("successfulRegistration"))
		{
			out.print("Unsuccessful registration! Please try again!");
		}
	}
		%>
</body>
</html>