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
     * Private constructor so noone can construct AbstractModel directly
     */
    private AbstractModel() {

    }
    
    public static AbstractModel getAll() {
	return null;
    }
    
    public static AbstractModel getById(int id) {
	return null;
    }
    
    public static AbstractModel getByKeys(Object... o) {
	return null;
    }
    
    public void save() {
	
    }
    
    public void remove() {
	
    }
}
