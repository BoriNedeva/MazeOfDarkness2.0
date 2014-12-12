package player;

import game.Coordinates;

public abstract class Player implements IPlayer {

	private int flashLight;

	private Coordinates coords;
	
	private String userName;

	private int score;
	
	private User user;

	public void setFlashLight(int flashLight) {
		this.flashLight = flashLight;
	}

	public void setCoords(Coordinates coords) {
		this.coords = coords;
	}

	public void setScore(int score) {
		this.score = score;
	}

	Player(final String userName, final User user) {

		this.userName = userName;
		this.score = 0;
		this.flashLight = 1;
		this.user = user;
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

	public void moveRight(){
		getCoords().setY((getCoords().getY()+1));
	}
	public void moveLeft(){
		getCoords().setY((getCoords().getY()-1));
	}
	public void moveUp(){
		getCoords().setX(getCoords().getX()-1);
	}
	public void moveDown(){
		getCoords().setX(getCoords().getX()+1);
	}
}
