<%@ page import="player.FightPlayer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="images/jpg; charset=ISO-8859-1">

<title>Maze Of darkness</title>
</head>
<body>
<%-- <%=request.getAttribute("show") %>
 --%>
 <% if((Boolean)session.getAttribute("Box")){%>
		Box!
		<form  action="wild">
		<input type="submit" name="choice" value="wild" checked>wild
		<br>
		</form>
		<form action="dispicable">
		<input type="submit" name="choice" value="despicable">despicable
		</form>
	
<%} session.setAttribute("Box", false);%>
 <%=session.getAttribute("show") %>
 <%	if((session.getAttribute("card")!=null)){ %>
	<%= (String)session.getAttribute("card") %>
	<% } session.setAttribute("card", null); %>
	<form action="move">	
		<table>
			<tr>
				<td>
					<input type="submit" value="left" name="move">	
				</td>
				<td>
					 <input type="submit" value="right" name="move">
				</td>
				<td>
					 <input type="submit" value="up" name="move">
				</td>
				<td>	
					 <input type="submit" value="down" name="move">	
				</td>
				<td> Moves left: <%=((FightPlayer)session.getAttribute("player1")).getNumberOfMoves() %> </td>
			</tr>
		</table>
	</form>	

</body>
</html>