package tagman.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import tagman.controller.MainController;

@SuppressWarnings("serial")
public class ContentPane extends JPanel {
	
	private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
	private static final int PADDING_BETWEEN = 5;
	
	PlayView playView;
	GameView gameView;
	TimeView timeView;

	public ContentPane(MainController mainController) {
		this.playView = new PlayView(mainController);
		this.gameView = new GameView(mainController);
		this.timeView = new TimeView(mainController);
		
		setBackground(BACKGROUND_COLOR);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout(PADDING_BETWEEN, PADDING_BETWEEN));
		infoPanel.setOpaque(false);
		infoPanel.add(gameView, BorderLayout.NORTH);
		infoPanel.add(timeView, BorderLayout.CENTER);
		
		this.add(infoPanel, BorderLayout.WEST);
		this.add(playView, BorderLayout.CENTER);
	}

}
