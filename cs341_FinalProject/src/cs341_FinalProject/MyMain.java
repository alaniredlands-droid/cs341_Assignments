package cs341_FinalProject;

public class MyMain {

	public static void main(String[] args) {
		// TASK 1: CREATE A CANVAS FOR ANIMATION
		Canvas canvas = new Canvas();
		canvas.requestFocus();

		// TASK 2: ADD A USER GAME OBJECTS A, B, C, D
		Type_A_GameObject objectA = new Type_A_GameObject(500, 500);
		Type_B_GameObject objectB = new Type_B_GameObject(50, 150);
		Type_C_GameObject objectC = new Type_C_GameObject(100, 350);
		Type_D_GameObject user = new Type_D_GameObject(350, 500);

		// set initial velocity, type D object responds to arrow keys
		user.setVelocity(10);
		canvas.addGameObject(user);

		// ADD OBJECTS TO CANVAS
		canvas.addGameObject(objectA);
		canvas.addGameObject(objectB);
		canvas.addGameObject(objectC);
	}

}
