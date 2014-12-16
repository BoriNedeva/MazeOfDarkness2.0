<%@ page import="player.FightPlayer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="2">
<title>Maze of Darkness</title>
</head>
<body>
	<%=session.getAttribute("show") %>
	
	<form >	
		<table  style="float: left">
			<tr>
				<td> Moves left: <%=(((FightPlayer)session.getAttribute("player1")).getNumberOfMoves()-1) %> </td>
				<td> Moves left: <%=(((FightPlayer)session.getAttribute("player2")).getNumberOfMoves()-1) %> </td>
			</tr>
			<tr>
			   <td>  name  <%=((FightPlayer)session.getAttribute("player1")).getUserName()  %> </td>
			    <td>  name  <%=((FightPlayer)session.getAttribute("player2")).getUserName()  %> </td>
			</tr>
			<tr>
			   <td>  score  <%=((FightPlayer)session.getAttribute("player1")).getScore() %> </td>
			   <td>  score  <%=((FightPlayer)session.getAttribute("player2")).getScore() %> </td>
			</tr>
			<tr>
			   <td>  health  <%=((FightPlayer)session.getAttribute("player1")).getHealth()  %> </td>
			   <td>  health  <%=((FightPlayer)session.getAttribute("player2")).getHealth()  %> </td>
			</tr>
			</table >
			 <%	if((session.getAttribute("card")!=null)){ %>
			<table  style="float: left">
							<tr> <td>  <%= ((FightPlayer)session.getAttribute("player1")).getUserName() %>  <%= (String)session.getAttribute("card") %> </td> </tr> 
								<%    session.setAttribute("card", null);} %>
		</table>
	</form>	
</div>
</body>
</html>