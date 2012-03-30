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

import de.aidger.model.models.FinancialCategory;
import de.aidger.view.tabs.ViewerTab.DataType;

/**
 * The UI financial category is used for prettier rendering of the model.
 * 
 * @author aidGer Team
 */
public class UIFinancialCategory extends FinancialCategory implements UIModel {

    /**
     * Initializes the Financial Category class.
     */
    public UIFinancialCategory() {
    }

    /**
     * Initializes the Financial Category class with the given financial
     * category model.
     * 
     * @param c
     *            the assistant model
     */
    public UIFinancialCategory(FinancialCategory fc) {
        super(fc);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.AbstractModel#toString()
     */
    @Override
    public String toString() {
        return getName() + " (" + getYear() + ")";
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.UIModel#getDataType()
     */
    @Override
    public DataType getDataType() {
        return DataType.FinancialCategory;
    }
}
