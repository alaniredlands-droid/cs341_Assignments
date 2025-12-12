package testerMain;

import cs341_HW4.Dictionary;
import cs341_HW4.Node;

public class DictionaryTester {

	public static void main(String[] args) {

		// CREATE NEW DICTIONARY OBJECT
		Dictionary dict = new Dictionary();

		// TEST: INSERT WORDS IN DICTIONARY
		String[] words = { "Here", "is", "the", "perfect", "system", "for", "cleaning", "your", "room", "First", "move",
				"all", "of", "the", "items", "that", "do", "not", "have", "a", "proper", "place", "to", "the", "center",
				"of", "the", "room", "Get", "rid", "at", "least", "five", "things", "that", "you", "have", "not",
				"used", "within", "the", "last", "year", "Take", "out", "all", "of", "the", "trash", "and", "place",
				"all", "of", "the", "dirty", "dishes", "in", "the", "kitchen", "sink", "Now", "find", "a", "location",
				"for", "each", "of", "the", "items", "you", "had", "placed", "in", "the", "center", "of", "the", "room",
				"For", "any", "remaining", "items", "see", "if", "you", "can", "squeeze", "them", "in", "under", "your",
				"bed", "or", "stuff", "them", "into", "the", "back", "of", "your", "closet", "See", "that", "was",
				"easy" };

		// INSERT WORDS INTO THE BTS
		for (int i = 0; i < words.length; i++) {
			dict.insertWordNode(words[i]);
		}

		// TEST: SEARCH WORDS IN DICTIONARY
		// WORDS IN DICTIONARY
		assert dict.spellCheck("room");
		assert dict.spellCheck("trash");
		assert dict.spellCheck("squeeze");

		// WORDS NOT IN DICTIONARY
		assert !dict.spellCheck("ice");
		assert !dict.spellCheck("cream");
		assert !dict.spellCheck("cheese");

		// TEST: DELETE WORDS FROM DICTIOARY
		// DELETE WORD NOT IN DICTIONARY, CHECK WORD THAT SHOULD STILL BE THERE
		dict.removeWord("christmas");
		dict.spellCheck("room");

		// DELETE WORD IN DICTIONARY, CHECK WORD IS DELETED
		// NO CHILDREN
		dict.removeWord("easy");
		assert !dict.spellCheck("easy");

		// ONE CHILD
		dict.removeWord("stuff");
		assert !dict.spellCheck("stuff");

		// TWO CHILDREN
		dict.removeWord("room");
		assert !dict.spellCheck("room");

		// VALIDATE BST & TESTS COMPLETED
		validateBST(dict);
		System.out.println("Tests completed successfully.");
	}

	// VALIDATE BST STRUCTURE
	public static void validateBST(Dictionary dict) {
		validateNode(dict.getRoot(), null, null);
	}

	private static void validateNode(Node node, String min, String max) {
		if (node == null) {
			return;
		}

		if (min != null) {
			assert node.word.compareToIgnoreCase(min) > 0 : "Error " + node.word + " is not > min " + min;
		}

		if (max != null) {
			assert node.word.compareToIgnoreCase(max) < 0 : "Error " + node.word + " is not < max " + max;
		}

		// VALIDATE LEFT AND RIGHT CHILDREN
		validateNode(node.left, min, node.word);
		validateNode(node.right, node.word, max);
	}

}
