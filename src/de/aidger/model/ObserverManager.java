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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ObserverManager {
	
    /**
     * Holds he only instance of the class.
     */
    private static ObserverManager instance = null;
    
    protected Map<String, List<Observer>> observers = new HashMap<String, List<Observer>>();
    
    /**
     * Constructor must be private and does nothing.
     */
    private ObserverManager() {

    }

    /**
     * Provides access to an instance of this class.
     * 
     * @return Instance of the ObserverManager class
     */
    public synchronized static ObserverManager getInstance() {
        if (instance == null) {
            instance = new ObserverManager();
        }

        return instance;
    }
    
    /**
     * Register a new observer for a model
     * 
     * @param observable The model to observer
     * @param observer The observer
     */
    public void register(AbstractModel<?> observable, Observer observer) {
    	String identifier = getIdentifier(observable);
    	
    	if (!observers.containsKey(identifier)) {
    		observers.put(identifier, new ArrayList<Observer>());
    	} else if (observers.get(identifier).contains(observer)) {
    		return;
    	}
    	
    	observers.get(identifier).add(observer);
    }
    
    /**
     * Unregister an observer from a model
     * 
     * @param observable The model that was observed
     * @param observer The observer to unregister
     */
    public void unregister(AbstractModel<?> observable, Observer observer) {
    	String identifier = getIdentifier(observable);
    	if (observers.containsKey(identifier)) {
    		observers.get(identifier).remove(observer);
    	}
    }
    
    /**
     * Trigger a save event
     * 
     * @param observable The model that got saved
     */
    public void triggerSave(AbstractModel observable) {
    	String identifier = getIdentifier(observable);
    	
    	if (observers.containsKey(identifier)) {  
	    	for (Observer o : observers.get(identifier)) {
	    		o.onSave(observable);
	    	}
    	}
    }
    
    /**
     * Trigger a remove event
     * 
     * @param observable The model that got removed
     */
    public void triggerRemove(AbstractModel observable) {
    	String identifier = getIdentifier(observable);
    	
    	if (observers.containsKey(identifier)) {    	
	    	for (Observer o : observers.get(identifier)) {
	    		o.onRemove(observable);
	    	}
    	}
    }
    
    /**
     * Return the identifier string
     * 
     * @param model The model to identify
     * @return The identifier string
     */
    protected String getIdentifier(AbstractModel<?> model) {
    	return model.clazz.getName() + "_" + model.getId().toString();
    }

}
