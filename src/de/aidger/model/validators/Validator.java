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

package de.aidger.model.validators;

import static de.aidger.utils.Translation._;
import de.aidger.model.AbstractModel;

/**
 * Defines the interface every validator needs to implement.
 * 
 * @author aidGer Team
 */
public abstract class Validator {

    /**
     * The members of the model to check.
     */
    protected String[] members;

    /**
     * The translated names of members.
     */
    protected String[] trans;

    /**
     * The error message.
     */
    protected String message;

    /**
     * Initialize the PresenceValidator class.
     */
    public Validator() {
        this.members = new String[0];
        this.trans = new String[0];
        this.message = _("Default error");
    }

    /**
     * Initialize the PresenceValidator class.
     * 
     * @param members
     *            The members of the model to validate
     * @param trans
     *            The translated names
     */
    public Validator(String[] members, String[] trans) {
        this.members = members;
        this.trans = trans;
        this.message = _("Default error");
    }

    /**
     * Validate the input.
     *
     * @param model
     *              The model to validate
     * @return True if it validates
     */
    public boolean validate(AbstractModel model) {
        boolean ret = true;
        for (int i = 0; i < members.length; ++i) {
            Object value = getValueOf(model, members[i]);
            if (!validateVar(value)) {
                ret = false;
                if (model != null) {
                    model.addError(members[i], trans[i], message);
                }
            }
        }
        return ret;
    }

    /**
     * Validate the variable.
     *
     * @param m
     *            The model to validate
     * @param o
     *            The variable to validate
     * @return True if the input validates, false otherwise
     */
    abstract public boolean validateVar(Object o);

    /**
     * Set the error message.
     * 
     * @param msg
     *            The new error message
     */
    public void setMessage(String msg) {
        message = msg;
    }

    /**
     * Get the error message.
     * 
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get the value of the given member variable.
     *
     * @param model
     *            The model
     * @param name
     *            The name of the member variable
     * @return The value of the member variable or null
     */
    protected Object getValueOf(AbstractModel model, String name) {
        String functionname = "get" + name.substring(0, 1).toUpperCase()
                + name.substring(1);
        try {
            java.lang.reflect.Method m = model.getClass().getDeclaredMethod(
                functionname, new Class<?>[0]);
            return m.invoke(model, new Object[0]);
        } catch (Exception ex) {
            try {
                return model.getClass().getField(name).get(model);
            } catch (Exception e) {
                return null;
            }
        }
    }

    /**
     * Returns the members.
     * 
     * @return the members
     */
    public String[] getMembers() {
        return members;
    }

}
