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

package de.unistuttgart.iste.se.adohive.controller.derby.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.controller.ansi.AnsiAdoHiveController;
import de.unistuttgart.iste.se.adohive.controller.test.AdohiveControllerTest;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

public class DerbyAdohiveControllerTest extends AdohiveControllerTest {
	
	@Test
	@Override
	public void testGetInstance() {
		AdoHiveController myInstance2 = null;
		
		// test if an instance is returned
		try {
			myInstance = AnsiAdoHiveController.getInstance();
		} catch (AdoHiveException e) {
			fail("getInstance threw an error.");
			e.printStackTrace();
		}
		assertNotNull(myInstance);

		// test if the same instance is returned
		try {
			myInstance2 = AnsiAdoHiveController.getInstance();
		} catch (AdoHiveException e) {
			fail("getInstance threw an error.");
			e.printStackTrace();
		}
		assertNotNull(myInstance2);
		assertTrue(myInstance == myInstance2);
	}
}