package tagman.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import tagman.model.Game;

@SuppressWarnings("serial")
public class PlayView extends JPanel implements Observer {
	
	Game game;

	public PlayView(Game game) {
		this.game = game;
		game.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {

	}

}
