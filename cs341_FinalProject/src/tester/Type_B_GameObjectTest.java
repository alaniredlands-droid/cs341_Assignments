package tester;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs341_FinalProject.Canvas;
import cs341_FinalProject.Direction;
import cs341_FinalProject.Type_B_GameObject;

public class Type_B_GameObjectTest {

	private Type_B_GameObject gameObject;

	@BeforeEach
	public void setUp() {
		gameObject = new Type_B_GameObject(100, 100);
	}

	// TEST UP DIRECTION
	@Test
	public void testMoveUp() {
		gameObject.setDirection(Direction.UP);
		gameObject.setVelocity(10);
		gameObject.move(new Canvas());

		assertTrue(gameObject.getY() < 200, "Object should move up.");
	}

	// TEST DOWN DIRECTION
	@Test
	public void testMoveDown() {
		gameObject.setDirection(Direction.DOWN);
		gameObject.setVelocity(10);
		gameObject.move(new Canvas());

		assertTrue(gameObject.getY() > 100, "Object should move down.");
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
