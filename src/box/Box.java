package box;

import game.Coordinates;

import java.util.ArrayList;

import cards.DespicableCard;
import cards.ICard;
import cards.WildCard;

public abstract class Box implements IBox {

	private ArrayList<ICard> wildCards;

	private ArrayList<ICard> despicableCards;
	
	private ArrayList<Coordinates> boxCoords;

	public ArrayList<Coordinates> getBoxCoords() {
		return boxCoords;
	}

	public void setWildCards(ArrayList<ICard> wildCards) {
		this.wildCards = wildCards;
	}

	public void setDespicableCards(ArrayList<ICard> despicableCards) {
		this.despicableCards = despicableCards;
	}
	
	public ArrayList<ICard> getWildCards() {
		return wildCards;
	}

	public ArrayList<ICard> getDespicableCards() {
		return despicableCards;
	}

	public void addBoxCoords(Coordinates coord) {
		boxCoords.add(coord);
	}
}

