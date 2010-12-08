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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.controller.IActivityManager;
import de.unistuttgart.iste.se.adohive.controller.IAdoHiveManager;
import de.unistuttgart.iste.se.adohive.controller.IAssistantManager;
import de.unistuttgart.iste.se.adohive.controller.ICourseManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IActivity;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.ICourse;
import de.unistuttgart.iste.se.adohive.model.test.Activity;

/**
 * 
 * @author Felix
 *
 * inherit test classes for implementations of IActivityManager
 * from this abstract class
 *
 */
public abstract class IActivityManagerTest extends IAdoHiveManagerTest<IActivity> {
	
	@Override
	public IActivity newE() throws AdoHiveException {
		return getTestDataProvider().newIActivity();
		
	}
	
	@Override
	protected int getItemId(IActivity item) {
		return item.getId();
	}
	
	@Override
	protected List<Object> getItemKeys(IActivity item) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(item.getId());
		return list;
	}

	@Override
	protected boolean itemClassHasId() {
		return true;
	}

	@Override
	protected void modifyItem(IActivity item) {
		if (item.getType().equals("test")) {
			item.setType("test2");
		} else {
			item.setType("test");
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetActivities() throws AdoHiveException {
		IAssistantManager amInstance = AdoHiveController.getInstance().getAssistantManager();
		ICourseManager cmInstance = AdoHiveController.getInstance().getCourseManager();
		IActivity a[] = new IActivity[24];
		IAssistant ass[] = new IAssistant[4];
		ICourse c[] = new ICourse[3];
		
		// generate some assistants and courses
		for (int i = 0; i < 4; i++) {
			ass[i] = getTestDataProvider().newIAssistant();
			amInstance.add(ass[i]);
			if (i < 3) {
				c[i] = getTestDataProvider().newICourse();
				cmInstance.add(c[i]);
			}
		}
		
		// generate some activities
		for (int i = 0; i < 24; i++) {
			a[i] = new Activity();
			a[i].setCourseId(c[i % 3].getId());
			a[i].setAssistantId(ass[i % 4].getId());
			a[i].setDate(new Date(i + 70, i % 12, i + 1));
			instance.add(a[i]);
		}
		
		// test getting the activities by course
		for (int i = 0; i < 3; i++) {
			List<IActivity> l = ((IActivityManager)instance).getActivities(c[i]);
			assertEquals(8, l.size());
			/*
			 * doesn't work because indexOf isn't implemented anymore
			for (int j = 0; j < 8; j++) {
				assertFalse(l.indexOf(a[j * 3 + i]) < 0);
			}
			*/
		}
		// test getting the activities by assistant
		for (int i = 0; i < 4; i++) {
			List<IActivity> l = ((IActivityManager)instance).getActivities(ass[i]);
			assertEquals(6, l.size());
			/*
			 * doesn't work because indexOf isn't implemented anymore
			for (int j = 0; j< 6; j++) {
				assertFalse(l.indexOf(a[j * 4 + i]) < 0);
			}
			*/
		}
		// test getting the activities by date
		for (int i = 0; i < 24; i++) {
			for (int j = i; j < 24; j++) {
				List<IActivity> l = ((IActivityManager)instance).getActivities(new Date(i + 70, i % 12, i + 1), new Date(j + 70, j % 12, j + 1));
				assertEquals(j - i + 1, l.size());
				for (IActivity ac : l) {
					
					assertTrue(ac.getDate().getYear() >= i + 70 && ac.getDate().getYear() <= j + 70);
					if (ac.getDate().getYear() == i + 70) {
						assertTrue(ac.getDate().getMonth() >= i % 12);
						if (ac.getDate().getMonth() == i % 12) {
							assertTrue(ac.getDate().getDate() >= i + 1);
						}
					}
					if (ac.getDate().getYear() == j + 70) {
						assertTrue(ac.getDate().getMonth() >= j % 12);
						if (ac.getDate().getMonth() == j % 12) {
							assertTrue(ac.getDate().getDate() <= j + 1);
						}
					}
				}
			}
		}
	}
	
	@Test
	public void TestFKNull() throws AdoHiveException {
		IActivity activity = newE();
		activity.setAssistantId(null);
		instance.add(activity);
	}
}
