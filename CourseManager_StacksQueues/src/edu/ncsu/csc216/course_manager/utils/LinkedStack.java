/**
 * 
 */
package edu.ncsu.csc216.course_manager.utils;

import java.util.EmptyStackException;

/**
 * Defines the behavior for the LinkedStack class. 
 * A linked stack class is one that implements 
 * the functionality of a stack list with a linked list
 *  based object.
 *  
 * @author Benjamin Zich
 * 
 * @param <E> is the object type that will define the class
 * instantiation
 *
 */
public class LinkedStack<E> implements Stack<E> {
	/** The linked list to hold the stack */
	private LinkedList<E> list;
	/** The front of the list */
	private static final int FRONT = 0;
	
	/**
	 * Constructs the LinkedStack object. 
	 */
	public LinkedStack() {
		list = new LinkedList<E>();
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.course_manager.utils.Stack#push(java.lang.Object)
	 */
	@Override
	public void push(E obj) {
		list.add(FRONT, obj);
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.course_manager.utils.Stack#pop()
	 */
	@Override
	public E pop() {
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}
		return list.remove(FRONT);
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.course_manager.utils.Stack#peek()
	 */
	@Override
	public E peek() {
		return list.get(FRONT);
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.course_manager.utils.Stack#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
