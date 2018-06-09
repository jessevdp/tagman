package tagman.view;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import tagman.controller.MainController;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	ContentPane contentPane;

	public MainFrame (MainController mainController) throws HeadlessException {
		this.contentPane = new ContentPane(mainController);
		
		setContentPane(contentPane);
		setTitle("TagMan by Jesse van der Pluijm");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
