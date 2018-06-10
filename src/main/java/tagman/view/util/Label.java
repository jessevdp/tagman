package tagman.view.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Label extends JLabel {

	public Label(String text, boolean large) {
		super(text);
		
		setOpaque(true);
		setFont(createFont(large));
		setForeground(Color.YELLOW);
		setBackground(Color.DARK_GRAY);
		setHorizontalAlignment(CENTER);
		
		Dimension preferredSize = large ? new Dimension(80, 32) : new Dimension(60, 22);
		setPreferredSize(preferredSize);
	}
	
	public Label(int value, boolean large) {
		this("" + value, large);
	}
	
	public Label(String text) {
		this(text, false);
	}
	
	public Label(int value) {
		this(value, false);
	}
	
	public void setText(int value) {
		super.setText("" + value);
	}
	
	private Font createFont(boolean large) {
		int size = large ? 24 : 12;
		Font current = getFont();
		return new Font(current.getFontName(), current.getStyle(), size);
	}

}
