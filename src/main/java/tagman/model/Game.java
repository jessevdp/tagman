package tagman.model;

import java.util.ArrayList;
import java.util.Observable;

public class Game extends Observable {
	
	private ArrayList<Dash> dashes;
	private ArrayList<Wall> walls;
	private TagMan tagMan;

	public Game() {
		this.tagMan = new TagMan(50, 350);
		this.walls = new ArrayList<>();
		this.dashes = new ArrayList<>();
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
