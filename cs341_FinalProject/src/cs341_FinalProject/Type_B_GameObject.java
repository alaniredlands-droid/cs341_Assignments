package cs341_FinalProject;

import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_B_GameObject extends GameObject {

	// ATTRIBUTES
	private int horizontalDirection;
	private int verticalDirection;
	private static final int VERTICAL = 0;
	private static final int HORIZONTAL = 1;
	private int movementMode;

	// INITIALIZE TYPE B GAME OBJECT - IMAGE AND DIRECTION
	public Type_B_GameObject(int x, int y) {
		super(x, y);
		horizontalDirection = Direction.LEFT;
		verticalDirection = Direction.DOWN;
		movementMode = VERTICAL;
		setVelocity(7);

		imageList = new LinkedList<Icon>();
		imageList.add(new ImageIcon("images/Type_B__Up.png"));
		imageList.add(new ImageIcon("images/Type_B_Down.png"));
		imageList.add(new ImageIcon("images/Type_B_Left.png"));
		imageList.add(new ImageIcon("images/Type_B_Right.png"));
	}

	@Override
	public void move(Canvas c) {
		Icon icon = getCurrentImage();
		int iconWidth = icon.getIconWidth();
		int iconHeight = icon.getIconHeight();
		int canvasWidth = c.getWidth();
		int canvasHeight = c.getHeight();

		// IF SELECTED, OBJECT MOVES BASED ON USER INPUT WITHIN RESTRCTIONS
		if (isSelected()) {

			// SWITCH AXIS BASED ON USER INPUT
			if (getDirection() == Direction.UP || getDirection() == Direction.DOWN) {
				movementMode = VERTICAL;
			} else if (getDirection() == Direction.LEFT || getDirection() == Direction.RIGHT) {
				movementMode = HORIZONTAL;
			}

			if (movementMode == VERTICAL) {

				if (getDirection() == Direction.UP) {
					setY(Math.max(0, getY() - getVelocity()));
				} else if (getDirection() == Direction.DOWN) {
					int maxY = c.getHeight() - getCurrentImage().getIconHeight();
					setY(Math.min(maxY, getY() + getVelocity()));
				}

			} else { // HORIZONTAL

				if (getDirection() == Direction.LEFT) {
					int maxX = c.getWidth() - getCurrentImage().getIconWidth();
					setX(Math.min(maxX, getX() - getVelocity()));
				} else if (getDirection() == Direction.RIGHT) {
					setX(Math.max(0, getX() + getVelocity()));
				}
			}

			return;
		}

		// AUTOMATIC MOVEMENT
		// VERTICAL MOVEMENT
		if (movementMode == VERTICAL) {

			if (verticalDirection == Direction.DOWN) {
				setY(getY() + getVelocity());
				if (getY() + iconHeight >= canvasHeight) {
					verticalDirection = Direction.UP;
					movementMode = HORIZONTAL;
				}
			} else { // UP
				setY(getY() - getVelocity());
				if (getY() <= 0) {
					verticalDirection = Direction.DOWN;
					movementMode = HORIZONTAL;
				}
			}
		}

		// HORIZONTAL MOVEMENT
		else { // HORIZONTAL

			if (horizontalDirection == Direction.LEFT) {
				setX(getX() + getVelocity());
				if (getX() + iconWidth >= canvasWidth) {
					horizontalDirection = Direction.RIGHT;
					movementMode = VERTICAL;
				}
			} else { // RIGHT
				setX(getX() - getVelocity());
				if (getX() <= 0) {
					horizontalDirection = Direction.LEFT;
					movementMode = VERTICAL;
				}
			}
		}
	}

	// SET IMAGE BASED ON DIRECTION
	@Override
	public void setImage() {
		if (isSelected()) {
			switch (getDirection()) {
			case Direction.UP:
				currentImage = 0;
				break;
			case Direction.DOWN:
				currentImage = 1;
				break;
			case Direction.LEFT:
				currentImage = 3;
				break;
			case Direction.RIGHT:
				currentImage = 2;
				break;
			default:
				break;
			}
			return;
		}

		// DEFAULT
		if (movementMode == VERTICAL) {
			if (verticalDirection == Direction.UP) {
				currentImage = 0;
			} else {
				currentImage = 1;
			}
		} else {
			if (horizontalDirection == Direction.LEFT) {
				currentImage = 2;
			} else {
				currentImage = 3;
			}
		}
	}

	// RESET DIRECTION TO DEFAULT
	@Override
	public void resetDirection() {
		movementMode = VERTICAL;
		verticalDirection = Direction.DOWN;
		horizontalDirection = Direction.LEFT;
		setDirection(Direction.NONE);
	}
}
