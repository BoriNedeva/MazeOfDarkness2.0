<%@page import="lobby.Lobby"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
table, th, td {
	border-collapse: collapse;
}

th, td {
	padding: 5px;
}

input{
  width:200px;
}
</style>

<title>
	<%
		out.print(session.getAttribute("username"));
	%>
</title>
</head>
<body>
	<form action="Logout.jsp" method="POST">
		<input type="submit" name="logout" value="logout" style="float: right">
	</form>
	<%
		final Lobby lobby = Lobby.getLobby();
		session = request.getSession(false);
		String userName = "";
		if (session != null) {
			HttpSession userSession = null;
			out.print("<form action=\"chooseOpponent\" method=\"GET\">");
			out.print("<table><caption>Free players</caption>");
			
			for (String user : lobby.getOnlineUsers().keySet()) {
				userSession = lobby.getSession(user);
				if (!session.getAttribute("username").equals(user)
						&&session.getAttribute("opponent")==null
						&& (Integer) userSession
								.getAttribute("opponentsLimit") != 0) {
					out.print("<tr><td>");
					out.println("<input type=\"submit\" name = \"onlineUsers\" value = \""
							+ user + "\"><br>");
					out.print("</td></tr>");

				}
			}
			out.print("</table></form>");
		}

		response.setIntHeader("Refresh", 3);
	%>

	<%
		if (session.getAttribute("playWith") != null
				&& (Integer) session.getAttribute("opponentsLimit") != 0) {
			session.setAttribute("opponentsLimit", 0);
			out.print(session.getAttribute("playWith")
					+ " wants to play with you.");
			out.print("<form id= \"playWith\" action=\"userChoice\" method=\"GET\">");
			out.print("<input type=\"submit\" value = \"play\" name = \"choice\">");
			out.print("<input type=\"submit\" value = \"reject\" name = \"choice\">");
			out.print("</form>");

		}
	%>

	<%
		if (session.getAttribute("startGame") != null) {
			out.print("<form action=\"display\" method=\"GET\">");
			out.print("<input type=\"submit\" value = \"play\" name = \"start\">");
			out.print("</form>");
			session.setAttribute("opponentsLimit", 0);
			session.setAttribute("playing", true);
		}
	%>

	<%
		if (session.getAttribute("rejection") != null) {
			out.print((String) session.getAttribute("rejectionMSG"));
			out.print("<form method=\"GET\" action=\"Lobby\"><input type=\"submit\" value=\"ok\"></form>");
		}
	%>
</body>
</html>