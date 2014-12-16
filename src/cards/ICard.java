package cards;

import player.IPlayer;

public interface ICard {
	
	String getCardInfo(IPlayer player);
	
	void execute(IPlayer player);
}
