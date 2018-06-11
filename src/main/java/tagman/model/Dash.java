package tagman.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class Dash extends GameObject {
	
	private static final int DEFAULT_WIDTH = 10;
	private static final int DEFAULT_HEIGHT = 40;
	private static final int DEFAULT_Y_POSITION = 0;

	private static final Direction DIRECTION = Direction.SOUTH;
	
	private int speed;
	
	public Dash(Point position, int speed) {
		super(position, new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.speed = speed;
	}
	
	public Dash(int x, int y, int speed) {
		this(new Point(x, y), speed);
	}
	
	public Dash(int x, int speed) {
		this(x, DEFAULT_Y_POSITION, speed);
	}
	
	public void move() {
		Rectangle hitbox = getHitbox();
		
		int x = hitbox.x + (speed * DIRECTION.getHorizontalDirection());
		int y = hitbox.y + (speed * DIRECTION.getVerticalDirection());
		setPosition(x, y);
	}

}
