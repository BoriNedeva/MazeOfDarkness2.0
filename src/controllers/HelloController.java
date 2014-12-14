package controllers;

import java.util.ArrayList;

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
	public String move(@RequestParam String move,HttpServletRequest request,ModelMap map){
		HttpSession session = request.getSession();
		Game currentGame = (Game) session.getAttribute("game");
		FightPlayer p = (FightPlayer) session.getAttribute("player1");
		int x=0;
		int y=0;
		switch(move){
			case "right":
				 x = p.getCoords().getX();
				 y = p.getCoords().getY() + 1;
				 break;
			case "left":
				 x = p.getCoords().getX();
				 y = p.getCoords().getY() - 1;
				 break;
			case "up":
				x = p.getCoords().getX()-1;
				y = p.getCoords().getY();
				break;
			case "down":
				x = p.getCoords().getX()+1;
				y = p.getCoords().getY();
				break;
			}
		if(p.getNumberOfMoves()>0 && p.getNumberOfMoves() <6){		
			try {
				if (currentGame.getMaze().getMaze()[x][y] == ' ')
					p.move(x,y);
				if(currentGame.checkForBox(p.getCoords())){
					session.setAttribute("Box", true);
					System.out.println(true);
				}
				else System.out.println(false);
			
			} catch (IndexOutOfBoundsException e) {
	
			}
		}
		String show = displayGame(currentGame,p);
		map.addAttribute("show", show);
		session.setAttribute("show", show);
		if(p.getNumberOfMoves()>0){
			if(p.getNumberOfMoves()==1){
				FightPlayer p2 = (FightPlayer)session.getAttribute("player2");
				p2.setNumberOfMoves(6);
				p.setNumberOfMoves(0);
				return"DisplayUnactiveMaze";
			}else
				p.setNumberOfMoves(p.getNumberOfMoves()-1);
			return "DisplayMaze";
		}else{
			return "DisplayUnactiveMaze";
		}
		
		
	}
	@RequestMapping(value = "/wild", method = RequestMethod.GET)
	public String move(HttpServletRequest request,ModelMap map){
		HttpSession  session = request.getSession();
		Game game = (Game)session.getAttribute("game");
		FightPlayer p = (FightPlayer)session.getAttribute("player1");
		
		System.out.println(p);
		String cardInfo = game.executeWildCard(p);
		p.toString();
		session.setAttribute("card",cardInfo);
		String show = displayGame(game,p);
		session.setAttribute("show", show);
		if(p.getNumberOfMoves()>0){
			if(p.getNumberOfMoves()==1){
				FightPlayer p2 = (FightPlayer)session.getAttribute("player2");
				p2.setNumberOfMoves(6);
				p.setNumberOfMoves(0);
				return"DisplayUnactiveMaze";
			}else
				p.setNumberOfMoves((p.getNumberOfMoves()-1));
			return "DisplayMaze";
		}else{
			return "DisplayUnactiveMaze";
		}
		
	}
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String StartGame(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		HttpSession session1 = request.getSession();// (HttpSession)map.get("player1");
		map = (ModelMap) session1.getAttribute("map");
		HttpSession session2 = (HttpSession) map.get("player2");
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		Box box = context.getBean("fightBox", FightBox.class);
		Maze m = new Maze(6, 10);
		Player p1 = (Player) session1.getAttribute("player1");
		Player p2 = (Player) session2.getAttribute("player1");
		p1.setFlashLight(1);
		p2.setFlashLight(1);
		p1.setCoords(m.getCoordinateOfPlayerOne());
		p2.setCoords(m.getCoordinateOfPlayerTwo());
		((FightPlayer)p1).setNumberOfMoves(6);		
		((FightPlayer)p2).setNumberOfMoves(0);
		Game game = new Game(p1, p2, box, m);
		game.placeBoxes();
		session1.setAttribute("game", game);
		session1.setAttribute("player1", p1);
		session2.setAttribute("game", game);
		session2.setAttribute("startGame", true);

		String show = displayGame(game,p1);
		//map.addAttribute("show", show);
		session1.setAttribute("show", show);
		session2.setAttribute("show", show);
		session1.setAttribute("Box", false);
		session2.setAttribute("Box", false);
		return "DisplayMaze";
	}

	private String displayGame(Game game,Player p) {
		StringBuilder sb = new StringBuilder();
		char[][] maze = game.getMaze().getMaze();
		sb.append("<table>");
		for (int i = 0; i < maze.length; i++) {
			sb.append("<tr>");
			for (int j = 0; j < maze[i].length; j++) {
				sb.append("<td>");
				if (i == game.getPlayerOne().getCoords().getX()
						&& j == game.getPlayerOne().getCoords().getY()) {
					sb.append(" <img src=\"img\\player.png\" alt=\"Player 1\" height=\"42\" width=\"42\"> ");
				} else if (i == game.getPlayerTwo().getCoords().getX()
						&& j == game.getPlayerTwo().getCoords().getY()) {
					sb.append("<img src=\"img\\player2.gif\" alt=\"Player 2\" height=\"42\" width=\"42\">");
				}
				 else if(game.checkForBox(new Coordinates(i, j))){
					 sb.append("<img src=\"img\\box.jpg\" alt=\"Obstacle\" height=\"42\" width=\"42\">");
				 }
				else if (maze[i][j] == '#' && (game.isLight(p, new Coordinates(i, j))))
					sb.append("<img src=\"img\\obstacle.gif\" alt=\"Obstacle\" height=\"42\" width=\"42\">");
				else if((game.isLight(p, new Coordinates(i, j))))
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
	public String displayMaze(){
		return "DisplayMaze";
	}
	
}