/*
 * Copyright (C) 2010 The AdoHive Team
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

import java.util.List;

import org.junit.Test;

import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.controller.IAdoHiveList;
import de.unistuttgart.iste.se.adohive.controller.IAdoHiveManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAdoHiveModel;
import de.unistuttgart.iste.se.adohive.model.test.ITestDataProvider;

/**
 * 
 * @author Felix
 *
 * inherit from this class to for test classes
 * of implementations of IAdohiveManager<T>
 */
public abstract class IAdoHiveManagerTest<T extends IAdoHiveModel<T>> extends IAdoHiveListTest<T> {

	/**
	 * returns the test data provider for the current testing environment
	 * @return a test data provider
	 */
	protected abstract ITestDataProvider getTestDataProvider();
	
	/**
	 * @return <tt>true</tt> if the item class has an
	 * id field, <tt>false</tt> otherwise
	 */
	protected abstract boolean itemClassHasId();
	
	/**
	 * for retrieving the id of an item from the list
	 * @param item
	 * @return if itemClassHasId() == true, the id of
	 * the item. -1 otherwise.
	 */
	protected abstract int getItemId(T item);
	
	/**
	 * retrieves a list of all keys of an item
	 * @param item
	 * @return list with all keys of the item
	 */
	protected abstract List<Object> getItemKeys(T item);

	/**
	 * modifies the given item, so that a calls of
	 * equals of the old item with the new modified
	 * item will return false
	 * @param item
	 */
	protected abstract void modifyItem(T item);
	
	@Test
	public void testGetById() throws AdoHiveException {
		if (itemClassHasId()) {
			T i1 = newE();
			T i2 = newE();
			T i3 = newE();
			T i4 = newE();
			instance.add(i1);
			instance.add(i2);
			instance.add(i3);
			instance.add(i4);
			int nid = 0;
			
			assertTrue(i1.equals(instance.getById(getItemId(i1))));
			assertTrue(i2.equals(instance.getById(getItemId(i2))));
			assertTrue(i3.equals(instance.getById(getItemId(i3))));
			assertTrue(i4.equals(instance.getById(getItemId(i4))));
			
			while (nid == getItemId(i1) || nid == getItemId(i2) || nid == getItemId(i3) || nid == getItemId(i4)) {
				nid++;
			}
			
			assertEquals(null, instance.getById(nid));
			
		}
	}

	@Test
	public void testGetByKeys() throws AdoHiveException {
		if (!itemClassHasId()) {
			T i1 = newE();
			T i2 = newE();
			T i3 = newE();
			T i4 = newE();
			List<Object> k1 = getItemKeys(i1);
			List<Object> k2 = getItemKeys(i2);
			List<Object> k3 = getItemKeys(i3);
			List<Object> k4 = getItemKeys(i4);
			instance.add(i1);
			instance.add(i2);
			instance.add(i3);
			instance.add(i4);
		
			assertTrue(i1.equals(instance.getByKeys(k1.toArray())));
			assertTrue(i2.equals(instance.getByKeys(k2.toArray())));
			assertTrue(i3.equals(instance.getByKeys(k3.toArray())));
			assertTrue(i4.equals(instance.getByKeys(k4.toArray())));
		}
	}

	@Test
	public void testGetAll() throws AdoHiveException {
		T i1 = newE();
		T i2 = newE();
		T i3 = newE();
		List<T> list;
		
		// test on empty list
		list = instance.getAll();
		assertEquals(true, list.isEmpty());
		
		// test on filled list
		instance.add(i1);
		instance.add(i2);
		instance.add(i3);
		list = instance.getAll();
		assertTrue(list.indexOf(i1) >= 0);
		assertTrue(list.indexOf(i2) >= 0);
		assertTrue(list.indexOf(i3) >= 0);
		assertEquals(3, list.size());
		
		// test on list with identical entries WHY GOD WHY WOULD YOU WANT TO DO THIS, THIS DIES HORRIBLY WITH PRIMARY KEYS!
		/*T i4 = i1.clone();
		T i5 = i2.clone();
		T i6 = i3.clone();
		instance.add(i1);
		instance.add(i2);
		instance.add(i3);
		list = instance.getAll();
		assertEquals(6, list.size());
		assertTrue(list.indexOf(i1) >= 0);
		assertTrue(list.indexOf(i2) >= 0);
		assertTrue(list.indexOf(i3) >= 0);
		assertTrue(list.indexOf(i4) >= 0);
		assertTrue(list.indexOf(i5) >= 0);
		assertTrue(list.indexOf(i6) >= 0);
		assertTrue(list.remove(i1));
		assertTrue(list.remove(i2));
		assertTrue(list.remove(i3));
		assertEquals(3, list.size());
		assertTrue(list.indexOf(i4) >= 0);
		assertTrue(list.indexOf(i5) >= 0);
		assertTrue(list.indexOf(i6) >= 0);*/
	}

	@Test
	public void testUpdate() throws AdoHiveException {
		T i1 = newE();
		T i2;
		
		// get a second item with the same data as the
		// first item, but independent from it
		instance.add(i1);
			
		modifyItem(i1);
		instance.update(i1);
		if (itemClassHasId()) {
			i2 = instance.getById(getItemId(i1));
		} else {
			i2 = instance.getByKeys(getItemKeys(i1).toArray());
		}
		assertEquals(i1,i2);
	}

}
