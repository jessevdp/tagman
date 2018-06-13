package tagman.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MessagePanel extends JLayeredPane {
	
	private static final Color MESSAGE_COLOR = Color.YELLOW;
	private static final int MESSAGE_SIZE = 30;
	
	private ArrayList<JLabel> lines;

	public MessagePanel() {
		this.lines = new ArrayList<>();
		
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	private JLabel createLine(String text) {
		JLabel line = new JLabel();
		line.setText(text);
		
		Font currentFont = line.getFont();
		Font newFont = new Font(currentFont.getFontName(), currentFont.getStyle(), MESSAGE_SIZE);
		line.setFont(newFont);
		
		line.setForeground(MESSAGE_COLOR);
		line.setMaximumSize(new Dimension(Integer.MAX_VALUE, line.getMinimumSize().height));
		line.setHorizontalAlignment(SwingConstants.CENTER);
		
		return line;
	}
	
	public void setText(String text) {
		this.lines.clear();
		String[] lines = splitMessage(text);
		for (String string : lines) {
			JLabel line = createLine(string);
			this.lines.add(line);
		}
		resetComponents();
	}
	
	private String[] splitMessage(String message) {
		String delimiter = "\n";
		String[] result = message.split(delimiter);
		return result;
	}
	
	private void resetComponents() {
		removeAll();
		for (JLabel line : lines) {
			this.add(line);
		}
	}

}
