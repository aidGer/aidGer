package de.aidger.model;

import java.util.List;
import java.util.Vector;
import java.util.Observable;

import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.controller.IAdoHiveManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAdoHiveModel;

import de.aidger.model.validators.*;

/**
 * AbstractModel contains all important database related functions which all
 * models need to contain. This includes getting instances of models and saving
 * or removing them.
 * 
 * @author Philipp Gildein
 */
public abstract class AbstractModel<T> extends Observable implements
        IAdoHiveModel<T> {

    /**
     * The unique id of the model in the database.
     */
    protected int id = -1;

    /**
     * Array containing all validators for that specific model.
     */
    protected List<Validator> validators = new Vector<Validator>();

    /**
     * Cloneable function inherited from IAdoHiveModel.
     *
     * @return Clone of the model
     */
    @Override
    abstract public T clone();

    /**
     * Get all models from the database.
     * 
     * @return An array containing all found models or null
     */
    @SuppressWarnings("unchecked")
    public List getAll() {
        try {
            return getManager().getAll();
        } catch (Exception ex) {
        }
        return null;
    }

    /**
     * Get a specific model by specifying its unique id.
     * 
     * @param id
     *            The unique id of the model
     * @return The model if one was found or null
     */
    @SuppressWarnings("unchecked")
    public T getById(int id) {
        try {
            return (T) getManager().getById(id);
        } catch (Exception ex) {
        }
        return null;
    }

    /**
     * Get a specific model by specifying a set of keys.
     * 
     * @param o
     *            The set of keys specific to this model
     * @return The model if one was found or null
     */
    @SuppressWarnings("unchecked")
    public T getByKeys(Object... o) {
        try {
            return (T) getManager().getByKeys(o);
        } catch (Exception ex) {
        }
        return null;
    }

    /**
     * Save the current model to the database.
     *
     * @return True if validation succeeds
     */
    @SuppressWarnings("unchecked")
    public boolean save() {
        if (!doValidate()) {
            return false;
        }

        /* Add or update model */
        IAdoHiveManager mgr = getManager();
        if (id == -1) {
            try {
                mgr.add(this);
            } catch (AdoHiveException e) {
            }
        } else {
            try {
                mgr.update(this);
            } catch (AdoHiveException e) {
            }
        }
        return true;
    }

    /**
     * Remove the current model from the database.
     */
    @SuppressWarnings("unchecked")
    public void remove() {
        if (id != -1) {
            try {
                getManager().remove(this);
            } catch (AdoHiveException e) {
            }
        }
    }

    /**
     * Add a validator to the model.
     *
     * @param valid The validator to add
     */
    public void validate(Validator valid) {
        validators.add(valid);
    }

    /**
     * Add a presence validator to the model.
     *
     * @param members The name of the member variables to validate
     */
    public void validatePresenceOf(String[] members) {
        validators.add(new PresenceValidator(this, members));
    }

    public void validateEmailAddress(String member) {
        validators.add(new EmailValidator(this, new String[] { member }));
    }

    /**
     * Extract the name of the class and return the correct manager.
     * 
     * @return The name of the model class
     */
    @SuppressWarnings("unchecked")
    protected IAdoHiveManager getManager() {
        String classname = getClass().getName();
        int idx = classname.lastIndexOf('.');
        classname = classname.substring(idx);

        try {
            java.lang.reflect.Method m = AdoHiveController.class.getMethod(
                    "get" + classname + "Manager");
            return (IAdoHiveManager) m.invoke(AdoHiveController.getInstance(),
                    new Object[0]);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Validate the input using the validators and a custom validate function.
     *
     * @return True if everything validates
     */
    protected boolean doValidate() {
        /* Try to validate before adding/updating */
        boolean ret = true;
        for (Validator v : validators) {
            if (!v.validate()) {
                ret = false;
            }
        }

        /* Check if the model got a validate() function */
        try {
            java.lang.reflect.Method m = getClass().getDeclaredMethod("validate");
            if (!(Boolean)m.invoke(this, new Object[0]))
                ret = false;
        } catch (Exception ex) { }

        return ret;
    }
}
