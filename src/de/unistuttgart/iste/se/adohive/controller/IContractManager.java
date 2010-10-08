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

import java.sql.Date;
import java.util.List;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.IContract;

/**
 * Allows manipulation of Contracts
 * Provides operations for fetching certain Contracts related to other data objects
 * @author Team AdoHive
 *
 */
public interface IContractManager extends IAdoHiveManager<IContract> {
	
	/**
	 * Returns an list of Contracts matching the provided date interval.
	 * More precisely, returns all Activities with 
	 * (startDate >= start & startDate <= end) | (endDate >= start & endDate <= end) | 
	 * (confirmationDate >= start & confirmationDate <= end) | (completionDate >= start & completionDate <= end)
	 * @param start
	 * @param end
	 * @return all Activities with 
	 * (startDate >= start & startDate <= end) | (endDate >= start & endDate <= end) | 
	 * (confirmationDate >= start & confirmationDate <= end) | (completionDate >= start & completionDate <= end)
	 * @throws AdoHiveException
	 */
	public List<IContract> getContracts(Date start, Date end) throws AdoHiveException;
	public List<IContract> getContracts(IAssistant assistant) throws AdoHiveException;
}
