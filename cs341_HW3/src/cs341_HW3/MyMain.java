package cs341_HW3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

/**
 * SalesListApp - A GUI application that allows users to enter items for
 * purchase. Records item, cost, and quantity.
 * 
 * @author Alani Dao
 * @version 1.0
 */

public class MyMain {

	private JFrame frmSalesList;
	private JTextField text_Item;
	private JTextField text_Cost;
	private JTextField text_Quantity;
	private JTextArea textArea;
	private JTextArea text_Total;
	private JButton buttonAdd;

	private SalesSlip salesSlip;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyMain window = new MyMain();
					window.frmSalesList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyMain() {
		salesSlip = new SalesSlip();
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSalesList = new JFrame();
		frmSalesList.getContentPane().setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		frmSalesList.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
		frmSalesList.setTitle("Sales List App");
		frmSalesList.setBounds(100, 100, 450, 363);
		frmSalesList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSalesList.getContentPane().setLayout(null);

		JLabel label_Item = new JLabel("Item:");
		label_Item.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		label_Item.setBounds(91, 23, 61, 16);
		frmSalesList.getContentPane().add(label_Item);

		JLabel lblCost = new JLabel("Cost:        $");
		lblCost.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		lblCost.setBounds(91, 57, 73, 16);
		frmSalesList.getContentPane().add(lblCost);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		lblQuantity.setBounds(91, 96, 61, 16);
		frmSalesList.getContentPane().add(lblQuantity);

		text_Item = new JTextField();
		text_Item.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		text_Item.setBounds(164, 18, 191, 26);
		frmSalesList.getContentPane().add(text_Item);
		text_Item.setColumns(10);

		text_Cost = new JTextField();
		text_Cost.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		text_Cost.setBounds(164, 52, 130, 26);
		frmSalesList.getContentPane().add(text_Cost);
		text_Cost.setColumns(10);

		text_Quantity = new JTextField();
		text_Quantity.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		text_Quantity.setBounds(164, 91, 130, 26);
		frmSalesList.getContentPane().add(text_Quantity);
		text_Quantity.setColumns(10);

		buttonAdd = new JButton("Add Item to the Sales List");
		buttonAdd.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		buttonAdd.setBounds(91, 134, 255, 29);
		frmSalesList.getContentPane().add(buttonAdd);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 175, 334, 106);
		frmSalesList.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea);

		JLabel label_Total = new JLabel("Total Sales:    $");
		label_Total.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		label_Total.setBounds(91, 293, 104, 16);
		frmSalesList.getContentPane().add(label_Total);

		text_Total = new JTextArea();
		text_Total.setEditable(false);
		text_Total.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		text_Total.setBounds(195, 290, 130, 26);
		frmSalesList.getContentPane().add(text_Total);
		frmSalesList.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { frmSalesList.getContentPane(), label_Item, lblCost, lblQuantity, text_Item, text_Cost,
						text_Quantity, buttonAdd, scrollPane, textArea, label_Total, text_Total }));
	}

	/**
	 * Create the action listener for the button.
	 */
	private void createEvents() {
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildOutput();
			}
		});
	}

	/**
	 * Create the handler to response to the button event. Displays sales list of
	 * item, cost, quantity. Displays total sales cost.
	 */
	private void buildOutput() {

		// GET USER INPUT
		String item = text_Item.getText();
		double cost = Double.parseDouble(text_Cost.getText());
		int quantity = Integer.parseInt(text_Quantity.getText());

		// CREATE SALESITEM OBJECT TO ADD TO SALESSLIP
		SalesItem salesItem = new SalesItem(item, cost, quantity);
		salesSlip.addItem(salesItem);

		// DISPLAY SALES LIST AND TOTAL SALES COST
		textArea.setText(salesSlip.toString());
		text_Total.setText(String.format("%.2f", salesSlip.calculateTotalPrice()));

		// CLEAR INTPUT TEXT FIELDS
		text_Item.setText("");
		text_Cost.setText("");
		text_Quantity.setText("");
	}
}
