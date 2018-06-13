package tagman.model;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

public class Game extends Observable {

  public static final Dimension WORLD_SIZE = new Dimension(1200, 800);
  public static final int FRAMES_PER_SECOND = 60;

  public static final Rectangle FINISH = new Rectangle(1140, 365, 60, 70);

  private static final ArrayList<ArrayList<GameObject>> levels = new ArrayList<>(Arrays.asList(
      // LEVEL 1
      new ArrayList<GameObject>(Arrays.asList(
        new Wall(0, 0, 60, 365),
        new Wall(0, 435, 60, 365),
        new Wall(1140, 0, 60, 365),
        new Wall(1140, 435, 60, 365),

        new TagMan(5, 375),

        new Dash(150, 4, 0),
        new Dash(250, 4, 1000),
        new Dash(350, 2, 1000),
        new Dash(450, 1, 1500),
        new Dash(550, 4, 3000),
        new Dash(650, 2, 3000),
        new Dash(750, 1, 1000),
        new Dash(850, 2, 1500),
        new Dash(950, 1, 1250),
        new Dash(1050, 3, 5000)
      )),
      // LEVEL 2
      new ArrayList<GameObject>(Arrays.asList(
        new Wall(0, 0, 60, 365),
        new Wall(0, 435, 60, 365),
        new Wall(1140, 0, 60, 365),
        new Wall(1140, 435, 60, 365),
        new Wall(400, 275, 40, 250),

        new TagMan(5, 375),

        new Dash(150, 5, 0),
        new Dash(250, 4, 1500),
        new Dash(350, 2, 500),
        new Dash(450, 2, 1500),
        new Dash(550, 4, 2500),
        new Dash(650, 2, 1000),
        new Dash(750, 3, 1500),
        new Dash(850, 2, 1500),
        new Dash(950, 3, 2500),
        new Dash(1050, 2, 4000)
      ))
  ));

  private ArrayList<Dash> dashes;
  private ArrayList<Wall> walls;
  private TagMan tagMan;

  private int currentLevel;
  private ArrayList<Integer> score;

  private boolean isRunning;
  private int currentFrame;

  private String message;

  public Game () {
    this.tagMan = new TagMan(5, 325);
    this.walls = new ArrayList<>();
    this.dashes = new ArrayList<>();
    this.score = new ArrayList<>();
    this.currentLevel = -1;
  }

  public void moveDashes () {
    if (! (dashes.size() > 0)) return;

    for (Dash dash : dashes) {
      int milisecondsSinceStart = getMilisecondsSinceStart();
      dash.move(milisecondsSinceStart);
    }
    cleanUpDashes();
    setChanged();
  }

  /**
   * Remove any dashes that are no longer
   * inside the world.
   */
  private void cleanUpDashes () {
    Rectangle world = new Rectangle(WORLD_SIZE);
    for (int i = dashes.size() - 1; i >= 0; i--) {
      Rectangle hitbox = dashes.get(i).getHitbox();
      if (!world.intersects(hitbox)) {
        dashes.remove(i);
      }
    }
  }

  public void loadLevel (int level) {
    dashes.clear();
    walls.clear();
    ArrayList<GameObject> levelObjects = levels.get(level);

    for (GameObject gameObject : levelObjects) {
      if (gameObject instanceof Wall) {
        Wall wall = (Wall) gameObject;
        walls.add(wall);
      }
      if (gameObject instanceof Dash) {
        Dash dash = (Dash) gameObject;
        dashes.add(dash);
      }
      if (gameObject instanceof TagMan) {
        tagMan = (TagMan) gameObject;
      }
    }

    this.currentLevel = level;
    setChanged();
  }

  public int getMilisecondsSinceStart () {
    double framesPerMilisecond = (FRAMES_PER_SECOND / 1000D);
    int milisecondsSinceStart = (int) Math.floor(currentFrame / framesPerMilisecond);
    return milisecondsSinceStart;
  }

  public void increaseFrame () {
    this.currentFrame++;
  }

  public void start () {
    this.isRunning = true;
  }

  public void stop () {
    this.isRunning = false;
  }

  public boolean isRunning () {
    return this.isRunning;
  }

  public TagMan getTagMan () {
    return this.tagMan;
  }

  public ArrayList<Wall> getWalls () {
    return this.walls;
  }

  public ArrayList<Dash> getDashes () {
    return this.dashes;
  }

  public int getCurrentLevel () {
    return this.currentLevel;
  }

  public int getCurrentPrintableLevel () {
    return this.currentLevel + 1;
  }

  public boolean hasNextLevel () {
    return (currentLevel + 1) < levels.size();
  }

  public boolean canEndCurrentLevel () {
    if (currentLevel < 0) return true;
    boolean hasScore = currentLevelHasScore();
    if (isRunning || !hasScore || !tagMan.hasFinished()) return false;
    return true;
  }

  public int getTotalScore () {
    int totalScore = 0;
    for (Integer score : this.score) {
      totalScore += score;
    }
    return totalScore;
  }

  public void addScore (int amount) {
    if (currentLevel < 0) return;
    score.add(amount);
    this.setChanged();
  }

  public boolean currentLevelHasScore () {
    return (currentLevel + 1) <= score.size();
  }

  /**
   * Getter for Game.message
   * 
   * @return the message (empty String if null).
   */
  public String getMessage () {
    String message = (this.message == null) ? "" : this.message;
    return message;
  }

  public void setMessage (String message) {
    this.message = message;
    setChanged();
  }

  public void resetMessage () {
    this.message = null;
  }

  /*
   * This method needs to be made public in order for it
   * to be called from the MainController. (Where we move
   * TagMan.)
   * 
   * If we can't call this method from the MainController
   * the observers of Game (this class) might not get
   * notified when TagMan moves. (Unless some other event
   * sets Game as changed. [For example Dash movement.])
   * 
   * This override only changes access to this method.
   */
  @Override
  public synchronized void setChanged () {
    super.setChanged();
  }

}
