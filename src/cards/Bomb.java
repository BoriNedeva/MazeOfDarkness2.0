package cards;

import player.FightPlayer;
import player.IPlayer;

public class Bomb extends DespicableCard{

	private static int HEALTH_DAMAGE = 50;
	
	public Bomb(String info) {
		super(info);
	}

	@Override
	public void execute(IPlayer player) {
		((FightPlayer)player).setHealth(((FightPlayer)player).getHealth() - HEALTH_DAMAGE);
	}

}
