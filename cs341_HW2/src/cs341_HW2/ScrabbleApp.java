package cs341_HW2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

/**
 * ScrabbleApp - A GUI application that generates all possible letter sequences a user
 * inputs (4-7 letter).
 * 
 * @author Alani Dao
 * @version 1.0
 */

public class ScrabbleApp {

	private JFrame frame;
	private JTextField textLetters;
	private JButton button;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrabbleApp window = new ScrabbleApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScrabbleApp() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel prompt = new JLabel("Enter 4 Scrabble Tile Letters:");
		prompt.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		prompt.setBounds(30, 18, 225, 28);
		frame.getContentPane().add(prompt);

		textLetters = new JTextField();
		textLetters.setBounds(253, 19, 167, 27);
		frame.getContentPane().add(textLetters);
		textLetters.setColumns(10);

		button = new JButton("Generate Sequences");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(134, 58, 176, 28);
		frame.getContentPane().add(button);

		textArea = new JTextArea();
		textArea.setEditable(false);

		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(58, 101, 336, 139);
		frame.getContentPane().add(scrollPane);
	}

	/**
	 * Create the action listener for the button.
	 */
	private void createEvents() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildOutput();
			}
		});
	}

	/**
	 * Create the handler to response to the button event. Checks for input errors
	 * and generates all possible letter sequences. Display sequences in text area.
	 */
	private void buildOutput() {
		// GET USER INPUT
		String letters = textLetters.getText().toLowerCase();

		// CHECK FOR INPUT ERRORS (ALPHABET AND LENGTH)
		if (!letters.matches("[a-z]+")) {
			textArea.setText("Error: Only letters a-z are allowed.");
			return;
		}

		if (letters.length() < 4 || letters.length() > 7) {
			textArea.setText("Input Error: Please enter between 4 and 7 letters.");
			return;
		}

		// GENERATE AND DISPLAY SEQUENCES (CALLS METHOD)
		Set<String> sequences = generateSequences(letters);
		StringBuilder output = new StringBuilder();
		for (String seq : sequences) {
			output.append(seq).append("\n");
		}
		textArea.setText(output.toString());
	}

	/**
	 * Calls helper method to generate all possible letter sequences. Stores
	 * sequences so there are no duplicates.
	 * 
	 * @param letters
	 * @return
	 */
	private Set<String> generateSequences(String letters) {
		Set<String> sequences = new HashSet<>();
		generateSequencesHelper("", letters, sequences);
		return sequences;
	}

	/**
	 * Loop through letters and build sequences.
	 * @param prefix
	 * @param remains
	 * @param sequences
	 */
	private void generateSequencesHelper(String prefix, String remains, Set<String> sequences) {
		int n = remains.length();

		if (n == 0) {
			sequences.add(prefix);
		} else {
			for (int i = 0; i < remains.length(); i++) {
				String newPrefix = prefix + remains.charAt(i);
				String newRemains = remains.substring(0, i) + remains.substring(i + 1);
				generateSequencesHelper(newPrefix, newRemains, sequences);
			}
		}
	}
}
