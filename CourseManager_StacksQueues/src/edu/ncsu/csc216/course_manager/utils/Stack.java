package edu.ncsu.csc216.course_manager.utils;


/**
 * Defines the behavior for the Stack interface. A Stack is 
 * a list that generally follows the last-in-first-out
 * ordering.
 * 
 * @author Benjamin Zich
 *
 * @param <E> the type of object that the Stack will be
 * restricted to when the implementing classes are instantiated.
 * 
 */
public interface Stack<E> {
	
	/**
	 * Add an object to the top of the stack. Any other items
	 * already in the stack will be pushed down in the stack
	 * so that the most recently added object is at the front
	 * of the stack.
	 * 
	 * @param obj the object to be put at the front of the stack
	 */
	public void push(E obj);
	
	/**
	 * Removes the top item from the stack. Returns the value of the
	 * top item.
	 * 
	 * @return the top item from the stack
	 */
	public E pop();
	
	/**
	 * Returns the value of the top item of the stack without
	 * removing the item from the top of the stack.
	 * 
	 * @return the object from the top of the stack
	 */
	public E peek();
	
	/**
	 * Tells the calling code whether the stack is empty.
	 * 
	 * @return true if empty and false if not empty
	 */
	public boolean isEmpty();

}
