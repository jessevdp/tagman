package tagman.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class GameObject {

  private Rectangle hitbox;

  public GameObject (Rectangle hitbox) {
    this.hitbox = hitbox;
  }

  public GameObject (Point position, Dimension dimension) {
    this.hitbox = new Rectangle(position, dimension);
  }

  public GameObject (int x, int y, int width, int height) {
    this.hitbox = new Rectangle(x, y, width, height);
  }

  public Rectangle getHitbox () {
    return this.hitbox;
  }

  protected void setPosition (Point position) {
    this.hitbox.setLocation(position);
  }

  protected void setPosition (int x, int y) {
    this.hitbox.setLocation(x, y);
  }

}
