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

import org.junit.Test;

import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * 
 * @author Felix
 *
 * this class tests if the Adohive controller
 * returns the required instances of the manager classes
 */
public class AdohiveControllerTest {
	protected static AdoHiveController myInstance = null;
	
	@Test
	public void testGetInstance() {
		AdoHiveController myInstance2 = null;
		
		// test if an instance is returned
		try {
			myInstance = AdoHiveController.getInstance();
		} catch (AdoHiveException e) {
			fail("getInstance threw an error.");
			e.printStackTrace();
		}
		assertNotNull(myInstance);

		// test if the same instance is returned
		try {
			myInstance2 = AdoHiveController.getInstance();
		} catch (AdoHiveException e) {
			fail("getInstance threw an error.");
			e.printStackTrace();
		}
		assertNotNull(myInstance2);
		assertTrue(myInstance == myInstance2);
	}

	@Test
	public void testGetActivityManager() {
		assertNotNull(myInstance.getActivityManager());
	}

	@Test
	public void testGetAssistantManager() {
		assertNotNull(myInstance.getAssistantManager());
	}

	@Test
	public void testGetCourseManager() {
		assertNotNull(myInstance.getCourseManager());
	}

	@Test
	public void testGetEmploymentManager() {
		assertNotNull(myInstance.getEmploymentManager());
	}

	@Test
	public void testGetFinancialCategoryManager() {
		assertNotNull(myInstance.getFinancialCategoryManager());
	}

	@Test
	public void testGetHourlyWageManager() {
		assertNotNull(myInstance.getHourlyWageManager());
	}

	

}
