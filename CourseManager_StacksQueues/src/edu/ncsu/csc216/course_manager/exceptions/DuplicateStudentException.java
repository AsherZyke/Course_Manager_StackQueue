/**
 * 
 */
package edu.ncsu.csc216.course_manager.exceptions;

/**
 * Defines the behavior for the DuplicateStudentException class.
 * 
 * @author Benjamin Zich
 *
 */
public class DuplicateStudentException extends RuntimeException {
	

	/**
	 * Constructs a DuplicateStudentException object with the
	 * default message.
	 */
	public DuplicateStudentException() {
		super("Student already exists in the system.");
	}
	


	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

}
