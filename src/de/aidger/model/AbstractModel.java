package de.aidger.model;

/**
 * AbstractModel contains all important database related functions which all
 * models need to contain. This includes getting instances of models and saving
 * or removing them.
 * 
 * @author Philipp Gildein
 */
public class AbstractModel {

    /**
     * Private constructor so no one can construct AbstractModel directly
     */
    private AbstractModel() {

    }
    
    /**
     * Get all models from the database
     * 
     * @return An array containing all found models or null
     */
    public static AbstractModel[] getAll() {
	return null;
    }
    
    /**
     * Get a specific model by specifying its unique id
     * 
     * @param id The unique id of the model
     * @return The model if one was found or null
     */
    public static AbstractModel getById(int id) {
	return null;
    }
    
    /**
     * Get a specific model by specifying a set of keys
     * 
     * @param o The set of keys specific to this model
     * @return The model if one was found or null
     */
    public static AbstractModel getByKeys(Object... o) {
	return null;
    }
    
    /**
     * Save the current model to the database
     */
    public void save() {
	
    }
    
    /**
     * Remove the current model from the database
     */
    public void remove() {
	
    }
}
