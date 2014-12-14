package player;

public class Statistics {
	
	public static final int WINS_TO_LEVEL_UP = 3;
	public static final int MAX_LEVEL = 100;
	
	private int level;
	private int highScore;
	private int wins;
	
	@Override
	public String toString() {
		return "Statistics [level=" + level + ", highScore=" + highScore
				+ ", wins=" + wins + "]";
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
}
