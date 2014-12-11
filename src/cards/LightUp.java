package cards;

import player.IPlayer;

public class LightUp extends WildCard{

	private static final int LIGHT_BONUS = 2;
	
	public LightUp(String info) {
		super(info);
	}

	@Override
	public void execute(IPlayer player) {
		player.setFlashLight(player.getFlashLight() + LIGHT_BONUS);
	}

}
