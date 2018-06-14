package tagman.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class SoundController {
  
  private static final String PATH_TO_SOUND = "src/main/resources/sounds/";
  private static final String FINISH_SOUND = PATH_TO_SOUND + "finish.wav";
  private static final String HIT_SOUND = PATH_TO_SOUND + "hit.wav";

  public void playHit () {
    playSound(HIT_SOUND);
  }
  
  public void playFinish () {
    playSound(FINISH_SOUND);
  }
  
  private AudioStream playSound (String path) {
    AudioStream sound = null;
    
    try {
      InputStream in = new FileInputStream(path);
      sound = new AudioStream(in);
      AudioPlayer.player.start(sound);
    }
    catch (FileNotFoundException e) {
      e.printStackTrace(); // TODO
    }
    catch (IOException e) {
      e.printStackTrace(); // TODO
    }
    
    return sound;
  }

}
