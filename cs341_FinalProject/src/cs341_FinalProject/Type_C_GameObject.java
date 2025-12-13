package cs341_FinalProject;

import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_C_GameObject extends GameObject {

	// INITIALIZE TYPE C GAME OBJECT - IMAGE AND DIRECTION
	public Type_C_GameObject(int x, int y) {
		super(x, y);
		setDirection(Direction.LEFT);
		setVelocity(7);

		imageList = new LinkedList<Icon>();
		imageList.add(new ImageIcon("images/Type_C_Left.png"));
		imageList.add(new ImageIcon("images/Type_C_Right.png"));
	}

	// WHEN OBJECT HITS LEFT OR RGHT OF CANVAS IT REVERSES
	@Override
	public void move(Canvas c) {

		// IF SELECTED, OBJECT MOVES BASED ON USER INPUT WITHIN RESTRCTIONS
		if (isSelected()) {
			if (getDirection() == Direction.LEFT) {
				int maxX = c.getWidth() - getCurrentImage().getIconWidth();
				setX(Math.min(maxX, getX()) - getVelocity());
			} else if (getDirection() == Direction.RIGHT) {
				setX(Math.max(0, getX() + getVelocity()));
			}
			return;
		}

		// AUTOMATIC MOVEMENT
		Icon icon = getCurrentImage();
		int iconWidth = icon.getIconWidth();
		int canvasWidth = c.getWidth();

		if (getDirection() == Direction.LEFT) {
			setX(getX() + getVelocity());
			if (getX() + iconWidth >= canvasWidth) {
				setDirection(Direction.RIGHT);
			}
		} else if (getDirection() == Direction.RIGHT) {
			setX(getX() - getVelocity());
			if (getX() <= 0) {
				setDirection(Direction.LEFT);
			}
		}
	}

	// SET IMAGE BASED ON DIRECTION
	@Override
	public void setImage() {

		// WHEN SELECTED
		if (isSelected()) {
			if (getDirection() == Direction.LEFT) {
				currentImage = 1;
			} else {
				currentImage = 0;
			}
			return;
		}

		// DEFAULT
		if (getDirection() == Direction.LEFT) {
			currentImage = 0;
		} else {
			currentImage = 1;
		}
	}

	// RESET DIRECTION TO DEFAULT
	@Override
	public void resetDirection() {
		setDirection(Direction.LEFT);
	}
}
