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

import junit.framework.Assert;
import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.controller.ansi.AnsiAdoHiveController;
import de.unistuttgart.iste.se.adohive.controller.test.IEmploymentManagerTest;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.test.ITestDataProvider;
import de.unistuttgart.iste.se.adohive.model.test.IndependentTestDataProvider;

public class DerbyEmploymentManagerTest extends IEmploymentManagerTest {

	private static AdoHiveController controller;
	private static ITestDataProvider tdp = new IndependentTestDataProvider();

	@Override
	protected AdoHiveController getController() {
		if (controller == null) {
			try {
				controller = AnsiAdoHiveController.getInstance();
			} catch (AdoHiveException e) {
				e.printStackTrace();
				Assert.fail("controller could not be loaded");
			}
		}
		
		return controller;
	}

	@Override
	protected ITestDataProvider getTestDataProvider() {
		return tdp;
	}
	
}