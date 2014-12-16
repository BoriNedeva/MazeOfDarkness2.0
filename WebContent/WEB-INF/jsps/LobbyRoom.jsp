<%@page import="player.Statistics"%>
<%@page import="player.User"%>
<%@page import="player.UsersDAO"%>
<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="lobby.Lobby"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body {
	background-image: url("img\\mazeWallpaper.jpg");
	background-repeat: no-repeat;
	background-size: cover;
	margin-top: 50px;
}

table {
	border-collapse: collapse;
}

.userInfo {
	float: left;
	width: 27%;
	padding: 10px;
	margin-right:100px;
	margin-left:10px;
	background: #7f7f7f;
	background: rgba(255, 255, 255, 0.5);
}

td.userInfo {
	float: center;
	font-weight: bold;
}

.freePlayers {
	float: left;
	width: 25%;
	padding: 20px;
	background: #7f7f7f;
	background: rgba(255, 255, 255, 0.5);
}

td.freePlayers {
	float: center;
	text-align: center;
	font-weight: bold;
}

.topPlayers {
	float: left;
	width: 25%;
	padding:10px;
	margin-right:50px;
	margin-left:100px;
	background: #7f7f7f;
	background: rgba(255, 255, 255, 0.5);
	font-weight: bold;
}

td.topPlayers {
	float: center;
	text-align: center;
}

#messages {
	position: absolute;
	top: 300px;
	margin: 10px;
	padding: 10px;
	width: 25%;
	float: right;
	background: #7f7f7f;
	background: rgba(255, 255, 255, 0.5);
}

input {
	width: 100px;
}
</style>

<title>Lobby</title>
</head>
<body>
	<form action="Logout" method="GET">
		<input type="submit" name="logout" value="logout" style="float: right; margin-top:-45px;">
	</form>
	<%
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		UsersDAO usersDAO = context.getBean("usersDAO", UsersDAO.class);

		Statistics statistics = ((User) session.getAttribute("user"))
				.getStatistics();
	%>

	<table class="userInfo">
		<tr>
			<td style="font-size: 30px; padding-left: 10px"><%=session.getAttribute("username")%>'s
				profile info</td>
		</tr>
		<tr>
			<td class="userInfo">Level</td>
			<td class="userInfo"><%=statistics.getLevel()%></td>
		</tr>
		<tr>
			<td class="userInfo">Number of wins</td>
			<td class="userInfo"><%=statistics.getWins()%></td>
		</tr>
		<tr>
			<td class="userInfo">High score</td>
			<td class="userInfo"><%=statistics.getHighScore()%></td>

		</tr>
	</table>
	
	<%
		//	session = request.getSession(false);
		String userName = "";
		if (session != null) {
			final Lobby lobby = Lobby.getLobby();
			HttpSession userSession = null;
			out.print("<form action=\"chooseOpponent\" method=\"GET\">");
			out.print("<table class=\"freePlayers\"><tr><td class=\"freePlayers\">Free players</td></tr>");
			if (lobby.getOnlineUsers().size() == 1) {
				out.print("<tr><td class=\"freePlayers\">No active players</td></tr>");
			}
			for (String user : lobby.getOnlineUsers().keySet()) {
				userSession = lobby.getSession(user);
				if (!session.getAttribute("username").equals(user)
						&& session.getAttribute("opponent") == null
						&& (Integer) userSession
								.getAttribute("opponentsLimit") != 0) {
					out.print("<tr><td class=\"freePlayers\">");
					out.println("<input type=\"submit\" name = \"onlineUsers\" value = \""
							+ user + "\"><br>");
					out.print("</td></tr>");
				}
			}
			out.print("</table></form>");
		}

		response.setIntHeader("Refresh", 3);
	%>


	<table class="topPlayers">


		<tr>
			<td  style="font-size: 30px;padding-left: 50px">Top players</td>
		</tr>
		<tr>
			<td class="topPlayers">User name</td>
			<td class="topPlayers">Level</td>
			<td class="topPlayers">Number of wins</td>
		</tr>
		<%
			for (int i = 0; i < usersDAO.getTopUsers().size(); i++) {
				out.print("<tr><td class=\"topPlayers\">"
						+ usersDAO.getTopUsers().get(i).getUsername() + "</td>");
				out.print("<td class=\"topPlayers\">"
						+ usersDAO.getTopUsers().get(i).getStatistics()
								.getLevel() + "</td>");
				out.print("<td class=\"topPlayers\">"
						+ usersDAO.getTopUsers().get(i).getStatistics()
								.getWins() + "</td></tr>");
			}
		%>
	</table>


	<div id="messages">
		<h1>Messages</h1>
		<%
			System.out.print(session.getAttribute("username") + " "
					+ session.getAttribute("playWith"));
			System.out.print(session.getAttribute("username") + " "
					+ session.getAttribute("opponentsLimit"));
			out.print("<h3>Messages from other users:</h3>");

			if (session != null) {
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

			}
		%>

		<%
			if (session != null) {
				if (session.getAttribute("startGame") != null) {
					out.print("<form action=\"display\" method=\"GET\">");
					out.print("<input type=\"submit\" value = \"play\" name = \"start\">");
					out.print("</form>");
					session.setAttribute("opponentsLimit", 0);
					session.setAttribute("playing", true);
				}
			}
		%>

		<%
			if (session != null) {
				if (session.getAttribute("rejection") != null) {
					out.print((String) session.getAttribute("rejectionMSG"));
					out.print("<form method=\"GET\" action=\"Lobby\"><input type=\"submit\" value=\"ok\"></form>");
				}
			}
		%>
	</div>
</body>
</html>