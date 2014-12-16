package cards;

import player.IPlayer;

public abstract class Card implements ICard{
	private final String info;
	
	public Card(final String info) {
		this.info = info;
	}

	@Override
	public String getCardInfo(IPlayer player) {
		return player.getUserName() + info;
	}
}
