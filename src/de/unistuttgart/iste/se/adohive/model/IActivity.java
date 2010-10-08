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
package de.unistuttgart.iste.se.adohive.model;

import java.sql.Date;

/**
 * Provides access to one Activity data object
 * @author Team AdoHive
 *
 */
public interface IActivity extends IAdoHiveModel<IActivity> {

	/* keeping this here for awesome code generation
	private int id;
	private int assistantId;
	private int courseId;
	private Date date;
	private String sender;
	private String documentType;
	private String type;
	private String processor;
	private String content;
	private String remark;
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
	 * @return the courseId
	 */
	public Integer getCourseId();
	
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(Integer courseId);
	
	/**
	 * @return the date
	 */
	public Date getDate();
	
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date);
	
	/**
	 * @return the sender
	 */
	public String getSender();
	
	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender);
	
	/**
	 * @return the documentType
	 */
	public String getDocumentType();
	
	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType);
	
	/**
	 * @return the type
	 */
	public String getType();
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type);

	/**
	 * @return the processor
	 */
	public String getProcessor();
	
	/**
	 * @param processor the processor to set
	 */
	public void setProcessor(String processor);
	
	/**
	 * @return the content
	 */
	public String getContent();
	
	/**
	 * @param content the content to set
	 */
	public void setContent(String content);
	
	/**
	 * @return the remark
	 */
	public String getRemark();
	
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark);
}
