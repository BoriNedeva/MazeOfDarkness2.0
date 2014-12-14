package cards;

import player.FightPlayer;
import player.IPlayer;

public class Trap extends DespicableCard{

	public static final int ALL_MOVES_TO_MISS = -5;
	
	public Trap(String info) {
		super(info);
	}

	@Override
	public void execute(IPlayer player) {
		((FightPlayer)player).setNumberOfMoves(ALL_MOVES_TO_MISS);
	}

}
