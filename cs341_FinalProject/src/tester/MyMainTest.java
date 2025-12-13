package tester;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs341_FinalProject.Canvas;
import cs341_FinalProject.Direction;
import cs341_FinalProject.Type_A_GameObject;
import cs341_FinalProject.Type_B_GameObject;
import cs341_FinalProject.Type_C_GameObject;
import cs341_FinalProject.Type_D_GameObject;

public class MyMainTest {

	private Canvas canvas;
	private Type_A_GameObject objectA;
	private Type_B_GameObject objectB;
	private Type_C_GameObject objectC;
	private Type_D_GameObject user;

	@BeforeEach
	public void setUp() {
		// CREATE CANVAS & ADD OBJECTS
		canvas = new Canvas();
		objectA = new Type_A_GameObject(500, 500);
		objectB = new Type_B_GameObject(50, 150);
		objectC = new Type_C_GameObject(100, 350);
		user = new Type_D_GameObject(350, 500);

		user.setVelocity(10);
		canvas.addGameObject(user);

		canvas.addGameObject(objectA);
		canvas.addGameObject(objectB);
		canvas.addGameObject(objectC);
	}

	// TEST CANVAS INITIALIZATION
	@Test
	public void testCanvasInitialization() {
		assertNotNull(canvas, "Canvas should not be null.");
	}

	// TEST VELOCITY
	@Test
	public void testUserVelocity() {
		assertEquals(10, user.getVelocity(), "User velocity should be 10.");
	}

	// TEST OBJECT POSITION
	@Test
	public void testObjectPosition() {
		assertEquals(500, objectA.getX(), "Object A should be at X position 500.");
		assertEquals(500, objectA.getY(), "Object A should be at Y position 500.");

		assertEquals(50, objectB.getX(), "Object B should be at X position 50.");
		assertEquals(150, objectB.getY(), "Object B should be at Y position 150.");

		assertEquals(100, objectC.getX(), "Object C should be at X position 100.");
		assertEquals(350, objectC.getY(), "Object C should be at Y position 350.");

		assertEquals(350, user.getX(), "User object should be at X position 350.");
		assertEquals(500, user.getY(), "User object should be at Y position 500.");
	}

	// TEST UP, DOWN, LEFT, RIGHT MOVEMENT BY USER
	@Test
	public void testUserMovesUp() {
		user.setDirection(Direction.UP);
		user.move(canvas);
		assertTrue(user.getY() < 500);
	}

	@Test
	public void testUserMovesDown() {
		user.setDirection(Direction.DOWN);
		user.move(canvas);
		assertTrue(user.getY() > 500);
	}

	@Test
	public void testUserMovesLeft() {
		user.setDirection(Direction.LEFT);
		user.move(canvas);
		assertTrue(user.getX() < 350);
	}

	@Test
	public void testUserMovesRight() {
		user.setDirection(Direction.RIGHT);
		user.move(canvas);
		assertTrue(user.getX() > 350);
	}
}
