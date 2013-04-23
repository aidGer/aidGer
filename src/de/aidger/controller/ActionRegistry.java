/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2013 The aidGer Team
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

package de.aidger.controller;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;

/**
 * All action classes have to register to ActionRegistry. Afterwards the view
 * classes can access the registry to get the functionality of a registered
 * action.
 * 
 * @author aidGer Team
 */
public final class ActionRegistry {
    /**
     * Holds an instance of this ActionRegistry.
     */
    private static ActionRegistry instance = null;

    /**
     * Holds the action objects available in this application.
     */
    private final Map<String, Action> actions = new HashMap<String, Action>();

    /**
     * Constructor must be private and does nothing.
     */
    private ActionRegistry() {
    }

    /**
     * Provides access to an instance of this action registry.
     * 
     * @return Instance of this action registry.
     */
    public synchronized static ActionRegistry getInstance() {
        if (instance == null) {
            instance = new ActionRegistry();
        }

        return instance;
    }

    /**
     * Registers an action with this application.
     * 
     * @param action
     *            The action to register
     */
    public void register(Action action) {
        actions.put(action.getClass().getName(), action);
    }

    /**
     * Retrieves an action from this registry. An action is identified by its
     * class' name that can be obtained by calling {@link Action#getClass()} and
     * calling getName on the class object {@link Class#getName()}.
     * 
     * @param className
     *            name of actions class
     * @return Action object or null if the action is not registered.
     * @throws ActionNotFoundException
     */
    public Action get(String className) throws ActionNotFoundException {
        Action action = actions.get(className);

        if (action == null) {
            throw new ActionNotFoundException(className);
        }

        return action;
    }
}
