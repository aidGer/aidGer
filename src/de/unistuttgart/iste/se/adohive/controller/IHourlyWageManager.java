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

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IHourlyWage;

/**
 * Allows manipulation of HourlyWages
 * Provides operations for fetching certain HourlyWages related to other data objects
 * @author Team AdoHive
 *
 */
public interface IHourlyWageManager extends IAdoHiveManager<IHourlyWage> {
	
	/**
	 * This method returns the HourlyWage object with the keys specified by o.
	 * These keys have to be Qualification,Month, Year (in this order)
	 * @param o one or more keys which identify the object (the keys have to be in the right order)
	 * @return the object with the key(s) o, or null if no object with this keys exists
	 * @throws AdoHiveException if the number of keys do not match or if the keys are not of the right type
	 */
	public IHourlyWage getByKeys(Object... o) throws AdoHiveException;
	
	/**
	 * Convenience method for getByKeys(Object... o)
	 * @param qualification the qualification part of the key
	 * @param month the month part of the key
	 * @param year the year part of the key
	 * @return returns the HourlyWage object with the key qualification, month, year, or null if no object with this key exists
	 * @throws AdoHiveException 
	 */
	public IHourlyWage getByKeys(String qualification, byte month, short year ) throws AdoHiveException;
}
