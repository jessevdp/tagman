package tagman.app;

import tagman.controller.TimeController;
import tagman.model.Game;
import tagman.view.MainFrame;

public class Main {

	public static void main(String[] args) {
		new MainFrame(new Game(), new TimeController());
	}

}
