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

import java.text.DecimalFormat;

import de.aidger.model.models.CostUnit;
import de.aidger.view.tabs.ViewerTab.DataType;

/**
 * The UI cost unit is used for prettier rendering of the model.
 * 
 * @author aidGer Team
 */
public class UICostUnit extends CostUnit implements UIModel {

    /**
     * Only one instance for all cost units because of performance problems.
     */
    private static final DecimalFormat format = new DecimalFormat("00000000");

    /**
     * Initializes the CostUnit class.
     */
    public UICostUnit() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.AbstractModel#toString()
     */
    @Override
    public String toString() {
        return getFunds() + " (" + getTokenDB() + ")";
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.UIModel#getDataType()
     */
    @Override
    public DataType getDataType() {
        return DataType.CostUnit;
    }

    /**
     * Formats an integer as cost unit value.
     * 
     * @param costUnit
     *            the cost unit as integer
     * @return the cost unit as string
     */
    public static String valueOf(Integer costUnit) {
        return format.format(costUnit);
    }
}
