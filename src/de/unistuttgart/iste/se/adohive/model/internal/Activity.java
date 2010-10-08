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

import de.unistuttgart.iste.se.adohive.model.IActivity;


/**
 * @author Team AdoHive
 */
@AdoClass(tableName = "Vorgang")
public class Activity implements IActivity{
	protected Integer id;
	protected Integer assistantId;
	protected Integer courseId;
	protected Date date;
	protected String sender;
	protected String documentType;
	protected String type;
	protected String processor;
	protected String content;
	protected String remark;
	
	protected IActivity activity;
	
	public Activity() {
		
	}
	
	public Activity(IActivity activity) {
		this.activity = activity;
	}
	
	/**
	 * @return the id
	 */
	@AdoMethod(columnName = "ID")
	public Integer getId() {
		if(activity != null)
			return activity.getId();
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	@AdoMethod(columnName = "ID")
	public void setId(Integer id) {
		if(activity != null)
			activity.setId(id);
		this.id = id;
	}
	
	/**
	 * @return the assistantId
	 */
	@AdoMethod(columnName = "Hilfskraft_ID")
	public Integer getAssistantId() {
		if(activity != null)
			return activity.getAssistantId();
		return assistantId;
	}
	
	/**
	 * @param assistantId the assistantId to set
	 */
	@AdoMethod(columnName = "Hilfskraft_ID")
	public void setAssistantId(Integer assistantId) {
		if(activity != null)
			activity.setAssistantId(assistantId);
		this.assistantId = assistantId;
	}
	
	/**
	 * @return the courseId
	 */
	@AdoMethod(columnName = "Veranstaltung_ID")
	public Integer getCourseId() {
		if(activity != null)
			return activity.getCourseId();
		return courseId;
	}
	
	/**
	 * @param courseId the courseId to set
	 */
	@AdoMethod(columnName = "Veranstaltung_ID")
	public void setCourseId(Integer courseId) {
		if(activity != null)
			activity.setCourseId(courseId);
		this.courseId = courseId;
	}
	
	/**
	 * @return the date
	 */
	@AdoMethod(columnName = "Datum")
	public Date getDate() {
		if(activity != null)
			return activity.getDate();
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	@AdoMethod(columnName = "Datum")
	public void setDate(Date date) {
		if(activity != null)
			activity.setDate(date);
		this.date = date;
	}
	
	/**
	 * @return the sender
	 */
	@AdoMethod(columnName = "Sender")
	public String getSender() {
		if(activity != null)
			return activity.getSender();
		return sender;
	}
	
	/**
	 * @param sender the sender to set
	 */
	@AdoMethod(columnName = "Sender")
	public void setSender(String sender) {
		if(activity != null)
			activity.setSender(sender);
		this.sender = sender;
	}
	
	/**
	 * @return the documentType
	 */
	@AdoMethod(columnName = "Dokumententyp")
	public String getDocumentType() {
		if(activity != null)
			return activity.getDocumentType();
		return documentType;
	}
	
	/**
	 * @param documentType the documentType to set
	 */
	@AdoMethod(columnName = "Dokumententyp")
	public void setDocumentType(String documentType) {
		if(activity != null)
			activity.setDocumentType(documentType);
		this.documentType = documentType;
	}
	
	/**
	 * @return the type
	 */
	@AdoMethod(columnName = "Art")
	public String getType() {
		if(activity != null)
			return activity.getType();
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	@AdoMethod(columnName = "Art")
	public void setType(String type) {
		if(activity != null)
			activity.setType(type);
		this.type = type;
	}
	
	/**
	 * @return the processor
	 */
	@AdoMethod(columnName = "Bearbeiter")
	public String getProcessor() {
		if(activity != null)
			return activity.getProcessor();
		return processor;
	}
	
	/**
	 * @param processor the processor to set
	 */
	@AdoMethod(columnName = "Bearbeiter")
	public void setProcessor(String processor) {
		if(activity != null)
			activity.setProcessor(processor);
		this.processor = processor;
	}
	
	/**
	 * @return the content
	 */
	@AdoMethod(columnName = "Inhalt")
	public String getContent() {
		if(activity != null)
			return activity.getContent();
		return content;
	}
	
	/**
	 * @param content the content to set
	 */
	@AdoMethod(columnName = "Inhalt")
	public void setContent(String content) {
		if(activity != null)
			activity.setContent(content);
		this.content = content;
	}
	
	/**
	 * @return the remark
	 */
	@AdoMethod(columnName = "Bemerkung")
	public String getRemark() {
		if(activity != null)
			return activity.getRemark();
		return remark;
	}
	
	/**
	 * @param remark the remark to set
	 */
	@AdoMethod(columnName = "Bemerkung")
	public void setRemark(String remark) {
		if(activity != null)
			activity.setRemark(remark);
		this.remark = remark;
	}
	
/*	@Override
	public boolean equals(Object o) {
		if (o instanceof IActivity) {
			IActivity a = (IActivity) o;
			return a.getAssistantId() == this.assistantId &&
					StringHelper.equals(a.getContent(), this.content) &&
					a.getCourseId() == this.courseId &&
					a.getDate().equals(this.date) &&
					StringHelper.equals(a.getDocumentType(), this.documentType) &&
					a.getId() == this.id &&
					StringHelper.equals(a.getProcessor(), this.processor) &&
					StringHelper.equals(a.getRemark(), this.remark) &&
					StringHelper.equals(a.getSender(), this.sender) &&
					StringHelper.equals(a.getType(), this.type);
		} else {
			return false;
		}
	}
*/	
	public Activity clone() {
		Activity a = new Activity();
		a.setAssistantId(this.assistantId);
		a.setContent(this.content);
		a.setCourseId(this.courseId);
		a.setDate(this.date);
		a.setDocumentType(this.documentType);
		a.setId(this.id);
		a.setProcessor(this.processor);
		a.setRemark(this.remark);
		a.setSender(this.sender);
		a.setType(this.type);
		
		return a;
	}
}
