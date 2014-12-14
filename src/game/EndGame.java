package game;

import player.FightPlayer;

public class EndGame {
	
	public boolean checkIfSomeoneDied(FightPlayer p1, FightPlayer p2) {
		if (p1.getHealth() == 0) {
			p2.setHasWon(true);
			return true;
		} else if (p2.getHealth() == 0) {
			p1.setHasWon(true);
			return true;
		} else {
			return false;
		}
	}
}
