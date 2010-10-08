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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import de.unistuttgart.iste.se.adohive.controller.IAdoHiveManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IHourlyWage;

/**
 * 
 * @author Felix
 *
 * inherit test classes for implementations of IHourlyWageManager
 * from this abstract class
 */
public abstract class IHourlyWageManagerTest extends IAdoHiveManagerTest<IHourlyWage> {

	@Override
	protected int getItemId(IHourlyWage item) {
		return -1;
	}
	
	@Override
	protected List<Object> getItemKeys(IHourlyWage item) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(item.getQualification());
		list.add(item.getMonth());
		list.add(item.getYear());
		return list;
	}

	@Override
	public IHourlyWage newE() throws AdoHiveException {
		return getTestDataProvider().newIHourlyWage();
	}

	@Override
	protected boolean itemClassHasId() {
		return false;
	}

	@Override
	protected void modifyItem(IHourlyWage item) {
		if (item.getWage() == BigDecimal.valueOf(23.5)) {
			item.setWage(BigDecimal.valueOf(5.23));
		} else {
			item.setWage(BigDecimal.valueOf(23.5));
		}
	}
	
	@Override
	protected IAdoHiveManager<IHourlyWage> getInstance() {
		try {
			getController().getHourlyWageManager().clear();
		} catch (AdoHiveException e) {
			fail("assistant manager could not be cleaned!");
		}
		
		return getController().getHourlyWageManager();
	}
}
