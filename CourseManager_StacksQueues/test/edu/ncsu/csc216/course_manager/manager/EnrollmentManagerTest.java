/**
 * 
 */
package edu.ncsu.csc216.course_manager.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.course_manager.courses.Course;
import edu.ncsu.csc216.course_manager.exceptions.CourseCapacityException;
import edu.ncsu.csc216.course_manager.exceptions.DuplicateCourseException;
import edu.ncsu.csc216.course_manager.exceptions.DuplicateStudentException;
import edu.ncsu.csc216.course_manager.exceptions.StudentAlreadyEnrolledException;
import edu.ncsu.csc216.course_manager.users.Student;

/**
 * Tests the EnrollmentMAnager class.
 * 
 * @author Benjamin Zich
 *
 */
public class EnrollmentManagerTest {
	
	/** The EnrollmentManager to test.*/
	EnrollmentManager manager;

	/**
	 * Sets up the EnrollmentManager to test.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		manager = new EnrollmentManager();
	}

	/**
	 * Tests the addCourse method.
	 */
	@Test
	public void testAddCourse() throws DuplicateCourseException {
		Course c1 = new Course("CSC116");
		Course c2 = new Course("CSC226");
		Course c3 = new Course("CSC116");
		manager.addCourse(c1);
		manager.addCourse(c2);
		try {
			manager.addCourse(c3);
		} catch (DuplicateCourseException e) {
			assertEquals("Course already exists in the system.", e.getMessage());
		}
	}
	
	/**
	 * Tests the addCourse method.
	 */
	@Test
	public void testAddStudent() throws DuplicateStudentException {
		Student s1 = new Student("First", "Last", "id1");
		Student s2 = new Student("First", "Last", "id2");
		Student s3 = new Student("First", "Last", "id1");
		manager.addStudent(s1);
		manager.addStudent(s2);
		try {
			manager.addStudent(s3);
		} catch (DuplicateStudentException e) {
			assertEquals("Student already exists in the system.", e.getMessage());
		}
	}
	
	/**
	 * Tests the addStudentToCourse method.
	 * @throws StudentAlreadyEnrolledException 
	 * @throws CourseCapacityException 
	 */
	@Test
	public void testAddStudentToCourse() throws CourseCapacityException, StudentAlreadyEnrolledException {
		Student s1 = new Student("First", "Last", "id1");
		Student s2 = new Student("First", "Last", "id2");
		Student s3 = new Student("First", "Last", "id3");
		Student s4 = new Student("First", "Last", "id4");
		Student s5 = new Student("First", "Last", "id5");
		Student s6 = new Student("First", "Last", "id6");
		Student s7 = new Student("First", "Last", "id7");
		Student s8 = new Student("First", "Last", "id8");
		Student s9 = new Student("First", "Last", "id9");
		Course c1 = new Course("CSC116");
		assertTrue(manager.addStudentToCourse(s1, c1));
		try {
			manager.addStudentToCourse(s1, c1);
		} catch (StudentAlreadyEnrolledException e) {
			assertEquals("Student is already enrolled in the Course.", e.getMessage());
		}
		assertTrue(manager.addStudentToCourse(s2, c1));
		assertTrue(manager.addStudentToCourse(s3, c1));
		assertFalse(manager.addStudentToCourse(s4, c1));
		assertFalse(manager.addStudentToCourse(s5, c1));
		assertFalse(manager.addStudentToCourse(s6, c1));
		assertFalse(manager.addStudentToCourse(s7, c1));
		assertFalse(manager.addStudentToCourse(s8, c1));
		try {
			manager.addStudentToCourse(s9, c1);
		} catch (CourseCapacityException e) {
			assertEquals("Course is at capacity.", e.getMessage());
		}
	}
	
	/**
	 * Tests the removeStudentFromCourse method.
	 * @throws StudentAlreadyEnrolledException 
	 * @throws CourseCapacityException 
	 */
	@Test
	public void testRemoveStudentFromCourse() throws CourseCapacityException, StudentAlreadyEnrolledException {
		Student s1 = new Student("First", "Last", "id1");
		Student s2 = new Student("First", "Last", "id2");
		Student s3 = new Student("First", "Last", "id3");
		Student s4 = new Student("First", "Last", "id4");
		Student s5 = new Student("First", "Last", "id5");
		Student s6 = new Student("First", "Last", "id6");
		Student s7 = new Student("First", "Last", "id7");
		Student s8 = new Student("First", "Last", "id8");
		Course c1 = new Course("CSC116");
		
		assertTrue(manager.addStudentToCourse(s1, c1));
		assertTrue(manager.addStudentToCourse(s2, c1));
		assertTrue(manager.addStudentToCourse(s3, c1));
		assertFalse(manager.addStudentToCourse(s4, c1));
		assertFalse(manager.addStudentToCourse(s5, c1));
		assertFalse(manager.addStudentToCourse(s6, c1));
		assertFalse(manager.addStudentToCourse(s7, c1));
		assertFalse(manager.addStudentToCourse(s8, c1));
		assertTrue(manager.removeStudentFromCourse(s1, c1));
		assertTrue(manager.removeStudentFromCourse(s2, c1));
		assertTrue(manager.removeStudentFromCourse(s3, c1));
		assertTrue(manager.removeStudentFromCourse(s4, c1));
		assertTrue(manager.removeStudentFromCourse(s5, c1));
		assertTrue(manager.removeStudentFromCourse(s6, c1));
		assertTrue(manager.removeStudentFromCourse(s7, c1));
		assertTrue(manager.removeStudentFromCourse(s8, c1));
	}
	
	/**
	 * Tests the getMatchingStudent and getMatchingCourse methods.
	 */
	@Test
	public void testGetMatching() {
		Student s1 = new Student("First", "Last", "id1");
		Student s3 = new Student("First", "Last", "id1");

		Course c1 = new Course("CSC116");
		Course c2 = new Course("CSC116");
		
		manager.addCourse(c1);
		manager.addStudent(s1);
		assertEquals(c1, manager.getMatchingCourse(c2));
		assertEquals(s1, manager.getMatchingStudent(s3));
	}
	

}
