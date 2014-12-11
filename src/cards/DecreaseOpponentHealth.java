package cards;

import player.FightPlayer;
import player.IPlayer;

public class DecreaseOpponentHealth extends DespicableCard{

	private static int HEALTH_DAMAGE = 25;
	
	public DecreaseOpponentHealth(String info) {
		super(info);
	}

	@Override
	public void execute(IPlayer player) {
		((FightPlayer)player).setHealth(((FightPlayer)player).getHealth() - HEALTH_DAMAGE);
	}

}
