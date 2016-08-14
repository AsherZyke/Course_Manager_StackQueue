/**
 * 
 */
package edu.ncsu.csc216.course_manager.exceptions;

/**
 * Defines the behavior for the StudentAlreadyEnrolledException class.
 * 
 * @author Benjamin Zich
 *
 */
public class StudentAlreadyEnrolledException extends RuntimeException {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a StudentAlreadyEnrolledException with
	 * the default message.
	 */
	public StudentAlreadyEnrolledException() {
		super("Student is already enrolled in the Course.");
	}



}
