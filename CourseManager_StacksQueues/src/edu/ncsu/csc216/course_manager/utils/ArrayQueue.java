package edu.ncsu.csc216.course_manager.utils;

import java.util.NoSuchElementException;

/**
 * Defines the behavior for the ArrayQueue class. 
 * A queue stack class is one that implements 
 * the functionality of a queue list with an array based
 * object.
 * 
 * @author Benjamin Zich
 * 
 * @param <E> is the object type that will define the class
 * instantiation
 *
 */
public class ArrayQueue<E> implements Queue<E> {
	/**An array list to hold the queue */
	ArrayBasedList<E> list;
	/** The front of the list */
	private static final int FRONT = 0;
	/** The back of the list */
	private int back = 0;
	
	/**
	 * Constructs the ArrayQueue object.
	 */
	public ArrayQueue() {
		list = new ArrayBasedList<E>();
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
