package cs341_HW3;

public class SalesItem {

	// ATTRIBUTES
	private String item;
	private double cost;
	private int quantity;

	// CONSTRUCTOR
	public SalesItem(String item, double cost, int quantity) {
		this.item = item;
		this.cost = cost;
		this.quantity = quantity;
	}

	// SETTERS AND GETTERS
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// METHOD TO CALCUALTE TOTAL PRICE PER ITEM QUANTITY
	public double getTotalItemPrice() {
		return cost * quantity;
	}

	// TOSTRING TO DISLPAY ITEM, COST, QUANTITY
	@Override
	public String toString() {
		return String.format("%-20s $%-9.2f %-5d", item, cost, quantity);
	}

}
