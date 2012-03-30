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

package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import de.aidger.model.models.Activity;
import de.aidger.utils.DateUtils;
import de.aidger.view.tabs.ViewerTab.DataType;

/**
 * The UI activity is used for prettier rendering of the model.
 * 
 * @author aidGer Team
 */
public class UIActivity extends Activity implements UIModel {

    /**
     * Initializes the Activity class.
     */
    public UIActivity() {
    }

    /**
     * Initializes the Activity class with the given activity model.
     * 
     * @param a
     *            the activity model
     */
    public UIActivity(Activity a) {
        super(a);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.AbstractModel#toString()
     */
    @Override
    public String toString() {
        return getType() + " (" + getSender() + " " + _("at") + " "
                + DateUtils.formatDate(getDate()) + ")";
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.UIModel#getDataType()
     */
    @Override
    public DataType getDataType() {
        return DataType.Activity;
    }
}
