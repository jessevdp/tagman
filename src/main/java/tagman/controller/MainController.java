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
	}

	public TimeController getTimeController() {
		return this.timeController;
	}

	public Game getGame() {
		return this.game;
	}

}
