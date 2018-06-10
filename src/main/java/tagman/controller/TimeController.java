package tagman.controller;

import java.util.Observable;

public class TimeController extends Observable implements Runnable {

	public static final int START_VALUE = 30;
	public static final int END_VALUE = 0;
	public static final int STEP_AMOUNT = 1;
	
	private static final int STEP_TIME = 1000;
	
	private int currentValue;
	private boolean playing;

	public TimeController() {
		this.playing = false;
		reset();
	}
	
	public void start() {
		playing = true;
		new Thread(this).start();
	}
	
	public void stop() {
		playing = false;
	}
	
	public void reset() {
		setCurrentValue(START_VALUE);
	}
	
	@Override
	public void run() {
		while (playing) {
			try {
				Thread.sleep(STEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executeStep();
		}
	}
	
	private void executeStep() {
		int step = getStep();
		int newValue = currentValue + step;
		
		if (step > 0 && newValue > END_VALUE) return;
		if (step < 0 && newValue < END_VALUE) return;
		
		setCurrentValue(newValue);
	}
	
	private void setCurrentValue(int newValue) {
		this.currentValue = newValue;
		setChanged();
		notifyObservers();
	}
	
	public int getCurrentValue() {
		return this.currentValue;
	}
	
	/**
	 * Get the actual step value by applying a direction to it. If the start value
	 * is higher than the end value we need to count down. Else we need to count up.
	 * 
	 * @return actual step value, including direction.
	 */
	private int getStep () {
		int step = Math.abs(STEP_AMOUNT);
		if (START_VALUE > END_VALUE) {
			step = (step * -1);
		}
		return step;
	}

}
