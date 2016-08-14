/**
 * 
 */
package edu.ncsu.csc216.course_manager.manager;

import edu.ncsu.csc216.course_manager.courses.Course;
import edu.ncsu.csc216.course_manager.exceptions.CourseCapacityException;
import edu.ncsu.csc216.course_manager.exceptions.DuplicateCourseException;
import edu.ncsu.csc216.course_manager.exceptions.DuplicateStudentException;
import edu.ncsu.csc216.course_manager.exceptions.StudentAlreadyEnrolledException;
import edu.ncsu.csc216.course_manager.users.Student;
import edu.ncsu.csc216.course_manager.utils.LinkedList;

/**
 * Defines the behavior of the EnrollmentManager class.
 * 
 * @author Benjamin Zich
 *
 */
public class EnrollmentManager {
	/**List of Courses in the system.*/
	private LinkedList<Course> courses;
	/** List of Students in the system.*/
	private LinkedList<Student> students;
	
	/**
	 * Constructs an EnrollmentManager object with empty
	 * courses and students lists.
	 */
	public EnrollmentManager() {
		courses = new LinkedList<Course>();
		students = new LinkedList<Student>();
	}
	
	/**
	 * Adds a Course to the system if it doesn't already exist.
	 * 
	 * @param c the course to add to the system
	 * @throws DuplicateCourseException if the Course already exists.
	 */
	public void addCourse(Course c) {
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getName().equalsIgnoreCase(c.getName())) {
				throw new DuplicateCourseException();
			}
		}
		courses.add(c);
	}
	
	/**
	 * Add a Student to the system if it doesn't already exist.
	 * 
	 * @param s the student to add to the system
	 * @throws DuplicateStudentException if the Student already exists
	 */
	public void addStudent(Student s) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getId().equals(s.getId())) {
				throw new DuplicateStudentException();
			}
		}
		students.add(s);
	}
	
	/**
	 * Attempts to add a student to a Course, adds student to the waitlist
	 * if not able to add to the Course. 
	 * 
	 * @param s the Student to add to the Course
	 * @param c the Course to which a Student will be added
	 * @return true if the Student is added to the Course and false if the Student
	 * is added to the waitlist.
	 * @throws CourseCapacityException if there is no more room in the course or the
	 * waitlist
	 * @throws StudentAlreadyEnrolledException if the Student is already enrolled in the 
	 * Course
	 */
	public boolean addStudentToCourse(Student s, Course c) {
		if(c.getCapacity() <= c.getEnrolledStudents().length && c.waitListIsFull()) {
			throw new CourseCapacityException();
		}
		if (!c.isNotEnrolled(s)) {
			throw new StudentAlreadyEnrolledException();
		}
		c.enroll(s);
		return (!c.isWaitListed(s) && !c.isNotEnrolled(s));
	}
	
	/**
	 * Removes a student from the main roll of a Course and replaces the Student with one from
	 * the waitlist if there are any Students on the waitlist.
	 * 
	 * @param s the Student to remove from the Course
	 * @param c the Course to remove a Student from
	 * @return true if the Student is removed from the Course, false if the student is not enrolled
	 * in the Course
	 */
	public boolean removeStudentFromCourse(Student s, Course c) {
		return c.drop(s);
	}
	
	/**
	 * Gets a matching Student from the list if one exists.
	 * 
	 * @param s the Student to match
	 * @return the Student in the list
	 */
	public Student getMatchingStudent(Student s) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getId().equalsIgnoreCase(s.getId())) {
				return students.get(i);
			}
		}
		throw new IllegalArgumentException("No such student exists in the system.");
	}
	
	/**
	 * Gets a matching Course from the list if one exists.
	 * 
	 * @param c the Course to find a match of
	 * @return the matching Course
	 */
	public Course getMatchingCourse(Course c) {
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getName().equalsIgnoreCase(c.getName())) {
				return courses.get(i);
			}
		}
		throw new IllegalArgumentException("No such course exists in teh system.");
	}

}
