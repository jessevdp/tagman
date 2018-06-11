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
	private int startFrame;
	
	public Dash(Point position, int speed, int startFrame) {
		super(position, new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.speed = speed;
		this.startFrame = startFrame;
	}
	
	public Dash(int x, int y, int speed, int startFrame) {
		this(new Point(x, y), speed, startFrame);
	}
	
	public Dash(int x, int speed, int startFrame) {
		this(x, DEFAULT_Y_POSITION, speed, startFrame);
	}
	
	public void move(int currentFrame) {
		if (currentFrame < startFrame) return;

		Rectangle hitbox = getHitbox();
		int x = hitbox.x + (speed * DIRECTION.getHorizontalDirection());
		int y = hitbox.y + (speed * DIRECTION.getVerticalDirection());
		setPosition(x, y);
	}

}
