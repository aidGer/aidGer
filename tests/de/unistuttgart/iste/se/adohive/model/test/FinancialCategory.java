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
package de.unistuttgart.iste.se.adohive.model.test;

import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;

/**
 * @author Team AdoHive
 */
public final class FinancialCategory implements IFinancialCategory {
	protected Integer id;
	protected String name = IndependentTestDataProvider.randomString();
	protected Short year = (short) IndependentTestDataProvider.randomChar();
	protected Integer[] budgetCosts;
	protected Integer[] funds;
	protected IFinancialCategory financialCategory;
	
	public FinancialCategory() {
		int arraylength = IndependentTestDataProvider.getRandom().nextInt(20)+1;
		funds = IndependentTestDataProvider.randomIntArray(arraylength);
		budgetCosts = IndependentTestDataProvider.randomIntArray(arraylength);
	}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the budgetCosts
	 */
	public Integer[] getBudgetCosts() {
		return budgetCosts;
	}
	
	/**
	 * @param budgetCosts the budgetCosts to set
	 */
	public void setBudgetCosts(Integer[] budgetCosts) {
		this.budgetCosts = budgetCosts;
	}
	
	/**
	 * @return the funds
	 */
	public Integer[] getFunds() {
		return funds;
	}
	
	/**
	 * @param funds the funds to set
	 */
	public void setFunds(Integer[] funds) {
		this.funds = funds;
	}
	
	/**
	 * @return the year
	 */
	public Short getYear() {
		return year;
	}
	
	/**
	 * @param year the year to set
	 */
	public void setYear(Short year) {
		this.year = year;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof IFinancialCategory))
			return false;
		IFinancialCategory f = (IFinancialCategory) o;
		Integer[] budgetCosts = f.getBudgetCosts();
		Integer[] funds = f.getFunds();
		for(int i=0; i<this.budgetCosts.length;i++) {
			boolean found = false;
			for(int j=0; j<budgetCosts.length;j++)
				if(this.budgetCosts[i].equals(budgetCosts[j])) {
					found = true;
					break;
				}
			if(!found)
				return false;
		}
		
		for(int i=0; i<this.funds.length;i++) {
			boolean found = false;
			for(int j=0; j<funds.length;j++)
				if(this.funds[i].equals(funds[j])) {
					found = true;
					break;
				}
			if(!found)
				return false;
		}
		
		return f.getId().equals(this.id) &&
		StringHelper.equals(f.getName(), this.name) &&
		f.getYear().equals(this.year);
	}
	
	public FinancialCategory clone() {
		FinancialCategory f = new FinancialCategory();
		f.setFunds(this.getFunds());
		f.setBudgetCosts(this.getBudgetCosts());
		f.setId(this.id);
		f.setName(this.name);
		f.setYear(this.year);
		return f;
	}
	
}
