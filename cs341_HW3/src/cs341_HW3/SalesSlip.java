package cs341_HW3;

import java.util.ArrayList;

public class SalesSlip {

	private ArrayList<SalesItem> itemsList;

	// CONSTRUCTOR
	public SalesSlip() {
		itemsList = new ArrayList<SalesItem>();
	}

	// METHOD TO ADD EACH ITEM TO LIST
	public void addItem(SalesItem item) {
		itemsList.add(item);
	}

	// METHOD TO CALCUALTE TOTAL PRICE OF ALL ITEMS
	public double calculateTotalPrice() {
		double totalPrice = 0;
		for (int i = 0; i < itemsList.size(); i++) {
			totalPrice += itemsList.get(i).getTotalItemPrice();
		}
		return totalPrice;
	}

	// TOSTRING TO CONTRUCT COMPLETE LIST OF SALES ITEMS
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < itemsList.size(); i++) {
			sb.append(itemsList.get(i).toString() + "\n");
		}
		return sb.toString();
	}

}
