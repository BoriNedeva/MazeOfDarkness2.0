package cards;

import game.Game;
import player.*;

public class Trap extends DespicableCard{

public static final int ALL_MOVES_TO_MISS = -Game.getAvailablemoves();
	
	public Trap(String info) {
		super(info);
	}

	@Override
	public void execute(IPlayer player) {
		((FightPlayer)player).setNumberOfMoves(ALL_MOVES_TO_MISS);
	}

}
