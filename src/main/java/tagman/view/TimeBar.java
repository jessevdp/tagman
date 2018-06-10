package tagman.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import tagman.controller.TimeController;

@SuppressWarnings("serial")
public class TimeBar extends JPanel {
	
	private static Color DEFAULT_COLOR = Color.CYAN;
	private static Color HALFWAY_DONE_COLOR = Color.ORANGE;
	private static Color ALMOST_DONE_COLOR = Color.RED;

	TimeController timeController;
	
	public TimeBar(TimeController timeController) {
		this.timeController = timeController;
		setOpaque(false);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Color barColor = calculateColor();
		g.setColor(barColor);
		
		int width = this.getWidth();
		int height = this.getHeight();

		int barHeight = calculateBarHeight(height);
		int barWidth = width / 2;
		int x = barWidth / 2;
		int y = height - barHeight;
		
		g.fillRect(x, y, barWidth, barHeight);
	}
	
	private int calculateBarHeight(int height) {		
		int amountOfSteps = calculateAmountOfSteps();
		int heightPerStep = height / amountOfSteps;
		int amountOfStepsLeft = calculateAmountOfStepsLeft();
		
		return amountOfStepsLeft * heightPerStep;
	}
	
	private Color calculateColor() {
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
