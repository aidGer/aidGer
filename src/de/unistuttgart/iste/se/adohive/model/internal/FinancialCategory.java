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

import java.util.ArrayList;
import java.util.List;

import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;

/**
 * @author Team AdoHive
 */
public final class FinancialCategory implements IFinancialCategory {
	protected Integer id;
	protected String name;
	protected Short year;
	protected List<InternalFinancialCategory> internalCategories = new ArrayList<InternalFinancialCategory>();
	
	protected IFinancialCategory financialCategory;
	
	public FinancialCategory() {
		
	}
	
	public FinancialCategory(IFinancialCategory financialCategory) {
		this.financialCategory = financialCategory;
		//copy everything
		this.id = financialCategory.getId();
		this.name = financialCategory.getName();
		this.year = financialCategory.getYear();
		for(int i=0;i<financialCategory.getFunds().length;i++) {
				InternalFinancialCategory internal = newInternal();
				internal.funds = financialCategory.getFunds()[i];
				internal.budgetCosts = financialCategory.getBudgetCosts()[i];
				internalCategories.add(internal);
		}
	}
	
	public FinancialCategory(List<InternalFinancialCategory> internalCategories) {
		this.internalCategories.addAll(internalCategories);
		this.id = internalCategories.get(0).id;
		this.name = internalCategories.get(0).name;
		this.year = internalCategories.get(0).year;
	}
	
	public FinancialCategory(InternalFinancialCategory internalCategory) {
		this.internalCategories.add(internalCategory);
		this.id = internalCategory.id;
		this.name = internalCategory.name;
		this.year = internalCategory.year;
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
		if(financialCategory != null)
			financialCategory.setId(id);
		this.id = id;
		for(InternalFinancialCategory internal : internalCategories)
			internal.id = id;
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
		if(financialCategory != null)
			financialCategory.setName(name);
		this.name = name;
		for(InternalFinancialCategory internal : internalCategories)
			internal.name = name;
	}
	
	/**
	 * @return the budgetCosts
	 */
	public Integer[] getBudgetCosts() {
		if(financialCategory != null)
			return financialCategory.getBudgetCosts();
		Integer[] costs = new Integer[internalCategories.size()];
		for(int i=0;i<internalCategories.size();i++)
			costs[i] = internalCategories.get(i).budgetCosts;
		return costs;
	}
	
	/**
	 * @param budgetCosts the budgetCosts to set
	 */
	public void setBudgetCosts(Integer[] budgetCosts) {
		if(financialCategory != null)
			financialCategory.setBudgetCosts(budgetCosts);
		for(int i=0;i<internalCategories.size();i++) {
			if(budgetCosts.length >= i+1)
				internalCategories.get(i).budgetCosts = budgetCosts[i];
			else 
				internalCategories.get(i).budgetCosts = null;
		}
	}
	
	/**
	 * @return the funds
	 */
	public Integer[] getFunds() {
		if(financialCategory != null)
			return financialCategory.getFunds();
		Integer[] funds = new Integer[internalCategories.size()];
		for(int i=0;i<internalCategories.size();i++)
			funds[i] = internalCategories.get(i).funds;
		return funds;
	}
	
	/**
	 * @param funds the funds to set
	 */
	public void setFunds(Integer[] funds) {
		if(financialCategory != null)
			financialCategory.setFunds(funds);
		for(int i=0;i<funds.length;i++) {
			if(internalCategories.size() >= i+1)
				internalCategories.get(i).funds = funds[i];
			else {
				InternalFinancialCategory internal = newInternal();
				internal.funds = funds[i];
				internalCategories.add(internal);
			}
		}
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
		if(financialCategory != null)
			financialCategory.setYear(year);
		this.year = year;
		for(InternalFinancialCategory internal : internalCategories)
			internal.year = year;
	}
	
/*	@Override
	public boolean equals(Object o) {
		if (o instanceof FinancialCategory) {
			FinancialCategory f = (FinancialCategory) o;
			for(InternalFinancialCategory ointernal : ((FinancialCategory) o).getInternalCategories())
				if(!internalCategories.contains(ointernal))
					return false;
			return 	f.getId() == this.id &&
					StringHelper.equals(f.getName(), this.name) &&
					f.getYear() == this.year;
			
		} else {
			return false;
		}
	}
	*/
	public FinancialCategory clone() {
		FinancialCategory f = new FinancialCategory();
		f.setFunds(this.getFunds());
		f.setBudgetCosts(this.getBudgetCosts());
		f.setId(this.id);
		f.setName(this.name);
		f.setYear(this.year);
		return f;
	}
	
	public List<InternalFinancialCategory> getInternalCategories() {
		return internalCategories;
	}
	
	private InternalFinancialCategory newInternal()
	{
		InternalFinancialCategory internal = new InternalFinancialCategory();
		internal.id = this.id;
		internal.name = this.name;
		internal.year = this.year;
		return internal;
	}
}
