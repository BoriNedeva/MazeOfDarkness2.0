<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MazeOfDarkness Login</title>
</head>
<body>
<%
	/*if(session.isNew()){
		session.setAttribute("wrongUsername", false);
	    session.setAttribute("wrongPassword", false);
	    //System.out.println("new");
	}
	else{
		//System.out.println("not new");
		boolean isUserInDB = false;
		if(ValidateData.validateNotEmpty(request.getParameter("username")) && ValidateData.validateNotEmpty(request.getParameter("password")))
		{
				List<User> users = Utilities.getListOfAllUsers();
				System.out.println(Utilities.checkusernameInDBDAO(request.getParameter("username")));
				if(ValidateData.checkUsernameInDB(request.getParameter("username"), users))
				{
					System.out.println(Utilities.checkpasswordInDBDAO(request.getParameter("username"),request.getParameter("password")));
					if(ValidateData.checkPasswordInDB(request.getParameter("password"),request.getParameter("username"),users))
					{
						isUserInDB = true;
						session.setAttribute("user", Utilities.getLoggedUser(users, request.getParameter("username")));
						//System.out.println(Utilities.getLoggedUser(users, request.getParameter("username")));
						System.out.println(Utilities.getdaoUser(request.getParameter("username")));
						
					}
					else{
						session.setAttribute("wrongPassword", true);
						//System.out.println("wrong pass");
					}
				}
				else{
					//System.out.println("wrong user");
					session.setAttribute("wrongUsername", true);
				}
			}
		if(isUserInDB){
			response.sendRedirect("/MazeOfDarkness2.0/Lobby");
		}
		}*/
		%>
	<form action="/MazeOfDarkness2.0/Login" method="POST">
		Username:<br> <input type="text" name=username required maxlength="20">
		<% 
			if(!session.isNew() && ((Boolean)request.getAttribute("wrongUsername")))
			{
				out.print("Wrong username!");
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
		<input type="submit" value="Login"><br>
		<a href="/MazeOfDarkness2.0/WEB-INF/jsps/Register.jsp">Not a member?</a>
	</form>
</body>
</html>