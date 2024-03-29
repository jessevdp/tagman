package tagman.controller;

import java.awt.event.KeyEvent;

import tagman.model.Direction;
import tagman.model.Input;

public class InputController {

  private MainController mainController;
  private Input input;

  public InputController (MainController mainController) {
    this.mainController = mainController;
    this.input = new Input();
  }

  public Direction getDirection () {
    
    boolean pressedOpposite = (
          (input.pressedArrowNorth() && input.pressedArrowSouth())
          || (input.pressedNumpadNorth() && input.pressedNumpadSouth())
          || (input.pressedNumpadNorthEast() && input.pressedNumpadSouthEast())
        );
    
    if (pressedOpposite) {
      return Direction.NONE;
    }

    if ((input.pressedArrowNorth() && input.pressedArrowEast()) || input.pressedNumpadNorthEast()) {
      return Direction.NORTH_EAST;
    } else if ( (input.pressedArrowSouth() && input.pressedArrowEast()) || input.pressedNumpadSouthEast()) {
      return Direction.SOUTH_EAST;
    } else if (input.pressedArrowNorth() || input.pressedNumpadNorth()) {
      return Direction.NORTH;
    } else if (input.pressedArrowEast() || input.pressedNumpadEast()) {
      return Direction.EAST;
    } else if (input.pressedArrowSouth() || input.pressedNumpadSouth()) {
      return Direction.SOUTH;
    } else {
      return Direction.NONE;
    }
  }

  public void handleKeyPress (KeyEvent e) {

    switch (e.getKeyCode()) {
    case KeyEvent.VK_ESCAPE:
      System.exit(0);
      break;
    case KeyEvent.VK_S:
      mainController.startLevel();
      break;
    case KeyEvent.VK_L:
      mainController.nextLevel();
      break;

    // MOVEMENT KEYS:
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

  public void handleKeyRelease (KeyEvent e) {

    switch (e.getKeyCode()) {
    // MOVEMENT KEYS:
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

}
