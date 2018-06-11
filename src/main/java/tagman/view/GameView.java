package tagman.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
	private static final Dimension PREFERRED_SIZE = new Dimension(100, 195);
	
	private static final int PADDING_TOP = 10;
	private static final int PADDING_BOTTOM = 10;
	private static final int PADDING_BETWEEN = 10;
	
	Game game;
	Label scoreLabel;
	Label levelLabel;
	
	public GameView(MainController mainController) {
		this.game = mainController.getGame();
		game.addObserver(this);
		
		setPreferredSize(PREFERRED_SIZE);
		setBackground(BACKGROUND_COLOR);
		setBorder(BorderFactory.createEmptyBorder(PADDING_TOP, 0, PADDING_BOTTOM, 0));
		setLayout(new BorderLayout(0, PADDING_BETWEEN));
		
		this.scoreLabel = new Label("00", true);
		this.levelLabel = new Label("00", true);
		
		JPanel scoreView = createScoreView();
		JPanel levelView = createLevelView();
		
		this.add(scoreView, BorderLayout.NORTH);
		this.add(levelView, BorderLayout.SOUTH);
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
	
	private JPanel createLevelView() {
		JPanel levelView = new JPanel();
		levelView.setLayout(new BorderLayout());
		levelView.setOpaque(false);
		
		JPanel levelPanel1 = new JPanel();
		levelPanel1.setOpaque(false);
		levelPanel1.add(new Label("level"));
		
		JPanel levelPanel2 = new JPanel();
		levelPanel2.setOpaque(false);
		levelPanel2.add(levelLabel);
		
		levelView.add(levelPanel1, BorderLayout.NORTH);
		levelView.add(levelPanel2, BorderLayout.SOUTH);
		
		return levelView;
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		scoreLabel.setText(0); // TODO implement actual value
		levelLabel.setText(0); // TODO implement actual value
	}

}
