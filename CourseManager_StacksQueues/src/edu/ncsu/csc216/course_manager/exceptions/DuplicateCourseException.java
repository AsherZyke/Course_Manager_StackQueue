/**
 * 
 */
package edu.ncsu.csc216.course_manager.exceptions;

/**
 * Defines the behavior for the DuplicateCourseException class.
 * 
 * @author Benjamin Zich
 *
 */
public class DuplicateCourseException extends RuntimeException {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a DuplicateCourseException with the
	 * default message.
	 */
	public DuplicateCourseException() {
		super("Course already exists in the system.");
	}



}
