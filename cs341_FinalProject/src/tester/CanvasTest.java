package tester;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs341_FinalProject.Canvas;
import cs341_FinalProject.Direction;
import cs341_FinalProject.GameObject;
import cs341_FinalProject.Type_A_GameObject;
import cs341_FinalProject.Type_B_GameObject;
import cs341_FinalProject.Type_D_GameObject;

import java.awt.event.KeyEvent;

public class CanvasTest {

	private Canvas canvas;

	@BeforeEach
	public void setUp() {
		canvas = new Canvas();
	}

	@Test
	public void testKeyReleasedTab() {
		GameObject objectA = new Type_A_GameObject(100, 100);
		GameObject objectB = new Type_B_GameObject(200, 200);

		canvas.addGameObject(objectA);
		canvas.addGameObject(objectB);

		// OBJECT A SHOULD BE SELECTED INITIALLY
		assertTrue(objectA.isSelected(), "Object A should be selected initially.");
		assertFalse(objectB.isSelected(), "Object B should not be selected initially.");

		// TAB PRESS
		KeyEvent tabKeyEvent = new KeyEvent(canvas, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,
				KeyEvent.VK_TAB, KeyEvent.CHAR_UNDEFINED);
		canvas.keyReleased(tabKeyEvent);

		// B SHOULD BE SELECTED AFTER TAB
		assertTrue(objectB.isSelected(), "Object B should be selected after TAB.");
	}

	@Test
	public void testKeyPressedArrowKeys() {
		GameObject gameObject = new Type_D_GameObject(100, 100);
		canvas.addGameObject(gameObject);

		// PRESS UP
		KeyEvent upKeyEvent = new KeyEvent(canvas, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP,
				KeyEvent.CHAR_UNDEFINED);
		canvas.keyPressed(upKeyEvent);

		// CHECK DIRECTION IS UP AFTR PRESSING UP
		assertEquals(Direction.UP, gameObject.getDirection(), "Direction should be UP after pressing the UP key.");
	}
}
