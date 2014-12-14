package game;

public class Coordinates {
	private int x;
	private int y;
	
	public Coordinates(final int x, final int y){
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coordinates){
			Coordinates c = (Coordinates)obj;
			if(this.x==c.getX() && this.y==c.getY())
				return true;
		}
		return false;
	}

}
