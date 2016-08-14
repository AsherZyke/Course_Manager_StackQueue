/**
 * 
 */
package edu.ncsu.csc216.course_manager.courses;

import java.util.ArrayList;

import edu.ncsu.csc216.course_manager.users.Student;
import edu.ncsu.csc216.course_manager.users.User;
import edu.ncsu.csc216.course_manager.utils.LinkedQueue;

/**
 * Implements the Course class behavior.
 * 
 * @author SarahHeckman and Benjamin Zich
 *
 */
public class Course implements Enrollable {
	
	//TODO Add your data structure for the Course wait list here.
	//TODO Provide a reasoning for WHY you selected a Stack vs. Queue
	//and why you selected the ArrayList or LinkedList version of the 
	//Stack or Queue as a comment on the field.
	/** 
	 * I chose the Queue structure because a waitlist is also a
	 * first-in-first-out list. I chose the LinkedList version
	 * because it seemed to better reflect the fluid nature of
	 * a waitlist, with students dropping from arbitrary parts
	 * of the waitlist as they delist themselves from the waitlist
	 * during the drop/add phase enrollment.
	 */
	private LinkedQueue<User> waitlist;
	
	/** Course name */
	private String name;
	/** Course credit hours */
	private int credits;
	/** Course capacity */
	private int capacity;
	/** Students enrolled in the course */
	private ArrayList<User> enrolledStudents;
	/** Minimum credit hours for a course */
	public static final int MIN_HOURS = 1;
	/** Maximum credit hours for a course */
	public static final int MAX_HOURS = 4;
	/** The max size of the waitlist */
	public static final int MAX_WAITLIST = 5;
	
	/**
	 * Constructs a Course with 3 credit hours
	 * and a capacity of 3.
	 * 
	 * @param name the name of the course.
	 */
	public Course(String name) {
		this(name, 3, 3);
	}
	
	/**
	 * Creates a Course with the given name and credit hours.
	 * @param name course name
	 * @param credits course credit hours
	 * @param capacity course capacity
	 */
	public Course(String name, int credits, int capacity) {
		super();
		enrolledStudents = new ArrayList<User>();
		setName(name);
		setCredits(credits);
		setCapacity(capacity);
		waitlist = new LinkedQueue<User>();
	}

	/**
	 * Returns the course name.
	 * @return the course name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the course name to the given name.  An IllegalArgumentException is
	 * thrown if the name is null or an empty string.
	 * @param name the course name
	 */
	public void setName(String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	/**
	 * Returns the course's credit hours.
	 * @return the course's credit hours
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the course credit hours to the given value.  An IllegalArgumentException is
	 * thrown if the value is less than MIN_HOUR and greater than MAX_HOURS.
	 * @param credits the course's credit hours
	 */
	public void setCredits(int credits) {
		if (credits < MIN_HOURS || credits > MAX_HOURS) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}

	/**
	 * Returns the course's capacity.
	 * @return the course's capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Sets the course's capacity to the given value.  An IllegalArgumentException is
	 * thrown if the value is less than 1 or if the new capacity is less than the 
	 * number of students currently enrolled in the course.
	 * @param capacity course's capacity
	 */
	public void setCapacity(int capacity) {
		if (capacity < 1  || capacity < enrolledStudents.size()) {
			throw new IllegalArgumentException();
		} 
		this.capacity = capacity;
	}

	/**
	 * Returns the enrolled students as an array.
	 * @return enrolled students
	 */
	public Student [] getEnrolledStudents() {
		Student [] s = new Student[enrolledStudents.size()];
		return enrolledStudents.toArray(s);
	}
	
	/**
	 * Returns true if there is capacity to add a user to the course and the 
	 * user is not already enrolled.
	 * @param user User to add to the course
	 * @return true if there is capacity
	 */
	public boolean canEnroll(User user) {
		if (enrolledStudents.size() < capacity) {
			if (user instanceof Student) {
				Student s = (Student) user;
				for (int i = 0; i < enrolledStudents.size(); i++) {
					if (enrolledStudents.get(i).equals(s)) {
						return false;
					}
				}
				return true;
			}
			return false;
		}
		return false;
	}
	
	/**
	 * Enroll the user in the course if there is room.
	 * @param user user to enroll
	 * @return true if user is enrolled.
	 */
	public boolean enroll(User user) {
		if (enrolledStudents.size() >= capacity && isNotEnrolled(user)) {
			if (!waitListIsFull() && !isWaitListed(user)) {
				waitlist.enqueue(user);
				return true;
			} 
		} else if (canEnroll(user) && isWaitListed(user)) {
			removeFromWaitlist(user);
		}
		return canEnroll(user) && enrolledStudents.add(user);
	}
	
	/**
	 * Drops the student from the course.
	 * 
	 * @param user student to drop
	 * 
	 * @return true if the student is dropped
	 */
	public boolean drop(User user) {
		if (isWaitListed(user)) {
			removeFromWaitlist(user);
			return true;
		}
		if (!isNotEnrolled(user)) {
			enrolledStudents.remove(user);
			if (!waitlist.isEmpty()) {
				this.enroll(waitlist.dequeue());
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a user from the waitlist. Leaves
	 * the order of the other students on the waitlist 
	 * unchanged.
	 * 
	 * @param user the user to remove from the waitlist
	 */
	public void removeFromWaitlist(User user) {
		LinkedQueue<User> temp = new LinkedQueue<User>();
		
		while (!waitlist.isEmpty()) {
			User current = waitlist.dequeue();
			if (user instanceof Student) {
				Student s = (Student) user;
				if (!current.equals(s)) {
					temp.enqueue(current);
				}
			}
		}
		waitlist = temp;
	}
	
	/**
	 * Checks to see if the user is already on the waitlist.
	 * 
	 * @param user the user that may be on the waitlist
	 * @return true if the user is on the waitlist,
	 * false if not on the waitlist
	 */
	public boolean isWaitListed(User user) {
		LinkedQueue<User> temp = new LinkedQueue<User>();
		boolean isInList = false;
		
		while (!waitlist.isEmpty()) {
			User current = waitlist.dequeue();
			if (user instanceof Student) {
				Student s = (Student) user;
				if (current.equals(s)) {
					isInList = true;
				}
			temp.enqueue(current);
			}
		}
		waitlist = temp;
		return isInList;
	}
	
	/**
	 * Checks to see if the waitlist is full.
	 * 
	 * @return true if the waitlist has reached its
	 * maximum capacity
	 */
	public boolean waitListIsFull() {
		int counter = 0;
		LinkedQueue<User> temp = new LinkedQueue<User>();
		
		while (!waitlist.isEmpty()) {
			User current = waitlist.dequeue();
			temp.enqueue(current);
			counter++;
		}
		waitlist = temp;
		return counter >= MAX_WAITLIST;
	}
	
	/**
	 * Sees if a student is already enrolled in the course.
	 * 
	 * @param user the user that may already be enrolled in the course
	 * @return true if student is enrolled, false if not
	 */
	public boolean isNotEnrolled(User user) {
		if (user instanceof Student) {
			Student s = (Student) user;
			for (int i = 0; i < enrolledStudents.size(); i++) {
				if (enrolledStudents.get(i).equals(s)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the current waitlist for the course. 
	 * 
	 * @return the current waitlist for the course
	 */
	public LinkedQueue<User> getWaitlist() {
		return waitlist;
	}

	/**
	 * Returns the hashCode for a course from the course's fields.
	 * @return the hashCode for the course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Compares courses by their name.  
	 * @param obj object to check for equality of
	 * @return true if objects are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name + "," + credits + "," + capacity;
	}

}
