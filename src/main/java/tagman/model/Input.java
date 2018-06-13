package tagman.model;

public class Input {

  private boolean arrowNorth;
  private boolean arrowSouth;
  private boolean arrowEast;

  private boolean numpadNorth;
  private boolean numpadNorthEast;
  private boolean numpadEast;
  private boolean numpadSouthEast;
  private boolean numpadSouth;

  public boolean pressedArrowNorth () {
    return arrowNorth;
  }

  public void setArrowNorth (boolean arrowNorth) {
    this.arrowNorth = arrowNorth;
  }

  public boolean pressedArrowSouth () {
    return arrowSouth;
  }

  public void setArrowSouth (boolean arrowSouth) {
    this.arrowSouth = arrowSouth;
  }

  public boolean pressedArrowEast () {
    return arrowEast;
  }

  public void setArrowEast (boolean arrowEast) {
    this.arrowEast = arrowEast;
  }

  public boolean pressedNumpadNorth () {
    return numpadNorth;
  }

  public void setNumpadNorth (boolean numpadNorth) {
    this.numpadNorth = numpadNorth;
  }

  public boolean pressedNumpadNorthEast () {
    return numpadNorthEast;
  }

  public void setNumpadNorthEast (boolean numpadNorthEast) {
    this.numpadNorthEast = numpadNorthEast;
  }

  public boolean pressedNumpadEast () {
    return numpadEast;
  }

  public void setNumpadEast (boolean numpadEast) {
    this.numpadEast = numpadEast;
  }

  public boolean pressedNumpadSouthEast () {
    return numpadSouthEast;
  }

  public void setNumpadSouthEast (boolean numpadSouthEast) {
    this.numpadSouthEast = numpadSouthEast;
  }

  public boolean pressedNumpadSouth () {
    return numpadSouth;
  }

  public void setNumpadSouth (boolean numpadSouth) {
    this.numpadSouth = numpadSouth;
  }

}
