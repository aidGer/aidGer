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

import de.unistuttgart.iste.se.adohive.model.IEmployment;

/**
 * @author Team AdoHive
 */
public final class Employment implements IEmployment {
	protected Integer id;
	protected Integer assistantId = IndependentTestDataProvider.randomInt();
	protected Integer courseId = IndependentTestDataProvider.randomInt();
	protected Integer contractId = IndependentTestDataProvider.randomInt();
	protected Byte month = (byte) IndependentTestDataProvider.randomChar();
	protected Short year = (short) IndependentTestDataProvider.randomChar();
	protected Double hourCount = IndependentTestDataProvider.randomDouble();
	protected Integer costUnit = IndependentTestDataProvider.randomInt();
	protected String remark = IndependentTestDataProvider.randomString();
	protected String funds = IndependentTestDataProvider.randomFixedLengthDigitString(8);
	protected String qualification = IndependentTestDataProvider.randomFixedLengthDigitString(1);
		
	public Employment() {
		
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
	 * @return the assistantId
	 */
	public Integer getAssistantId() {
		return assistantId;
	}
	
	/**
	 * @param assistantId the assistantId to set
	 */
	public void setAssistantId(Integer assistantId) {
		this.assistantId = assistantId;
	}
	
	/**
	 * @return the courseId
	 */
	public Integer getCourseId() {
		return courseId;
	}
	
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	@Override
	public Integer getContractId() {
		return contractId;
	}

	@Override
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}
	
	/**
	 * @return the month
	 */
	public Byte getMonth() {
		return month;
	}
	
	/**
	 * @param month the month to set
	 */
	public void setMonth(Byte month) {
	this.month = month;
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
	
	/**
	 * @return the hourCount
	 */
	public Double getHourCount() {
		return hourCount;
	}
	
	/**
	 * @param hourCount the hourCount to set
	 */
	public void setHourCount(Double hourCount) {
		this.hourCount = hourCount;
	}
	
	/**
	 * @return the costUnit
	 */
	public Integer getCostUnit() {
		return costUnit;
	}
	
	/**
	 * @param costUnit the costUnit to set
	 */
	public void setCostUnit(Integer costUnit) {
	this.costUnit = costUnit;
	}
	
	/**
	 * @return the funds
	 */
	public String getFunds() {
	return funds;
	}
	
	/**
	 * @param funds the funds to set
	 */
	public void setFunds(String funds) {
	this.funds = funds;
	}
	
	/**
	 * @return the remark
	 */
	public String getRemark() {
	return remark;
	}
	
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}
	
	/**
	 * @param qualification the qualification
	 */
	public void setQualification(String qualification) {
	this.qualification = qualification;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof IEmployment) {
			IEmployment e = (IEmployment) o;
			return e.getAssistantId().equals(this.assistantId) &&
					StringHelper.equals(e.getFunds(), this.funds) &&
					e.getCostUnit().equals(costUnit) &&
					e.getCourseId().equals(this.courseId) &&
					e.getHourCount().equals(this.hourCount) &&
					e.getId().equals(this.id) &&
					e.getMonth().equals(this.month) &&
					StringHelper.equals(e.getRemark(), this.remark) &&
					e.getYear().equals(this.year) &&
					StringHelper.equals(e.getQualification(), this.qualification) ;
		} else {
			return false;
		}
	}
	
	public Employment clone() {
		Employment e = new Employment();
		e.setAssistantId(this.assistantId);
		e.setContractId(this.contractId);
		e.setCostUnit(this.costUnit);
		e.setFunds(this.funds);
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
