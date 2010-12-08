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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.IContract;
import de.unistuttgart.iste.se.adohive.model.ICourse;
import de.unistuttgart.iste.se.adohive.model.IEmployment;
import de.unistuttgart.iste.se.adohive.model.test.Contract;
import de.unistuttgart.iste.se.adohive.model.test.Employment;

/**
 * 
 * @author Felix
 *
 * inherit test classes for implementations of IEmploymentManager
 * from this abstract class
 */
public abstract class IEmploymentManagerTest extends IAdoHiveManagerTest<IEmployment> {

	@Override
	protected int getItemId(IEmployment item) {
		return item.getId();
	}

	@Override
	protected List<Object> getItemKeys(IEmployment item) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(item.getId());
		return list;
	}

	@Override
	protected boolean itemClassHasId() {
		return true;
	}

	@Override
	protected void modifyItem(IEmployment item) {
		if (item.getHourCount() == 42.) {
			item.setHourCount(47.);
		} else {
			item.setHourCount(42.);
		}
		
	}

	@Override
	public IEmployment newE()  throws AdoHiveException {
		return getTestDataProvider().newIEmployment();
	}
	
	@Test
	public void testGetEmployments() throws AdoHiveException {
		ICourse[] courses = new ICourse[10];
		IContract[] contracts = new IContract[10];
		IAssistant[] assistants = new IAssistant[10];
		String[] semesters = new String[5];
		
		// get some testing data and make sure there are no
		// equal objects
		// also add the testing data to the database
		for (int i = 0; i < 10; i++) {
			ICourse c;
			IAssistant a;
			IContract ct;
			String s = "Semes" + Integer.toString(i % 5);
			
			boolean equal;
			do {
				equal = false;
				c = getTestDataProvider().newICourse();
				for (int j = 0; j < i; j++) {
					if (c.equals(courses[j])) {
						equal = true;
						break;
					}
				}
			} while (equal);
			c.setSemester(s);
			courses[i] = c;
			AdoHiveController.getInstance().getCourseManager().add(c);
			
			do {
				equal = false;
				a = getTestDataProvider().newIAssistant();
				for (int j = 0; j < i; j++) {
					if (a.equals(assistants[j])) {
						equal = true;
						break;
					}
				}
			} while (equal);
			assistants[i] = a;
			AdoHiveController.getInstance().getAssistantManager().add(a);
			
			do {
				equal = false;
				ct = new Contract();
				for (int j = 0; j < i; j++) {
					if (ct.equals(contracts[j])) {
						equal = true;
						break;
					}
				}
			} while (equal);
			contracts[i] = ct;
			ct.setAssistantId(a.getId());
			AdoHiveController.getInstance().getContractManager().add(ct);
			
			if (i< 5) semesters[i] = s;
		}
		
		// now generate some employments referring the stuff we just created
		// and add them to the database
		// these employments are the power set of the first 9 courses, assistants and contracts.
		// the 10th items will be used for checking the behaviour if no employment exists.
		IEmployment[] empls = new IEmployment[729];
		for (int i = 0; i < 729; i++) {
			empls[i] = new Employment();
			empls[i].setAssistantId(assistants[i % 9].getId());
			empls[i].setContractId(contracts[(i / 9) % 9].getId());
			empls[i].setCourseId(courses[i / 81].getId());
			empls[i].setMonth((byte) ((i % 100) % 12));
			empls[i].setYear((short) ((i % 100) / 12 + 1970));
			AdoHiveController.getInstance().getEmploymentManager().add(empls[i]);
		}
		
		// test if all the stuff is returned properly
		for (int i = 0; i < 9; i++) {
			List<IEmployment> listByAss = AdoHiveController.getInstance().getEmploymentManager().getEmployments(assistants[i]);
			List<IEmployment> listByCou = AdoHiveController.getInstance().getEmploymentManager().getEmployments(courses[i]);
			List<IEmployment> listByCon = AdoHiveController.getInstance().getEmploymentManager().getEmployments(contracts[i]);
			
			assertEquals(81, listByAss.size());
			assertEquals(81, listByCou.size());
			assertEquals(81, listByCon.size());
			
			for (int j = 0; j < 81; j++) {
				assertEquals(listByAss.get(j).getAssistantId(), assistants[i].getId());
				assertEquals(listByCou.get(j).getCourseId(), courses[i].getId());
				assertEquals(listByCon.get(j).getContractId(), contracts[i].getId());
			}
		}
		
		// now test behaviour for unused objects
		assertEquals(AdoHiveController.getInstance().getEmploymentManager().getEmployments(assistants[9]).size(), 0);
		assertEquals(AdoHiveController.getInstance().getEmploymentManager().getEmployments(courses[9]).size(), 0);
		assertEquals(AdoHiveController.getInstance().getEmploymentManager().getEmployments(contracts[9]).size(), 0);
		
		// finally, test getting stuff with dates
		for (int startMonth = 0; startMonth < 50; startMonth++) {
			for (int endMonth = startMonth; endMonth < 50; endMonth++) {
				short startYear = (short) (startMonth / 12 + 1970);
				byte realStartMonth = (byte) (startMonth % 12);
				short endYear = (short) (endMonth / 12 + 1970);
				byte realEndMonth = (byte) (endMonth % 12);
				
				int months = endMonth - startMonth + 1;
				int expected = 0;
								
				if (startMonth < 29) {
					if (endMonth >= 29) {
						expected += (29 - startMonth) * 8;
						expected += (endMonth - 28) * 7;
					} else {
						expected += months * 8;
					}
				} else {
					expected += months * 7;
				}
				
				/* for debugging the test, you know
				System.out.println("testing result from month " + Integer.toString(startMonth) + " (" + Byte.toString(realStartMonth) + "." + 
						Short.toString(startYear) + ") to month " + Integer.toString(endMonth) + " (" + Byte.toString(realEndMonth) + "." +
						Short.toString(endYear) + ")");
				*/
				
				assertEquals(expected, AdoHiveController.getInstance().getEmploymentManager().getEmployments(startYear, realStartMonth, endYear, realEndMonth).size());
			}
		}
		
		// test getting for semesters
		for (int i = 0; i < 5; i++) {
			List<IEmployment> l = AdoHiveController.getInstance().getEmploymentManager().getEmployments(semesters[i]);
			// too lazy to calculate exact number
			assertTrue(l.size() > 10);
			for (IEmployment e : l) {
				assertEquals(semesters[i], AdoHiveController.getInstance().getCourseManager().getById(e.getCourseId()).getSemester());
			}
		}
	}
}
