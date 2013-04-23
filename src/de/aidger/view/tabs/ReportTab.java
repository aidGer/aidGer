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

/**
 * 
 */
package de.aidger.view.tabs;

import javax.swing.JPanel;

/**
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public abstract class ReportTab extends Tab {

    /**
     * Removes the specified filter from the filters list.
     * 
     * @param type
     *            The type of filter.
     * @param value
     *            The value of the filter.
     */
    public void removeFilter(String name, String value) {
    }

    /**
     * Removes the given panel from the filter panel.
     * 
     * @param panel
     *            The panel to remove
     */
    public void removeFilterPanel(JPanel panel) {
    }
}
