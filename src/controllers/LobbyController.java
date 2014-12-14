package controllers;

import game.Coordinates;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lobby.Lobby;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import player.FightPlayer;
import player.Player;
import player.User;

@Controller
// @SessionAttributes("user")
public class LobbyController {
	private static final Lobby LOBBY = new Lobby();

	@RequestMapping(value = "/Lobby", method = RequestMethod.GET)
	public String handleLobby(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		LOBBY.addUser((String) session.getAttribute("username"), session);
		// model.addAttribute("lobby", LOBBY);
		session.setAttribute("lobby", LOBBY);
		// System.out.println(session.getAttribute("username"));
		return "LobbyRoom";
	}

	@RequestMapping(value = "/chooseOpponent", method = RequestMethod.GET)
	public String chooseOpponent(@RequestParam String onlineUsers,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		HttpSession session = request.getSession();
		Lobby lobby = (Lobby) session.getAttribute("lobby");
		// System.out.println(request.getAttribute("lobby"));
		HttpSession sessionOpponent = lobby.getSession(onlineUsers);

		sessionOpponent.setAttribute("playWith",
				session.getAttribute("username"));

		sessionOpponent.setAttribute("opponent",
				session.getAttribute("username"));
		session.setAttribute("opponent",
				sessionOpponent.getAttribute("username"));

		// System.out.println(sessionOpponent.getAttribute("username") + "opp");
		// System.out.println(session.getAttribute("username") + "curr");

		return "LobbyRoom";
	}

	@RequestMapping(value = "/userChoice", method = RequestMethod.GET)
	public String userChoice(@RequestParam String choice,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap map) throws IOException {

		HttpSession session = request.getSession();
		Lobby lobby = (Lobby) session.getAttribute("lobby");
		HttpSession sessionOpponent = lobby.getSession((String) session
				.getAttribute("opponent"));
		if (choice.equals("play")) {
			//Coordinates coorP1 = new Coordinates(0, 0);
			//Coordinates coorP2 = new Coordinates(0, 0);
			Player p1 = new FightPlayer((String)session.getAttribute("username"),(User)session.getAttribute("user"));
			Player p2 = new FightPlayer((String)sessionOpponent.getAttribute("username"), (User)sessionOpponent.getAttribute("user"));
			session.setAttribute("player1", p1);
			session.setAttribute("player2", p2);
			sessionOpponent.setAttribute("player1", p2);
			sessionOpponent.setAttribute("player2", p1);
			//sessionOpponent.setAttribute("startGame", true);
			map.addAttribute("player1", session);
			map.addAttribute("player2", sessionOpponent);
			session.setAttribute("map", map);
			return StartGame(map);
			
		} else if (choice.equals("reject")) {
			return "reject";
		}
		return "greshka pri user choice";
	}

	
	public String StartGame(ModelMap map) throws IOException {

		
		System.out.println(((HttpSession) map.get("player1"))
				.getAttribute("username")
				+ ((HttpSession) map.get("player1")).getId());
		System.out.println(((HttpSession) map.get("player2"))
				.getAttribute("username")
				+ ((HttpSession) map.get("player2")).getId());
		
		return "redirect: start";
	}

}
