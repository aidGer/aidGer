/*
 * Author:
 *       RashFael <rashfael@codeberserkers.org>
 * 
 * Copyright (c) 2010 rashfael
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package de.unistuttgart.iste.se.adohive.controller.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unistuttgart.iste.se.adohive.controller.IAdoHiveList;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAdoHiveModel;

/**
 * 
 * @author Felix
 *
 * inherit test classes for implementations of IAdohiveListTest
 * from this abstract class
 */
public abstract class IAdoHiveListTest<E extends IAdoHiveModel<E>> {

	/**
	 * this abstract method has to be implemented by
	 * child classes. It always has to return a new
	 * unused instance of IAdohiveListTest
	 * 
	 * @return a new instance of the class implementing
	 * IAdohiveListTest
	 */
	public abstract IAdoHiveList<E> newInstance();
	
	/**
	 * this abstract method generates a new E object
	 * to be used within the test methods.
	 * 
	 * @return a new instance of E
	 */
	public abstract E newE() throws AdoHiveException;
	
	
	@Test
	public void testSize() throws AdoHiveException {
		
		IAdoHiveList<E> instance = newInstance();
		// test initial state
		assertEquals(0, instance.size());
		
		// test size after adding some stuff
		for (int i = 0; i < 42; i++) {
			instance.add(newE());
		}
		assertEquals(42, instance.size());
		
		// test size after removing some stuff
		for (int i = 5; i < 24; i++) {
			instance.remove(instance.get(i));
		}
		assertEquals(23, instance.size());
	}

	@Test
	public void testIsEmpty() throws AdoHiveException {
		IAdoHiveList<E> instance = newInstance();
		
		// test initial state
		assertTrue(instance.isEmpty());
		
		// test state after adding stuff
		instance.add(newE());
		assertFalse(instance.isEmpty());
		
		// test state after removing all entries
		instance.remove(instance.get(0));
		assertTrue(instance.isEmpty());
	}

	@Test
	public void testContains() throws AdoHiveException {
		IAdoHiveList<E> instance = newInstance();
		E e1 = newE();
		E e2 = newE();
		E e3 = newE();
		
		// test initial state
		assertFalse(instance.contains(e1) || instance.contains(e2) || instance.contains(e3));
		
		// test state after adding some items
		instance.add(e1);
		instance.add(e2);
		assertTrue(instance.contains(e1));
		assertTrue(instance.contains(e2));
		assertFalse(instance.contains(e3));
		
		// test state after adding all items
		instance.add(e3);
		assertTrue(instance.contains(e1) && instance.contains(e2) && instance.contains(e3));
		
		// test state after removing some items
		instance.remove(e1);
		instance.remove(e2);
		assertTrue(!instance.contains(e1) && !instance.contains(e2) && instance.contains(e3));
		
		// test state after removing all items
		instance.remove(e3);
		assertFalse(instance.contains(e1) || instance.contains(e2) || instance.contains(e3));
	}

	@Test
	public void testAdd() throws AdoHiveException {
		IAdoHiveList<E> instance = newInstance();
		E e1 = newE();
		E e2 = newE();
		E e3 = newE();
		
		// test adding to empty list
		instance.add(e1);
		assertTrue(instance.contains(e1));
		assertEquals(1, instance.size());
		
		// test adding to non-empty list
		instance.add(e2);
		assertTrue(instance.contains(e1));
		assertTrue(instance.contains(e2));
		assertEquals(2, instance.size());
		
		// test adding an element after removing another one
		instance.remove(e1);
		instance.add(e3);
		assertTrue(!instance.contains(e1));
		assertTrue(instance.contains(e2));
		assertTrue(instance.contains(e3));
		assertEquals(2, instance.size());
	}

