package tagman.controller;

import java.awt.Rectangle;
import java.util.ArrayList;

import tagman.model.Dash;
import tagman.model.Direction;
import tagman.model.Game;
import tagman.model.TagMan;
import tagman.model.Wall;
import tagman.view.MainFrame;

public class MainController {

	MainFrame mainFrame;
	TimeController timeController;
	InputController inputController;
	
	Game game;

	public MainController() {
		this.timeController = new TimeController();
		this.inputController = new InputController();
		this.game = new Game();
		this.mainFrame = new MainFrame(this);
		
		timeController.start();
		nextLevel();
		game.start();
		startGameThread();
	}
	
	private void startGameThread() {
		int milisecondsPerFrame = (1000 / Game.FRAMES_PER_SECOND);
		
		Runnable gameRunnable = () -> {
			while (game.isRunning()) {
				long startTime = System.currentTimeMillis();
				processFrame();
				long endTime = System.currentTimeMillis();
				long timeToProcess = endTime - startTime;

				long milisecondsToSleep = milisecondsPerFrame - timeToProcess;
				if (milisecondsToSleep < 0) milisecondsToSleep = 0;
				
				try {
					Thread.sleep(milisecondsToSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		new Thread(gameRunnable).start();;
	}
	
	private void processFrame() {
		game.moveDashes();
		moveTagMan(inputController.getDirection());
		
		boolean isHit = checkDashCollision();
		if (isHit) {
			endGame(false);
		}
		
		game.increaseFrame();
		game.notifyObservers();
	}
	
	private void moveTagMan(Direction direction) {
		TagMan tagMan = game.getTagMan();
		Rectangle newLocation = tagMan.getHitboxAfterMove(direction);
		
		Rectangle world = new Rectangle(Game.WORLD_SIZE);
		if (!world.contains(newLocation)) {
			return;
		}
		
		for (Wall wall : game.getWalls()) {
			Rectangle wallHitbox = wall.getHitbox();
			if (wallHitbox.intersects(newLocation)) {
				return;
			}
		}
		
		tagMan.move(direction);
		game.setChanged();
	}
	
	private boolean checkDashCollision() {
		Rectangle tagMan = game.getTagMan().getHitbox();
		ArrayList<Dash> dashes = game.getDashes();
		for (Dash dash : dashes) {
			Rectangle hitbox = dash.getHitbox();
			if (hitbox.intersects(tagMan)) {
				return true;
			}
		}
		
		return false;
	}
	
	private void endGame(boolean won) {
		
	}
	
	private void nextLevel() {
		if (game.isRunning()) return;
		int currentLevel = game.getCurrentLevel();
		game.loadLevel(currentLevel + 1);
	}

	public TimeController getTimeController() {
		return this.timeController;
	}
	
	public InputController getInputController() {
		return this.inputController;
	}

	public Game getGame() {
		return this.game;
	}

}
