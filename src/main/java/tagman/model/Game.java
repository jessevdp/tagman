package tagman.model;

import java.util.ArrayList;
import java.util.Observable;

public class Game extends Observable {
	
	private ArrayList<Dash> dashes;
	private ArrayList<Wall> walls;
	private TagMan tagMan;

	public Game () {
		this.dashes = new ArrayList<>();
		this.walls = new ArrayList<>();
		this.tagMan = new TagMan(50, 350);
	}

}
