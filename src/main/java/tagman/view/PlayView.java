package tagman.view;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import tagman.controller.MainController;
import tagman.model.Game;

@SuppressWarnings("serial")
public class PlayView extends JPanel implements Observer {
	
	private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
	
	Game game;

	public PlayView(MainController mainController) {
		this.game = mainController.getGame();
		game.addObserver(this);
		
		setBackground(BACKGROUND_COLOR);
	}

	@Override
	public void update(Observable o, Object arg) {

	}

}
