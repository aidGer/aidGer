package de.aidger.model.models;

import static de.aidger.utils.Translation._;
import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.IContract;

import java.util.List;

/**
 * Represents a single entry in the assistant column of the database. Contains
 * functions to retrieve and change the data in the database.
 * 
 * @author aidGer Team
 */
public class Assistant extends AbstractModel<IAssistant> implements IAssistant {

    /**
     * The email address of the assistant.
     */
    private String email;

    /**
     * The first name of the assistant.
     */
    private String firstName;

    /**
     * The last name of the assistant.
     */
    private String lastName;

    /**
     * The qualification level of the assistant.
     */
    private String qualification;

    /**
     * Initializes the Assistant class.
     */
    public Assistant() {
        if (getValidators().isEmpty()) {
            validatePresenceOf(new String[] { "email", "firstName", "lastName",
                    "qualification" }, new String[] { _("Email"), _("First Name"),
                    _("Last Name"), _("Qualification") });
            validateEmailAddress("email", _("Email"));
            validateInclusionOf(new String[] { "qualification" }, new String[] {
                    _("Qualification") }, new String[] { "g", "u", "b"});
        }
    }

    /**
     * Initializes the Assistant class with the given assistant model.
     * 
     * @param a
     *            the assistant model
     */
    public Assistant(IAssistant a) {
        this();
        setId(a.getId());
        setEmail(a.getEmail());
        setFirstName(a.getFirstName());
        setLastName(a.getLastName());
        setQualification(a.getQualification());
        setNew(false);
    }

    /**
     * Clone the current activity.
     */
    @Override
    public Assistant clone() {
        Assistant a = new Assistant();
        a.setEmail(email);
        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.setQualification(qualification);
        a.doClone(this);
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
        if (o instanceof Assistant) {
            Assistant a = (Assistant) o;
            return (id == null ? a.id == null : id.equals(a.id))
                    && (firstName == null ? a.firstName == null : a.firstName
                            .equals(firstName))
                    && (lastName == null ? a.lastName == null : a.lastName
                            .equals(lastName))
                    && (qualification == null ? a.qualification == null
                            : a.qualification.equals(qualification));
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
        int hash = 7;
        hash = 19 * hash + (email != null ? email.hashCode() : 0);
        hash = 19 * hash + (firstName != null ? firstName.hashCode() : 0);
        hash = 19 * hash + (lastName != null ? lastName.hashCode() : 0);
        hash = 19 * hash
                + (qualification != null ? qualification.hashCode() : 0);
        return hash;
    }

    /**
     * Custom validation function for remove().
     *
     * @return True if everything is okay
     */
    public boolean validateOnRemove() throws AdoHiveException {
        boolean ret = true;

        List act = (new Activity()).getActivities(this);
        List emp = (new Employment()).getEmployments(this);
        List contr = (new Contract()).getContracts(this);

        if (act.size() > 0) {
            addError(_("Assistant is still linked to an Activity"));
            ret = false;
        }
        if (emp.size() > 0) {
            addError(_("Assistant is still linked to an Employment"));
            ret = false;
        }
        if (contr.size() > 0) {
            addError(_("Assistant is still linked to a Contract"));
            ret = false;
        }

        return ret;
    }

    /**
     * Get the email address of the assistant.
     * 
     * @return The email address of the assistant
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Get the first name of the assistant.
     * 
     * @return The first name of the assistant
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name of the assistant.
     * 
     * @return The last name of the assistant.
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the qualification level of the assistant.
     * 
     * @return The qualification level of the assistant
     */
    @Override
    public String getQualification() {
        return qualification;
    }

    /**
     * Set the email address of the assistant.
     * 
     * @param mail
     *            The email address of the assistant
     */
    @Override
    public void setEmail(String mail) {
        email = mail;
    }

    /**
     * Set the first name of the assistant.
     * 
     * @param first
     *            The first name of the assistant
     */
    @Override
    public void setFirstName(String first) {
        firstName = first;
    }

    /**
     * Set the last name of the assistant.
     * 
     * @param last
     *            The last name of the assistant
     */
    @Override
    public void setLastName(String last) {
        lastName = last;
    }

    /**
     * Set the qualification level of the assistant.
     * 
     * @param qual
     *            The qualification level of the assistant
     */
    @Override
    public void setQualification(String qual) {
        qualification = qual;
    }

}
