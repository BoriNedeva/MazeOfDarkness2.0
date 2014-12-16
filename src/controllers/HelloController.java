package controllers;

import game.Coordinates;
import game.Game;
import game.Maze;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import player.FightPlayer;
import player.Player;
import box.Box;
import box.FightBox;

@Controller
public class HelloController {

	@RequestMapping(value = "/move", method = RequestMethod.GET)
	public String move(@RequestParam String move, HttpServletRequest request,
			ModelMap map) {
		HttpSession session = request.getSession();
		Game currentGame = (Game) session.getAttribute("game");
		FightPlayer p = (FightPlayer) session.getAttribute("player1");
		FightPlayer p2 = (FightPlayer) session.getAttribute("player2");		
		
		if(p.isHasLose()){
			return "redirect: lose";
		}
		if(p.isHasWon()){
			session.setAttribute("winner", true);
			return "redirect: win";
		}
		int x = 0;
		int y = 0;
		switch (move) {
		case "right":
			x = p.getCoords().getX();
			y = p.getCoords().getY() + 1;
			break;
		case "left":
			x = p.getCoords().getX();
			y = p.getCoords().getY() - 1;
			break;
		case "up":
			x = p.getCoords().getX() - 1;
			y = p.getCoords().getY();
			break;
		case "down":
			x = p.getCoords().getX() + 1;
			y = p.getCoords().getY();
			break;
		}
			try {
				if (currentGame.getMaze().getMaze()[x][y] == ' ')
					p.move(x, y);
				if (currentGame.checkIfPlayerCanKill()) {
					//Player winner = currentGame.checkForWinner();
					currentGame.endGame();
				}
				if(currentGame.getCupCoordinates()!=null && currentGame.getCupCoordinates().equals(p.getCoords())){
					p.setHasWon(true);
					p2.setHasLose(true);
				}
				
				if (currentGame.checkForBox(p.getCoords())) {
					session.setAttribute("Box", true);
					System.out.println(true);
				} else
					System.out.println(false);

			} catch (IndexOutOfBoundsException e) {

			}
	
		String show = displayGame(currentGame, p);
		session.setAttribute("show", show);
		
			if (p.getNumberOfMoves() == 1) {
						
				p2.setNumberOfMoves(Game.getAvailablemoves());
				if(p2.getNumberOfMoves()>0){
					p.setNumberOfMoves(-1);
					return "redirect: displayUnactive";
				}else{
					p.setNumberOfMoves(Game.getAvailablemoves()-1);
				}
			} else{
				p.setNumberOfMoves(-1);
			}
				return "redirect: display";
		
		

	}

	@RequestMapping(value = "/win", method = RequestMethod.GET)
	public String win(HttpServletRequest request,
			ModelMap map) {

		return "Win";
	}

	@RequestMapping(value = "/lose", method = RequestMethod.GET)
	public String lose(HttpServletRequest request,
			ModelMap map) {

		return "Lose";
	}

	@RequestMapping(value = "/card", method = RequestMethod.GET)
	public String getCard(@RequestParam String card,
			HttpServletRequest request ) {
		HttpSession session = request.getSession();
		HttpSession sessionOpponent= (HttpSession)session.getAttribute("sessionOpponent");
		Game game = (Game) session.getAttribute("game");
		FightPlayer p = (FightPlayer) session.getAttribute("player1");

		System.out.println(p);
		String cardInfo = "";
		//if (p.getNumberOfMoves() > 0 && p.getNumberOfMoves() < 6) {
			switch (card) {
			case "wild":
				cardInfo = game.executeWildCard(p);
				break;
			case "despicable":
				cardInfo = game.executeDespicableCard(p);
				session.setAttribute("winner", game.checkForWinner());
				if (session.getAttribute("winner") != null) {
					game.endGame();
				}
			}
	//	}
		p.toString();
		session.setAttribute("card", cardInfo);
		sessionOpponent.setAttribute("card", cardInfo);
		String show = displayGame(game, p);
		session.setAttribute("show", show);
		sessionOpponent.setAttribute("show", show);
//		if (p.getNumberOfMoves() > 0) {
//			if (p.getNumberOfMoves() == 1) {
//				FightPlayer p2 = (FightPlayer) session.getAttribute("player2");
//				p2.setNumberOfMoves(6);
//				p.setNumberOfMoves(-1);
//				return "DisplayUnactiveMaze";
//			} else
//				p.setNumberOfMoves(-1);
			return "redirect: display";
//		} else {
//			return "DisplayUnactiveMaze";
//		}

	}

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String StartGame(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		HttpSession session1 = request.getSession();// (HttpSession)map.get("player1");
		map = (ModelMap) session1.getAttribute("map");
		HttpSession session2 = (HttpSession) map.get("player2");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		Box box = context.getBean("fightBox", FightBox.class);
		Maze m = new Maze(6, 10);
		Player p1 = (Player) session1.getAttribute("player1");
		Player p2 = (Player) session2.getAttribute("player1");
		p1.setFlashLight(1);
		p2.setFlashLight(1);
		p1.setCoords(m.getCoordinateOfPlayerOne());
		p2.setCoords(m.getCoordinateOfPlayerTwo());
		
		session1.setAttribute("canMove",true);
		session2.setAttribute("canMove",false);
		Game game = new Game(p1, p2, box, m);
		game.placeBoxes();
		((FightPlayer) p1).setNumberOfMoves(Game.getAvailablemoves());
		((FightPlayer) p2).setNumberOfMoves(0);
		session1.setAttribute("game", game);
		session1.setAttribute("player1", p1);
		session1.setAttribute("sessionOpponent", session2);
		session2.setAttribute("game", game);
		session2.setAttribute("startGame", true);
		session2.setAttribute("sessionOpponent", session1);

		String show = displayGame(game, p1);
		// map.addAttribute("show", show);
		session1.setAttribute("show", show);
		session2.setAttribute("show", show);
		session1.setAttribute("Box", false);
		session2.setAttribute("Box", false);
		return "DisplayMaze";
	}

