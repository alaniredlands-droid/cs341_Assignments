package cs341_HW5;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Font;

/**
 * NumberFile - A GUI application that reads numbers from a file and calculates
 * their mean and standard deviation.
 * 
 * @author Alani Dao
 * @version 1.0
 */

public class NumberFile {

	private JFrame frame;
	private JTextArea textArea;
	private JButton buttonLoad;
	private LinkedList<Double> listNumbers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// CREATE WINDOW
					NumberFile window = new NumberFile();
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
	public NumberFile() {
		listNumbers = new LinkedList<Double>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Mean and Standard Deviation Calculator");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(19, 16, 410, 180);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		buttonLoad = new JButton("Load File");
		buttonLoad.setFont(new Font("Luminari", Font.PLAIN, 13));
		buttonLoad.setBounds(150, 210, 150, 30);
		buttonLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadFile();
			}
		});
		frame.getContentPane().add(buttonLoad, BorderLayout.SOUTH);
	}

	/**
	 * Load a file and read numbers from it.
	 */
	private void loadFile() {
		JFileChooser fileChooser = new JFileChooser();

		// ONLY ALLOW TXT FILES
		fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
		int result = fileChooser.showOpenDialog(frame);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			// READ NUMBERS FROM SELECTED FILE
			readNumbers(selectedFile);
		}
	}

	/**
	 * Read numbers from file and store them in linked list.
	 * 
	 * @param file
	 */
	private void readNumbers(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;

			// READ FILE LINE BY LINE
			while ((line = reader.readLine()) != null) {
				line = line.trim().replaceAll("[^0-9.-]", "");
				try {
					// PARSE NUMBER AND ADD TO LIST
					double num = Double.parseDouble(line.trim());
					listNumbers.add(num);
				} catch (NumberFormatException e) {
					// ERROR MESSAGE FOR INVALID NUMBER
					textArea.append("Input Error: " + line + " is not a valid number.\n");
				}
			}
			// CALCULATE MEAN AND STANDARD DEVIATION
			calculateNumbers();
		} catch (IOException e) {
			// ERROR MESSAGE FOR FILE READ ISSUES
			textArea.append("File Error");
		}
	}

	/**
	 * Calculate and display mean and standard deviation of numbers from file.
	 */
	private void calculateNumbers() {
		if (listNumbers.isEmpty()) {
			textArea.append("Error: Cannot read file");
			return;
		}

		// CALCULATE MEAN AND STANDARD DEVIATION
		double mean = calculateMean();
		double standDev = calculateStandDev(mean);

		// DISPLAY RESULT OF MEAN AND STANDARD DEVIATION
		textArea.append("Mean: " + mean + "\n");
		textArea.append("Standard Deviation: " + standDev + "\n");
	}

	/**
	 * Calculate the mean of numbers in linked list.
	 * 
	 * @return mean
	 */
	private double calculateMean() {
		double sum = 0;

		// SUM ALL NUMBERS IN LIST
		for (int i = 0; i < listNumbers.size(); i++) {
			sum += listNumbers.get(i);
		}

		// RETURN MEAN
		return sum / listNumbers.size();
	}

	/**
	 * Calculate the standard deviation of numbers in linked list.
	 * 
	 * @param mean
	 * @return standard deviation
	 */
	private double calculateStandDev(double mean) {
		double sumSqDiff = 0;

		// CALCULATE SUM OF SQUARED DIFFERENCES FROM MEAN
		for (int i = 0; i < listNumbers.size(); i++) {
			sumSqDiff += Math.pow(listNumbers.get(i) - mean, 2);
		}

		// RETURN STANDARD DEVIATION (SQUARE ROOT OF)
		return Math.sqrt(sumSqDiff / listNumbers.size());
	}
}
