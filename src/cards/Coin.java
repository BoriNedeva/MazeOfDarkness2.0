package cards;

import player.IPlayer;

public class Coin extends WildCard{
	
	private static final int SCORE_BONUS = 20;

	public Coin(String info) {
		super(info);
	}

	@Override
	public void execute(IPlayer player) {
			player.setScore(player.getScore() + SCORE_BONUS);
	}

}
