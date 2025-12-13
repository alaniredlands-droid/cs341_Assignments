package cs341_FinalProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Canvas extends JComponent implements ActionListener, KeyListener {
	// DEFAULT SERIAL NUMBER
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private Timer gameLoopTimer;
	private List<GameObject> gameObjectList;
	private int highlighted = 0;

	public Canvas() {
		// TASK 1: CREATE A LIST OF CHARACTERS THAT WILL APPEAR ON THE CANVAS
		gameObjectList = new LinkedList<GameObject>();

		// TASK 2: CREATE A WINDOW FOR THE APPLICATION
		frame = new JFrame("Animation Canvas");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);

		// TASK 3: CONSTRUCT A TIMER FOR GAME LOOP
		gameLoopTimer = new Timer(25, this);
		gameLoopTimer.start();

		setFocusTraversalKeysEnabled(false);
		addKeyListener(this);

		// TASK 4: MAKE THE WINDOW VISIBLE
		frame.setVisible(true);

	}

	/**
	 * Adds GameObjects to the List, which are latter added to the Canvas
	 */
	public synchronized void addGameObject(GameObject sprite) {
		if (gameObjectList.isEmpty()) {
			sprite.setSelected(true);
		}
		gameObjectList.add(sprite);
	}

	/**
	 * Draws the GameObject graphic onto the Canvas
	 */
	public synchronized void paint(Graphics g) {
		for (GameObject s : gameObjectList) {
			s.draw(this, g);
		}
	}

	// ****************************************************
	// Canvas must implement the inherited abstract method
	// ActionListener.actionPerformed(ActionEvent)
	public synchronized void actionPerformed(ActionEvent e) {
		for (GameObject gameObject : gameObjectList) {
			gameObject.move(this);
			gameObject.setImage();
		}
		repaint();
	}

	// ****************************************************
	// Canvas must implement the inherited abstract methods
	// for KeyListener

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		GameObject selectedObject = gameObjectList.get(highlighted);

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			selectedObject.setDirection(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			selectedObject.setDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			selectedObject.setDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			selectedObject.setDirection(Direction.RIGHT);
			break;
		}
	}

	// SWITCH SELECTION & HIGHLIGHT TO NEXT GAME OBJECT WHEN TAB IS RELEASED
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_TAB) {

			GameObject oldObject = gameObjectList.get(highlighted);
			oldObject.setSelected(false);
			oldObject.resetDirection();

			highlighted++;
			if (highlighted >= gameObjectList.size()) {
				highlighted = 0;
			}

			GameObject newObject = gameObjectList.get(highlighted);
			newObject.setSelected(true);
			return;
		}
		gameObjectList.get(highlighted).setDirection(Direction.NONE);
	}

}
