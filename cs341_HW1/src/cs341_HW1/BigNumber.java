package cs341_HW1;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 * BigNumber - A GUI application that adds two positive integers.
 * 
 * @author Alani Dao
 * @version 1.0
 */

public class BigNumber {
	private JFrame title;
	private JTextField textX;
	private JTextField textY;
	private JTextArea textArea;
	private JButton buttonAdd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BigNumber window = new BigNumber();
					window.title.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BigNumber() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		title = new JFrame();
		title.setTitle("Big Number Adder");
		title.setBounds(100, 100, 450, 300);
		title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		title.getContentPane().setLayout(null);
		
		JLabel header = new JLabel("Enter ONLY positive integers");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setBounds(114, 16, 235, 22);
		title.getContentPane().add(header);
		
		textX = new JTextField();
		textX.setBounds(66, 50, 347, 22);
		title.getContentPane().add(textX);
		textX.setColumns(10);
		
		textY = new JTextField();
		textY.setColumns(10);
		textY.setBounds(66, 84, 348, 22);
		title.getContentPane().add(textY);
		
		JLabel labelX = new JLabel("X");
		labelX.setHorizontalAlignment(SwingConstants.CENTER);
		labelX.setBounds(32, 52, 36, 19);
		title.getContentPane().add(labelX);
		
		JLabel labelY = new JLabel("Y");
		labelY.setHorizontalAlignment(SwingConstants.CENTER);
		labelY.setBounds(32, 84, 36, 22);
		title.getContentPane().add(labelY);
		
		buttonAdd = new JButton("Add");
		buttonAdd.setBounds(169, 121, 117, 29);
		title.getContentPane().add(buttonAdd);
		
		textArea = new JTextArea();
		textArea.setBounds(66, 177, 347, 63);
		title.getContentPane().add(textArea);
	}

	// CREATE THE ACTION LISTENER FOR THE BUTTON
	private void createEvents() {
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildOutput();
			}
		});
	}

	// CREATE HANDLER TO RESPOND TO THE BUTTON EVENT
	// ADD INTEGERS INPUT
	private void buildOutput() {
		try {
			// GET X AND Y VALUES FROM TEXT FIELDS
			Integer x = Integer.parseInt(textX.getText());
			Integer y = Integer.parseInt(textY.getText());
			
			// ADD X AND Y
			int output = x + y;
			
			// DISPLAY OUTPUT
			textArea.setText(String.valueOf(output));
		} catch (NumberFormatException e) {
			textArea.setText("Input Error");
		}
	}
}