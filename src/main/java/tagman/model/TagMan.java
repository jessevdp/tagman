package tagman.model;

import java.awt.Dimension;
import java.awt.Point;

public class TagMan extends GameObject {
	
	private static final int DEFAULT_SIZE = 50;
	
	public TagMan(Point position) {
		super(position, new Dimension(DEFAULT_SIZE, DEFAULT_SIZE));
	}
	
	public TagMan(int x, int y) {
		this(new Point(x, y));
	}

}
