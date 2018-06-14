package tagman.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class TagMan extends GameObject {

  private static final int SIZE = 50;
  private static final int MAX_SPEED = 3;

  private boolean hasFinished;
  private boolean wasHit;

  public TagMan (Point position) {
    super(position, new Dimension(SIZE, SIZE));
  }

  public TagMan (int x, int y) {
    this(new Point(x, y));
  }

  public void move (Direction direction) {
    int speed = getSpeed(direction);
    Rectangle hitbox = getHitbox();
    int x = hitbox.x + (speed * direction.getHorizontalDirection());
    int y = hitbox.y + (speed * direction.getVerticalDirection());
    setPosition(x, y);
  }

  public Rectangle getHitboxAfterMove (Direction direction) {
    Rectangle newHitbox = new Rectangle(getHitbox());
    int speed = getSpeed(direction);
    newHitbox.x += (speed * direction.getHorizontalDirection());
    newHitbox.y += (speed * direction.getVerticalDirection());
    return newHitbox;
  }

  public boolean hasFinished () {
    return this.hasFinished;
  }

  public void setFinished () {
    this.hasFinished = true;
  }

  public boolean wasHit () {
    return this.wasHit;
  }

  public void setHit () {
    this.wasHit = true;
  }

  /**
   * Applies potential modifications to the MAX_SPEED
   * to come to the actual speed. (If TagMan moves
   * diagonal
   * his speed becomes 2/3 of the MAX_SPEED.)
   * 
   * @param direction
   * @return speed
   */
  private int getSpeed (Direction direction) {
    int speed = MAX_SPEED;
    if (direction == Direction.NORTH_EAST || direction == Direction.SOUTH_EAST) {
      double factor = (2D / 3D);
      speed = (int) Math.ceil(factor * speed);
    }
    return speed;
  }

}
