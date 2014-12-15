package player;

import game.Coordinates;

public interface IPlayer {
	
	public void setFlashLight(int flashLight);
	
	public void setScore(int score);
	
	int getScore();
	
	int getFlashLight();
	
	public Coordinates getCoords();
}
