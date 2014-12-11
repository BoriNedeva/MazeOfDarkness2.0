package game;

public class Coordinates {
	private int x;
	private int y;
	
	Coordinates(final int x, final int y){
		setX(x);
		setY(y);
	}
	void setX(int x){
		this.x = x;
	}
	
	void setY(int y){
		this.y = y;
	}
	
	int getX(){
		return x;
	}
	
	int getY(){
		return y;
	}

	@Override
	public String toString() {
		
		return "\nx: "  + x + " y: " + y;
	}

}
