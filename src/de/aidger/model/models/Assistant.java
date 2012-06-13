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
import de.aidger.model.AbstractModel;

import java.util.List;

import siena.Table;
import siena.Column;

/**
 * Represents a single entry in the assistant column of the database. Contains
 * functions to retrieve and change the data in the database.
 * 
 * @author aidGer Team
 */
@Table("Hilfskraft")
public class Assistant extends AbstractModel<Assistant> {

    /**
     * The email address of the assistant.
     */
    @Column("Email")
    private String email;

    /**
     * The first name of the assistant.
     */
    @Column("Vorname")
    private String firstName;

    /**
     * The last name of the assistant.
     */
    @Column("Nachname")
    private String lastName;

    /**
     * The qualification level of the assistant.
     */
    @Column("Qualifikation")
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
    public Assistant(Assistant a) {
        this();
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
     * Custom validation function for remove().
     *
     * @return True if everything is okay
     */
    public boolean validateOnRemove() {
        boolean ret = true;

        if (getId() == null) {
            addError(_("Assistant not saved yet."));
            return false;
        }

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
    public String getEmail() {
        return email;
    }

    /**
     * Get the first name of the assistant.
     * 
     * @return The first name of the assistant
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name of the assistant.
     * 
     * @return The last name of the assistant.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the qualification level of the assistant.
     * 
     * @return The qualification level of the assistant
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * Set the email address of the assistant.
     * 
     * @param mail
     *            The email address of the assistant
     */
    public void setEmail(String mail) {
        email = mail;
    }

    /**
     * Set the first name of the assistant.
     * 
     * @param first
     *            The first name of the assistant
     */
    public void setFirstName(String first) {
        firstName = first;
    }

    /**
     * Set the last name of the assistant.
     * 
     * @param last
     *            The last name of the assistant
     */
    public void setLastName(String last) {
        lastName = last;
    }

    /**
     * Set the qualification level of the assistant.
     * 
     * @param qual
     *            The qualification level of the assistant
     */
    public void setQualification(String qual) {
        qualification = qual;
    }

}
