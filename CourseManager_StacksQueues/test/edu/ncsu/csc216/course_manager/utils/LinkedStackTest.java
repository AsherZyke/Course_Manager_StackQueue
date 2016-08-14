/**
 * 
 */
package edu.ncsu.csc216.course_manager.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;
import org.junit.Test;

/**
 * Tests the LinkedStack class.
 * 
 * @author Benjamin Zich
 *
 */
public class LinkedStackTest {
	
	/**
	 * Tests adding a single element to the stack, then adding
	 * multiple elements to the stack, then remove a single 
	 * element from the stack, then removing multiple elements
	 * from the stack, then interleaved inserts and removals from
	 * the stack, and finally attempting to remove an element
	 * from an empty stack.
	 */
	@Test
	public void testInsertAndRemoveElements() {
		String one = "apple";
		String two = "orange";
		String three = "banana";
		LinkedStack<String> testList = new LinkedStack<String>();
		
		//Inserts one object
		assertTrue(testList.isEmpty());
		testList.push(one);
		assertFalse(testList.isEmpty());
		assertEquals(one, testList.peek());
		
		//Removes one object
		assertFalse(testList.isEmpty());
		assertEquals(one, testList.peek());
		assertEquals(one, testList.pop());
		assertTrue(testList.isEmpty());
		
		//Inserts multiple objects
		assertTrue(testList.isEmpty());
		testList.push(one);
		assertFalse(testList.isEmpty());
		assertEquals(one, testList.peek());
		testList.push(two);
		assertEquals(two, testList.peek());
		testList.push(three);
		assertEquals(three, testList.peek());
		
		//Removes multiple items
		assertEquals(three, testList.pop());
		assertEquals(two, testList.pop());
		assertEquals(one, testList.pop());
		assertTrue(testList.isEmpty());
		
		//Interleaves adding and removal
		assertTrue(testList.isEmpty());
		testList.push(one);
		assertFalse(testList.isEmpty());
		assertEquals(one, testList.peek());
		testList.push(two);
		assertEquals(two, testList.pop());
		testList.push(two);
		assertEquals(two, testList.peek());
		testList.push(three);
		assertEquals(three, testList.pop());
		assertEquals(two, testList.pop());
		testList.push(two);
		assertEquals(two, testList.pop());
		assertEquals(one, testList.pop());
		assertTrue(testList.isEmpty());
		
		//Attempt to remove an element from an empty list
		try {
			assertTrue(testList.isEmpty());
			testList.pop();
			fail();
		} catch (EmptyStackException e) {
			assertTrue(testList.isEmpty());
		}
	}
	
	


}
