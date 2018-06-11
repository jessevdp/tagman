package tagman.model;

public enum Direction {
	NONE(0, 0),
	NORTH(0, -1),
	NORTH_EAST(1, -1),
	EAST(1, 0),
	SOUTH_EAST(1, 1),
	SOUTH(0, 1);
	
	private int horizontalDirection;
	private int verticalDirection;
	
	private Direction (int horizontalDirection, int verticalDirection) {
		this.horizontalDirection = horizontalDirection;
		this.verticalDirection = verticalDirection;
	}
	
	public int getHorizontalDirection() {
		return horizontalDirection;
	}
	
	public int getVerticalDirection() {
		return verticalDirection;
	}
}
