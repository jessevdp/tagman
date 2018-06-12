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
	private int startTime;
	
	/**
	 * Constructor for Dash.
	 * 
	 * @param x The X position that the dash should start at. (Y defaults to 0.)
	 * @param speed The amount of pixels a dash should move every frame.
	 * @param startTime The time at which the dash should start moving (milliseconds).
	 */
	public Dash(int x, int speed, int startTime) {
		this(x, DEFAULT_Y_POSITION, speed, startTime);
	}
	
	private Dash(int x, int y, int speed, int startTime) {
		this(new Point(x, y), speed, startTime);
	}
	
	private Dash(Point position, int speed, int startTime) {
		super(position, new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.speed = speed;
		this.startTime = startTime;
	}
	
	/**
	 * Move the Dash to it's next position.
	 * 
	 * @param currentTime The amount of milliseconds elapsed since the start of the game.
	 */
	public void move(int currentTime) {
		if (currentTime < startTime) return;

		Rectangle hitbox = getHitbox();
		int x = hitbox.x + (speed * DIRECTION.getHorizontalDirection());
		int y = hitbox.y + (speed * DIRECTION.getVerticalDirection());
		setPosition(x, y);
	}

}
