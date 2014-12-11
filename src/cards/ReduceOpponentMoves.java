package cards;

import player.FightPlayer;
import player.IPlayer;

public class ReduceOpponentMoves extends DespicableCard{

	public static final int MOVES_TO_MISS = -3;
	
	public ReduceOpponentMoves(String info) {
		super(info);
	}

	@Override
	public void execute(IPlayer player) {
		((FightPlayer)player).setNumberOfMoves(MOVES_TO_MISS);
	}

}
