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

package de.unistuttgart.iste.se.adohive.controller;

import java.util.List;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.IContract;
import de.unistuttgart.iste.se.adohive.model.ICourse;
import de.unistuttgart.iste.se.adohive.model.IEmployment;

/**
 * Allows manipulation of Employments
 * Provides operations for fetching certain Employments related to other data objects
 * @author Team AdoHive
 *
 */
public interface IEmploymentManager extends IAdoHiveManager<IEmployment> {
	
	/**
	 * Returns a list of all Employments with their Month and Year fields in the given interval (including boundary).
	 * More precisely, Returns a list of all Employments with:<br/>
	 * (Year > startYear | (Year == startYear & Month >= startMonth)) &
	 * (Year< endYear | (Year = endYear & Month <= endMonth))
	 * @param startYear
	 * @param startMonth
	 * @param endYear
	 * @param endMonth
	 * @return a list of Employments with matching year and month
	 * @throws AdoHiveException
	 */
	public List<IEmployment> getEmployments(short startYear, byte startMonth, short endYear, byte endMonth) throws AdoHiveException;
	
	/**
	 * Returns an list of Employments matching the provided Contract.
	 * More precisely, returns all Employments with the same ContractID as the provided Contract.
	 * @param contract
	 * @return an list of Employments matching the provided Contract
	 * @throws AdoHiveException
	 */
	public List<IEmployment> getEmployments(IContract contract) throws AdoHiveException;
	
	/**
	 * Returns an list of Employments matching the provided Assistant.
	 * More precisely, returns all Employments with the same AssistantID as the provided Assistant.
	 * @param assistant
	 * @return an list of Employments matching the provided Assistant
	 * @throws AdoHiveException
	 */
	public List<IEmployment> getEmployments(IAssistant assistant) throws AdoHiveException;
	
	/**
	 * Returns an list of Employments matching the provided Course.
	 * More precisely, returns all Employments with the same CourseID as the provided Course.
	 * @param course
	 * @return an list of Employments matching the provided Course
	 * @throws AdoHiveException
	 */
	public List<IEmployment> getEmployments(ICourse course) throws AdoHiveException;
	
	/**
	 * Returns an list of Employments matching the provided Semester.
	 * More precisely, returns all Employments with this.course.semester = semester.
	 * @param semester
	 * @return a list of all Employments with this.course.semester = semester
	 * @throws AdoHiveException
	 */
	public List<IEmployment> getEmployments(String semester) throws AdoHiveException;
	
	/**
	 * Returns a list of all distinct cost units in the employment table
	 * @return a list of all distinct cost units in the employment table
	 * @throws AdoHiveException
	 */
	public List<Integer> getDistinctCostUnits() throws AdoHiveException;
}
