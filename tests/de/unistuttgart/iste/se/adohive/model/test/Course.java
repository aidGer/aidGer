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

import de.unistuttgart.iste.se.adohive.model.ICourse;


/**
 * @author Team AdoHive
 */
public final class Course implements ICourse {
	protected Integer id;
	protected Integer financialCategoryId = IndependentTestDataProvider.randomInt();
	protected String description = IndependentTestDataProvider.randomString();
	protected String semester = IndependentTestDataProvider.randomFixedLengthDigitString(6);
	protected String lecturer = IndependentTestDataProvider.randomString();
	protected String advisor = IndependentTestDataProvider.randomString();
	protected Integer numberOfGroups = IndependentTestDataProvider.randomInt();
	protected String targetAudience = IndependentTestDataProvider.randomString();
	protected Double unqualifiedWorkingHours = IndependentTestDataProvider.randomDouble();
	protected String scope = IndependentTestDataProvider.randomStringFromArr(new String[] {"1�", "2�", "1P", "2P", "4P", "6P"});
	protected Character part = IndependentTestDataProvider.randomChar();
	protected String group = IndependentTestDataProvider.randomString();
	protected String remark = IndependentTestDataProvider.randomString();
		
	public Course() {
		
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
	 * @return the financeCategoryId
	 */
	public Integer getFinancialCategoryId() {
		return financialCategoryId;
	}

	/**
	 * @param financeCategoryId the financeCategoryId to set
	 */
	public void setFinancialCategoryId(Integer financialCategoryId) {
		this.financialCategoryId = financialCategoryId;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}
	
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	/**
	 * @return the lecturer
	 */
	public String getLecturer() {
		return lecturer;
	}
	
	/**
	 * @param lecturer the lecturer to set
	 */
	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}
	
	/**
	 * @return the advisor
	 */
	public String getAdvisor() {
		return advisor;
	}
	
	/**
	 * @param advisor the advisor
	 */
	public void setAdvisor(String advisor) {
	this.advisor = advisor;
	}
	
	/**
	 * @return the numberOfGroups
	 */
	public Integer getNumberOfGroups() {
		return numberOfGroups;
	}
	
	/**
	 * @param numberOfGroups the numberOfGroups to set
	 */
	public void setNumberOfGroups(Integer numberOfGroups) {
		this.numberOfGroups = numberOfGroups;
	}
	
	/**
	 * @return the targetAudience
	 */
	public String getTargetAudience() {
		return targetAudience;
	}
	
	/**
	 * @param targetAudience the targetAudience to set
	 */
	public void setTargetAudience(String targetAudience) {
		this.targetAudience = targetAudience;
	}
	
	/**
	 * @return the unqualifiedWorkingHours
	 */
	public Double getUnqualifiedWorkingHours() {
		return unqualifiedWorkingHours;
	}
	
	/**
	 * @param unqualifiedWorkingHours the unqualifiedWorkingHours to set
	 */
	public void setUnqualifiedWorkingHours(Double unqualifiedWorkingHours) {
		this.unqualifiedWorkingHours = unqualifiedWorkingHours;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}
	
	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
	this.scope = scope;
	}
	
	/**
	 * @return the part
	 */
	public Character getPart() {
	return part;
	}
	
	/**
	 * @param part the part to set
	 */
	public void setPart(Character part) {
		this.part = part;
	}
	
	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}
	
	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
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
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof ICourse) {
			ICourse c = (ICourse) o;
			return StringHelper.equals(c.getDescription(), this.description) &&
					c.getFinancialCategoryId().equals(this.financialCategoryId) &&
					StringHelper.equals(c.getGroup(), this.group) &&
					c.getId().equals(this.id) &&
					StringHelper.equals(c.getLecturer(), this.lecturer) &&
					StringHelper.equals(c.getAdvisor(), this.advisor) &&
					c.getNumberOfGroups().equals(this.numberOfGroups) &&
					c.getPart().equals(this.part) &&
					StringHelper.equals(c.getRemark(), this.remark) &&
					StringHelper.equals(c.getScope(), this.scope) &&
					StringHelper.equals(c.getSemester(), this.semester) &&
					StringHelper.equals(c.getTargetAudience(), this.targetAudience) &&
					c.getUnqualifiedWorkingHours().equals(this.unqualifiedWorkingHours);
		} else {
			return false;
		}
	}
	
	public Course clone() {
		Course c = new Course();
		c.setDescription(this.description);
		c.setFinancialCategoryId(this.financialCategoryId);
		c.setGroup(this.group);
		c.setId(this.id);
		c.setLecturer(this.lecturer);
		c.setLecturer(this.advisor);
		c.setNumberOfGroups(this.numberOfGroups);
		c.setPart(this.part);
		c.setRemark(this.remark);
		c.setScope(this.scope);
		c.setSemester(this.semester);
		c.setTargetAudience(this.targetAudience);
		c.setUnqualifiedWorkingHours(this.unqualifiedWorkingHours);
		return c;
	}
}
