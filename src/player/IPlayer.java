package player;

import game.Coordinates;

public interface IPlayer {
	
	void setFlashLight(int flashLight);
	
	void setScore(int score);
	
	int getScore();
	
	int getFlashLight();
	
	Coordinates getCoords();

	void setCoords(Coordinates coords);

	boolean isHasWon();

	void setHasWon(boolean hasWon);

	User getUser();

	String getUserName();

	public void move(int x, int y);

}