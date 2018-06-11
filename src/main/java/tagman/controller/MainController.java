package tagman.controller;

import tagman.model.Game;
import tagman.view.MainFrame;

public class MainController {

	MainFrame mainFrame;
	TimeController timeController;
	Game game;

	public MainController() {
		this.timeController = new TimeController();
		this.game = new Game();
		this.mainFrame = new MainFrame(this);
		
		timeController.start();
		
		new Thread(() -> {
			while (true) {
				game.moveDashes();
				game.increaseFrame();
				game.notifyObservers();
				try {
					Thread.sleep(1000 / 60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public TimeController getTimeController() {
		return this.timeController;
	}

	public Game getGame() {
		return this.game;
	}

}
