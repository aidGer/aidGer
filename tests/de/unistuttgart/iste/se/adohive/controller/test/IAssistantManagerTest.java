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

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.controller.IAdoHiveManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.test.ITestDataProvider;

/**
 * 
 * @author Felix
 *
 * inherit test classes for implementations of IAssistantManager
 * from this abstract class
 */
public abstract class IAssistantManagerTest extends IAdoHiveManagerTest<IAssistant> {

	protected abstract ITestDataProvider getTestDataProvider();
	
	@Override
	protected int getItemId(IAssistant item) {
		return item.getId();
	}

	@Override
	protected List<Object> getItemKeys(IAssistant item) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(item.getId());
		return list;
	}

	@Override
	protected boolean itemClassHasId() {
		return true;
	}

	@Override
	protected void modifyItem(IAssistant item) {
		if (item.getFirstName().equals("hurrdurr")) {
			item.setFirstName("derpderpderp");
		} else {
			item.setFirstName("hurrdurr");
		}
	}
	
	@Override
	public IAssistant newE() throws AdoHiveException {
		return getTestDataProvider().newIAssistant();
	}
}
