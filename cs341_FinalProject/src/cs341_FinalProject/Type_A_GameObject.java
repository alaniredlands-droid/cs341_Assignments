package cs341_FinalProject;

import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_A_GameObject extends GameObject {

	// INITIALIZE TYPE A GAME OBJECT - IMAGE AND DIRECTION
	public Type_A_GameObject(int x, int y) {
		super(x, y);
		setDirection(Direction.DOWN);
		setVelocity(7);

		imageList = new LinkedList<Icon>();
		imageList.add(new ImageIcon("images/Type_A_Up.png"));
		imageList.add(new ImageIcon("images/Type_A_Down.png"));
	}

	// WHEN OBJECT HITS TOP OR BOTTOM OF CANVAS IT REVERSES
	@Override
	public void move(Canvas c) {

		// IF SELECTED, OBJECT MOVES BASED ON USER INPUT WITHIN RESTRCTIONS
		if (isSelected()) {
			if (getDirection() == Direction.UP) {
				setY(Math.max(0, getY() - getVelocity()));
			} else if (getDirection() == Direction.DOWN) {
				int maxY = c.getHeight() - getCurrentImage().getIconHeight();
				setY(Math.min(maxY, getY() + getVelocity()));
			}
			return;
		}

		// AUTOMATIC MOVEMENT
		Icon icon = getCurrentImage();
		int iconHeight = icon.getIconHeight();
		int canvasHeight = c.getHeight();

		if (getDirection() == Direction.DOWN) {
			setY(getY() + getVelocity());
			if (getY() + iconHeight >= canvasHeight) {
				setDirection(Direction.UP);
			}
		} else if (getDirection() == Direction.UP) {
			setY(getY() - getVelocity());
			if (getY() <= 0) {
				setDirection(Direction.DOWN);
			}
		}
	}

	// SET IMAGE BASED ON DIRECTION
	@Override
	public void setImage() {
		if (getDirection() == Direction.UP) {
			currentImage = 0;
		} else if (getDirection() == Direction.DOWN) {
			currentImage = 1;
		}
	}

	// RESENT DIRECTION TO DEFAULT
	@Override
	public void resetDirection() {
		setDirection(Direction.DOWN);
	}
}
