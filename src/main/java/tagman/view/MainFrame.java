package tagman.view;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import tagman.controller.InputController;
import tagman.controller.MainController;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements KeyListener {
	
	private static final String WINDOW_TITLE = "TagMan by Jesse van der Pluijm";
	
	ContentPane contentPane;
	MainController mainController;
	InputController inputController;

	public MainFrame (MainController mainController) throws HeadlessException {
		this.mainController = mainController;
		this.inputController = mainController.getInputController();
		this.contentPane = new ContentPane(mainController);
		
		setContentPane(contentPane);
		setTitle(WINDOW_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		setFocusable(true);
	    requestFocus();
	    addKeyListener(this);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		inputController.handleKeyPress(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		inputController.handleKeyRelease(e);
	}
	
	/*
	 * We need to override this method when implementing
	 * the KeyListener interface. The override does
	 * absolutely nothing.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
