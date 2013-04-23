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

import static de.aidger.utils.Translation._;

/**
 * An empty tab that will be created when adding a new tab with the "+" button.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class EmptyTab extends Tab {

    /**
     * Initializes a new empty tab.
     */
    public EmptyTab() {
    }

    /**
     * Don't add the empty tab into the saved tabs array.
     *
     * @return null
     */
    @Override
    public String toString() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.tabs.Tab#getTabName()
     */
    @Override
    public String getTabName() {
        return _("(Unnamed)");
    }

}
