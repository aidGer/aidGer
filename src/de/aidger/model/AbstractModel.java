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

package de.aidger.model;

import static de.aidger.utils.Translation._;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import siena.*;

import de.aidger.model.validators.DateRangeValidator;
import de.aidger.model.validators.ExistenceValidator;
import de.aidger.model.validators.FormatValidator;
import de.aidger.model.validators.InclusionValidator;
import de.aidger.model.validators.PresenceValidator;
import de.aidger.model.validators.Validator;
import de.aidger.model.validators.ValidationException;
import de.aidger.utils.Logger;
import de.aidger.utils.history.HistoryEvent;
import de.aidger.utils.history.HistoryException;
import de.aidger.utils.history.HistoryManager;

/**
 * AbstractModel contains all important database related functions which all
 * models need to contain. This includes getting instances of models and saving
 * or removing them.
 * 
 * @author Philipp Gildein
 */
//TODO: Had to remove Observable ... do something similar
public abstract class AbstractModel<T> extends Model {

    /**
     * The unique id of the model in the database.
     */
    @Id(Generator.AUTO_INCREMENT)
    protected Long id = null;

    /**
     * The name of the class.
     */
    @Ignore
    protected String classname;

    /**
     * The class object
     */
    @Ignore
    protected Class<? extends AbstractModel> clazz;

    /**
     * Array containing all validators for that specific model.
     */
    @Ignore
    protected static Map<String, List<Validator>> validators = new HashMap<String, List<Validator>>();

    /**
     * Array containing errors if a validator fails.
     */
    @Ignore
    protected List<String> errors = new ArrayList<String>();

    /**
     * Map of errors for specific fields.
     */
    @Ignore
    protected Map<String, List<String>> fieldErrors = new HashMap<String, List<String>>();

    /**
     * The constructor of the AbstractModel class.
     */
    public AbstractModel() {
        clazz = getClass();
        classname = clazz.getName();
        if (!validators.containsKey(classname)) {
            validators.put(classname, new ArrayList<Validator>());
        }
    }

    /**
     * Cloneable function inherited from IAdoHiveModel.
     * 
     * @return Clone of the model
     */
    @Override
    abstract public T clone();

    /**
     * Save the current model to the database.
     * 
     * @return True if validation succeeds
     */
    public void save() {
        /* Validation of the model */
        if (!doValidate()) {
            throw new ValidationException(_("Validation failed."));
        } else if (!errors.isEmpty()) {
            throw new ValidationException(_("The model was not saved because the error list is not empty."));
        }

        Logger.info(MessageFormat.format(_("Saving model: {0}"), new Object[] { toString() }));
        boolean wasNew = getId() == null;
        super.save();

        /* Add event to the HistoryManager */
        HistoryEvent evt = new HistoryEvent();
        evt.id = getId();
        evt.status = wasNew ? HistoryEvent.Status.Added
                : HistoryEvent.Status.Changed;
        evt.type = getClass().getSimpleName();
        evt.date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        try {
            HistoryManager.getInstance().addEvent(evt);
        } catch (HistoryException ex) {
            Logger.error(ex.getMessage());
        }

        /* Observable calls */
        //TODO: See top
        //setChanged();
        //notifyObservers(true);
    }

    /**
     * Remove the current model from the database.
     * 
     * @return False if the model is new or doesn't validate
     */
    public void remove() {
        /* Check if the model is saved in the db */
        if (!isInDatabase()) {
            return;
        }

        /* Check if there is a custom validation function */
        try {
            java.lang.reflect.Method m = getClass().getDeclaredMethod(
                "validateOnRemove");
            if (!(Boolean) m.invoke(this, new Object[0])) {
                throw new ValidationException(_("Validation failed"));
            }
        } catch (NoSuchMethodException x) {
        } catch (IllegalAccessException x) {
        } catch (InvocationTargetException x) {}


        Logger.info(MessageFormat.format(_("Removing model: {0}"), new Object[] { toString() }));

        delete();
        if (getId() != null) {
            setId(null);
        }
        //TODO: See top
        //setChanged();
        //notifyObservers(false);

        /* Add event to the HistoryManager */
        HistoryEvent evt = new HistoryEvent();
        evt.id = getId();
        evt.status = HistoryEvent.Status.Removed;
        evt.type = getClass().getSimpleName();
        evt.date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        try {
            HistoryManager.getInstance().addEvent(evt);
        } catch (HistoryException ex) {
            Logger.error(ex.getMessage());
        }
    }
    
    /**
     * Mark this model as new.
     */
    public void markAsNew() {
        this.setId(null);
    }

    /**
     * Get the Query object for the current class.
     *
     * @return The query object
     */
    public Query<T> all() {
        return (Query<T>) Model.all(clazz);
    }

    /**
     * Get all models from the database.
     *
     * @return An array containing all found models or null
     */
    public List getAll() {
        List l = all().fetch();
        return l;
    }

    /**
     * Get a specific model by specifying its unique id.
     *
     * @param id
     *            The unique id of the model
     * @return The model if one was found or null
     */
    public T getById(Long id) {
        return (T) all().getByKey(id);
    }

    /**
     * Get a specific model by specifying its unique id.
     *
     * @param id
     *            The unique id of the model
     * @return The model if one was found or null
     */
    public T getById(int id) {
        return getById((long) id);
    }

    /**
     * Get a specific model by specifying its primary key.
     *
     * @param id
     *          The primary key of the model
     * @return The model if one was found or null
     */
    public T getByKey(Object id) {
        return (T) all().getByKey(id);
    }

    /**
     * Get the number of models in the database.
     *
     * @return The number of models
     */
    public int size() {
        return all().count();
    }

