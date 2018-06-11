package tagman.model;

import java.awt.Dimension;
import java.awt.Point;

public class Dash extends GameObject {
	
	private static final int DEFAULT_WIDTH = 20;
	private static final int DEFAULT_HEIGHT = 80;
	private static final int DEFAULT_Y_POSITION = 0;
	
	public Dash(Point position) {
		super(position, new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
	}
	
	public Dash(int x, int y) {
		this(new Point(x, y));
	}
	
	public Dash(int x) {
		this(x, DEFAULT_Y_POSITION);
	}

}
