/**
 * 
 */
package edu.ncsu.csc216.course_manager.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the CSC216ArrayList class.
 * 
 * @author SarahHeckman
 * @author Benjamin Zich
 *
 */
public class ArrayBasedListTest {
	/** Initializes the CSC216ArrayList to be tested */
	private ArrayBasedList<String> list;

	/**
	 * Constructs the CSC216ArrayList to be tested.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new ArrayBasedList<String>();
	}

	/**
	 * Tests that a CSC216ArrayList is constructed correctly.  A CSC216ArrayList of
	 * any generic type should be not null and empty, which implies a size of 0.
	 * Test method for {@link edu.ncsu.csc216.collections.CSC216ArrayList#CSC216ArrayList()}.
	 */
	@Test
	public void testArrayBasedList() {
		assertEquals(0, list.size());
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
	}

	/**
	 * Tests adding elements to an empty CSC216ArrayList.  Then tests adding elements to the 
	 * front, middle, and back of a CSC216ArrayList.  The size and contents should be checked
	 * after each insertion.  Additionally, the bounds of the list should be checked and null
	 * elements should not be added to the list.  Finally, test that the ArrayList with an
	 * initial capacity of 10 grows when an 11th element is added.
	 * Test method for {@link edu.ncsu.csc216.collections.CSC216ArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntE() {
		//Attempt to add an element to index 1 in an empty list.
		//Check that the element was not added.
		try {
			list.add(1, "apple");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		//Add element to empty list
		list.add(0, "apple");
		assertEquals(1, list.size());
		assertEquals("apple", list.get(0));
		
		//Add element to the front of a list
		list.add(0, "front");
		assertEquals(2, list.size());
		assertEquals("front", list.get(0));
		assertEquals("apple", list.get(1));
		
		
		//Add element to the middle of a list
		list.add(1, "middle");
		assertEquals(3, list.size());
		assertEquals("front", list.get(0));
		assertEquals("middle", list.get(1));
		assertEquals("apple", list.get(2));
		
		
		//Add element to the back of a list
		list.add(3, "back");
		assertEquals(4, list.size());
		assertEquals("front", list.get(0));
		assertEquals("middle", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("back", list.get(3));
		
		
		//Attempt to add a null element.  Check that the element
		//was not added.
		String foo = null;
		try {
			list.add(4, foo);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
			assertEquals("front", list.get(0));
			assertEquals("middle", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("back", list.get(3));
		}
		
		
		//Attempt to add at index -1.  Check that the element was not
		//added.
		try {
			list.add(-1, "negative");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("front", list.get(0));
			assertEquals("middle", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("back", list.get(3));
		}
		
		
		//Attempt to add at index 5 (since there are 4 elements in the list).
		//Check that the element was not added.
		try {
			list.add(5, "too far");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("front", list.get(0));
			assertEquals("middle", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("back", list.get(3));
		}
		
		
		//Test adding an 11th element to an ArrayList with an initial 
		//capacity of 10.
		try {
			list.add(4, "five");
			list.add(5, "six");
			list.add(6, "seven");
			list.add(7, "eight");
			list.add(8, "nine");
			list.add(9, "ten");
			list.add(10, "eleven");
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		assertEquals(11, list.size());
		
				
	}

	/**
	 * Tests that elements are correctly removed from the front, middle, and back of an 
	 * ArrayList.  Removing the last element should leave an empty list.  The bounds are
	 * checked for the appropriate exceptions.
	 * Test method for {@link edu.ncsu.csc216.collections.CSC216ArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		//Attempt to remove an element from an empty list
		try {
			list.remove(0);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		
		//Add 4 elements to the list and test that the contents are correct.
		//Reuse code from your testAddIntE.
		list.add(0, "orange");
		list.add(1, "banana");
		list.add(2, "apple");
		list.add(3, "kiwi");
		assertEquals(4, list.size());
		assertEquals("orange", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("kiwi", list.get(3));
		
		//Test that IndexOutOfBoundsException is thrown when remove() is passed
		//a negative index.  Make sure the list is unchanged.
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));
		}
		
		
		//Test that IndexOutOfBoundsException is thrown when remove() is passed
		//an index > size() - 1.  Make sure the list is unchanged.
		try {
			list.remove(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));
		}
		
		
		//Remove middle element.  Test that the removed element is correct and
		//that the remaining list is correct after the removal.
		String s1 = list.remove(1);
		assertEquals(s1, "banana");
		assertEquals(3, list.size());
		assertEquals("orange", list.get(0));
		assertEquals("apple", list.get(1));
		assertEquals("kiwi", list.get(2));
		
		//Remove first element
		String s2 = list.remove(0);
		assertEquals(s2, "orange");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("kiwi", list.get(1));
		
		
		//Remove last element
		String s3 = list.remove(1);
		assertEquals(s3, "kiwi");
		assertEquals(1, list.size());
		assertEquals("apple", list.get(0));
		
		//Remove only element
		String s4 = list.remove(0);
		assertEquals(s4, "apple");
		assertEquals(0, list.size());
						
	}

	/**
	 * Tests setting an element in an empty list, the bounds of the list when
	 * using the set() method, and setting an element at the front, middle, and back
	 * of the list.  The set() method is also passed null.
	 * Test method for {@link edu.ncsu.csc216.collections.CSC216ArrayList#set(int, java.lang.Object)}.
	 */
	@Test
	public void testSetIntE() {
		//Attempt to set a value in an empty list
		try {
			list.set(0, "empty");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
			
		
		//Add 4 elements to the list and test that the contents are correct.
		//Reuse code from your testAddIntE.
		list.add(0, "orange");
		list.add(1, "banana");
		list.add(2, "apple");
		list.add(3, "kiwi");
		assertEquals(4, list.size());
		assertEquals("orange", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("kiwi", list.get(3));
		
		//Test that IndexOutOfBoundsException is thrown when set() is passed
		//a negative index.  Make sure the list is unchanged.
		try {
			list.set(-1, "foo");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));
		}
		
		
		//Test that IndexOutOfBoundsException is thrown when set() is passed
		//an index > size() - 1.  Make sure the list is unchanged.
		try {
			list.set(4, "foo");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));
		}
		
		
		//Set middle element.  Test that the element is modified correctly, set() returns the
		//right value, and that the rest of the list is correct.
		String s1 = list.set(1, "strawberry");
		assertEquals(s1, "banana");
		assertEquals(4, list.size());
		assertEquals("orange", list.get(0));
		assertEquals("strawberry", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("kiwi", list.get(3));
		
		//Set first element
		String s2 = list.set(0, "mango");
		assertEquals(s2, "orange");
		assertEquals(4, list.size());
		assertEquals("mango", list.get(0));
		assertEquals("strawberry", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("kiwi", list.get(3));
		
		//Set last element
		String s3 = list.set(3, "tomato");
		assertEquals(s3, "kiwi");
		assertEquals(4, list.size());
		assertEquals("mango", list.get(0));
		assertEquals("strawberry", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("tomato", list.get(3));
		
		//Attempt to set an element to null.  Check that the element
		//was not modified.
		String s4 = null;
		try {
			list.set(2, s4);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
			assertEquals("mango", list.get(0));
			assertEquals("strawberry", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("tomato", list.get(3));
		}
		

	}

	/**
	 * Main get() functionality is tested in the other test methods.  This method will
	 * focus on testing the exceptions associated with bounds.
	 * Test method for {@link edu.ncsu.csc216.collections.CSC216ArrayList#get(int)}.
	 */
	@Test
	public void testGetInt() {
		//Add 4 elements to the list and test that the contents are correct.
		//Reuse code from your testAddIntE.
		list.add(0, "orange");
		list.add(1, "banana");
		list.add(2, "apple");
		list.add(3, "kiwi");
		assertEquals(4, list.size());
		assertEquals("orange", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("kiwi", list.get(3));
		
		
		//Test that IndexOutOfBoundsException is thrown when get() is passed
		//a negative index.  Make sure the list is unchanged.
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));
		}
		
		
		//Test that IndexOutOfBoundsException is thrown when get() is passed
		//an index > size() - 1.  Make sure the list is unchanged.
		try {
			list.get(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));
		}
		
	}
	
	/**
	 * Tests the ArrayBasedList for functionality with the 
	 * Integer object.
	 * 
	 */
	@Test
	public void testIntegerArrayBasedList() {
		ArrayBasedList<Integer> intList = new ArrayBasedList<Integer>();
		
		//Adding five numbers to the list
		intList.add(0, 0);
		intList.add(1, 1);
		intList.add(2, 2);
		intList.add(3, 3);
		intList.add(4, 4);
		assertEquals(5, intList.size());
		assertEquals((Integer)0, intList.get(0));
		assertEquals((Integer)1, intList.get(1));
		assertEquals((Integer)2, intList.get(2));
		assertEquals((Integer)3, intList.get(3));
		assertEquals((Integer)4, intList.get(4));
		
		//Remove the middle number at index 2
		assertEquals((Integer)2, intList.remove(2));
		assertEquals(4, intList.size());
		assertEquals((Integer)0, intList.get(0));
		assertEquals((Integer)1, intList.get(1));
		assertEquals((Integer)3, intList.get(2));
		assertEquals((Integer)4, intList.get(3));
		
		//Add two more numbers
		intList.add(1, 5);
		intList.add(4, 6);
		assertEquals(6, intList.size());
		assertEquals((Integer)0, intList.get(0));
		assertEquals((Integer)5, intList.get(1));
		assertEquals((Integer)1, intList.get(2));
		assertEquals((Integer)3, intList.get(3));
		assertEquals((Integer)6, intList.get(4));
		assertEquals((Integer)4, intList.get(5));
		
		
		//Remove the first number
		assertEquals((Integer)0, intList.remove(0));
		assertEquals(5, intList.size());
		assertEquals((Integer)5, intList.get(0));
		assertEquals((Integer)1, intList.get(1));
		assertEquals((Integer)3, intList.get(2));
		assertEquals((Integer)6, intList.get(3));
		assertEquals((Integer)4, intList.get(4));
		
		//Add a number
		intList.add(0, 7);
		assertEquals(6, intList.size());
		assertEquals((Integer)7, intList.get(0));
		assertEquals((Integer)5, intList.get(1));
		assertEquals((Integer)1, intList.get(2));
		assertEquals((Integer)3, intList.get(3));
		assertEquals((Integer)6, intList.get(4));
		assertEquals((Integer)4, intList.get(5));
		
		//remove the last number
		assertEquals((Integer)4, intList.remove(5));
		assertEquals(5, intList.size());
		assertEquals((Integer)7, intList.get(0));
		assertEquals((Integer)5, intList.get(1));
		assertEquals((Integer)1, intList.get(2));
		assertEquals((Integer)3, intList.get(3));
		assertEquals((Integer)6, intList.get(4));
		
		//Add a number
		intList.add(3, 8);
		assertEquals(6, intList.size());
		assertEquals((Integer)7, intList.get(0));
		assertEquals((Integer)5, intList.get(1));
		assertEquals((Integer)1, intList.get(2));
		assertEquals((Integer)8, intList.get(3));
		assertEquals((Integer)3, intList.get(4));
		assertEquals((Integer)6, intList.get(5));

		//remove the second number
		assertEquals((Integer)5, intList.remove(1));
		assertEquals(5, intList.size());
		assertEquals((Integer)7, intList.get(0));
		assertEquals((Integer)1, intList.get(1));
		assertEquals((Integer)8, intList.get(2));
		assertEquals((Integer)3, intList.get(3));
		assertEquals((Integer)6, intList.get(4));
		
		//Remove the remaining numbers in the list
		assertEquals(5, intList.size());
		assertEquals((Integer)7, intList.remove(0));
		assertEquals((Integer)1, intList.remove(0));
		assertEquals((Integer)8, intList.remove(0));
		assertEquals((Integer)3, intList.remove(0));
		assertEquals((Integer)6, intList.remove(0));
		assertEquals(0, intList.size());
	}

}
