package box;

import game.Coordinates;
import cards.ICard;

interface IBox {
	public void randomIdx();

	ICard getWildCard();

	ICard getDespicableCard();
	
	void addBoxCoords(Coordinates coords);
	
}
