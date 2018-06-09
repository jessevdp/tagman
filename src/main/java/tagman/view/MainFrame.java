package tagman.view;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import tagman.controller.TimeController;
import tagman.model.Game;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	ContentPane contentPane;

	public MainFrame (Game game, TimeController timeController) throws HeadlessException {
		this.contentPane = new ContentPane(game, timeController);
		
		setTitle("TagMan by Jesse van der Pluijm");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
