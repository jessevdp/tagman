package tagman.model;

import java.awt.Dimension;
import java.awt.Point;

public abstract class GameObject {
	
	Dimension size;
	Point position;

	public GameObject(Dimension size, Point position) {
		this.size = size;
		this.position = position;
	}
	
	public GameObject(int width, int height, int x, int y) {
		this.size = new Dimension(width, height);
		this.position = new Point(x, y);
	}
	


	
	public Dimension getSize() {
		return this.size;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	protected void setSize(Dimension size) {
		this.size = size;
	}
	
	protected void setPosition(Point position) {
		this.position = position;
	}

}
