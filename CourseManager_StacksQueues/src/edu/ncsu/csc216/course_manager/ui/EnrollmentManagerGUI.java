/**
 * 
 */
package edu.ncsu.csc216.course_manager.ui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.ncsu.csc216.course_manager.courses.Course;
import edu.ncsu.csc216.course_manager.manager.EnrollmentManager;
import edu.ncsu.csc216.course_manager.users.Student;

/**
 * Defines the behavior of the EnrollmentManagerGUI class.
 * 
 * @author Benjamin Zich
 *
 */
public class EnrollmentManagerGUI extends JFrame implements ActionListener {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	//Constants
	private static final String ENROLL = "Enroll Student";
	private static final String DROP = "Drop Student";
	private static final String ERROR = "ERROR";
	private static final String ADDED = "Student was added to course.";
	private static final String DROPPED = "Student was dropped from the course.";
	private static final String NEW_STUDENT = "New student was added to the system.";
	private static final String NEW_COURSE = "New course was added to the system.";
	private static final String NOT_DROPPED = "Student could not be dropped from the course. Probably because the student"
			+ "was not enrolled in the course.";
	private static final String ADDED_TO_WAITLIST = "Student was added to the waitlist.";
	private static final String SUCCESS = "Success";
	private static final String FAILURE = "Failure";
	private static final int FRAME_WIDTH = 500; 
	private static final int FRAME_HEIGHT = 400; 
	private static final int DISTANCE = 100; 
	
	//Panel title
	private static final String WINDOW_TITLE = "Enrollment Manager";
	
	// Buttons and combo box
	private JButton btnEnrollStudent = new JButton(ENROLL);
	private JButton btnDropStudent = new JButton(DROP);
	
	//Text fields
	JTextField tfFirstNameText = new JTextField();
	JTextField tfLastNameText = new JTextField();
	JTextField tfStudentIDText = new JTextField();
	JTextField tfCourseText = new JTextField();
	
	private Container mainWindow = getContentPane();
	
	private EnrollmentManager manager = new EnrollmentManager();
	
	/**
	 * Runs the GUI.
	 */
	public EnrollmentManagerGUI() {
		try {
			initializeUI();
			this.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	/**
	 * Creates the UI interface.
	 */
	private void initializeUI() {
		
	//  Initialize the main frame parameters.
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle(WINDOW_TITLE);
		setLocation(DISTANCE, DISTANCE);
		
		mainWindow.setLayout(new GridLayout(5, 2));
		
		mainWindow.add(new JLabel("Student First Name: "));
		mainWindow.add(tfFirstNameText);
		
		mainWindow.add(new JLabel("Student Last Name: "));
		mainWindow.add(tfLastNameText);
		
		mainWindow.add(new JLabel("Student ID: "));
		mainWindow.add(tfStudentIDText);
		
		mainWindow.add(new JLabel("Course to Add/Drop: "));
		mainWindow.add(tfCourseText);
		
		mainWindow.add(btnEnrollStudent);
		mainWindow.add(btnDropStudent);
		
		
		// Enable buttons to respond to events.
		addListeners();
		
		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		}); 
	}
	
