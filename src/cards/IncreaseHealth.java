package cards;

import player.FightPlayer;
import player.IPlayer;

public class IncreaseHealth extends WildCard{

	private static final int HEALTH_BONUS = 25;
	
	public IncreaseHealth(String info) {
		super(info);
	}

	@Override
	public void execute(IPlayer player) {
		((FightPlayer)player).setHealth(((FightPlayer)player).getHealth() + HEALTH_BONUS);
	}

}
