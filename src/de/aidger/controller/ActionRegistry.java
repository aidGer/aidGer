package de.aidger.controller;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;

import de.aidger.controller.actions.*;

public final class ActionRegistry {
	/**
	 * Holds an instance of this ActionRegistry.
	 */
	private static ActionRegistry instance = null;
	
	/**
	 * Provides access to an instance of this action registry.
	 * @return Instance of this action registry.
	 */
	public static ActionRegistry getInstance() {
		if (instance == null)
			instance = new ActionRegistry();

		return instance;
	}
	
	/**
	 * Holds the action objects available in this application.
	 */
	private Map<String, Action> actions;
	
	/**
	 * Initializes this action registry by registering all
	 * needed actions.
	 */
	private ActionRegistry() {
		super();

		register(new ExitAction());
	}
	
	/**
	 * Provides access to the stored actions.
	 * @return Stored actions.
	 */
	private Map<String, Action> getActions() {
		// The map for storing the actions is initialized on demand
		if (actions == null)
			actions = new HashMap<String, Action>();
		
		return actions;
	}
	
	/**
	 * Registers an action with this application.
	 * 
	 * @param action to register
	 */
	public void register(Action action) {
		getActions().put(action.getClass().getName(), action);
	}
	
	/**
	 * Retrieves an action from this registry. An action is identified
	 * by its class' name that can be obtained by calling
	 * {@link Action#getClass()} and calling getName on the class object
	 * {@link Class#getName()}.
	 * 
	 * @param className name of actions class
	 * @return Action object or null if the action is not registered.
	 */
	public Action get(String className) {
		return getActions().get(className);
	}
}
