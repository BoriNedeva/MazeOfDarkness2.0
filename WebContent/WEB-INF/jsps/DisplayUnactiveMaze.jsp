<%@page import="lobby.Lobby"%>
<%@ page import="player.FightPlayer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="1">
<title>Maze of Darkness</title>
</head>
<body>

<%	Lobby lobby = Lobby.getLobby(); 
	String opponent = (String)session.getAttribute("opponent"); 
	HttpSession sessionOpponent = lobby.getSession(opponent);
	 if(sessionOpponent.getAttribute("winner")!= null){
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/lose");
		 dispatcher.forward(request,response);
	}
%>
	<%=session.getAttribute("show") %>
	
	<form   action="move"  >	
		<table  style="float: left">
			<tr>
				<td> Moves left: <%=((FightPlayer)session.getAttribute("player1")).getNumberOfMoves() %> </td>
			</tr>
			<tr>
			   <td>  name  <%=((FightPlayer)session.getAttribute("player1")).getUserName()  %> </td>
			</tr>
			<tr>
			   <td>  score  <%=((FightPlayer)session.getAttribute("player1")).getScore() %> </td>
			</tr>
			<tr>
			   <td>  health  <%=((FightPlayer)session.getAttribute("player1")).getHealth()  %> </td>
			</tr>
		</table>
	</form>	
</body>
</html>