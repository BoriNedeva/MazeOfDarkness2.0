package player;

import java.util.Random;

public class FightPlayer extends Player {
	private int numberOfMoves;
	private int health;
	boolean hasKnife;
		
	public FightPlayer(final String userName, final User user) {
		super(userName, user);
		this.health = 100;
		this.hasKnife = false;
	}
	
	int throwDice()
	{
		Random randomNumber = new Random();
	    int randomNum = randomNumber.nextInt(6) + 1;
	    this.numberOfMoves = randomNum;
		return numberOfMoves;
	};

	public boolean isHasKnife() {
		return hasKnife;
	}

	public void setHasKnife(boolean hasKnife) {
		this.hasKnife = hasKnife;
	}

	public int getNumberOfMoves() {
		return numberOfMoves;
	}

	public void setNumberOfMoves(int numberOfMoves) {
			this.numberOfMoves += numberOfMoves;
	}

	public int getHealth() {
		return health;
	}

	@Override
	public String toString() {
		return super.toString() + "FightPlayer [numberOfMoves=" + numberOfMoves + ", health="
				+ health + ", hasKnife=" + hasKnife + "]";
	}

	public void setHealth(int health) {
		this.health = health;
	}	

}
