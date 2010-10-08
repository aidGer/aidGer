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

package de.unistuttgart.iste.se.adohive.model;

/**
 * Provides access to one Employment data object
 * @author Team AdoHive
 *
 */
public interface IEmployment extends IAdoHiveModel<IEmployment> {
	
	/* keeping this here for awesome code generation
	private int id;
	private int assistantId;
	private int courseId;
	private byte month;
	private short year;
	private double hourCount;
	private String costUnit;
	private String remark;
	private String qualification;
	*/
	
	/**
	 * @return the assistantId
	 */
	public Integer getAssistantId();
	
	/**
	 * @param assistantId the assistantId to set
	 */
	public void setAssistantId(Integer assistantId);
	
	/**
	 * @return the eventId
	 */
	public Integer getCourseId();
	
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(Integer courseId);
	
	/**
	 * @return the contractId
	 */
	public Integer getContractId();
	
	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(Integer contractId);
	
	
	/**
	 * @return the month
	 */
	public Byte getMonth();
	
	/**
	 * @param month the month to set
	 */
	public void setMonth(Byte month);
	
	/**
	 * @return the year
	 */
	public Short getYear();
	
	/**
	 * @param year the year to set
	 */
	public void setYear(Short year);
	
	/**
	 * @return the hourCount
	 */
	public Double getHourCount();
	
	/**
	 * @param hourCount the hourCount to set
	 */
	public void setHourCount(Double hourCount);
	
	/**
	 * @return the costUnit
	 */
	public Integer getCostUnit();
	
	/**
	 * @param costUnit the costUnit to set
	 */
	public void setCostUnit(Integer costUnit);
	
	/**
	 * @return the funds
	 */
	public String getFunds();
	
	/**
	 * @param funds the funds to set
	 */
	public void setFunds(String funds);
	
	/**
	 * @return the remark
	 */
	public String getRemark();
	
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark);
	
	/**
	 * @return the qualification
	 */
	public String getQualification();
	
	/**
	 * @param qualification the qualification
	 */
	public void setQualification(String qualification);
}
