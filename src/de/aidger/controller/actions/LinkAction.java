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

package de.aidger.controller.actions;

import java.awt.event.MouseListener;

import javax.swing.AbstractAction;

/**
 * Base abstract class for all link actions.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public abstract class LinkAction extends AbstractAction implements
        MouseListener, Cloneable {
    /**
     * A flag whether the action is a link.
     */
    private boolean isLink = true;

    /**
     * Marks the action as no link.
     */
    public void markNoLink() {
        isLink = false;
    }

    /**
     * Returns whether the action is a link.
     * 
     * @return whether the ation is a link.
     */
    public boolean isLink() {
        return isLink;
    }
}
