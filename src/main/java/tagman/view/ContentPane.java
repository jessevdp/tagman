package tagman.view;

import java.awt.BorderLayout;

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
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoPanel.add(gameView, BorderLayout.NORTH);
		infoPanel.add(timeView, BorderLayout.SOUTH);
		
		this.add(infoPanel, BorderLayout.WEST);
		this.add(playView, BorderLayout.CENTER);
	}

}
