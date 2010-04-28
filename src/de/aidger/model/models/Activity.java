package de.aidger.model.models;

import java.util.Date;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.model.IActivity;

/**
 * Represents a single entry in the activity column of the database.
 * Contains functions to retrieve and change the data in the database.
 * 
 * @author aidGer Team
 */
public class Activity extends AbstractModel implements IActivity {
	
	/**
	 * The unique id of the element in the database.
	 */
	private int id;
	
	/**
	 * References the assistant by its unique id.
	 */
	private int assistantId;
	
	/**
	 * References the course by its unique id.
	 */
	private int courseId;
	
	/**
	 * The date on which this activity took place.
	 */
	private Date date;
	
	/**
	 * The sender of the activity.
	 */
	private String sender;
	
	/**
	 * The type of document referenced by this activity.
	 */
	private String documentType;
	
	/**
	 * The type of the activity.
	 */
	private String type;
	
	/**
	 * The processor of the activity.
	 */
	private String processor;
	
	/**
	 * The contents of the activity.
	 */
	private String content;
	
	/**
	 * Remarks for the activity.
	 */
	private String remark;
	
	/** 
	 * Clone the current activity
	 */
	public Activity clone()
	{
		return new Activity();
	}

	/**
	 * Get the id of the referenced assistant.
	 * 
	 * @return The id of the referenced assistant
	 */
	@Override
	public int getAssistantId() {		
		return assistantId;
	}

	/**
	 * Get the contents of the activity.
	 * 
	 * @return The contents of the activity
	 */
	@Override
	public String getContent() {		
		return content;
	}

	/**
	 * Get the id of the referenced course.
	 * 
	 * @return The id of the referenced course.
	 */
	@Override
	public int getCourseId() {		
		return courseId;
	}

	/**
	 * Get the date of the activity.
	 * 
	 * @return The date of the activity.
	 */
	@Override
	public Date getDate() {		
		return date;
	}

	/**
	 * Get the type of document.
	 * 
	 * @return The type of document
	 */
	@Override
	public String getDocumentType() {		
		return documentType;
	}

	/**
	 * Get the processor of the activity.
	 * 
	 * @return The processor of the activity.
	 */
	@Override
	public String getProcessor() {
		return processor;
	}

	/**
	 * Get remarks to the activity.
	 * 
	 * @return Remarks to the activity
	 */
	@Override
	public String getRemark() {
		return remark;
	}

	/**
	 * Get the sender of the activity.
	 * 
	 * @return The sender of the activity.
	 */
	@Override
	public String getSender() {
		return sender;
	}

	/**
	 * Get the type of the activity.
	 * 
	 * @return The type of the activity
	 */
	@Override
	public String getType() {
		return type;
	}

	/**
	 * Set the id of the assistant referenced by this activity.
	 * 
	 * @param id The id of the assistant
	 */
	@Override
	public void setAssistantId(int id) {
		assistantId = id;
	}

	/**
	 * Set the contents of the activity.
	 * 
	 * @param cont The contents of the activity
	 */
	@Override
	public void setContent(String cont) {
		content = cont;
	}

	/**
	 * Set the id of the course referenced by this activity.
	 * 
	 * @param id The id of the course
	 */
	@Override
	public void setCourseId(int id) {
		courseId = id;
	}

	/**
	 * Set the date of the activity.
	 * 
	 * @param dt The date of the activity
	 */
	@Override
	public void setDate(Date dt) {
		date = dt;
	}

	/**
	 * Set the type of the document
	 * 
	 * @param type The type of the document
	 */
	@Override
	public void setDocumentType(String type) {
		documentType = type;
	}

	/**
	 * Set the processor of the activity.
	 * 
	 * @param proc The processor of the activity
	 */
	@Override
	public void setProcessor(String proc) {
		processor = proc;
	}

	/**
	 * Set remarks to the activity.
	 * 
	 * @param rem Remarks to the activity
	 */
	@Override
	public void setRemark(String rem) {
		remark = rem;
	}

	/**
	 * Set the sender of the activity.
	 * 
	 * @param send The sender of the activity
	 */
	@Override
	public void setSender(String send) {
		sender = send;
	}

	/**
	 * Set the type of the activity.
	 * 
	 * @param typec The type of the activity
	 */
	@Override
	public void setType(String typec) {
		type = typec;
	}

	/**
	 * Returns the unique id of the activity.
	 * 
	 * @return The unique id of the activity
	 */
	@Override
	public int getId() {		
		return id;
	}

	/**
	 * Set the unique id of the activity.
	 * 
	 * <b>!!! THIS IS FOR INTERNAL ADOHIVE USAGE ONLY !!!</b>
	 * 
	 * @param id The unique id of the activity
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}

}
