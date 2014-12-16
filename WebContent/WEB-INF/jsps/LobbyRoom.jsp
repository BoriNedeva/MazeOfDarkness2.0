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
table {
	border-collapse: collapse;
}

.userInfo {
	float: left;
	width: 25%;
	margin-left: 10px;
}

td.userInfo {
	float:center;
	padding: 10px;
}

.freePlayers {
	float: right;
	width: 50%;
}

/* th, td {
	padding: 5px;
	text-align: left;
} */
input {
	width: 100px;
}
</style>

<title>
	Lobby
</title>
</head>
<body>
	<form action="Logout" method="GET">
		<input type="submit" name="logout" value="logout" style="float: right">
	</form>
	<%
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		UsersDAO usersDAO = context.getBean("usersDAO", UsersDAO.class);

		Statistics statistics = ((User) session.getAttribute("user"))
				.getStatistics();
	%>


	<table class="userInfo" style="width: 25%;">
		<caption style="font-size: 30px;"><%=session.getAttribute("username")%>'s profile info
		</caption>
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
		
	%>

	<%
		//	session = request.getSession(false);
		String userName = "";
		if (session != null) {

			final Lobby lobby = Lobby.getLobby();

			HttpSession userSession = null;
			out.print("<form action=\"chooseOpponent\" method=\"GET\">");
			out.print("<table class=\"freePlayers\"><caption>Free players</caption>");
			for (String user : lobby.getOnlineUsers().keySet()) {

				userSession = lobby.getSession(user);
				System.out.print(user);
				if (!session.getAttribute("username").equals(user)
						&& session.getAttribute("opponent") == null
						&& (Integer) userSession
								.getAttribute("opponentsLimit") != 0) {
					System.out.print("1");

					out.print("<tr style=\"text-align:center\"><td>");
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
</body>
</html>