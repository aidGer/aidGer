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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.controller.IAdoHiveManager;
import de.unistuttgart.iste.se.adohive.controller.ICourseManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.ICourse;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;
import de.unistuttgart.iste.se.adohive.model.test.Course;

/**
 * 
 * @author Felix
 *
 * inherit test classes for implementations of ICourseManager
 * from this abstract class
 */
public abstract class ICourseManagerTest extends IAdoHiveManagerTest<ICourse> {
	
	@Override
	protected int getItemId(ICourse item) {
		return item.getId();
	}

	@Override
	protected List<Object> getItemKeys(ICourse item) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(item.getId());
		return list;
	}
	
	@Override
	protected boolean itemClassHasId() {
		return true;
	}

	@Override
	protected void modifyItem(ICourse item) {
		if (item.getUnqualifiedWorkingHours() == 42) {
			item.setUnqualifiedWorkingHours(13.37);
		} else {
			item.setUnqualifiedWorkingHours(42d);
		}
	}
	
	@Override
	public ICourse newE()  throws AdoHiveException {
		return getTestDataProvider().newICourse();
	}
	
	@Test
	public void testGetCourses() throws AdoHiveException {
		IFinancialCategory[] fcs = new IFinancialCategory[7];
		for (int i = 0; i < 7; i++) {
			fcs[i] = getTestDataProvider().newIFinancialCategory();
			AdoHiveController.getInstance().getFinancialCategoryManager().add(fcs[i]);
		}
		
		ICourse[] cs = new ICourse[42];
		for (int i = 0; i < 42; i++) {
			cs[i] = getTestDataProvider().newICourse();
			cs[i].setFinancialCategoryId(fcs[i % 7].getId());
			AdoHiveController.getInstance().getCourseManager().add(cs[i]);
		}
		
		for (int i = 0; i < 7; i++) {
			List<ICourse> lc = AdoHiveController.getInstance().getCourseManager().getCourses(fcs[i]);
			assertEquals(6, lc.size());
			for (int j = 0; j < 6; j++) {
				assertEquals(fcs[i].getId(), lc.get(j).getFinancialCategoryId());
			}
		}
	}
	
	@Test
	public void testGetCoursesBySemester() throws AdoHiveException {
		String[] semesters = new String[6];
		String[] groups = new String[4]; 
		for (int i = 0; i< 6; i++) {
			semesters[i] = "Semes" + Integer.toString(i);
		}
		for (int i = 0; i < 4; i++) {
			groups[i] = "Group" + Integer.toString(i);
		}
		
		
		ICourse[] courses = new ICourse[24];
		for (int i = 0; i < 24; i++) {
			courses[i] = new Course();
			courses[i].setSemester(semesters[i % 6]);
			courses[i].setGroup(groups[i % 4]);
			instance.add(courses[i]);
		}
		
		List<String> s = ((ICourseManager)instance).getDistinctSemesters();
		List<String> g = ((ICourseManager)instance).getDistinctGroups();
		
		assertEquals(6, s.size());
		assertEquals(4, g.size());
		
		for (int i = 0; i < 6; i++) {
			List<ICourse> lc = ((ICourseManager)instance).getCoursesBySemester(semesters[i]);
			assertEquals(4, lc.size());
			for (ICourse c : lc) {
				assertEquals(semesters[i], c.getSemester());
			}
		}
		
		for (int i = 0; i < 4; i++) {
			List<ICourse> lc = ((ICourseManager)instance).getCoursesByGroup(groups[i]);
			assertEquals(6, lc.size());
			for (ICourse c : lc) {
				assertEquals(groups[i], c.getGroup());
			}
		}
		
		
	}
}
