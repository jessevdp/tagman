package tagman.model;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

public class Game extends Observable {
	
	public static final Dimension WORLD_SIZE = new Dimension(1000, 700);
	public static final int FRAMES_PER_SECOND = 60;
	
	private static final ArrayList<ArrayList<GameObject>> levels = new ArrayList<>(Arrays.asList(
				new ArrayList<GameObject>(Arrays.asList(
						new Wall(0, 0, 60, 315),
						new Wall(0, 385, 60, 315),
						new Wall(940, 0, 60, 315),
						new Wall(940, 385, 60, 315),
						
						new TagMan(5, 325),
						
						new Dash(150, 2, 500),
						new Dash(250, 1, 1000),
						new Dash(350, 2, 2000),
						new Dash(450, 1, 1500),
						new Dash(550, 2, 10000),
						new Dash(650, 2, 3000),
						new Dash(750, 1, 1000),
						new Dash(850, 2, 1500),
						new Dash(950, 1, 1250)
					)),
				new ArrayList<GameObject>(Arrays.asList(
						new Wall(0, 0, 60, 315),
						new Wall(0, 385, 60, 315),
						new Wall(940, 0, 60, 315),
						new Wall(940, 385, 60, 315),
						new Wall(270, 270, 40, 160),
						
						new TagMan(5, 325),
						
						new Dash(150, 2, 500),
						new Dash(250, 1, 1000),
						new Dash(350, 2, 2000),
						new Dash(450, 1, 1500),
						new Dash(550, 2, 10000),
						new Dash(650, 2, 3000),
						new Dash(750, 1, 1000),
						new Dash(850, 2, 1500),
						new Dash(950, 1, 1250)
					))
			));
	
	private ArrayList<Dash> dashes;
	private ArrayList<Wall> walls;
	private TagMan tagMan;
	
	private int currentLevel;
	private ArrayList<Integer> score;
	
	private boolean isRunning;
	private int currentFrame;

	public Game() {
		this.tagMan = new TagMan(5, 325);
		this.walls = new ArrayList<>();
		this.dashes = new ArrayList<>();
		this.score = new ArrayList<>();
		this.currentLevel = -1;
	}
	
	public void moveDashes() {
		if (!(dashes.size() > 0)) return;

		for (Dash dash : dashes) {
			int milisecondsSinceStart = getMilisecondsSinceStart();
			dash.move(milisecondsSinceStart);
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
		
		this.currentLevel = level;
	}
	
	public int getMilisecondsSinceStart() {
		double framesPerMilisecond = (FRAMES_PER_SECOND / 1000D);
		int milisecondsSinceStart = (int) Math.floor(currentFrame / framesPerMilisecond);
		return milisecondsSinceStart;
	}
	
	public void increaseFrame() {
		this.currentFrame++;
	}
	
	public void start() {
		this.isRunning = true;
	}
	
	public void stop() {
		this.isRunning = false;
	}
	
	public boolean isRunning() {
		return this.isRunning;
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
	
	public int getCurrentLevel() {
		return this.currentLevel;
	}
	
	public boolean hasNextLevel() {
		return currentLevel <= levels.size();
	}
	
	public boolean canEndCurrentLevel() {
		if (currentLevel < 0) return true;
		boolean hasScore = (currentLevel - 1) <= score.size();
		if (isRunning || !hasScore) return false;
		return true;
	}
	
	public int getTotalScore() {
		int totalScore = 0;
		for (Integer score : this.score) {
			totalScore += score;
		}
		return totalScore;
	}
	
	public void addScore(int amount) {
		if (currentLevel < 0) return;
		score.add(amount);
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
