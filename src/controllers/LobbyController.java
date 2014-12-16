package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lobby.Lobby;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import player.FightPlayer;
import player.Player;
import player.User;
import player.UsersDAO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Controller
public class LobbyController {
	private static final Lobby LOBBY = Lobby.getLobby();

	@RequestMapping(value = "/Lobby", method = RequestMethod.GET)
	public String handleLobby(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			LOBBY.addUser((String) session.getAttribute("username"), session);
			session.setAttribute("playing", false);
			session.setAttribute("rejectionMSG", null);
			session.setAttribute("rejection", null);
			session.setAttribute("playWtih", null);
			session.setAttribute("opponentsLimit", 1);
		}
		return "LobbyRoom";
	}

	@RequestMapping(value = "/chooseOpponent", method = RequestMethod.GET)
	public String chooseOpponent(@RequestParam String onlineUsers,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		HttpSession session = request.getSession();

		HttpSession sessionOpponent = LOBBY.getSession(onlineUsers);
		sessionOpponent.setAttribute("playWith",
				session.getAttribute("username"));

		sessionOpponent.setAttribute("opponent",
				session.getAttribute("username"));

		session.setAttribute("opponent",
				sessionOpponent.getAttribute("username"));

		sessionOpponent.setAttribute("opponentsLimit", 0);

		session.setAttribute("opponentsLimit", 0);

		return "redirect: waitAnswer";
	}

	@RequestMapping(value = "/userChoice", method = RequestMethod.GET)
	public String userChoice(@RequestParam String choice,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap map) throws IOException {

		HttpSession session = request.getSession();
		HttpSession sessionOpponent = LOBBY.getSession((String) session
				.getAttribute("opponent"));

		if (choice.equals("play")) {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"Beans.xml");
			UsersDAO usersDAO = context.getBean("usersDAO", UsersDAO.class);
			String opponentName = (String) sessionOpponent.getAttribute("username");
			String myName = (String) session.getAttribute("username");
			Player p1 = new FightPlayer(myName,(User)usersDAO.getLoggedUser(myName));
			Player p2 = new FightPlayer(opponentName,(User)usersDAO.getLoggedUser(opponentName));

			session.setAttribute("player1", p1);
			
			session.setAttribute("player2", p2);
			sessionOpponent.setAttribute("player1", p2);
			sessionOpponent.setAttribute("player2", p1);
			map.addAttribute("player1", session);
			map.addAttribute("player2", sessionOpponent);
			session.setAttribute("map", map);
			session.setAttribute("playing", true);
			sessionOpponent.setAttribute("playing", true);
			return StartGame(map);

		}
		sessionOpponent.setAttribute("playWith", null);
		if (choice.equals("reject")) {
			return "redirect: rejection";
		}
		return "Error";
	}

	
	@RequestMapping(value = "/rejection", method = RequestMethod.GET)
	public String rejection(HttpSession session,HttpSession sessionOpponent) {
			sessionOpponent.setAttribute("rejection", "true");
			String msg = (String) session.getAttribute("username")
					+ " doesn't want to play with you!";
			sessionOpponent.setAttribute("rejectionMSG", msg);
			sessionOpponent.setAttribute("opponent", null);
			sessionOpponent.setAttribute("opponentsLimit", 1);
			session.setAttribute("opponentsLimit", 1);
			session.setAttribute("playWith", null);
			session.setAttribute("opponent", null);
			return "redirect: Lobby";
		
	}
	
	public String StartGame(ModelMap map) throws IOException {
		return "redirect: start";
	}

	@RequestMapping(value = "/waitAnswer", method = RequestMethod.GET)
	public String waitAnswer(HttpSession session) {
		return "LobbyRoom";
	}

	@RequestMapping(value = "/goBackToLobby", method = RequestMethod.GET)
	public String goBackToLobby(HttpSession session) {

		session.setAttribute("startGame", null);
		session.setAttribute("opponent", null);
		session.removeAttribute("game");
		session.removeAttribute("player1");
		session.removeAttribute("player2");

		session.removeAttribute("startGame");
		session.removeAttribute("playWith");
		
		return "redirect: Lobby";
	}

	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			LOBBY.removeUser((String) session.getAttribute("username"), session);
			session.invalidate();
		}
		return "redirect: Login";
	}
}
