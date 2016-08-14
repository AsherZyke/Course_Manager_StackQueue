/**
 * 
 */
package edu.ncsu.csc216.course_manager.exceptions;

/**
 * Defines the behavior for the CourseCapacityException class.
 * 
 * @author Benjamin Zich
 *
 */
public class CourseCapacityException extends RuntimeException {
	
	/**
	 * CourseCapacityException constructor with default message.
	 */
	public CourseCapacityException() {
		super("Course is at capacity.");
	}
	

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

}