	/**
	 * Responds to actions in the UI.
	 * 
	 * @param ae the action events in the UI.
	 */
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(btnEnrollStudent)) {
			Student newStudent = null;
			Course newCourse = null;
			try {
				newStudent = new Student(tfFirstNameText.getText(), 
						tfLastNameText.getText(), tfStudentIDText.getText());
				newCourse = new Course(tfCourseText.getText());
				manager.addStudent(newStudent);
				try {
					manager.addCourse(newCourse);
					try {
						if(manager.addStudentToCourse(newStudent, newCourse)) {
							JOptionPane.showMessageDialog(new JFrame(), NEW_STUDENT, SUCCESS, JOptionPane.INFORMATION_MESSAGE);
							JOptionPane.showMessageDialog(new JFrame(), NEW_COURSE, SUCCESS, JOptionPane.INFORMATION_MESSAGE);
							JOptionPane.showMessageDialog(new JFrame(), ADDED, SUCCESS, JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e) {
					Course oldCourse = manager.getMatchingCourse(newCourse);
					try {
						if(manager.addStudentToCourse(newStudent, oldCourse)) {
							JOptionPane.showMessageDialog(new JFrame(), NEW_STUDENT, SUCCESS, JOptionPane.INFORMATION_MESSAGE);
							JOptionPane.showMessageDialog(new JFrame(), ADDED, SUCCESS, JOptionPane.INFORMATION_MESSAGE);
						} else if (oldCourse.isWaitListed(newStudent)) {
							JOptionPane.showMessageDialog(new JFrame(), ADDED_TO_WAITLIST, SUCCESS, JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (Exception t) {
						JOptionPane.showMessageDialog(new JFrame(), t.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
					}
					JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), ERROR, JOptionPane.INFORMATION_MESSAGE);
				}
					
			} catch (Exception e) {
				
				Student oldStudent = manager.getMatchingStudent(newStudent);
				try {
					manager.addCourse(newCourse);
					try {
						if(manager.addStudentToCourse(oldStudent, newCourse)) {
							JOptionPane.showMessageDialog(new JFrame(), NEW_COURSE, SUCCESS, JOptionPane.INFORMATION_MESSAGE);
							JOptionPane.showMessageDialog(new JFrame(), ADDED, SUCCESS, JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (Exception w) {
						JOptionPane.showMessageDialog(new JFrame(), w.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception d) {
					Course oldCourse = manager.getMatchingCourse(newCourse);
					try {
						if(manager.addStudentToCourse(oldStudent, oldCourse)) {
							JOptionPane.showMessageDialog(new JFrame(), ADDED, SUCCESS, JOptionPane.INFORMATION_MESSAGE);
						} else if (oldCourse.isWaitListed(newStudent)) {
							JOptionPane.showMessageDialog(new JFrame(), ADDED_TO_WAITLIST, SUCCESS, JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (Exception t) {
						JOptionPane.showMessageDialog(new JFrame(), t.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
					}
					JOptionPane.showMessageDialog(new JFrame(), d.getMessage(), ERROR, JOptionPane.INFORMATION_MESSAGE);
				}
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), ERROR, JOptionPane.INFORMATION_MESSAGE);
			}
			resetTextFields();
		}
		
		if (ae.getSource().equals(btnDropStudent)) {
			Student studentDropping = null;
			Course courseToDrop = null;
			try {
				studentDropping = new Student(tfFirstNameText.getText(), 
						tfLastNameText.getText(), tfStudentIDText.getText());
				courseToDrop = new Course(tfCourseText.getText());
				Student actualStudent = manager.getMatchingStudent(studentDropping);
				Course actualCourse = manager.getMatchingCourse(courseToDrop);
				if (manager.removeStudentFromCourse(actualStudent, actualCourse)) {
					JOptionPane.showMessageDialog(new JFrame(), DROPPED, SUCCESS, JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), NOT_DROPPED, FAILURE, JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception t) {
				JOptionPane.showMessageDialog(new JFrame(), t.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
			}
			resetTextFields();
		}
		
	}
	
	/**
	 * Resets all text fields to blank.
	 */
	private void resetTextFields() {
		tfFirstNameText.setText("");
		tfLastNameText.setText("");
		tfStudentIDText.setText("");
		tfCourseText.setText("");
	}
	
	/**
	 * Private method -- Adds action listeners to all the buttons.
	 */
	private void addListeners() {
		btnEnrollStudent.addActionListener(this);
		btnDropStudent.addActionListener(this);
	}
	
	
	/**
	 * Runs the program.
	 * 
	 * @param args the arguments for the program.
	 */
	public static void main(String[] args) {
		new EnrollmentManagerGUI();

	}

}
