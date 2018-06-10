package tagman.model;

import java.awt.Dimension;
import java.awt.Point;

public class TagMan extends GameObject {

	public TagMan(Point position) {
		super(new Dimension(80, 80), position);
	}
	
	public TagMan(int x, int y) {
		this(new Point(x, y));
	}

}
