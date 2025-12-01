package cs341_HW2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PasswordStrengthApp - A GUI application that checks the strength of a
 * password a user inputs.
 * 
 * @author Alani Dao
 * @version 1.0
 */

public class PasswordStrengthApp {

	private JFrame frame;
	private JTextField textPassword;
	private JButton button;
	private JTextArea textArea;
	
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordStrengthApp window = new PasswordStrengthApp();
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
	public PasswordStrengthApp() {
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

		JLabel prompt = new JLabel("Enter a password of 8-12 characters:");
		prompt.setFont(new Font("AppleGothic", Font.PLAIN, 13));
		prompt.setBounds(26, 38, 246, 28);
		frame.getContentPane().add(prompt);

		textPassword = new JTextField();
		textPassword.setBounds(256, 37, 169, 28);
		frame.getContentPane().add(textPassword);
		textPassword.setColumns(10);

		button = new JButton("Check Password");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(139, 76, 169, 28);
		frame.getContentPane().add(button);

		textArea = new JTextArea();
		textArea.setFont(new Font("AppleGothic", Font.PLAIN, 13));
		textArea.setBounds(64, 126, 324, 87);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		frame.getContentPane().add(textArea);
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
	 * Create handler to respond to button event. Checks for password strength and
	 * input errors. Displays results in text area.
	 */
	private void buildOutput() {
		// GET USER INPUT
		String password = textPassword.getText();

		// CHECK PASSWORD LENGTH
		if (password.length() < 8 || password.length() > 12) {
			textArea.setText("Error: Please enter a password between 8 and 12 characters.");
			return;
		}

		// CHECK IF PASSWORD HAS A SPACE
		if (password.contains(" ")) {
			textArea.setText("Error: Password cannot contain spaces.");
			return;
		}

		// CHECK PASSWORD STRENGTH BY LENGTH OF BLOCK (SAME CHARACTERS IN A ROW) IN
		// PASSWORD
		int countBlock = 1;
		int bigBlock = 1;

		for (int i = 0; i < password.length() - 1; i++) {

			// increment countBlock for every same character in a row
			if (password.charAt(i) == password.charAt(i + 1)) {
				countBlock++;
			} else {
				if (countBlock > bigBlock) {
					bigBlock = countBlock;
				}
				countBlock = 1;
			}
		}

		if (countBlock > bigBlock) {
			bigBlock = countBlock;
		}

		// DISPLAY PASSWORD STRENGTH BASED ON COUNTBLOCK
		if (bigBlock <= 2) {
			textArea.setText("The largest block in the password is " + bigBlock + ". This is a decent password.");
		} else {
			int shrinkBlock = bigBlock - 2;
			textArea.setText(
					"The largest block in the password is " + bigBlock + " This password can be made stronger by "
							+ "reducing this block by reducing this block by " + shrinkBlock + ".");
		}
	}
}
