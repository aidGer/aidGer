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

import de.unistuttgart.iste.se.adohive.model.IEmployment;

/**
 * @author Team AdoHive
 */
@AdoClass(tableName = "Beschaeftigung")
public final class Employment implements IEmployment {
	protected Integer id;
	protected Integer assistantId;
	protected Integer courseId;
	protected Integer contractId;
	protected Byte month;
	protected Short year;
	protected Double hourCount;
	protected Integer costUnit;
	protected String remark;
	protected String funds;
	protected String qualification;
	
	protected IEmployment employment;
	
	public Employment() {
		
	}
	
	public Employment(IEmployment employment) {
		this.employment = employment;
	}
	
	/**
	 * @return the id
	 */
	@AdoMethod(columnName = "ID")
	public Integer getId() {
		if(employment != null)
			return employment.getId();
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	@AdoMethod(columnName = "ID")
	public void setId(Integer id) {
		if(employment != null)
			employment.setId(id);
		this.id = id;
	}
	
	/**
	 * @return the assistantId
	 */
	@AdoMethod(columnName = "Hilfskraft_ID")
	public Integer getAssistantId() {
		if(employment != null)
			return employment.getAssistantId();
		return assistantId;
	}
	
	/**
	 * @param assistantId the assistantId to set
	 */
	@AdoMethod(columnName = "Hilfskraft_ID")
	public void setAssistantId(Integer assistantId) {
		if(employment != null)
			employment.setAssistantId(assistantId);
		this.assistantId = assistantId;
	}
	
	/**
	 * @return the courseId
	 */
	@AdoMethod(columnName = "Veranstaltung_ID")
	public Integer getCourseId() {
		if(employment != null)
			return employment.getCourseId();
		return courseId;
	}
	
	/**
	 * @param courseId the courseId to set
	 */
	@AdoMethod(columnName = "Veranstaltung_ID")
	public void setCourseId(Integer courseId) {
		if(employment != null)
			employment.setCourseId(courseId);
		this.courseId = courseId;
	}
	
	@Override
	@AdoMethod(columnName = "Vertrag_ID")
	public Integer getContractId() {
		if(employment != null)
			return employment.getContractId();
		return contractId;
	}

	@Override
	@AdoMethod(columnName = "Vertrag_ID")
	public void setContractId(Integer contractId) {
		if(employment != null)
			employment.setContractId(contractId);
		this.contractId = contractId;
	}
	
	/**
	 * @return the month
	 */
	@AdoMethod(columnName = "Monat")
	public Byte getMonth() {
		if(employment != null)
			return employment.getMonth();
		return month;
	}
	
	/**
	 * @param month the month to set
	 */
	@AdoMethod(columnName = "Monat")
	public void setMonth(Byte month) {
		if(employment != null)
			employment.setMonth(month);
		this.month = month;
	}
	
	/**
	 * @return the year
	 */
	@AdoMethod(columnName = "Jahr")
	public Short getYear() {
		if(employment != null)
			return employment.getYear();
		return year;
	}
	
	/**
	 * @param year the year to set
	 */
	@AdoMethod(columnName = "Jahr")
	public void setYear(Short year) {
		if(employment != null)
			employment.setYear(year);
		this.year = year;
	}
	
	/**
	 * @return the hourCount
	 */
	@AdoMethod(columnName = "AnzahlStunden")
	public Double getHourCount() {
		if(employment != null)
			return employment.getHourCount();
		return hourCount;
	}
	
	/**
	 * @param hourCount the hourCount to set
	 */
	@AdoMethod(columnName = "AnzahlStunden")
	public void setHourCount(Double hourCount) {
		if(employment != null)
			employment.setHourCount(hourCount);
		this.hourCount = hourCount;
	}
	
	/**
	 * @return the costUnit
	 */
	@AdoMethod(columnName = "Kostenstelle")
	public Integer getCostUnit() {
		if(employment != null)
			return employment.getCostUnit();
		return costUnit;
	}
	
	/**
	 * @param costUnit the costUnit to set
	 */
	@AdoMethod(columnName = "Kostenstelle")
	public void setCostUnit(Integer costUnit) {
		if(employment != null)
			employment.setCostUnit(costUnit);
		this.costUnit = costUnit;
	}
	
	/**
	 * @return the funds
	 */
	@AdoMethod(columnName = "Fonds")
	public String getFunds() {
		if(employment != null)
			return employment.getFunds();
		return funds;
	}
	
	/**
	 * @param funds the funds to set
	 */
	@Deprecated
	@AdoMethod(columnName = "Fonds")
	public void setFunds(String funds) {
		if(employment != null)
			employment.setFunds(funds);
		this.funds = funds;
	}
	
	/**
	 * @return the remark
	 */
	@AdoMethod(columnName = "Bemerkung")
	public String getRemark() {
		if(employment != null)
			return employment.getRemark();
		return remark;
	}
	
	/**
	 * @param remark the remark to set
	 */
	@AdoMethod(columnName = "Bemerkung")
	public void setRemark(String remark) {
		if(employment != null)
			employment.setRemark(remark);
		this.remark = remark;
	}
	
	/**
	 * @return the qualification
	 */
	@AdoMethod(columnName = "Qualifikation")
	public String getQualification() {
		if (employment != null) {
			return employment.getQualification();
		}
		return qualification;
	}
	
	/**
	 * @param qualification the qualification
	 */
	@AdoMethod(columnName = "Qualifikation")
	public void setQualification(String qualification) {
		if (employment != null) {
			employment.setQualification(qualification);
		}
		this.qualification = qualification;
	}
	
/*	@Override
	public boolean equals(Object o) {
		if (o instanceof Employment) {
			Employment e = (Employment) o;
			return e.getAssistantId() == this.assistantId &&
					StringHelper.equals(e.getCostUnit(), this.costUnit) &&
					e.getCourseId() == this.courseId &&
					e.getHourCount() == this.hourCount &&
					e.getId() == this.id &&
					e.getMonth() == this.month &&
					StringHelper.equals(e.getRemark(), this.remark) &&
					e.getYear() == this.year &&
					StringHelper.equals(e.getQualification(), this.qualification) ;
		} else {
			return false;
		}
	}
*/	
	public Employment clone() {
		Employment e = new Employment();
		e.setAssistantId(this.assistantId);
		e.setContractId(this.contractId);
		e.setCostUnit(this.costUnit);
		e.setCourseId(this.courseId);
		e.setHourCount(this.hourCount);
		e.setId(this.id);
		e.setMonth(this.month);
		e.setRemark(this.remark);
		e.setQualification(this.qualification);
		e.setYear(this.year);
		return e;
	}
}
