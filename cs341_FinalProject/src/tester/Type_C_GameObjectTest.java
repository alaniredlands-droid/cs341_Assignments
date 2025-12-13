package tester;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs341_FinalProject.Canvas;
import cs341_FinalProject.Direction;
import cs341_FinalProject.Type_C_GameObject;

public class Type_C_GameObjectTest {

	private Type_C_GameObject gameObject;

	@BeforeEach
	public void setUp() {
		gameObject = new Type_C_GameObject(100, 100);
	}

	// TEST LEFT DIRECTION
	@Test
	public void testMoveLeft() {
		gameObject.setDirection(Direction.LEFT);
		gameObject.setVelocity(10);
		gameObject.move(new Canvas());

		assertTrue(gameObject.getX() < 200, "Object should move left.");
	}

	// TEST RIGHT DIRECTION
	@Test
	public void testMoveRight() {
		gameObject.setDirection(Direction.RIGHT);
		gameObject.setVelocity(10);
		gameObject.move(new Canvas());

		assertTrue(gameObject.getX() > 50, "Object should move right.");
	}
}
