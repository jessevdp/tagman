package tagman.model;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

public class Game extends Observable {
	
	public static final Dimension WORLD_SIZE = new Dimension(1000, 700);
	
	private static final ArrayList<ArrayList<GameObject>> levels = new ArrayList<>(Arrays.asList(
				new ArrayList<GameObject>(Arrays.asList(
							new Wall(0, 0, 60, 315),
							new Wall(0, 385, 60, 315),
							new Wall(940, 0, 60, 315),
							new Wall(940, 385, 60, 315),
							
							new TagMan(5, 325),
							
							new Dash(150, 2, 30),
							new Dash(250, 1, 60),
							new Dash(350, 2, 90),
							new Dash(450, 1, 45),
							new Dash(550, 2, 180),
							new Dash(650, 2, 30),
							new Dash(750, 1, 60),
							new Dash(850, 2, 90),
							new Dash(950, 1, 45)
						))
			));
	
	private ArrayList<Dash> dashes;
	private ArrayList<Wall> walls;
	private TagMan tagMan;
	
	private int currentFrame;

	public Game() {
		this.tagMan = new TagMan(5, 325);
		this.walls = new ArrayList<>();
		this.dashes = new ArrayList<>();
		
		loadLevel(0);
	}
	
	public void moveDashes() {
		if (!(dashes.size() > 0)) return;

		for (Dash dash : dashes) {
			dash.move(currentFrame);
		}
		cleanUpDashes();
		setChanged();
	}
	
	/**
	 * Remove any dashes that are no longer
	 * inside the world.
	 */
	private void cleanUpDashes() {
		Rectangle world = new Rectangle(WORLD_SIZE);
		for (int i = dashes.size() - 1; i >= 0; i--) {
			Rectangle hitbox = dashes.get(i).getHitbox();
			if (!world.intersects(hitbox)) {
				dashes.remove(i);
			}
		}
	}
	
	public void loadLevel(int level) {
		dashes.clear();
		walls.clear();
		ArrayList<GameObject> levelObjects = levels.get(level);
		
		for (GameObject gameObject : levelObjects) {
			if (gameObject instanceof Wall) {
				Wall wall = (Wall) gameObject;
				walls.add(wall);
			}
			if (gameObject instanceof Dash) {
				Dash dash = (Dash) gameObject;
				dashes.add(dash);
			}
			if (gameObject instanceof TagMan) {
				tagMan = (TagMan) gameObject;
			}
		}
	}
	
	public void increaseFrame() {
		this.currentFrame++;
	}
	
	public TagMan getTagMan() {
		return this.tagMan;
	}
	
	public ArrayList<Wall> getWalls() {
		return this.walls;
	}
	
	public ArrayList<Dash> getDashes() {
		return this.dashes;
	}
	
	/*
	 * This method needs to be made public in order for it
	 * to be called from the MainController. (Where we move
	 * TagMan.)
	 * 
	 * If we can't call this method from the MainController
	 * the observers of Game (this class) might not get
	 * notified when TagMan moves. (Unless some other event
	 * sets Game as changed. [For example Dash movement.])
	 * 
	 * This override only changes access to this method.
	 */
	@Override
	public synchronized void setChanged() {
		super.setChanged();
	}

}
