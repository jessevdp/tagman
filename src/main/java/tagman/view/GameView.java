package tagman.view;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import tagman.controller.MainController;
import tagman.model.Game;

@SuppressWarnings("serial")
public class GameView extends JPanel implements Observer {
	
	Game game;
	
	public GameView(MainController mainController) {
		this.game = mainController.getGame();
		game.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

}
