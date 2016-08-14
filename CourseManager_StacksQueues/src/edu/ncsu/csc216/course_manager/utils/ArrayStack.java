/**
 * 
 */
package edu.ncsu.csc216.course_manager.utils;

import java.util.EmptyStackException;

/**
 * Defines the behavior for the ArrayStack class. 
 * An array stack class is one that implements 
 * the functionality of a stack list with an array based
 * object.
 * 
 * @author Benjamin Zich
 * 
 * @param <E> is the object type that will define the instantiation
 *
 */
public class ArrayStack<E> implements Stack<E> {
	/** The array list to hold the stack */
	private ArrayBasedList<E> list;
	/** The front of the list */
	private static final int FRONT = 0;
	
	/**
	 * Constructs the ArrayStack object.
	 */
	public ArrayStack() {
		list = new ArrayBasedList<E>();
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
