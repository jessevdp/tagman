package tagman.model;

public class Messages {
	
	private static final String NEW_LINE = "\n";
	private static final String EMPTY_LINE = " \n";
	
	public static String createWelcomeMessage(int level) {
		return "Welome to TagMan" + NEW_LINE
				+ "Move with Arrows or Numpad" + NEW_LINE
				+ EMPTY_LINE
				+ createStartLevelMessage(level);
	}
	
	public static String createStartLevelMessage(int level) {
		return "LEVEL " + level + NEW_LINE
				+ EMPTY_LINE
				+ "Press S to Start!";
	}
	
	public static String createEndLevelMessage(int levelScore) {
		return "TagMan Finished!\n"
				+ "Score: " + levelScore + NEW_LINE
				+ EMPTY_LINE
				+ "Press L to continue!";
	}
	
	public static String createEndGameMessage(boolean won, int totalScore) {
		String prefix = won ? "Nice! TagMan has Reached the End!" : "Oeps! TagMan was Hit...";
		
		return prefix + NEW_LINE
				+ EMPTY_LINE
				+ "GAME OVER" + NEW_LINE
				+ "Total Score: " + totalScore + NEW_LINE
				+ EMPTY_LINE
				+ "Press ESC to Exit.";
	}

}
