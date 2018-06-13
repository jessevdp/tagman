package tagman.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import tagman.controller.TimeController;

@SuppressWarnings("serial")
public class TimeBar extends JPanel {
	
	private static final Color DEFAULT_COLOR = Color.CYAN;
	private static final Color HALFWAY_DONE_COLOR = Color.ORANGE;
	private static final Color ALMOST_DONE_COLOR = Color.RED;
	private static final Color BACKGROUND_COLOR = Color.DARK_GRAY.brighter();
	
	private static final int HEIGHT_WHEN_EMPTY = 2;

	TimeController timeController;
	
	public TimeBar(TimeController timeController) {
		this.timeController = timeController;
		setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int width = this.getWidth();
		int height = this.getHeight();
		
		int initialBarHeight = calculateInitialBarHeight(height);
		int offset = height - initialBarHeight;

		int barHeight = calculateBarHeight(height);
		int barWidth = width / 2;
		int x = barWidth / 2;
		int y = (initialBarHeight - barHeight) + offset;
		
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(x, offset, barWidth, initialBarHeight);
		
		Color barColor = calculateBarColor();
		g.setColor(barColor);
		g.fillRect(x, y, barWidth, barHeight);
	}
	
	private int calculateInitialBarHeight(int height) {
		int amountOfSteps = calculateAmountOfSteps();
		int heightPerStep = height / amountOfSteps;
		return amountOfSteps * heightPerStep;
	}
	
	private int calculateBarHeight(int height) {	
		int amountOfSteps = calculateAmountOfSteps();
		int heightPerStep = height / amountOfSteps;
		int amountOfStepsLeft = calculateAmountOfStepsLeft();

		int barHeight = amountOfStepsLeft * heightPerStep;
		if (barHeight <= 0) barHeight = HEIGHT_WHEN_EMPTY;
		
		return barHeight;
	}
	
	private Color calculateBarColor() {
		Color color = DEFAULT_COLOR;
		
		int amountOfSteps = calculateAmountOfSteps();
		int amountOfStepsLeft = calculateAmountOfStepsLeft();

		if (amountOfStepsLeft <= (amountOfSteps / 4)) {
			color = ALMOST_DONE_COLOR;
		} else if (amountOfStepsLeft <= (amountOfSteps / 2)) {
			color = HALFWAY_DONE_COLOR;
		}
		
		return color;
	}
	
	private int calculateAmountOfSteps() {
		int start = TimeController.START_VALUE;
		int end = TimeController.END_VALUE;
		int step = TimeController.getStep();
		
		return (end - start) / step;
	}
	
	private int calculateAmountOfStepsLeft() {
		int end = TimeController.END_VALUE;
		int step = TimeController.getStep();
		int current = timeController.getCurrentValue();
		
		return (end - current) / step;
	}

}
