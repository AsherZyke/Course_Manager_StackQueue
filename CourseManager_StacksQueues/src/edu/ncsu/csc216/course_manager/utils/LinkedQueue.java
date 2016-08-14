/**
 * 
 */
package edu.ncsu.csc216.course_manager.utils;

import java.util.NoSuchElementException;

/**
 * Defines the behavior for the LinkedQueue class. 
 * A linked queue class is one that implements 
 * the functionality of a queue list with a linked list
 *  based object.
 *  
 * @author Benjamin Zich
 *
 *@param <E> is the object type that will define this class
 *instantion.
 *
 */
public class LinkedQueue<E> implements Queue<E> {
	/**A linked list to hold the queue */
	LinkedList<E> list;
	/** The front of the list */
	private static final int FRONT = 0;
	/** The end of the list */
	private int back = 0;
	
	/**
	 * Constructs the LinkedQueue object.
	 */
	public LinkedQueue() {
		list = new LinkedList<E>();
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.course_manager.utils.Queue#enqueue(java.lang.Object)
	 */
	@Override
	public void enqueue(E obj) {
		list.add(back, obj);
		back++;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.course_manager.utils.Queue#dequeue()
	 */
	@Override
	public E dequeue() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		back--;
		return list.remove(FRONT);
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.course_manager.utils.Queue#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
