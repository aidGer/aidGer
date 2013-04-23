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

package de.aidger.view.tabs;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import de.aidger.view.models.GenericListModel;

/**
 * Tab is the abstract class of all tabs that are located in the tabbed pane on
 * the main window.
 * 
 * @author aidGer Team
 */

@SuppressWarnings("serial")
public abstract class Tab extends JPanel {

    /**
     * The predecessor tab.
     */
    private Tab predecessor;

    /**
     * A flag whether the tab can be a predecessor or not.
     */
    private boolean noPredecessor = false;

    /**
     * The list models of the tab.
     */
    protected final List<GenericListModel> listModels = new ArrayList<GenericListModel>();

    /**
     * Constructs a tab.
     */
    public Tab() {
        setBorder(BorderFactory.createTitledBorder(""));
    }

    /**
     * Get the name of the tab and constructor options if necessary.
     * 
     * @return A string representation of the class
     */
    @Override
    public String toString() {
        return getClass().getName();
    }

    /**
     * Returns the name of the tab.
     * 
     * @return The name of the tab
     */
    public abstract String getTabName();

    /**
     * Tab can perform actions before it is opened.
     */
    public void performBeforeOpen() {
    }

    /**
     * Tab can perform actions before it is closed.
     */
    public void performBeforeClose() {
    }

    /**
     * Sets the last predecessor.
     * 
     * @param p
     *            the last predecessor
     */
    public void setPredecessor(Tab p) {
        if (!p.noPredecessor) {
            predecessor = p;
        }
    }

    /**
     * Whether the tab should be on a scroll pane.
     * 
     * @return
     */
    public boolean isScrollable() {
        return true;
    }

    /**
     * Returns the last predecessor.
     */
    public Tab getPredecessor() {
        return predecessor;
    }

    /**
     * Marks the tab as no predecessor.
     */
    public void markAsPredecessor() {
        noPredecessor = false;
    }

    /**
     * Marks the tab as no predecessor.
     */
    public void markAsNoPredecessor() {
        noPredecessor = true;
    }

    /**
     * Returns the list models of the tab.
     * 
     * @return the list models of the tab.
     */
    public List<GenericListModel> getListModels() {
        return listModels;
    }
}
