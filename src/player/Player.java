package player;

import game.Coordinates;

public abstract class Player implements IPlayer {

	private int flashLight;

	private Coordinates coords;

	public void setFlashLight(int flashLight) {
		this.flashLight = flashLight;
	}

	public void setCoords(Coordinates coords) {
		this.coords = coords;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private String userName;

	private int score;

	Player(final String userName) {

		this.userName = userName;
		this.score = 0;
		this.flashLight = 1;
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

}
