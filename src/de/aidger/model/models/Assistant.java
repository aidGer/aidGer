package de.aidger.model.models;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.model.IAssistant;

/**
 * Represents a single entry in the assistant column of the database.
 * Contains functions to retrieve and change the data in the database.
 *
 * @author aidGer Team
 */
public class Assistant extends AbstractModel implements IAssistant {

	/**
	 * The unique id of the assistant.
	 */
	 private int id;

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
	 * Clone the current activity.
	 */
	@Override
	public Assistant clone() {
		return new Assistant();
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
	 * @param mail The email address of the assistant
	 */
	@Override
	public void setEmail(String mail) {
		email = mail;
	}

	/**
	 * Set the first name of the assistant.
	 *
	 * @param first The first name of the assistant
	 */
	@Override
	public void setFirstName(String first) {
		firstName = first;
	}

	/**
	 * Set the last name of the assistant.
	 *
	 * @param last The last name of the assistant
	 */
	@Override
	public void setLastName(String last) {
		lastName = last;
	}

	/**
	 * Set the qualification level of the assistant.
	 *
	 * @param qual The qualification level of the assistant
	 */
	@Override
	public void setQualification(String qual) {
		qualification = qual;
	}

	/**
	 * Returns the unique id of the assistant.
	 *
	 * @return The unique id of the assistant
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * Set the unique id of the assistant.
	 *
	 * <b>!!! THIS IS FOR INTERNAL ADOHIVE USAGE ONLY !!!</b>
	 *
	 * @param id The unique id of the assistant
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}

}
