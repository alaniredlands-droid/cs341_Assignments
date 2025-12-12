package cs341_HW5;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom implementation of linked list.
 * 
 * @param <T>
 */
public class LinkedList<T> {

	// ATTRIBUTES
	private Node<T> head;
	private int size;

	// CONSTRUCTOR
	public LinkedList() {
		head = null;
		size = 0;
	}

	/**
	 * Adds new value to end of linked list.
	 * 
	 * @param value
	 */
	public void add(T value) {
		Node<T> newNode = new Node<>(value);

		if (head == null) {
			head = newNode;
		} else {
			// TRAVERSE TO END OF LIST AND ADD NEW NODE
			Node<T> temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = newNode;
		}
		size++;
	}

	/**
	 * Returns number of elements in linked list.
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns all values in linked list as iterable.
	 * 
	 * @return
	 */
	public Iterable<T> getValues() {
		List<T> values = new ArrayList<>();
		Node<T> temp = head;

		// TRAVERSE LIST AND ADD TO ITERABLE
		while (temp != null) {
			values.add(temp.value);
			temp = temp.next;
		}
		return values;
	}

	/**
	 * Node class for linked list.
	 * 
	 * @param <T>
	 */
	private static class Node<T> {
		T value;
		Node<T> next;

		// CONSTRUCTOR
		Node(T value) {
			this.value = value;
			this.next = null;
		}
	}

}
