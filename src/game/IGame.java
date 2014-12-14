package game;

import player.IPlayer;

 interface IGame {
	void changePosition();
	void lightUp(int flashLight);
	void placeBoxes();
	String executeWildCard(IPlayer player);
	String executeDespicableCard(IPlayer player);
	void endGame();
}
