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
package de.unistuttgart.iste.se.adohive.model;

/**
 * Provides access to one FinancialCategory data object
 * ATTENTION: Funds and Budget Costs array MUST have the same length!
 * @author Team AdoHive
 *
 */
public interface IFinancialCategory extends IAdoHiveModel<IFinancialCategory> {
	
	/* keeping this here for awesome code generation
	private int id;
	private String name;
	private int budgetCosts;
	private int funds;
	private short year;
	*/

	/**
	 * @return the name
	 */
	public String getName();
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name);
	
	/**
	 * @return the budgetCosts
	 */
	public Integer[] getBudgetCosts();
	
	/**
	 * @param budgetCosts the budgetCosts to set
	 */
	public void setBudgetCosts(Integer[] budgetCosts);
	
	/**
	 * @return the funds
	 */
	public Integer[] getFunds();
	
	/**
	 * @param funds the funds to set
	 */
	public void setFunds(Integer[] funds);
	
	/**
	 * @return the year
	 */
	public Short getYear();
	
	/**
	 * @param year the year to set
	 */
	public void setYear(Short year);
}
