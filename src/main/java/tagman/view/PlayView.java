package tagman.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import tagman.controller.MainController;
import tagman.model.Dash;
import tagman.model.Game;
import tagman.model.Wall;

@SuppressWarnings("serial")
public class PlayView extends JPanel implements Observer {
	
	private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
	private static final Color WALL_COLOR = Color.DARK_GRAY.darker().darker();
	private static final Color DASH_COLOR = Color.RED;
	
	Game game;

	public PlayView(MainController mainController) {
		this.game = mainController.getGame();
		game.addObserver(this);

		setPreferredSize(Game.WORLD_SIZE);
		setBackground(BACKGROUND_COLOR);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintWalls(g);
		paintDashes(g);
		TagManPainterPlain.paint(g, game.getTagMan());
	}
	
	private void paintWalls(Graphics g) {
		ArrayList<Wall> walls = game.getWalls();
		g.setColor(WALL_COLOR);
		for (Wall wall : walls) {
			Rectangle hitbox = wall.getHitbox();
			g.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		}
	}
	
	private void paintDashes(Graphics g) {
		ArrayList<Dash> dashes = game.getDashes();
		g.setColor(DASH_COLOR);
		for (Dash dash : dashes) {
			Rectangle hitbox = dash.getHitbox();
			g.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		}
	}

}
