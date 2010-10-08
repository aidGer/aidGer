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

import java.sql.Date;

import de.unistuttgart.iste.se.adohive.model.IContract;

/**
 * @author Team AdoHive
 */
public final class Contract implements IContract{
	protected Integer id;
	protected String type = IndependentTestDataProvider.randomStringFromArr(new String[] {"Neuvertrag", "Aenderung", "Anschluss"});
	protected Date startDate = IndependentTestDataProvider.randomDate();
	protected Date endDate = IndependentTestDataProvider.randomDate();
	protected Date completionDate = IndependentTestDataProvider.randomDate();
	protected Date confirmationDate = IndependentTestDataProvider.randomDate();
	protected Boolean delegation = false;
	protected Integer assistantId = IndependentTestDataProvider.randomInt();
		
	public Contract() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public Date getStartDate() {
	return startDate;
	}

	public void setStartDate(Date startDate) {
			this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getCompletionDate() {
		return completionDate;
	}
	
	public void setCompletionDate(Date completionDate) {
	this.completionDate = completionDate;
	}


	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}
	
	public Boolean isDelegation() {
		return delegation;
	}

	public void setDelegation(Boolean delegation) {
		this.delegation = delegation;
	}
	
	public Contract clone() {
		Contract c = new Contract();
		c.setCompletionDate(this.completionDate);
		c.setConfirmationDate(this.confirmationDate);
		c.setDelegation(this.delegation);
		c.setEndDate(this.endDate);
		c.setId(this.id);
		c.setStartDate(this.startDate);
		c.setType(this.type);
		return c;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof IContract) {
			IContract c = (IContract) o;
			return this.completionDate.equals(c.getCompletionDate()) &&
					this.confirmationDate.equals(c.getConfirmationDate()) &&
					this.delegation.equals(c.isDelegation()) &&
					this.endDate.equals(c.getEndDate()) &&
					this.id.equals(c.getId()) &&
					this.startDate.equals(c.getStartDate()) &&
					StringHelper.equals(this.type, c.getType());
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.model.IContract#getAssistantId()
	 */
	@Override
	public Integer getAssistantId() {
		return assistantId;
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.model.IContract#setAssistantId(java.lang.Integer)
	 */
	@Override
	public void setAssistantId(Integer assistantId) {
		this.assistantId = assistantId;
		
	}
}
