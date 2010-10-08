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
 * Provides access to one Course data object
 * 
 * @author anonymous AdoHive Core Developer
 */
public interface ICourse extends IAdoHiveModel<ICourse> {

	/* keeping this here for awesome code generation
	private int id;
	private int financeCategoryId;
	private String description;
	private String semester;
	private String lecturer;
	private String advisor;
	private int numberOfGroups;
	private String targetAudience;
	private double unqualifiedWorkingHours; // UHKS
	private String scope;
	private char part;
	private String group;
	private String remark;
	
	*/
	
	
	/**
	 * @return the financeCategoryId
	 */
	public Integer getFinancialCategoryId();
	
	/**
	 * @param financialCategoryId the financialCategoryId to set
	 */
	public void setFinancialCategoryId(Integer financialCategoryId);
	
	/**
	 * @return the description
	 */
	public String getDescription();
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description);
	
	/**
	 * @return the semester
	 */
	public String getSemester();
	
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(String semester);
	
	/**
	 * @return the lecturer
	 */
	public String getLecturer();
	
	/**
	 * @param lecturer the lecturer to set
	 */
	public void setLecturer(String lecturer);
	
	/**
	 * @return the advisor
	 */
	public String getAdvisor();
	
	/**
	 * @param advisor the advisor
	 */
	public void setAdvisor(String advisor);
	
	/**
	 * @return the numberOfGroups
	 */
	public Integer getNumberOfGroups();
	
	/**
	 * @param numberOfGroups the numberOfGroups to set
	 */
	public void setNumberOfGroups(Integer numberOfGroups);
	
	/**
	 * @return the targetAudience
	 */
	public String getTargetAudience();
	
	/**
	 * @param targetAudience the targetAudience to set
	 */
	public void setTargetAudience(String targetAudience) ;
	
	/**
	 * @return the unqualifiedWorkingHours
	 */
	public Double getUnqualifiedWorkingHours();
	
	/**
	 * @param unqualifiedWorkingHours the unqualifiedWorkingHours to set
	 */
	public void setUnqualifiedWorkingHours(Double unqualifiedWorkingHours);
	
	/**
	 * @return the scope
	 */
	public String getScope();
	
	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope);
	
	/**
	 * @return the part
	 */
	public Character getPart();
	
	/**
	 * @param part the part to set
	 */
	public void setPart(Character part);
	
	/**
	 * @return the group
	 */
	public String getGroup();
	
	/**
	 * @param group the group to set
	 */
	public void setGroup(String group);
	
	/**
	 * @return the remark
	 */
	public String getRemark();
	
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark);
}
