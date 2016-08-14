/**
 * 
 */
package edu.ncsu.csc216.course_manager.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests the LinkedQueue class.
 * 
 * @author Benjamin Zich
 *
 */
public class LinkedQueueTest {

	/**
	 * Tests adding a single element to the queue, then adding
	 * multiple elements to the queue, then remove a single 
	 * element from the queue, then removing multiple elements
	 * from the queue, then interleaved inserts and removals from
	 * the queue, and finally attempting to remove an element
	 * from an empty queue.
	 */
	@Test
	public void testInsertAndRemoveElements() {
		String one = "apple";
		String two = "orange";
		String three = "banana";
		LinkedQueue<String> testList = new LinkedQueue<String>();
		
		//Inserts one object
		assertTrue(testList.isEmpty());
		testList.enqueue(one);
		assertFalse(testList.isEmpty());
		
		//Removes one object
		assertFalse(testList.isEmpty());
		assertEquals(one, testList.dequeue());
		assertTrue(testList.isEmpty());
		
		//Inserts multiple objects
		assertTrue(testList.isEmpty());
		testList.enqueue(one);
		testList.enqueue(two);
		testList.enqueue(three);
		assertFalse(testList.isEmpty());
		
		//Removes multiple items
		assertEquals(one, testList.dequeue());
		assertEquals(two, testList.dequeue());
		assertEquals(three, testList.dequeue());
		assertTrue(testList.isEmpty());
		
		//Interleaves adding and removal
		assertTrue(testList.isEmpty());
		testList.enqueue(one);
		assertFalse(testList.isEmpty());
		testList.enqueue(two);
		assertEquals(one, testList.dequeue());
		testList.enqueue(one);
		testList.enqueue(three);
		assertEquals(two, testList.dequeue());
		assertEquals(one, testList.dequeue());
		testList.enqueue(two);
		assertEquals(three, testList.dequeue());
		assertEquals(two, testList.dequeue());
		assertTrue(testList.isEmpty());
		
		//Attempt to remove an element from an empty list
		try {
			assertTrue(testList.isEmpty());
			testList.dequeue();
			fail();
		} catch (NoSuchElementException e) {
			assertTrue(testList.isEmpty());
		}
		
	}
}
