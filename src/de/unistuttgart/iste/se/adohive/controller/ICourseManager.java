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

/**
 * 
 */
package de.unistuttgart.iste.se.adohive.controller;

import java.util.List;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.ICourse;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;

/**
 * Allows manipulation of Courses
 * Provides operations for fetching certain Courses related to other data objects
 * @author Team AdoHive
 *
 */
public interface ICourseManager extends IAdoHiveManager<ICourse> {
	
	/**
	 * Returns an list of Courses matching the provided FinancialCategory.
	 * More precisely, returns all Course with the same FinancialCategoryID as the provided FinancialCategory.
	 * @param financialCategory
	 * @return an list of all Courses matching the provided FinancialCategory
	 * @throws AdoHiveException
	 */
	public List<ICourse> getCourses(IFinancialCategory financialCategory) throws AdoHiveException;
	
	/**
	 * Returns an list of Courses matching the provided Semester.
	 * More precisely, returns all Courses with this.semester = semester.
	 * @param semester
	 * @return a list of all Courses with this.semester = semester
	 * @throws AdoHiveException
	 */
	public List<ICourse> getCoursesBySemester(String semester) throws AdoHiveException;
	
	/**
	 * Returns an list of Courses matching the provided group.
	 * More precisely, returns all Courses with this.group = group.
	 * @param group
	 * @return a list of all Courses with this.group = group
	 * @throws AdoHiveException
	 */
	public List<ICourse> getCoursesByGroup(String group) throws AdoHiveException;
	
	/**
	 * Returns a list of all distinct semester strings in the course table
	 * @return a list of all distinct semester strings in the course table
	 * @throws AdoHiveException
	 */
	public List<String> getDistinctSemesters() throws AdoHiveException;
	
	/**
	 * Returns a list of all distinct group strings in the course table
	 * @return a list of all distinct group strings in the course table
	 * @throws AdoHiveException
	 */
	public List<String> getDistinctGroups() throws AdoHiveException;
}