	private String displayGame(Game game, Player p) {
		StringBuilder sb = new StringBuilder();
		char[][] maze = game.getMaze().getMaze();
		sb.append("<div><table style=\"float: left\">");
		for (int i = 0; i < maze.length; i++) {
			sb.append("<tr>");
			for (int j = 0; j < maze[i].length; j++) {
				sb.append("<td>");
				if (game.getPlayerOne().getCoords().equals(new Coordinates(i, j))) {
					sb.append(" <img src=\"img\\player.png\" alt=\"Player 1\" height=\"42\" width=\"42\"> ");
				} else if (game.getPlayerTwo().getCoords().equals(new Coordinates(i, j))) {
					sb.append("<img src=\"img\\player2.gif\" alt=\"Player 2\" height=\"42\" width=\"42\">");
				} else if (game.checkForBox(new Coordinates(i, j))) {
					sb.append("<img src=\"img\\box.jpg\" alt=\"box\" height=\"42\" width=\"42\">");
				}else if(game.getCupCoordinates()!=null && game.getCupCoordinates().equals(new Coordinates(i, j)) ){
						sb.append("<img src=\"img\\cup.jpg\" alt=\"Cup\" height=\"42\" width=\"42\">");
				} else if (maze[i][j] == '#'
						&& (game.isLight(p, new Coordinates(i, j))))
					sb.append("<img src=\"img\\obstacle.gif\" alt=\"Obstacle\" height=\"42\" width=\"42\">");
				else if ((game.isLight(p, new Coordinates(i, j))))
					sb.append("<img src=\"img\\road.gif\" alt=\"Road\" height=\"42\" width=\"42\">");
				else
					sb.append("<img src=\"img\\byBori.jpg\" alt=\"Road\" height=\"42\" width=\"42\">");
				sb.append("</td>");
			}
			sb.append("</tr>");
		}

		return sb.toString();
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public String displayMaze(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Game game = (Game) session.getAttribute("game");
		FightPlayer p = (FightPlayer) session.getAttribute("player1");
		if (((FightPlayer) session.getAttribute("player1")).isHasLose() == true)
			return "Lose";
		if (((FightPlayer) session.getAttribute("player1")).isHasWon() == true)
			return "win";
		String show = displayGame(game, p);
		session.setAttribute("show", show);
		if(((FightPlayer) session.getAttribute("player1")).getNumberOfMoves()==0){
			return"redirect: displayUnactive";
		}
		return "DisplayMaze";
	}

	@RequestMapping(value = "/displayUnactive", method = RequestMethod.GET)
	public String displayUnactiveMaze(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (((FightPlayer) session.getAttribute("player1")).isHasLose() == true)
			return "Lose";
		if (((FightPlayer) session.getAttribute("player1")).isHasWon() == true)
			return "win";

		FightPlayer p = (FightPlayer) session.getAttribute("player1");
		Game game = (Game) session.getAttribute("game");
		String show = displayGame(game, p);
		session.setAttribute("show", show);
		if(p.getNumberOfMoves()>0){
			return"redirect: display";
		}
		return "DisplayUnactiveMaze";
	}

}