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
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;

/**
 * Allows manipulation of FinancialCategories
 * Provides operations for fetching certain FinancialCategories related to other data objects
 * @author Team AdoHive
 *
 */
public interface IFinancialCategoryManager extends IAdoHiveManager<IFinancialCategory> {

	/**
	 * Returns a list of all distinct funds in the financial category table
	 * @return a list of all distinct funds in the financial category table
	 * @throws AdoHiveException
	 */
	public List<Integer> getDistinctFunds() throws AdoHiveException;
}
