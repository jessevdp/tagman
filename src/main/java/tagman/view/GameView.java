package tagman.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import tagman.controller.MainController;
import tagman.model.Game;
import tagman.view.util.Label;

@SuppressWarnings("serial")
public class GameView extends JPanel implements Observer {
	
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	private static final Dimension PREFERRED_SIZE = new Dimension(100, 200);
	
	private static final int PADDING_TOP = 10;
	private static final int PADDING_BOTTOM = 10;
	private static final int PADDING_BETWEEN = 10;
	
	Game game;
	Label scoreLabel;
	
	public GameView(MainController mainController) {
		this.game = mainController.getGame();
		game.addObserver(this);
		
		setPreferredSize(PREFERRED_SIZE);
		setBackground(BACKGROUND_COLOR);
		setBorder(BorderFactory.createEmptyBorder(PADDING_TOP, 0, PADDING_BOTTOM, 0));
		setLayout(new BorderLayout(0, PADDING_BETWEEN));
		
		this.scoreLabel = new Label("00", true);
		
		JPanel scoreView = createScoreView();
		
		this.add(scoreView, BorderLayout.NORTH);
	}
	
	private JPanel createScoreView() {
		JPanel scoreView = new JPanel();
		scoreView.setLayout(new BorderLayout());
		scoreView.setOpaque(false);
		
		JPanel scorePanel1 = new JPanel();
		scorePanel1.setOpaque(false);
		scorePanel1.add(new Label("score"));
		
		JPanel scorePanel2 = new JPanel();
		scorePanel2.setOpaque(false);
		scorePanel2.add(scoreLabel);
		
		scoreView.add(scorePanel1, BorderLayout.NORTH);
		scoreView.add(scorePanel2, BorderLayout.SOUTH);
		
		return scoreView;
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

}
