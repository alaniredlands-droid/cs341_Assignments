package cs341_HW4;

/**
 * Node class representing a node in BST.
 */
public class Node {

	// ATTRIBUTES
	public String word;
	public Node left;
	public Node right;

	// CONSTRUCTOR
	public Node(String word) {
		this.word = word;
		this.left = null;
		this.right = null;
	}

}
