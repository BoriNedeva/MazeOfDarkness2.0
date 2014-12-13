package controllers;

import java.util.ArrayList;

import game.Coordinates;
import game.Game;
import game.Maze;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import player.FightPlayer;
import player.Player;
import box.Box;
import box.FightBox;

@Controller
public class HelloController {
	@RequestMapping(value = "/up", method = RequestMethod.GET)
	public String moveUp(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {

		HttpSession session = request.getSession();
		Game currentGame = (Game) session.getAttribute("game");
		Player p = (Player) session.getAttribute("player1");
		int x = p.getCoords().getX() - 1;
		int y = p.getCoords().getY();
		try {
			if (currentGame.getMaze().getMaze()[x][y] == ' '){
				p.moveUp();
				if(currentGame.checkForBox(p.getCoords())){
					session.setAttribute("Box", true);
					System.out.println(true);
				}
				else System.out.println(false);
			}
				
			
		} catch (IndexOutOfBoundsException e) {

		}
		String show = displayGame(currentGame);
		map.addAttribute("show", show);
		session.setAttribute("show", show);
		return "DisplayMaze";
	}

	@RequestMapping(value = "/left", method = RequestMethod.GET)
	public String moveLeft(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		HttpSession session = request.getSession();
		Game currentGame = (Game) session.getAttribute("game");
		Player p = (Player) session.getAttribute("player1");
		int x = p.getCoords().getX();
		int y = p.getCoords().getY() - 1;
		try {
			if (currentGame.getMaze().getMaze()[x][y] == ' ')
				p.moveLeft();
			if(currentGame.checkForBox(p.getCoords())){
				session.setAttribute("Box", true);
				System.out.println(true);
			}
			else System.out.println(false);
		
		} catch (IndexOutOfBoundsException e) {

		}
		String show = displayGame(currentGame);
		map.addAttribute("show", show);
		session.setAttribute("show", show);

		return "DisplayMaze";
	}

	@RequestMapping(value = "/down", method = RequestMethod.GET)
	public String moveDown(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		HttpSession session = request.getSession();
		Game currentGame = (Game) session.getAttribute("game");
		Player p = (Player) session.getAttribute("player1");
		int x = p.getCoords().getX() + 1;
		int y = p.getCoords().getY();
		try {
			if (currentGame.getMaze().getMaze()[x][y] == ' ')
				p.moveDown();
			if(currentGame.checkForBox(p.getCoords())){
				session.setAttribute("Box", true);
				System.out.println(true);
			}
			else System.out.println(false);
		
		} catch (IndexOutOfBoundsException e) {

		}
		String show = displayGame(currentGame);
		map.addAttribute("show", show);
		session.setAttribute("show", show);
		return "DisplayMaze";
	}

	@RequestMapping(value = "/right", method = RequestMethod.GET)
	public String moveRight(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		HttpSession session = request.getSession();
		Game currentGame = (Game) session.getAttribute("game");
		Player p = (Player) session.getAttribute("player1");
		int x = p.getCoords().getX();
		int y = p.getCoords().getY() + 1;
		try {
			if (currentGame.getMaze().getMaze()[x][y] == ' ')
				p.moveRight();
			if(currentGame.checkForBox(p.getCoords())){
				session.setAttribute("Box", true);
				System.out.println(true);
			}
			else System.out.println(false);
		
		} catch (IndexOutOfBoundsException e) {

		}
		String show = displayGame(currentGame);
		map.addAttribute("show", show);
		session.setAttribute("show", show);
		return "DisplayMaze";
	}

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String StartGame(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		HttpSession session1 = request.getSession();// (HttpSession)map.get("player1");
		map = (ModelMap) session1.getAttribute("map");
		HttpSession session2 = (HttpSession) map.get("player2");

		Box box = new FightBox();
		Maze m = new Maze(6, 10);
		Player p1 = (Player) session1.getAttribute("player1");
		Player p2 = (Player) session2.getAttribute("player1");
		p1.setCoords(m.getCoordinateOfPlayerOne());
		p2.setCoords(m.getCoordinateOfPlayerTwo());

		Game game = new Game(p1, p2, box, m);
		game.placeBoxes();
		session1.setAttribute("game", game);
		session1.setAttribute("player1", p1);
		session2.setAttribute("game", game);
		session2.setAttribute("startGame", true);

		String show = displayGame(game);
		map.addAttribute("show", show);
		session1.setAttribute("show", show);
		session2.setAttribute("show", show);
		session1.setAttribute("Box", false);
		session2.setAttribute("Box", false);

		return "DisplayMaze";
	}

	private String displayGame(Game game) {
		StringBuilder sb = new StringBuilder();
		char[][] maze = game.getMaze().getMaze();
		sb.append("<table>");
		for (int i = 0; i < maze.length; i++) {
			sb.append("<tr>");
			for (int j = 0; j < maze[i].length; j++) {
				sb.append("<td>");
				if (i == game.getPlayerOne().getCoords().getX()
						&& j == game.getPlayerOne().getCoords().getY()) {
					sb.append(" <img src=\"img\\player2.jpg\" alt=\"Player 1\" height=\"42\" width=\"42\"> ");
				} else if (i == game.getPlayerTwo().getCoords().getX()
						&& j == game.getPlayerTwo().getCoords().getY()) {
					sb.append("<img src=\"img\\player2.gif\" alt=\"Player 2\" height=\"42\" width=\"42\">");
				}
				 else if(game.checkForBox(new Coordinates(i, j))){
					 sb.append("<img src=\"img\\box.jpg\" alt=\"Obstacle\" height=\"42\" width=\"42\">");
				 }
				else if (maze[i][j] == '#')
					sb.append("<img src=\"img\\obstacle.gif\" alt=\"Obstacle\" height=\"42\" width=\"42\">");
				else
					sb.append("<img src=\"img\\road.gif\" alt=\"Road\" height=\"42\" width=\"42\">");
				sb.append("</td>");
			}
			sb.append("</tr>");
		}

		return sb.toString();
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public String displayMaze(){
		return "DisplayMaze";
	}
	
	
//	 private boolean checkBox(Game game,int x,int y) {
//		 ArrayList<Coordinates> boxCoords = game.getBox().getBoxCoords();
//		 for (int i = 0; i < boxCoords.size(); i++) {
//			 if(boxCoords.get(i).getX()==x && boxCoords.get(i).getY()==y)
//				 return true;
//		 }
//		 return false;
//	 }
}