package cards;

import game.Game;
import player.FightPlayer;
import player.IPlayer;

public class ReduceOpponentMoves extends DespicableCard{

	public static final int MOVES_TO_MISS = -((Game.getAvailablemoves()/2)+1);
	
	public ReduceOpponentMoves(String info) {
		super(info);
	}

	@Override
	public void execute(IPlayer player) {
		((FightPlayer)player).setNumberOfMoves(MOVES_TO_MISS);
	}

}
