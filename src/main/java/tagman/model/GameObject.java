package tagman.model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

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
	
	public boolean collides(GameObject gameObject) {
		ArrayList<Point> points = new ArrayList<>(Arrays.asList(
				new Point(gameObject.getStartX(), gameObject.getStartY()),
				new Point(gameObject.getStartX(), gameObject.getEndY()),
				new Point(gameObject.getEndX(), gameObject.getEndY()),
				new Point(gameObject.getEndX(), gameObject.getStartY())
			));
		
		for (Point point : points) {
			int x = (int) point.getX();
			int y = (int) point.getY();

			boolean pastStartX = x >= this.getStartX();
			boolean beforeEndX = x <= this.getEndX();
			boolean pastStartY = y >= this.getStartY();
			boolean beforeStartY = y <= this.getStartY();
			
			if (pastStartX && beforeEndX && pastStartY && beforeStartY) {
				return true;
			}
		}

		return false;
	}
	
	public Dimension getSize() {
		return this.size;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public int getStartX() {
		int radius = (int) size.getWidth() / 2;
		int x = (int) position.getX();
		return x - radius;
	}
	
	public int getEndX() {
		int radius = (int) size.getWidth() / 2;
		int x = (int) position.getX();
		return x + radius;
	}
	
	public int getStartY () {
		int radius = (int) size.getHeight() / 2;
		int y = (int) position.getY();
		return y - radius;
	}
	
	public int getEndY () {
		int radius = (int) size.getHeight() / 2;
		int y = (int) position.getY();
		return y + radius;
	}
	
	protected void setSize(Dimension size) {
		this.size = size;
	}
	
	protected void setPosition(Point position) {
		this.position = position;
	}

}
