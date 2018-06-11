package tagman.model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

public class Game extends Observable {
	
	public static final Dimension WORLD_SIZE = new Dimension(1000, 700);
	
	private ArrayList<Dash> dashes;
	private ArrayList<Wall> walls;
	private TagMan tagMan;

	public Game() {
		
		this.tagMan = new TagMan(5, 325);
		this.walls = new ArrayList<>(Arrays.asList(
					new Wall(0, 0, 60, 315),
					new Wall(0, 385, 60, 315),
					new Wall(940, 0, 60, 315),
					new Wall(940, 385, 60, 315),
					new Wall(180, 270, 40, 160)
				));
		this.dashes = new ArrayList<>(Arrays.asList(
					new Dash(120, 3),
					new Dash(220, 1),
					new Dash(320, 2),
					new Dash(420, 1),
					new Dash(520, 1)
				));
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

}
