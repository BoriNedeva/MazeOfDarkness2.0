<%@ page import="player.FightPlayer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="images/jpg; charset=ISO-8859-1">
<style >input{with:80ph;}</style>
<title>Maze Of darkness</title>
</head>
<body>
<% if(session.getAttribute("winner")!= null){
	 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/win");
	 dispatcher.forward(request,response);
}


 %>
 <% if(((FightPlayer)session.getAttribute("player1")).getNumberOfMoves()==0) {
	 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/displayUnactive");
	 dispatcher.forward(request,response);
 } %>
 	 <%=session.getAttribute("show") %>
 	 <table style="float: left">
		<tr>
				<td> Moves left: <%=((FightPlayer)session.getAttribute("player1")).getNumberOfMoves() %> </td>
				<td> Moves left: <%=((FightPlayer)session.getAttribute("player2")).getNumberOfMoves() %> </td>
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
		</table>
	
	
	<form   action="move"  >	
		<table  style="float: left">
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="up" name="move">	
				</td>
			</tr>
			<tr>
				<td>
					 <input type="submit" value="left" name="move">
				</td>
				<td>
					 <input type="submit" value="right" name="move">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">	
					 <input type="submit" value="down" name="move">	
			</td>
			</tr>
	</table>
	<table style="float: left">
			 <%	if((session.getAttribute("card")!=null)){ %>
			 <tr>
			  <td> <%= ((FightPlayer)session.getAttribute("player1")).getUserName() %> <%= (String)session.getAttribute("card") %> 
			 </td>
			  </tr>
								
					<%    session.setAttribute("card", null);} %>
			
	</table>
</form>	
		
	<% if((Boolean)session.getAttribute("Box")){%>
							Box!
							<form   action="card" >
							<table style="float: left">
							<tr>
								<td>
								<input type="submit" name="card" value="wild" checked>wild
								</td>
							</tr>
							<tr>
								<td>
								<input type="submit" name="card" value="despicable">despicable
								</td>
							</tr>
							</table>
														
							</form>
								
							<%} session.setAttribute("Box", false);%>
							
							
</div>
</body>
</html>