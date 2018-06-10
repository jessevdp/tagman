package tagman.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import tagman.controller.MainController;
import tagman.controller.TimeController;
import tagman.view.util.Label;

@SuppressWarnings("serial")
public class TimeView extends JPanel implements Observer {
	
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	private static final Dimension PREFERRED_SIZE = new Dimension(100, 500);

	private static final int PADDING_TOP = 20;
	private static final int PADDING_BOTTOM = 10;
	private static final int PADDING_BETWEEN = 10;
	
	TimeController timeController;
	Label timeLabel;
	TimeBar timeBar;

	public TimeView(MainController mainController) {
		this.timeController = mainController.getTimeController();
		timeController.addObserver(this);
		
		setPreferredSize(PREFERRED_SIZE);
		setBackground(BACKGROUND_COLOR);
		setBorder(BorderFactory.createEmptyBorder(PADDING_TOP, 0, PADDING_BOTTOM, 0));
		this.setLayout(new BorderLayout(0, PADDING_BETWEEN));
		
		this.timeLabel = new Label(timeController.getCurrentValue(), true);
		JPanel timePanel = new JPanel();
		timePanel.setOpaque(false);
		timePanel.add(timeLabel);
		this.add(timePanel, BorderLayout.NORTH);
		
		this.timeBar = new TimeBar(timeController);
		this.add(timeBar, BorderLayout.CENTER);
		
		JPanel secondsPanel = new JPanel();
		secondsPanel.setOpaque(false);
		secondsPanel.add(new Label("seconds"));
		this.add(secondsPanel, BorderLayout.SOUTH);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		timeLabel.setText(timeController.getCurrentValue());
	}

}
