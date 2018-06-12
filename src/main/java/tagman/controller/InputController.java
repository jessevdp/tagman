package tagman.controller;

import java.awt.event.KeyEvent;

import tagman.model.Direction;
import tagman.model.Input;

public class InputController {
	
	private Input input;

	public InputController() {
		this.input = new Input();
	}
	
	public void handleKeyPress(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			input.setArrowNorth(true);
			break;
		case KeyEvent.VK_RIGHT:
			input.setArrowEast(true);
			break;
		case KeyEvent.VK_DOWN:
			input.setArrowSouth(true);
			break;
		case KeyEvent.VK_NUMPAD8:
			input.setNumpadNorth(true);
			break;
		case KeyEvent.VK_NUMPAD9:
			input.setNumpadNorthEast(true);
			break;
		case KeyEvent.VK_NUMPAD6:
			input.setNumpadEast(true);
			break;
		case KeyEvent.VK_NUMPAD3:
			input.setNumpadSouthEast(true);
			break;
		case KeyEvent.VK_NUMPAD2:
			input.setNumpadSouth(true);
			break;
		default:
			break;
		}

	}
	
	public void handleKeyRelease(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			input.setArrowNorth(false);
			break;
		case KeyEvent.VK_RIGHT:
			input.setArrowEast(false);
			break;
		case KeyEvent.VK_DOWN:
			input.setArrowSouth(false);
			break;
		case KeyEvent.VK_NUMPAD8:
			input.setNumpadNorth(false);
			break;
		case KeyEvent.VK_NUMPAD9:
			input.setNumpadNorthEast(false);
			break;
		case KeyEvent.VK_NUMPAD6:
			input.setNumpadEast(false);
			break;
		case KeyEvent.VK_NUMPAD3:
			input.setNumpadSouthEast(false);
			break;
		case KeyEvent.VK_NUMPAD2:
			input.setNumpadSouth(false);
			break;
		default:
			break;
		}
		
	}
	
	public void handleKeyType(KeyEvent e) {
		
	}

}
