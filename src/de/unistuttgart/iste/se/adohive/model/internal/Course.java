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

import de.unistuttgart.iste.se.adohive.model.ICourse;


/**
 * @author Team AdoHive
 */
@AdoClass(tableName = "Veranstaltung")
public final class Course implements ICourse {
	protected Integer id;
	protected Integer financialCategoryId;
	protected String description;
	protected String semester;
	protected String lecturer;
	protected String advisor;
	protected Integer numberOfGroups;
	protected String targetAudience;
	protected Double unqualifiedWorkingHours; // UHKS
	protected String scope;
	protected Character part;
	protected String group;
	protected String remark;
	
	protected ICourse course;
	
	public Course() {
		
	}
	
	public Course(ICourse course) {
		this.course = course;
	}
	
	/**
	 * @return the id
	 */
	@AdoMethod(columnName = "ID")
	public Integer getId() {
		if(course != null)
			return course.getId();
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	@AdoMethod(columnName = "ID")
	public void setId(Integer id) {
		if(course != null)
			course.setId(id);
		this.id = id;
	}
	
	/**
	 * @return the financeCategoryId
	 */
	@AdoMethod(columnName = "Finanzkategorie_ID")
	public Integer getFinancialCategoryId() {
		if(course != null)
			return course.getFinancialCategoryId();
		return financialCategoryId;
	}

	/**
	 * @param financeCategoryId the financeCategoryId to set
	 */
	@AdoMethod(columnName = "Finanzkategorie_ID")
	public void setFinancialCategoryId(Integer financialCategoryId) {
		if(course != null)
			course.setFinancialCategoryId(financialCategoryId);
		this.financialCategoryId = financialCategoryId;
	}
	
	/**
	 * @return the description
	 */
	@AdoMethod(columnName = "Bezeichnung")
	public String getDescription() {
		if(course != null)
			return course.getDescription();
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	@AdoMethod(columnName = "Bezeichnung")
	public void setDescription(String description) {
		if(course != null)
			course.setDescription(description);
		this.description = description;
	}
	
	/**
	 * @return the semester
	 */
	@AdoMethod(columnName = "Semester")
	public String getSemester() {
		if(course != null)
			return course.getSemester();
		return semester;
	}
	
	/**
	 * @param semester the semester to set
	 */
	@AdoMethod(columnName = "Semester")
	public void setSemester(String semester) {
		if(course != null)
			course.setSemester(semester);
		this.semester = semester;
	}
	
	/**
	 * @return the lecturer
	 */
	@AdoMethod(columnName = "Dozent")
	public String getLecturer() {
		if(course != null)
			return course.getLecturer();
		return lecturer;
	}
	
	/**
	 * @param lecturer the lecturer to set
	 */
	@AdoMethod(columnName = "Dozent")
	public void setLecturer(String lecturer) {
		if(course != null)
			course.setLecturer(lecturer);
		this.lecturer = lecturer;
	}
	
	/**
	 * @return the advisor
	 */
	@AdoMethod(columnName = "Betreuer")
	public String getAdvisor() {
		if (course != null) {
			return course.getAdvisor();
		}
		return advisor;
	}
	
	/**
	 * @param advisor the advisor
	 */
	@AdoMethod(columnName = "Betreuer")
	public void setAdvisor(String advisor) {
		if (course != null) {
			course.setAdvisor(advisor);
		}
		this.advisor = advisor;
	}
	
	/**
	 * @return the numberOfGroups
	 */
	@AdoMethod(columnName = "Gruppenanzahl")
	public Integer getNumberOfGroups() {
		if(course != null)
			return course.getNumberOfGroups();
		return numberOfGroups;
	}
	
	/**
	 * @param numberOfGroups the numberOfGroups to set
	 */
	@AdoMethod(columnName = "Gruppenanzahl")
	public void setNumberOfGroups(Integer numberOfGroups) {
		if(course != null)
			course.setNumberOfGroups(numberOfGroups);
		this.numberOfGroups = numberOfGroups;
	}
	
	/**
	 * @return the targetAudience
	 */
	@AdoMethod(columnName = "Zielpublikum")
	public String getTargetAudience() {
		if(course != null)
			return course.getTargetAudience();
		return targetAudience;
	}
	
	/**
	 * @param targetAudience the targetAudience to set
	 */
	@AdoMethod(columnName = "Zielpublikum")
	public void setTargetAudience(String targetAudience) {
		if(course != null)
			course.setTargetAudience(targetAudience);
		this.targetAudience = targetAudience;
	}
	
	/**
	 * @return the unqualifiedWorkingHours
	 */
	@AdoMethod(columnName = "HKS")
	public Double getUnqualifiedWorkingHours() {
		if(course != null)
			return course.getUnqualifiedWorkingHours();
		return unqualifiedWorkingHours;
	}
	
	/**
	 * @param unqualifiedWorkingHours the unqualifiedWorkingHours to set
	 */
	@AdoMethod(columnName = "HKS")
	public void setUnqualifiedWorkingHours(Double unqualifiedWorkingHours) {
		if(course != null)
			course.setUnqualifiedWorkingHours(unqualifiedWorkingHours);
		this.unqualifiedWorkingHours = unqualifiedWorkingHours;
	}

	/**
	 * @return the scope
	 */
	@AdoMethod(columnName = "Umfang")
	public String getScope() {
		if(course != null)
			return course.getScope();
		return scope;
	}
	
	/**
	 * @param scope the scope to set
	 */
	@AdoMethod(columnName = "Umfang")
	public void setScope(String scope) {
		if(course != null)
			course.setScope(scope);
		this.scope = scope;
	}
	
	/**
	 * @return the part
	 */
	@AdoMethod(columnName = "Teil")
	public Character getPart() {
		if(course != null)
			return course.getPart();
		return part;
	}
	
	/**
	 * @param part the part to set
	 */
	@AdoMethod(columnName = "Teil")
	public void setPart(Character part) {
		if(course != null)
			course.setPart(part);
		this.part = part;
	}
	
	/**
	 * @return the group
	 */
	@AdoMethod(columnName = "Gruppe")
	public String getGroup() {
		if(course != null)
			return course.getGroup();
		return group;
	}
	
	/**
	 * @param group the group to set
	 */
	@AdoMethod(columnName = "Gruppe")
	public void setGroup(String group) {
		if(course != null)
			course.setGroup(group);
		this.group = group;
	}
	
	/**
	 * @return the remark
	 */
	@AdoMethod(columnName = "Bemerkung")
	public String getRemark() {
		if(course != null)
			return course.getRemark();
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	@AdoMethod(columnName = "Bemerkung")
	public void setRemark(String remark) {
		if(course != null)
			course.setRemark(remark);
		this.remark = remark;
	}
	
/*	@Override
	public boolean equals(Object o) {
		if (o instanceof Course) {
			Course c = (Course) o;
			return StringHelper.equals(c.getDescription(), this.description) &&
					c.getFinancialCategoryId() == this.financialCategoryId &&
					StringHelper.equals(c.getGroup(), this.group) &&
					c.getId() == this.id &&
					StringHelper.equals(c.getLecturer(), this.lecturer) &&
					StringHelper.equals(c.getAdvisor(), this.advisor) &&
					c.getNumberOfGroups() == this.numberOfGroups &&
					c.getPart() == this.part &&
					StringHelper.equals(c.getRemark(), this.remark) &&
					StringHelper.equals(c.getScope(), this.scope) &&
					StringHelper.equals(c.getSemester(), this.semester) &&
					StringHelper.equals(c.getTargetAudience(), this.targetAudience) &&
					c.getUnqualifiedWorkingHours() == this.unqualifiedWorkingHours;
		} else {
			return false;
		}
	}
	*/
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
