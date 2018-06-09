package tagman.view;

import javax.swing.JPanel;

import tagman.controller.TimeController;
import tagman.model.Game;

@SuppressWarnings("serial")
public class ContentPane extends JPanel {
	
	PlayView playView;
	GameView gameView;
	TimeView timeView;

	public ContentPane(Game game, TimeController timeController) {
		this.playView = new PlayView(game);
		this.gameView = new GameView(game);
		this.timeView = new TimeView(timeController);
	}

}
