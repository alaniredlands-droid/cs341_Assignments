package cs341_HW4;

/**
 * Dictionary class that implements a binary search tree to store words.
 * 
 * @author Alani Dao
 * @version 1.0
 */
public class Dictionary {

	// ATTRIBUTES
	private Node root;

	// CONSTRUCTOR
	public Dictionary() {
		this.root = null;
	}

	// GETTER
	public Node getRoot() {
		return root;
	}

	// METHODS
	// insertWordNode()
	public void insertWordNode(String word) {
		root = insert(root, word);
	}

	/**
	 * Insert word into BST.
	 * 
	 * @param node
	 * @param word
	 * @return
	 */
	private Node insert(Node node, String word) {
		if (node == null) {
			return new Node(word);
		}

		int compare = word.compareToIgnoreCase(node.word);

		if (compare < 0) {
			node.left = insert(node.left, word);
		} else if (compare > 0) {
			node.right = insert(node.right, word);
		}
		return node;
	}

	/**
	 * Check if word exists in dictionary.
	 * 
	 * @param word
	 * @return
	 */
	public boolean spellCheck(String word) {
		return searchNode(root, word);
	}

	/**
	 * Search for word in BST.
	 * 
	 * @param node
	 * @param word
	 * @return
	 */
	private boolean searchNode(Node node, String word) {
		if (node == null) {
			return false;
		}

		int compare = word.compareToIgnoreCase(node.word);

		if (compare == 0) {
			return true;
		} else if (compare < 0) {
			return searchNode(node.left, word);
		} else {
			return searchNode(node.right, word);
		}
	}

	/**
	 * Removes a word from dictionary.
	 * 
	 * @param word
	 */
	public void removeWord(String word) {
		root = deleteNode(root, word);
	}

	/**
	 * Delete word from BST.
	 * 
	 * @param node
	 * @param word
	 * @return
	 */
	private Node deleteNode(Node node, String word) {
		// RETURN NULL IS NODE NOT FOUND
		if (node == null) {
			return null;
		}

		// CASE SENSITIVE COMPARISON
		int compare = word.compareToIgnoreCase(node.word);

		if (compare < 0) {
			node.left = deleteNode(node.left, word);
		} else if (compare > 0) {
			node.right = deleteNode(node.right, word);
		} else {
			// FOUND NODE TO DELETE
			// 1. NO CHILDREN
			if (node.left == null && node.right == null) {
				return null;
			}
			// 2. ONE CHILD
			else if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			}
			// 3. TWO CHILDREN
			Node nextNode = findNextNode(node.right);
			node.word = nextNode.word;
			node.right = deleteNode(node.right, nextNode.word);
		}
		return node;
	}

	/**
	 * Find smallest node in right subtree.
	 * 
	 * @param node
	 * @return
	 */
	private Node findNextNode(Node node) {
		// TRAVERSE LEFT SIDE OF RIGHT SUBTREE TO FIND SMALLEST NODE
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

}
