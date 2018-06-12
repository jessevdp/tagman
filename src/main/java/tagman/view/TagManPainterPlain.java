package tagman.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import tagman.model.TagMan;

public class TagManPainterPlain {
	
	protected static void paint(Graphics g, TagMan tagMan) {
		boolean won = tagMan.hasFinished();
		Rectangle hitbox = tagMan.getHitbox();
		
		Color outerColor = won ? Color.GREEN.darker().darker() : Color.RED;
		Color middleColor = won ? Color.GREEN.darker() : Color.ORANGE;
		Color innerColor = won ? Color.GREEN : Color.YELLOW;
		
		drawPart(g, hitbox, outerColor);
		drawPart(g, hitbox, middleColor, 8);
		drawPart(g, hitbox, innerColor, 24);
	}
	
	private static void drawPart(Graphics g, Rectangle hitbox, Color color, int smaller) {
		int offset = smaller / 2;
		int x = hitbox.x + offset;
		int y = hitbox.y + offset;
		int width = hitbox.width - smaller;
		int height = hitbox.height - smaller;

		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
	
	private static void drawPart(Graphics g, Rectangle hitbox, Color color) {
		drawPart(g, hitbox, color, 0);
	}

}
