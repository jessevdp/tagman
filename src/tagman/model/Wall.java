package tagman.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class Wall extends GameObject {

  public Wall (Rectangle hitbox) {
    super(hitbox);
  }

  public Wall (Dimension size, Point position) {
    super(position, size);
  }

  public Wall (int x, int y, int width, int height) {
    super(x, y, width, height);
  }

}
