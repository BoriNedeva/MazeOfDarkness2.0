package cards;

import player.IPlayer;

public interface ICard {
	
	String getCardInfo();
	
	void execute(IPlayer player);
}
