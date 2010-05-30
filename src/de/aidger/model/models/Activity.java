package de.aidger.model.models;

import java.util.Date;
import java.util.List;
import java.text.MessageFormat;

import static de.aidger.utils.Translation._;
import de.aidger.utils.Logger;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.controller.IActivityManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IActivity;


/**
 * Represents a single entry in the activity column of the database.
 * Contains functions to retrieve and change the data in the database.
 *
 * @author aidGer Team
 */
public class Activity extends AbstractModel<IActivity> implements IActivity {

    /**
     * References the corresponding assistant.
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
     * Initializes the Activity class.
     */
    public Activity() {
        validatePresenceOf(new String[] { "date", "sender", "type",
                "processor", "content" });
        //TODO: Check that documentType and type are correct (need to know what valid values are first)
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
     * Check if two objects are equal.
     *
     * @param o
     *            The other object
     * @return True if both are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Activity) {
            Activity a = (Activity) o;
            return a.assistantId == assistantId && a.content.equals(content) &&
                    a.courseId == courseId &&  a.date.equals(date) &&
                    a.documentType.equals(documentType) &&  a.id == this.id &&
                    a.processor.equals(processor) && a.remark.equals(remark) &&
                    a.sender.equals(sender) && a.type.equals(type);
        } else {
            return false;
        }
    }

    /**
     * Generate a unique hashcode for this instance.
     *
     * @return The hashcode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + assistantId;
        hash = 97 * hash + courseId;
        hash = 97 * hash + (date != null ? date.hashCode() : 0);
        hash = 97 * hash + (sender != null ? sender.hashCode() : 0);
        hash = 97 * hash + (documentType != null ? documentType.hashCode() : 0);
        hash = 97 * hash + (type != null ? type.hashCode() : 0);
        hash = 97 * hash + (processor != null ? processor.hashCode() : 0);
        hash = 97 * hash + (content != null ? content.hashCode() : 0);
        hash = 97 * hash + (remark != null ? remark.hashCode() : 0);
        return hash;
    }

    /**
     * Get all activities related to a specified assistant.
     *
     * @param assistant
     * 			The assistant for which all activities should be returned
     * @return The activities related to the assistant or null if none were found
     */
    @SuppressWarnings("unchecked")
    public List<Activity> getActivities(Assistant assistant) {
    	IActivityManager mgr = (IActivityManager) getManager();
    	try {
            return (List<Activity>)(List<?>)mgr.getActivities(assistant);
        } catch (AdoHiveException e) {
            Logger.error(MessageFormat.format(
                    _("Caught exception in getActivities(assistant): {0}"),
                    new Object[] { e.getMessage() }));
            return null;
        }
    }

    /**
     * Get all activities related to a specified course.
     *
     * @param course
     * 			The course for which all activities should be returned
     * @return The activities related to the course or null if none were found
     */
    @SuppressWarnings("unchecked")
    public List<Activity> getActivities(Course course) {
    	IActivityManager mgr = (IActivityManager) getManager();
    	try {
            return (List<Activity>)(List<?>)mgr.getActivities(course);
        } catch (AdoHiveException e) {
            Logger.error(MessageFormat.format(
                    _("Caught exception in getActivities(course): {0}"),
                    new Object[] { e.getMessage() }));
            return null;
        }
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

}
