package tagman.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import tagman.controller.TimeController;

@SuppressWarnings("serial")
public class TimeView extends JPanel implements Observer {
	
	TimeController timeController;

	public TimeView(TimeController timeController) {
		this.timeController = timeController;
		timeController.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {

	}

}
