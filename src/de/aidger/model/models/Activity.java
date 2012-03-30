/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2011 The aidGer Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.aidger.model.models;

import static de.aidger.utils.Translation._;

import java.sql.Date;
import java.util.List;

import siena.Table;
import siena.Column;

import de.aidger.model.AbstractModel;

/**
 * Represents a single entry in the activity column of the database. Contains
 * functions to retrieve and change the data in the database.
 * 
 * @author aidGer Team
 */
@Table("Vorgang")
public class Activity extends AbstractModel<Activity>  {

    /**
     * References the corresponding assistant.
     */
    @Column("Hilfskraft_ID")
    private Integer assistantId;

    /**
     * References the course by its unique id.
     */
    @Column("Veranstaltung_ID")
    private Integer courseId;

    /**
     * The date on which this activity took place.
     */
    @Column("Datum")
    private Date date;

    /**
     * The sender of the activity.
     */
    @Column("Sender")
    private String sender;

    /**
     * The type of document referenced by this activity.
     */
    @Column("Dokumententyp")
    private String documentType;

    /**
     * The type of the activity.
     */
    @Column("Art")
    private String type;

    /**
     * The processor of the activity.
     */
    @Column("Bearbeiter")
    private String processor;

    /**
     * The contents of the activity.
     */
    @Column("Inhalt")
    private String content;

    /**
     * Remarks for the activity.
     */
    @Column("Bemerkung")
    private String remark;

    /**
     * Initializes the Activity class.
     */
    public Activity() {
        if (getValidators().isEmpty()) {
             validatePresenceOf(new String[] { "date", "type", "processor",
                    "content", "documentType" }, new String[] { _("Date"),
                    _("Type"), _("Processor"), _("Content"), _("Document Type") });
            validateExistenceOf(new String[]{"assistantId"},
                    new String[]{_("Assistant")}, new Assistant());
            validateExistenceOf(new String[]{"courseId"},
                    new String[]{_("Course")}, new Course());
        }
    }

    /**
     * Initializes the Activity class with the given activity model.
     * 
     * @param a
     *            the activity model
     */
    public Activity(Activity a) {
        this();
        setId(a.getId());
        setAssistantId(a.getAssistantId());
        setContent(a.getContent());
        setCourseId(a.getCourseId());
        setDate(a.getDate());
        setDocumentType(a.getDocumentType());
        setProcessor(a.getProcessor());
        setRemark(a.getRemark());
        setSender(a.getSender());
        setType(a.getType());
    }

    /**
     * Clone the current activity
     */
    @Override
    public Activity clone() {
        Activity a = new Activity();
        a.setId(id);
        a.setAssistantId(assistantId);
        a.setContent(content);
        a.setCourseId(courseId);
        a.setDate(date);
        a.setDocumentType(documentType);
        a.setProcessor(processor);
        a.setRemark(remark);
        a.setSender(sender);
        a.setType(type);
        return a;
    }

    /**
     * Custom validation function
     * 
     * @return True if everything is correct
     */
    public boolean validate() {
        boolean ret = true;
        if (type.length() > 20) {
            addError("type", _("Type"), _("is too long"));
            ret = false;
        }
        if (documentType.length() > 50) {
            addError("documentType", _("Document Type"), _("is too long"));
            ret = false;
        }
        if (processor.length() > 2) {
            addError("processor", _("Processor"), _("is too long"));
            ret = false;
        }
        return ret;
    }

    /**
     * Get all activities related to a specified assistant.
     * 
     * @param assistant
     *            The assistant for which all activities should be returned
     * @return The activities related to the assistant or null if none were
     *         found
     */
    @SuppressWarnings("unchecked")
    public List<Activity> getActivities(Assistant assistant) {
        //TODO: Implement
        return null;
    }

    /**
     * Get all activities related to a specified course.
     * 
     * @param course
     *            The course for which all activities should be returned
     * @return The activities related to the course or null if none were found
     */
    @SuppressWarnings("unchecked")
    public List<Activity> getActivities(Course course) {
        //TODO: Implement
        return null;
    }

    /**
     * Get all activities for a specified date range
     * 
     * @param from
     *            Start of the date range
     * @param to
     *            End of the date range
     * @return All activities that occured during that date range
     */
    public List<Activity> getActivities(Date from, Date to) {
        //TODO: Implement
        return null;
    }

    /**
     * Get the id of the referenced assistant.
     * 
     * @return The id of the referenced assistant
     */
    public Integer getAssistantId() {
        return assistantId;
    }

    /**
     * Get the contents of the activity.
     * 
     * @return The contents of the activity
     */
    public String getContent() {
        return content;
    }

    /**
     * Get the id of the referenced course.
     * 
     * @return The id of the referenced course.
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * Get the date of the activity.
     * 
     * @return The date of the activity.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get the type of document.
     * 
     * @return The type of document
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Get the processor of the activity.
     * 
     * @return The processor of the activity.
     */
    public String getProcessor() {
        return processor;
    }

    /**
     * Get remarks to the activity.
     * 
     * @return Remarks to the activity
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Get the sender of the activity.
     * 
     * @return The sender of the activity.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Get the type of the activity.
     * 
     * @return The type of the activity
     */
    public String getType() {
        return type;
    }

    /**
     * Set the id of the assistant referenced by this activity.
     * 
     * @param id
     *            The id of the assistant
     */
    public void setAssistantId(Integer id) {
        assistantId = id;
    }

    /**
     * Set the contents of the activity.
     * 
     * @param cont
     *            The contents of the activity
     */
    public void setContent(String cont) {
        content = cont;
    }

    /**
     * Set the id of the course referenced by this activity.
     * 
     * @param id
     *            The id of the course
     */
    public void setCourseId(Integer id) {
        courseId = id;
    }

    /**
     * Set the date of the activity.
     * 
     * @param dt
     *            The date of the activity
     */
    public void setDate(Date dt) {
        date = dt;
    }

    /**
     * Set the type of the document
     * 
     * @param type
     *            The type of the document
     */
    public void setDocumentType(String type) {
        documentType = type;
    }

    /**
     * Set the processor of the activity.
     * 
     * @param proc
     *            The processor of the activity
     */
    public void setProcessor(String proc) {
        processor = proc;
    }

    /**
     * Set remarks to the activity.
     * 
     * @param rem
     *            Remarks to the activity
     */
    public void setRemark(String rem) {
        remark = rem;
    }

    /**
     * Set the sender of the activity.
     * 
     * @param send
     *            The sender of the activity
     */
    public void setSender(String send) {
        sender = send;
    }

    /**
     * Set the type of the activity.
     * 
     * @param typec
     *            The type of the activity
     */
    public void setType(String typec) {
        type = typec;
    }

}
