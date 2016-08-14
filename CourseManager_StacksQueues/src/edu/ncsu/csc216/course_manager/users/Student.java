package edu.ncsu.csc216.course_manager.users;

import java.util.ArrayList;

import edu.ncsu.csc216.course_manager.courses.Course;

/**
 * Creates the Student class that defines the behavior that
 * a student can have. The student can enroll in courses
 * up to a certain amount of credit hours.
 * 
 * @author Benjamin Zich
 *
 */
public class Student extends User {
	/** A list of all the courses that a student is enrolled in. */
	private ArrayList<Course> courses;
	/** The maximum credits a student is taking. */
	private int maxCredits;
	/** The maximum number of credits a student can take. */
	public static final int MAX_CREDITS = 18;

	
	
	/**
	 * Constructs the Student object.
	 * 
	 * @param firstName the student's first name
	 * @param lastName the student's last name
	 * @param id the student's id
	 */
	public Student(String firstName, String lastName, String id) {
		this(firstName, lastName, id, id + "@ncsu.edu", id, MAX_CREDITS);
	}
	/**
	 * Constructs the Student object.
	 * 
	 * @param firstName
	 *            the student's first name
	 * @param lastName
	 *            the student's last name
	 * @param id
	 *            the student's id
	 * @param email
	 *            the student's email
	 * @param password
	 *            the student's password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}

	/**
	 * Constructs the Student object.
	 * 
	 * @param firstName
	 *            the student's first name
	 * @param lastName
	 *            the student's last name
	 * @param id
	 *            the student's id
	 * @param email
	 *            the student's email
	 * @param password
	 *            the student's password
	 * @param maxCredits
	 *            the maximum amount of credits a student can take
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		courses = new ArrayList<Course>();
		setMaxCredits(maxCredits);
	}

	/**
	 * Determines whether a student can add a course.
	 * 
	 * @param c
	 *            the course to see if it can be added
	 * @return true if the course can be added, false if otherwise
	 */
	@Override
	public boolean canAddCourse(Course c) {
		if ((c.getCredits() + this.getCurrentCredits()) <= maxCredits) {
			if (!courses.contains(c)) {
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * Adds the course to the Student object.
	 * 
	 * @param c
	 *            the course to be added
	 * @return true when the course is added, false if otherwise
	 */
	@Override
	public boolean addCourse(Course c) {
		if (this.canAddCourse(c)) {
			courses.add(c);
			if (courses.contains(c)) {
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * Removes a course from a Student's collection of courses.
	 * 
	 * @param c
	 *            the course to be removed from the collection
	 * @return true if the course has been removed, false if otherwise
	 */
	@Override
	public boolean removeCourse(Course c) {
		return courses.remove(c);
	}

	/**
	 * Gets a courses from a Student's collection.
	 * 
	 * @return the collection of courses as an Array
	 * 
	 */
	@Override
	public Course[] getCourses() {
		Course[] c = new Course[courses.size()];
		return courses.toArray(c);
	}

	/**
	 * Gets the current credits in the student's schedule.
	 * 
	 * @return credits the number of credits
	 */
	public int getCurrentCredits() {
		int credits = 0;
		for (Course j : courses) {
			credits += j.getCredits();
		}
		return credits;
	}

	/**
	 * Gets the maximum credits.
	 * 
	 * @return maxCredits the maximum credits that a student can take
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the maximum number of credits that a Student can take.
	 * 
	 * @param maxCredits
	 *            the maxCredits to set
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 0 || maxCredits > MAX_CREDITS || maxCredits < this.getCurrentCredits()) {
			throw new IllegalArgumentException();
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Converts the courses and the max credits into hash code.
	 * 
	 * @return result the hash code of max credits and courses
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Compares this Student with another object for equality.
	 * 
	 * @param obj
	 *            the object for comparison with this Student
	 * @return true if the object is the same as this student, false if
	 *         otherwise
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}

	/**
	 * Gets the Student as a String object.
	 * 
	 * @return Student as a formatted String
	 * 
	 */
	@Override
	public String toString() {
		String courseList = "";
		for (Course list : courses) {
			courseList += "," + list.getName();
		}
		return super.toString() + "," + maxCredits + courseList;
	}

}
