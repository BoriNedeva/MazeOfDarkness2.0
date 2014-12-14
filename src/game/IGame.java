package game;

import player.IPlayer;

 interface IGame {
	void changePosition();
	void placeBoxes();
	String executeWildCard(IPlayer player);
	String executeDespicableCard(IPlayer player);
	void endGame();
}
