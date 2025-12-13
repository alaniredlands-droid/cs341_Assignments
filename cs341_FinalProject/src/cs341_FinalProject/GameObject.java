package cs341_FinalProject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.Icon;

public abstract class GameObject {
	// EACH GAME OBJECT HAS AN X,Y LOCATION, VELOCITY, DIRECTION, AND SELECTION
	// STATUS
	private int x;
	private int y;
	private int velocity;
	private int direction;
	private boolean selected = false;

	// EACH GAME OBJECT CAN HAVE A COLLECTION OF IMAGES
	protected List<Icon> imageList;
	protected int currentImage;

	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
		velocity = 0;
		currentImage = 0;
	}

	// DRAW THE GAME OBJECT & HIGHLIGHT ON THE CANVAS
	public void draw(Component c, Graphics g) {
		imageList.get(currentImage).paintIcon(c, g, x, y);

		if (selected) {
			Graphics2D g2 = (Graphics2D) g;

			int width = getCurrentImage().getIconWidth();
			int height = getCurrentImage().getIconHeight();

			Color originColor = g2.getColor();
			java.awt.Stroke originStroke = g2.getStroke();

			g2.setColor(Color.GREEN);
			g2.setStroke(new BasicStroke(3));

			g2.drawRect(x - 2, y - 2, width + 4, height + 4);

			g2.setColor(originColor);
			g2.setStroke(originStroke);
		}
	}

	// SETTERS AND GETTERS
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getVelocity() {
		return velocity;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Icon getCurrentImage() {
		return imageList.get(currentImage);
	}

	// ABSTRACT METHODS
	public abstract void move(Canvas c);

	public abstract void setImage();

	// GETTER AND SETTER FOR SELECTION STATUS
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	// RESET DIRECTION TO DEFAULT
	public abstract void resetDirection();

}
