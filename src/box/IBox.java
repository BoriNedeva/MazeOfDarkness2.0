package box;

import java.util.ArrayList;

import game.Coordinates;
import cards.ICard;

interface IBox {

	ICard giveRandomWildCard();

	ICard giveRandomDespicableCard();
	
	void addBoxCoords(Coordinates coords);
	
	ArrayList<ICard> getWildCards();
	
	ArrayList<ICard> getDespicableCards();
}
