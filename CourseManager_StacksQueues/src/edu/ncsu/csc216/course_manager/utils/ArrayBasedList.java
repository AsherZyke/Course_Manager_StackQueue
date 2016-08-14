/**
 * 
 */
package edu.ncsu.csc216.course_manager.utils;

import java.util.AbstractList;

/**
 * Defines the behavior of the ArrayBasedList class.
 * 
 * @author Benjamin Zich
 *
 *@param <E> is the generic type for this class.
 *
 */
public class ArrayBasedList<E> extends AbstractList<E> {
	/** The array of items to be manipulated by this class */
	private E[] list;
	/** The number of actual list items in the array */
	private int sizeOfList;
	/** The the default size of the array*/
	private static final int INITIAL_ARRAY_LENGTH = 10;
	
	/**
	 * Constructs the default ArrayBasedList object with
	 * a default length and a size of 0.
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList() {
		list = (E[])new Object[INITIAL_ARRAY_LENGTH];
		sizeOfList = 0;
	}

	/**
	 * Adds an element to the list at a given location.
	 * 
	 * @param index is the index where the object will be inserted
	 * @param element is the element to be inserted at the specified index
	 */
	@Override
	public void add(int index, E element) {
		if (index < 0 || index > sizeOfList) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		if (index == list.length){
			doubleCapacity();
		}
		for (int i = sizeOfList; i > index; i--) {
			list[i] = list[i - 1];
		}
		list[index] = element;
		sizeOfList++;
	}
	
	/**
	 * Doubles the list arrays capacity while copying over the
	 * items that already exist in the original list.
	 */
	private void doubleCapacity() {
		@SuppressWarnings("unchecked")
		E[] temp = (E[])new Object[list.length * 2];
		for (int i = 0; i < list.length; i++) {
			temp[i] = list[i];
		}
		list = temp;
	}

	/** 
	 * Gets an item at a particular index.
	 * 
	 * @param index is the location index of the object to be returned.
	 */
	@Override
	public E get(int index) {
		if (sizeOfList == 0 || index < 0 || index >= sizeOfList) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}

	/**
	 * Removes an item from the list at a given index.
	 * 
	 * @param index is the index where the item to be removed is located
	 * and the point at which the remaining items will shift to replace
	 * the removed object.
	 * 
	 * @return the item that was removed.
	 */
	@Override
	public E remove(int index) {
		if (sizeOfList == 0 || index < 0 || index >= sizeOfList) {
			throw new IndexOutOfBoundsException();
		}
		E temp = null;
		temp = list[index];
		for (int i = index; i < sizeOfList - 1; i++) {
			list[i] = list[i + 1];
		}
		sizeOfList--;
		return temp;
	}

	/**
	 * Puts an element at a given index and returns the element
	 * that was at that index.
	 * 
	 * @param index is the index to set the object at.
	 * 
	 * @return the object that was replaced.
	 */
	@Override
	public E set(int index, E element) {
		if (sizeOfList == 0 || index < 0 || index >= sizeOfList) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		E temp = list[index];
		list[index] = element;
		return temp;
	}

	/** 
	 * Gets the number of items in the list.
	 * 
	 * @return the number of items in the list.
	 */
	@Override
	public int size() {
		return sizeOfList;
	}

}
