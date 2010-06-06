package de.aidger.model.models;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.model.IAssistant;

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
        validatePresenceOf(new String[] { "email", "firstName", "lastName",
                "qualification" });
        validateEmailAddress("email");
        // TODO: Check qualification level
    }

    /**
     * Initializes the Assistant class with the given assistant model.
     * 
     * @param a
     *            the assistant model
     */
    public Assistant(IAssistant a) {
        setId(a.getId());
        setEmail(a.getEmail());
        setFirstName(a.getFirstName());
        setLastName(a.getLastName());
        setQualification(a.getQualification());
    }

    /**
     * Clone the current activity.
     */
    @Override
    public Assistant clone() {
        Assistant a = new Assistant();
        a.setId(id);
        a.setEmail(email);
        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.setQualification(qualification);
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
            return a.id == id
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
