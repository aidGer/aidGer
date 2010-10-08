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

import java.sql.Date;
import java.util.List;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IActivity;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * Allows manipulation of Activities
 * Provides operations for fetching certain Activities related to other data objects
 * 
 * @author Team AdoHive
 *
 */
public interface IActivityManager extends IAdoHiveManager<IActivity> {
	
	/**
	 * Returns an list of Activities matching the provided Course.
	 * More precisely, returns all Activities with the same CourseID as the provided Course.
	 * @param course
	 * @return an list of Activities matching the provided Course
	 * @throws AdoHiveException
	 */
	public List<IActivity> getActivities(ICourse course) throws AdoHiveException;
	/**
	 * Returns an list of Activities matching the provided Assistant.
	 * More precisely, returns all Activities with the same AssistantID as the provided Assistant.
	 * @param assistant
	 * @return an list of Activities matching the provided Assistant
	 * @throws AdoHiveException
	 */
	public List<IActivity> getActivities(IAssistant assistant) throws AdoHiveException;
	
	/**
	 * Returns an list of Activities matching the provided date interval.
	 * More precisely, returns all Activities with date >= start & date <= end
	 * @param start
	 * @param end
	 * @return all Activities with date >= start & date <= end
	 * @throws AdoHiveException
	 */
	public List<IActivity> getActivities(Date start, Date end) throws AdoHiveException;
}
