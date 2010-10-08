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

import java.sql.Date;

import de.unistuttgart.iste.se.adohive.model.IContract;

/**
 * @author Team AdoHive
 */
@AdoClass(tableName = "Vertrag")
public final class Contract implements IContract{
	protected Integer id;
	protected Integer assistantId;
	protected String type;
	protected Date startDate;
	protected Date endDate;
	protected Date completionDate;
	protected Date confirmationDate;
	protected Boolean delegation;
	
	protected IContract contract;
	
	public Contract() {
		
	}
	
	public Contract(IContract contract) {
		this.contract = contract;
	}
	
	/**
	 * @return the id
	 */
	@AdoMethod(columnName = "ID")
	public Integer getId() {
		if(contract != null)
			return contract.getId();
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	@AdoMethod(columnName = "ID")
	public void setId(Integer id) {
		if(contract != null)
			contract.setId(id);
		this.id = id;
	}
	
	/**
	 * @return the assistantId
	 */
	@AdoMethod(columnName = "Hilfskraft_ID")
	public Integer getAssistantId() {
		if(contract != null)
			return contract.getAssistantId();
		return assistantId;
	}
	
	/**
	 * @param assistantId the assistantId to set
	 */
	@AdoMethod(columnName = "Hilfskraft_ID")
	public void setAssistantId(Integer assistantId) {
		if(contract != null)
			contract.setAssistantId(assistantId);
		this.assistantId = assistantId;
	}
	
	/**
	 * @return the type
	 */
	@AdoMethod(columnName = "Art")
	public String getType() {
		if(contract != null)
			return contract.getType();
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	@AdoMethod(columnName = "Art")
	public void setType(String type) {
		if(contract != null)
			contract.setType(type);
		this.type = type;
	}

	/**
	 * @return the startDate
	 */
	@AdoMethod(columnName = "DatumAnfang")
	public Date getStartDate() {
		if(contract != null)
			return contract.getStartDate();
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	@AdoMethod(columnName = "DatumAnfang")
	public void setStartDate(Date startDate) {
		if(contract != null)
			contract.setStartDate(startDate);
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	@AdoMethod(columnName = "DatumEnde")
	public Date getEndDate() {
		if(contract != null)
			return contract.getEndDate();
		return endDate;
	}
	
	/**
	 * @param endDate the endDate to set
	 */
	@AdoMethod(columnName = "DatumEnde")
	public void setEndDate(Date endDate) {
		if(contract != null)
			contract.setEndDate(endDate);
		this.endDate = endDate;
	}
	
	/**
	 * @return the completionDate
	 */
	@AdoMethod(columnName = "DatumAbschluss")
	public Date getCompletionDate() {
		if(contract != null)
			return contract.getCompletionDate();
		return completionDate;
	}
	
	/**
	 * @param completionDate the completionDate to set
	 */
	@AdoMethod(columnName = "DatumAbschluss")
	public void setCompletionDate(Date completionDate) {
		if(contract != null)
			contract.setCompletionDate(completionDate);
		this.completionDate = completionDate;
	}

	/**
	 * @return the confirmationDate
	 */
	@AdoMethod(columnName = "DatumBestaetigung")
	public Date getConfirmationDate() {
		if(contract != null)
			return contract.getConfirmationDate();
		return confirmationDate;
	}

	/**
	 * @param confirmationDate the confirmationDate to set
	 */
	@AdoMethod(columnName = "DatumBestaetigung")
	public void setConfirmationDate(Date confirmationDate) {
		if(contract != null)
			contract.setConfirmationDate(confirmationDate);
		this.confirmationDate = confirmationDate;
	}
	
	/**
	 * @return the delegation
	 */
	@AdoMethod(columnName = "Delegation")
	public Boolean isDelegation() {
		if(contract != null)
			return contract.isDelegation();
		return delegation;
	}

	/**
	 * @param delegation the delegation to set
	 */
	@AdoMethod(columnName = "Delegation")
	public void setDelegation(Boolean delegation) {
		if(contract != null)
			contract.setDelegation(delegation);
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
}
