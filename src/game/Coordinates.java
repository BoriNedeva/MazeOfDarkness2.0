package game;

public class Coordinates {
	private int x;
	private int y;
	
	Coordinates(final int x, final int y){
		setX(x);
		setY(y);
	}
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	@Override
	public String toString() {
		
		return "\nx: "  + x + " y: " + y;
	}

}
