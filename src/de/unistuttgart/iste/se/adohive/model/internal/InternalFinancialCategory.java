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
package de.unistuttgart.iste.se.adohive.model.internal;

import de.unistuttgart.iste.se.adohive.model.IAdoHiveModel;

/**
 * @author rashfael
 *
 */
@AdoClass(tableName = "Finanzkategorie")
public class InternalFinancialCategory implements IAdoHiveModel<InternalFinancialCategory>{

	protected Integer id;
	protected Integer costUnit;
	protected Integer budgetCosts;
	protected String name;
	protected Short year;
	
	
	public InternalFinancialCategory() {
		
	}
	
	/**
	 * @return the id
	 */
	@AdoMethod(columnName = "ID")
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	@AdoMethod(columnName = "ID")
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	@AdoMethod(columnName = "Name")
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	@AdoMethod(columnName = "Name")
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the budgetCosts
	 */
	@AdoMethod(columnName = "Plankosten")
	public Integer getBudgetCosts() {
		return budgetCosts;
	}
	
	/**
	 * @param budgetCosts the budgetCosts to set
	 */
	@AdoMethod(columnName = "Plankosten")
	public void setBudgetCosts(Integer budgetCosts) {
		this.budgetCosts = budgetCosts;
	}
	
	/**
	 * @return the funds
	 */
	@AdoMethod(columnName = "Kostenstelle")
	public Integer getCostUnit() {
		return costUnit;
	}
	
	/**
	 * @param funds the funds to set
	 */
	@AdoMethod(columnName = "Kostenstelle")
	public void setCostUnit(Integer costUnit) {
		this.costUnit = costUnit;
	}
	
	/**
	 * @return the year
	 */
	@AdoMethod(columnName = "Jahr")
	public Short getYear() {
		return year;
	}
	
	/**
	 * @param year the year to set
	 */
	@AdoMethod(columnName = "Jahr")
	public void setYear(Short year) {
		this.year = year;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof InternalFinancialCategory) {
			InternalFinancialCategory f = (InternalFinancialCategory) o;
			return f.getBudgetCosts().equals(this.budgetCosts) &&
					f.getCostUnit().equals(this.costUnit) &&
					f.getId().equals(this.id);
		} else {
			return false;
		}
	}
	
	public InternalFinancialCategory clone() {
		InternalFinancialCategory f = new InternalFinancialCategory();
		f.setBudgetCosts(this.budgetCosts);
		f.setCostUnit(this.costUnit);
		f.setId(this.id);
		f.setName(this.name);
		f.setYear(this.year);
		return f;
	}
}
