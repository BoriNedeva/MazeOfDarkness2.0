package game;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cards.ICard;
import cards.UniqueCard;
import player.FightPlayer;
import player.IPlayer;
import player.Player;
import player.UsersDAO;
import box.Box;
import box.FightBox;

public  class Game implements IGame {
	private static final int NUMBER_OF_BOXES=10;
	
	private Player playerOne;
	private Player playerTwo;
	private Box box ;
	private static final int availableMoves = 5;
	
	private Maze maze;
	
	public Game(Player playerOne, Player playerTwo, Box box,Maze maze ) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.box = box;
		this.maze = maze;
		
	}
	public Box getBox() {
		return box;
	}
	public void placeBoxes() {
		Random rand = new  Random();
		int idx = rand.nextInt(maze.getEmptyFieldCoords().size());
		int x = maze.getEmptyFieldCoords().get(idx).getX();
		int y = maze.getEmptyFieldCoords().get(idx).getY();
		Coordinates coordsForBox = new Coordinates(x, y);
		box.addBoxCoords(coordsForBox);
		for(int i=1;i<=NUMBER_OF_BOXES;i++){
			while(box.getBoxCoords().contains(coordsForBox)){
				 idx = rand.nextInt(maze.getEmptyFieldCoords().size());
				 x = maze.getEmptyFieldCoords().get(idx).getX();
				 y = maze.getEmptyFieldCoords().get(idx).getY();
				 coordsForBox = new Coordinates(x, y);	
			}
			box.addBoxCoords(coordsForBox);
		}
	}
	
	public void changePosition() {
		// TODO Auto-generated method stub
		
	}

	public void lightUp(int flashLight) {
		// TODO Auto-generated method stub
		
	}
	
	public Maze getMaze() {
		return maze;
	}
	public Player getPlayerOne() {
	
		return playerOne;
	}
	
	public Player getPlayerTwo() {
		
		return playerTwo;
	}
	public boolean checkForBox(Coordinates coords){
		for (int i = 0; i < box.getBoxCoords().size(); i++) {
			if(coords.equals(box.getBoxCoords().get(i)))
				return true;
		}
		return false;
	}
	public boolean isLight(Player p,Coordinates c){
		int x = p.getCoords().getX();
		int y = p.getCoords().getY();
		int light = p.getFlashLight();

		for(int i = light; i>0 ;i--){
			for (int j = light; j>0; j--) {
				if(c.equals(new Coordinates(x+i, y+j))||
					c.equals(new Coordinates(x-i, y-j))||
					c.equals(new Coordinates(x-i, y+j))||
					c.equals(new Coordinates(x+i, y-j))||
					c.equals(new Coordinates(x+i, y))||
					c.equals(new Coordinates(x-i, y))||
					c.equals(new Coordinates(x, y+j))||
					c.equals(new Coordinates(x, y-j))){
					return true;
				}
			}
		}
		return false;
	}
	public String executeWildCard(IPlayer player)
	{
			ICard card = this.box.giveRandomWildCard();
			card.execute(player);
			if(card instanceof UniqueCard){
				this.box.getWildCards().remove(card);
			}
			this.box.getBoxCoords().remove(player.getCoords());
			return card.getCardInfo(player);
	}
	
	public String executeDespicableCard(IPlayer player)
	{
			ICard card = this.box.giveRandomDespicableCard() ;
			this.box.getBoxCoords().remove(player.getCoords());
			
			if(player.getCoords().equals(this.playerOne.getCoords()))
			{
				card.execute(this.playerTwo);
				return card.getCardInfo(playerTwo);
			}
			else{
				card.execute(this.playerOne);
				return card.getCardInfo(playerOne);
		}

	}
	@Override
	public void endGame() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		UsersDAO usersDao = context.getBean("usersDAO", UsersDAO.class);
		usersDao.updateStatistics(this.playerOne);
		usersDao.updateStatistics(this.playerTwo);
	}
	public boolean checkIfPlayerCanKill()
	{
		if(playerOne.getCoords().equals(playerTwo.getCoords()))
		{
			if(((FightPlayer)playerOne).isHasKnife())
			{
				((FightPlayer)playerTwo).setHealth(0);
				return true;
			}
			else if(((FightPlayer)playerTwo).isHasKnife())
			{
				((FightPlayer)playerOne).setHealth(0);
				return true;
			}
		}
		return false;
	}
	
	public Player checkForWinner() {
		if (((FightPlayer)playerOne).getHealth() == 0) {
			playerTwo.setHasWon(true);
			return playerTwo;
		} else if (((FightPlayer)playerTwo).getHealth() == 0) {
			playerOne.setHasWon(true);
			return playerOne;
		} else {
			return null;
		}
	}
	public static int getAvailablemoves() {
		return availableMoves;
	}	
}

