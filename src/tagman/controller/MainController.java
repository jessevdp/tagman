package tagman.controller;

import java.awt.Rectangle;
import java.util.ArrayList;

import tagman.model.Dash;
import tagman.model.Direction;
import tagman.model.Game;
import tagman.model.Messages;
import tagman.model.TagMan;
import tagman.model.Wall;
import tagman.view.MainFrame;

public class MainController {

  @SuppressWarnings("unused")
  private MainFrame mainFrame;
  private TimeController timeController;
  private InputController inputController;
  private SoundController soundController;

  Game game;

  public MainController () {
    this.timeController = new TimeController();
    this.inputController = new InputController(this);
    this.soundController = new SoundController();
    this.game = new Game();
    this.mainFrame = new MainFrame(this);

    nextLevel();
    int currentLevel = game.getCurrentPrintableLevel();
    game.setMessage(Messages.createWelcomeMessage(currentLevel));
    game.notifyObservers();
  }

  private void startGameThread () {
    int milisecondsPerFrame = (1000 / Game.FRAMES_PER_SECOND);

    Runnable gameRunnable = () -> {
      while (game.isRunning()) {
        long startTime = System.currentTimeMillis();
        processFrame();
        long endTime = System.currentTimeMillis();
        long timeToProcess = endTime - startTime;

        long milisecondsToSleep = milisecondsPerFrame - timeToProcess;
        if (milisecondsToSleep < 0) milisecondsToSleep = 0;

        try {
          Thread.sleep(milisecondsToSleep);
        } catch (InterruptedException e) {
        }
      }
    };

    new Thread(gameRunnable).start();
    ;
  }

  private void processFrame () {
    game.moveDashes();
    moveTagMan(inputController.getDirection());

    boolean isHit = checkDashCollision();
    if (isHit) {
      game.getTagMan().setHit();
      endLevel();
      soundController.playHit();
    }

    boolean hasFinished = checkFinished();
    if (hasFinished) {
      game.getTagMan().setFinished();
      endLevel();
      soundController.playFinish();
    }

    game.increaseFrame();
    game.notifyObservers();
  }

  private void moveTagMan (Direction direction) {
    TagMan tagMan = game.getTagMan();
    Rectangle newLocation = tagMan.getHitboxAfterMove(direction);

    Rectangle world = new Rectangle(Game.WORLD_SIZE);
    if (!world.contains(newLocation)) {
      return;
    }

    for (Wall wall : game.getWalls()) {
      Rectangle wallHitbox = wall.getHitbox();
      if (wallHitbox.intersects(newLocation)) {
        return;
      }
    }

    tagMan.move(direction);
    game.setChanged();
  }

  private boolean checkDashCollision () {
    Rectangle tagMan = game.getTagMan().getHitbox();
    ArrayList<Dash> dashes = game.getDashes();
    for (Dash dash : dashes) {
      Rectangle hitbox = dash.getHitbox();
      if (hitbox.intersects(tagMan)) {
        return true;
      }
    }

    return false;
  }

  private boolean checkFinished () {
    Rectangle tagMan = game.getTagMan().getHitbox();
    return Game.FINISH.contains(tagMan);
  }

  private void endLevel () {
    game.stop();
    timeController.stop();
    if (game.getTagMan().hasFinished()) {
      int score = timeController.getCurrentValue();
      game.addScore(score);
      game.setMessage(Messages.createEndLevelMessage(score));
    } else {
      game.addScore(0);
      game.setMessage(Messages.createEndGameMessage(false, game.getTotalScore()));
    }
    game.notifyObservers();
  }

  public void startLevel () {
    if (game.isRunning() || game.currentLevelHasScore()) return;

    game.resetMessage();
    game.start();
    timeController.reset();
    timeController.start();
    startGameThread();
  }

  public void nextLevel () {
    if (!game.canEndCurrentLevel()) return;
    if (game.hasNextLevel()) {
      int currentLevel = game.getCurrentLevel();
      game.loadLevel(currentLevel + 1);
      game.resetFrames();
      timeController.reset();
      game.setMessage(Messages.createStartLevelMessage(game.getCurrentPrintableLevel()));
    } else {
      game.setMessage(Messages.createEndGameMessage(true, game.getTotalScore()));
    }
    game.notifyObservers();
  }

  public TimeController getTimeController () {
    return this.timeController;
  }

  public InputController getInputController () {
    return this.inputController;
  }

  public Game getGame () {
    return this.game;
  }

}
