package cards;

import player.FightPlayer;
import player.IPlayer;

public class Trap extends DespicableCard{

	public Trap(String info) {
		super(info);
	}

	@Override
	public void execute(IPlayer player) {
		((FightPlayer)player).setNumberOfMoves(0);
	}

}