	@Test
	public void testRemove() throws AdoHiveException {
		IAdoHiveList<E> instance = newInstance();
		E e1 = newE();
		E e2 = newE();
		E e3 = newE();
		
		// test removing non-existing item from empty list
		assertEquals(false, instance.remove(e1));
		
		// test removing non-existing item from non-empty list
		instance.add(e1);
		assertEquals(false, instance.remove(e2));
		
		// test removing sole element from list
		assertEquals(true, instance.remove(e1));
		assertEquals(0, instance.size());
		
		// test removing first element from list containing multiple elements
		instance.add(e1);
		instance.add(e2);
		instance.add(e3);
		assertEquals(true, instance.remove(e1));
		assertTrue(instance.contains(e2));
		assertTrue(instance.contains(e3));
		assertTrue(!instance.contains(e1));
		assertEquals(2, instance.size());
		
		// test removing last element from list containing multiple elements
		assertEquals(true, instance.remove(e3));
		assertTrue(instance.contains(e2));
		assertTrue(!instance.contains(e3));
		assertTrue(!instance.contains(e1));
		assertEquals(1, instance.size());
		
		// test removing element from the middle of the list
		instance.add(e1);
		instance.add(e3);
		assertEquals(true, instance.remove(e1));
		assertTrue(instance.contains(e2));
		assertTrue(instance.contains(e3));
		assertTrue(!instance.contains(e1));
		assertEquals(2, instance.size());
		
		// test removing an element that exists more than once in the list WTF, DONT DO THAT, ITS A SET!
		/*E e4 = e3.clone();
		instance.add(e1);
		instance.add(e2);
		instance.add(e3);
		assertEquals(true, instance.remove(e3));
		assertTrue(instance.contains(e2));
		assertTrue(instance.contains(e4));
		assertTrue(instance.contains(e1));
		assertEquals(4, instance.size());*/
	}

	@Test
	public void testClear() throws AdoHiveException {
		IAdoHiveList<E> instance = newInstance();
		E e1 = newE();
		
		// test clear on empty list
		instance.clear();
		assertEquals(true, instance.isEmpty());
		assertEquals(0, instance.size());
		
		// test clear on full list
		instance.add(e1);
		instance.clear();
		assertEquals(true, instance.isEmpty());
		assertEquals(0, instance.size());
		
		// test on cleared an re-filled list
		instance.add(e1);
		instance.clear();
		assertEquals(true, instance.isEmpty());
		assertEquals(0, instance.size());
		
	}

	@Test
	public void testGet() throws AdoHiveException {
		IAdoHiveList<E> instance = newInstance();
		E e1 = newE();
		E e2 = newE();
		E e3 = newE();
		E e4 = newE();
		E e5 = newE();
		boolean gotException = false;
		
		// test on empty list
		try {
			instance.get(0);
		// should actually be an IndexOutOfBoundsException...
		} catch (IndexOutOfBoundsException e) {
			gotException = true;
		}
		assertEquals(true, gotException);
		
		// test on existing elements
		instance.add(e1);
		instance.add(e2);
		instance.add(e3);
		instance.add(e4);
		instance.add(e5);
		
		int bitVec[] = new int[5];
		for (int i = 0; i < 5; i++) {
			bitVec[i] = 0;
		}
		for (int i = 0; i < 5; i++) {
			E e = instance.get(i);
			if (e1.equals(e))
				bitVec[0]++;
			if (e2.equals(e))
				bitVec[1]++;
			if (e3.equals(e))
				bitVec[2]++;
			if (e4.equals(e))
				bitVec[3]++;
			if (e5.equals(e))
				bitVec[4]++;
		}
		assertEquals(1, bitVec[0]);
		assertEquals(1, bitVec[1]);
		assertEquals(1, bitVec[2]);
		assertEquals(1, bitVec[3]);
		assertEquals(1, bitVec[4]);
		
		// test on index > size()
		gotException = false;
		try {
			instance.get(5);
		} catch (IndexOutOfBoundsException e) {
			gotException = true;
		}
		assertEquals(true, gotException);
		
		// test on negative index
		gotException = false;
		try {
			instance.get(-42);
		} catch (IndexOutOfBoundsException e) {
			gotException = true;
		}
		assertEquals(true, gotException);
	}
}
