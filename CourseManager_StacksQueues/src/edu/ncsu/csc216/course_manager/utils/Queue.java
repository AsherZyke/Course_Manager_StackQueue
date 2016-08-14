/**
 * 
 */
package edu.ncsu.csc216.course_manager.utils;

/**
 *  Defines the behavior for the Queue interface. A Queue is 
 * a list that generally follows the first-in-first-out
 * ordering.
 * 
 * @author Benjamin Zich
 *
 * @param <E> the type of object that the Stack will be
 * restricted to when the implementing classes are instantiated.
 *
 */
public interface Queue<E> {
	
	/**
	 * Adds the given element to the end
	 * of the queue.
	 * 
	 * @param obj is the object to be added
	 * to the end of the queue
	 */
	public void enqueue(E obj);
	
	/**
	 * Removes and returns the element at the
	 * front of the queue.
	 * 
	 * @return the element at the front of the queue
	 */
	public E dequeue();
	
	/**
	 * Tells calling code whether the queue is empty.
	 * 
	 * @return true if empty and false if not empty
	 */
	public boolean isEmpty();

}
