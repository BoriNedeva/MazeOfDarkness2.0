package player;

import game.Coordinates;

public abstract class Player implements IPlayer {

	private int flashLight;

	private Coordinates coords;
	
	private String userName;

	private int score;
	
	private User user;
	
	private boolean hasWon;
	
	private boolean hasLose;
	
	Player(final String userName, final User user) {

		this.userName = userName;
		this.score = 150;
		this.flashLight = 1;
		this.hasWon = false;
		this.user = user;
	}

	
	public boolean isHasLose() {
			return hasLose;
		}
		
	public void setHasLose(boolean hasLose) {
		 this.hasLose = hasLose;
		}
	
	public void setFlashLight(int flashLight) {
		this.flashLight = flashLight;
	}

	public void setCoords(Coordinates coords) {
		this.coords = coords;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isHasWon() {
		return hasWon;
	}

	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}

	public User getUser() {
		return user;
	}

	public int getFlashLight() {
		return flashLight;
	}

	public Coordinates getCoords() {
		return coords;
	}

	public String getUserName() {
		return userName;
	}

	public int getScore() {
		return score;
	}

	public void move(int x, int y)
	{
		coords.setX(x);
		coords.setY(y);
		this.setScore(this.score - 1);
	}

	@Override
	public String toString() {
		return "Player [flashLight=" + flashLight + ", coords=" + coords
				+ ", userName=" + userName + ", score=" + score + ", user="
				+ user + ", hasWon=" + hasWon + "]";
	}
}
