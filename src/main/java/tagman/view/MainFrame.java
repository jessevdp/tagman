package tagman.view;

import java.awt.HeadlessException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public MainFrame () throws HeadlessException {
		setTitle("TagMan by Jesse van der Pluijm");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
