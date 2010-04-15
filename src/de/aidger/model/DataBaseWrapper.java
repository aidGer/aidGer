/**
 * 
 */
package de.aidger.model;

import java.util.*;

/**
 * Wraps the AdoHive Database interface into a nicer API
 * 
 * @author Philipp Gildein
 */
public final class DataBaseWrapper {
    
    public static <T extends AbstractModel> List<T> getAll() {
	return new Vector<T>();
    }
    
    public static <T extends AbstractModel> T getById(int id) {
	return null;
    }
    
    public static <T extends AbstractModel> T getByKeys(Object... o) {
	return null;
    }
    
    public <T extends AbstractModel> void save(T model) {
	
    }
    
    public <T extends AbstractModel> void remove(T model) {
	
    }
}