    /**
     * Returns true if no model has been saved into the database.
     *
     * @return True if no model is in the database
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Checks if the current instance exists in the database.
     *
     * @return True if the instance exists
     */
    public boolean isInDatabase() {
        return id != null && getById(id) != null;
    }

    /**
     * Deletes everything from the associated table.
     */
    public void clearTable() {
        try {
            all().delete();
        } catch (SienaException x) {
            if (!x.getMessage().equals("No updated rows") && !x.getMessage().endsWith("rows deleted"))
                throw x;
        }
        id = null; // Reset
    }

    /**
     * Returns the unique id of the model.
     *
     * @return The unique id of the model
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique id of the model
     *
     * @param id
     *          The new id of the model
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get a list of all errors.
     * 
     * @return A list of errors
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Get a list of errors for a specific field.
     * 
     * @param field
     *            The field to get the errors for
     * @return A list of errors
     */
    public List<String> getErrorsFor(String field) {
        return fieldErrors.get(field);
    }

    /**
     * Add an error to the list,
     * 
     * @param error
     *            The error to add
     */
    public void addError(String error) {
        errors.add(error);
    }

    /**
     * Add an error for a specific field to the list.
     * 
     * @param field
     *            The field on which the error occured
     * @param trans
     *            The translated field name
     * @param error
     *            The error to add
     */
    public void addError(String field, String trans, String error) {
        error = trans + " " + error;
        errors.add(error);
        if (fieldErrors.containsKey(field)) {
            fieldErrors.get(field).add(error);
        } else {
            List<String> list = new ArrayList<String>();
            list.add(error);
            fieldErrors.put(field, list);
        }
    }

    /**
     * Clear the error lists.
     */
    public void resetErrors() {
        errors.clear();
        fieldErrors.clear();
    }

    /**
     * Add a presence validator to the model.
     * 
     * @param members
     *            The name of the member variables to validate
     * @param trans
     *            The translated names
     */
    public void validatePresenceOf(String[] members, String[] trans) {
        validators.get(classname).add(new PresenceValidator(members, trans));
    }

    /**
     * Add an email validator to the model.
     * 
     * @param member
     *            The name of the member variable to validate
     * @param trans
     *            The translated name
     */
    public void validateEmailAddress(String member, String trans) {
        validators.get(classname).add(new FormatValidator(new String[] { member },
            new String[] { trans },
            "^[\\w\\-]([\\.\\w])+[\\w]+@([\\p{L}\\-]+\\.)+[A-Z]{2,4}$", false));
    }

    /**
     * Add an date range validator to the model.
     * 
     * @param from
     *            The starting date
     * @param to
     *            The end date
     * @param transFrom
     *            Translated from date
     * @param transTo
     *            Translated to date
     */
    public void validateDateRange(String from, String to, String transFrom,
            String transTo) {
        validators.get(classname).add(new DateRangeValidator(from, to, transFrom,
            transTo));
    }

    /**
     * Add an inclusion validator to the model.
     * 
     * @param members
     *            The name of the member variables to validate
     * @param trans
     *            The translated names
     * @param inc
     *            The list to check for inclusion
     */
    public void validateInclusionOf(String[] members, String[] trans,
            String[] inc) {
        validators.get(classname).add(new InclusionValidator(members, trans, inc));
    }

    /**
     * Add an existance validator to the model.
     * 
     * @param members
     *            The name of the member variables to validate
     * @param type
     *            The type to check for
     * @param trans
     *            The translated names
     */
    public void validateExistenceOf(String[] members, String[] trans,
            AbstractModel type) {
        validators.get(classname).add(new ExistenceValidator(members, trans, type));
    }

    /**
     * Add an format validator to the model.
     * 
     * @param members
     *            The name of the member variables to validate
     * @param trans
     *            The translated names
     * @param format
     *            The format to check
     */
    public void validateFormatOf(String[] members, String[] trans, String format) {
        validators.get(classname).add(new FormatValidator(members, trans, format));
    }

    /**
     * Validate the input using the validators and a custom validate function.
     * 
     * @return True if everything validates
     */
    public boolean validateModel() {
        return doValidate() && errors.isEmpty();
    }

    /**
     * Returns a string containing all informations stored in the model.
     * 
     * @return A string containing informations on the model
     */
    @Override
    public String toString() {
        String ret = getClass().getSimpleName() + " [" + "ID: " + getId()
                + ", ";
        try {
            for (java.lang.reflect.Method m : getClass().getDeclaredMethods()) {
                if (m.getName().startsWith("get")
                        && m.getParameterTypes().length == 0) {
                    ret += m.getName().substring(3) + ": ";
                    ret += m.invoke(this, new Object[0]) + ", ";
                }
            }
            if (ret.endsWith(", ")) {
                ret = ret.substring(0, ret.length() - 2);
            }
        } catch (Exception ex) {
            Logger.error(ex.getMessage());
        }
        return ret + "]";
    }

    /**
     * Validate the input using the validators and a custom validate function.
     * 
     * @return True if everything validates
     */
    protected boolean doValidate() {
        /* Try to validate before adding/updating */
        boolean ret = true;
        for (Validator v : validators.get(classname)) {
            if (!v.validate(this)) {
                ret = false;
            }
        }

        /* Check if the model got a validate() function */
        try {
            java.lang.reflect.Method m = getClass().getDeclaredMethod(
                "validate");
            if (!(Boolean) m.invoke(this, new Object[0])) {
                ret = false;
            }
        } catch (Exception ex) {
        }

        return ret;
    }

    /**
     * Returns the validators of the model.
     * 
     * @return the validators of the model
     */
    public List<Validator> getValidators() {
        return validators.get(classname);
    }

}
