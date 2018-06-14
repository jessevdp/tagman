package tagman.view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import tagman.model.TagMan;

public class TagManPainterAnimated implements TagManPainter {

  private static final String PATH_TO_PICTURES = "/resources/player_pictures/";

  private BufferedImage finished;
  private BufferedImage hit;

  private ArrayList<BufferedImage> stages;
  private int currentStage;
  
  private Rectangle lastHitBox;

  public TagManPainterAnimated (TagMan tagMan) {
    this.finished = loadImage(PATH_TO_PICTURES + "finished.png");
    this.hit = loadImage(PATH_TO_PICTURES + "hit.png");
    
    this.lastHitBox = new Rectangle(tagMan.getHitbox());
    
    this.currentStage = 0;
    this.stages = new ArrayList<>(Arrays.asList(
        loadImage(PATH_TO_PICTURES + "step_1.png"),
        loadImage(PATH_TO_PICTURES + "step_2.png"),
        loadImage(PATH_TO_PICTURES + "step_3.png"),
        loadImage(PATH_TO_PICTURES + "step_4.png"),
        loadImage(PATH_TO_PICTURES + "step_5.png"),
        loadImage(PATH_TO_PICTURES + "step_6.png")
    ));
  }

  @Override
  public void paint (Graphics g, TagMan tagMan) {    
    Rectangle hitbox = tagMan.getHitbox();
    
    if (!hitbox.contains(lastHitBox)) {
      nextStage();
    }
    
    BufferedImage image = getImage(tagMan);
    g.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
    
    this.lastHitBox = new Rectangle(hitbox);
  }
  
  private BufferedImage getImage (TagMan tagMan) {
    if (tagMan.hasFinished()) {
      return this.finished;
    }
    if (tagMan.wasHit()) {
      return this.hit;
    }
    return stages.get(currentStage);
  }

  private void nextStage () {
    currentStage++;
    if (currentStage >= stages.size()) {
      currentStage = 0;
    }
  }

  private BufferedImage loadImage (String path) {
    BufferedImage image = null;

    try {
      URL file = getClass().getResource(path);
      image = ImageIO.read(file);
    } catch (IOException e) {
      e.printStackTrace(); // TODO
    }

    return image;
  }

}
