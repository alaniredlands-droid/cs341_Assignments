package cs341_FinalProject;

import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_D_GameObject extends GameObject {

	public Type_D_GameObject(int x, int y) {
		super(x, y);
		setDirection(Direction.NONE);

		imageList = new LinkedList<Icon>();
		imageList.add(new ImageIcon("images/Type_D_Up.png"));
		imageList.add(new ImageIcon("images/Type_D_Down.png"));
		imageList.add(new ImageIcon("images/Type_D_Left.png"));
		imageList.add(new ImageIcon("images/Type_D_Right.png"));

	}

	@Override
	public void move(Canvas c) {

		if (!isSelected()) {
			return;
		}

		int iconWidth = getCurrentImage().getIconWidth();
		int iconHeight = getCurrentImage().getIconHeight();

		// MOVE BLUE GAME OBJECT
		switch (getDirection()) {

		case Direction.UP:
			setY(Math.max(0, getY() - getVelocity()));
			break;

		case Direction.DOWN:
			setY(Math.min(c.getHeight() - iconHeight, getY() + getVelocity()));
			break;

		case Direction.LEFT:
			setX(Math.min(c.getWidth() - iconWidth, getX() - getVelocity()));
			break;

		case Direction.RIGHT:
			setX(Math.max(0, getX() + getVelocity()));
			break;

		case Direction.NONE:
		default:
			break;
		}

	}

	// SPECIFY THE IMAGE TO DISPLAY
	// USED FOR ANIMATION
	public void setImage() {
		switch (getDirection()) {
		case Direction.NONE:
			break;
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
		}
	}

	// RESET DIRECTION TO DEFAULT
	@Override
	public void resetDirection() {
		setDirection(Direction.LEFT);
	}

}
