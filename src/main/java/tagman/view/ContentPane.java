package tagman.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import tagman.controller.MainController;

@SuppressWarnings("serial")
public class ContentPane extends JPanel {
	
	PlayView playView;
	GameView gameView;
	TimeView timeView;

	public ContentPane(MainController mainController) {
		this.playView = new PlayView(mainController);
		this.gameView = new GameView(mainController);
		this.timeView = new TimeView(mainController);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoPanel.add(gameView, BorderLayout.NORTH);
		infoPanel.add(timeView, BorderLayout.SOUTH);
		
		this.add(infoPanel, BorderLayout.WEST);
		this.add(playView, BorderLayout.CENTER);
	}

}
