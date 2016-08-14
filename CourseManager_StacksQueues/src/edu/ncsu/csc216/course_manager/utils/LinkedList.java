/**
 * 
 */
package edu.ncsu.csc216.course_manager.utils;

import java.util.AbstractList;

/**
 * Defines the behavior for a generic linked list class.
 * 
 *@author Benjamin Zich
 * 
 *@param <E> the object or primitive type accepted into the list
 *
 */
public class LinkedList<E> extends AbstractList<E> {
	/** The first node in the linked list. */
	private Node<E> head;
	/** The back of the linked list. */
	private Node<E> back;
	/** The size of the linked list. */
	private int size;
	
	/**
	 * Constructs a null linked list.
	 */
	public LinkedList() {
		head = null;
		back = null;
		size = 0;
	}

	/**
	 * Adds an element to the LinkedList at a given index.
	 * 
	 * @param index is the index to add the element. If the index
	 * is longer than the list, the element will be added to the end 
	 * of the list.
	 * @param element is the element to be inserted into the LinkedList.
	 */
	@Override
	public void add(int index, E element) {
		/** Keeps track of the position within the list. */
		int counter = 0;
		/** The current position of the index. */
		Node<E> current = head;
		/** The previous position of the index. */
		Node<E> previous = null;
		
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		
		while (current != null && counter < index) {
			previous = current;
			current = current.next;
			counter++;
		}
		
		if (current == head) {
			head = new Node<E>(element, current);
			if (size == 0) {
				back = head;
			}	
		} else if (size == index){
			back.next = new Node<E>(element);
			back = back.next;
		} else {
			previous.next = new Node<E>(element, current);
		}
		
		size++;
	}

	/**
	 * Removes an item at a given index and returns the item to the client code.
	 * 
	 * @index is the index to remove the item. Items before and after the item are
	 * linked together.
	 */
	@Override
	public E remove(int index) {
		/** Keeps track of the position within the list. */
		int counter = 0;
		/** The current position of the index. */
		Node<E> current = head;
		/** The previous position of the index. */
		Node<E> previous = null;
		
		if (size == 0 || index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		while (current != null && counter < index) {
			previous = current;
			current = current.next;
			counter++;
		}
		
		if (current != null) {
			if (current == head) {
				head = head.next;
			} else if (current == back) {
				 previous.next = back.next;
				 back = previous;
			} else {
				previous.next = current.next;
			}
			size--;
		}
		
		try {
			return current.data;
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * Changes the value of the list item at the given index
	 * to the provided value.
	 * 
	 * @param index is the index of the item to be replaced
	 * @param element is the element to replace the list item with
	 * 
	 * @return the list item that was replaced
	 */
	@Override
	public E set(int index, E element) {
		/** Keeps track of the position within the list. */
		int counter = 0;
		/** The current position of the index. */
		Node<E> current = head;
		/** The previous position of the index. */
		Node<E> previous = null;
		
		if (size == 0 || index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		
		
		while (current != null && counter < index) {
			previous = current;
			current = current.next;
			counter++;
		}
		
		if (current != null) {
			if (current == head) {
				head = new Node<E>(element, head.next);
			} else {
				previous.next = new Node<E>(element, current.next);
			}
		}
		
		try {
			return current.data;
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * Gets an object in the linked list at a given index position.
	 * 
	 * @param index is the indexed position of the object to get
	 * @return the object at a given index position
	 */
	@Override
	public E get(int index) {
		/** A counter to keep track of where we are in traversing the LinkedList */
		int counter = 0;
		/** A pointer to hold the object at whatever index we are on. */
		Node<E> pointer = head;
		
		if (size == 0 || index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		while (counter < index && pointer != null) {
			pointer = pointer.next;
			counter++;
		}
		
		try {
			return pointer.data;
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * Gets the size of the LinkedList.
	 * 
	 * @return the size of the linked list as an integer.
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Defines the behavior for a node in the linked list. It is 
	 * the object reference to the next object in the list.
	 * 
	 * @author Benjamin Zich
	 *
	 * @param <E> is the next object in the list
	 */
	private class Node<E> {
		public E data;
		public Node<E> next;
		
		public Node(E data) {
			this.data = data;
		}
		
		public Node (E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
		
		
	}

}
