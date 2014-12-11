package cards;

import player.FightPlayer;
import player.IPlayer;

public class Knife extends UniqueCard{

	public Knife(String info) {
		super(info);
	}

	@Override
	public void execute(IPlayer player) {
		((FightPlayer)player).setHasKnife(true);
	}

}
