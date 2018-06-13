package tagman.model;

public class Messages {
	
	private static final String NEW_LINE = "\n";
	private static final String EMPTY_LINE = " ";
	
	public static String createWelcomeMessage(int level) {
		String[] message = {
				"Welome to TagMan!",
				"Move with Arrows or Numpad",
				EMPTY_LINE
		};
		return assembleMessage(message) + createStartLevelMessage(level);
	}
	
	public static String createStartLevelMessage(int level) {
		String[] message = {
				"LEVEL " + level,
				EMPTY_LINE,
				"Press S to Start"
		};
		return assembleMessage(message);
	}
	
	public static String createEndLevelMessage(int levelScore) {
		String[] message = {
				"TagMan Finished!",
				"Level Score: " + levelScore,
				EMPTY_LINE,
				"Press L to Continue"
		};
		return assembleMessage(message);
	}
	
	public static String createEndGameMessage(boolean won, int totalScore) {
		String reason = won ? "All Levels Complete!" : "TagMan was Hit...";

		String[] message = {
				"GAME OVER",
				reason,
				EMPTY_LINE,
				"Total Score: " + totalScore,
				EMPTY_LINE,
				"Press ESC to Exit"
		};

		return assembleMessage(message);
	}
	
	private static String assembleMessage(String[] parts) {
		String message = "";
		for (String part : parts) {
			message += part + NEW_LINE;
		}
		return message;
	}

}
