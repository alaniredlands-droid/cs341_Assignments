package tester;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs341_FinalProject.Canvas;
import cs341_FinalProject.Direction;
import cs341_FinalProject.GameObject;
import cs341_FinalProject.Type_A_GameObject;

public class GameObjectTest {

	private GameObject gameObject;

	// CREATE CLASS FOR TESTING
	@BeforeEach
	public void setUp() {
		gameObject = new Type_A_GameObject(100, 100);
	}

	// TEST MOVEMENT ON CANVAS
	@Test
	public void testMove() {
		gameObject.setDirection(Direction.UP);
		gameObject.setVelocity(10);
		gameObject.move(new Canvas());

		assertTrue(gameObject.getY() < 100, "Object should move up.");
	}

	// TEST DIRECTION AND OBJECT WHEN RESET
	@Test
	public void testResetDirection() {
		gameObject.setDirection(Direction.RIGHT);
		assertEquals(Direction.RIGHT, gameObject.getDirection(), "Direction should be RIGHT.");

		gameObject.resetDirection();
		assertEquals(Direction.DOWN, gameObject.getDirection(), "Direction should reset to DOWN.");
	}

	// TEST WHEN OBJECT IS SELECTED
	@Test
	public void testSelection() {
		assertFalse(gameObject.isSelected(), "Object should not be selected initially.");

		gameObject.setSelected(true);
		assertTrue(gameObject.isSelected(), "Object should be selected after calling setSelected(true).");
	}
}
