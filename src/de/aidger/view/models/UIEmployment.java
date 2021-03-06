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

package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Calendar;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Employment;
import de.aidger.utils.DateUtils;
import de.aidger.view.tabs.ViewerTab.DataType;
import siena.SienaException;

/**
 * The UI employment is used for prettier rendering of the model.
 * 
 * @author aidGer Team
 */
public class UIEmployment extends Employment implements UIModel {

    /**
     * Initializes the Employment class.
     */
    public UIEmployment() {
    }

    /**
     * Initializes the Employment class with the given employment model.
     * 
     * @param e
     *            the employment model
     */
    public UIEmployment(Employment e) {
        super(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.AbstractModel#toString()
     */
    @Override
    public String toString() {
        try {
            Assistant assistant = (new Assistant()).getById(getAssistantId());

            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.set(Calendar.MONTH, getMonth() - 1);
            cal.set(Calendar.YEAR, getYear());

            return _("from") + " " + (new UIAssistant(assistant)).toString()
                    + " " + _("in") + " "
                    + DateUtils.formatDate(cal.getTime());
        } catch (SienaException e) {
            return "";
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.UIModel#getDataType()
     */
    @Override
    public DataType getDataType() {
        return DataType.Employment;
    }
}
