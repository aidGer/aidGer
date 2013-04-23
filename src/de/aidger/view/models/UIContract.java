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

import de.aidger.model.models.Contract;
import de.aidger.utils.DateUtils;
import de.aidger.view.forms.ContractEditorForm.ContractType;
import de.aidger.view.tabs.ViewerTab.DataType;

/**
 * The UI contract is used for prettier rendering of the model.
 * 
 * @author aidGer Team
 */
public class UIContract extends Contract implements UIModel {

    /**
     * Initializes the Contract class.
     */
    public UIContract() {
    }

    /**
     * Initializes the Contract class with the given contract model.
     * 
     * @param c
     *            the contract model
     */
    public UIContract(Contract c) {
        super(c);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.AbstractModel#toString()
     */
    @Override
    public String toString() {
        if (getType() == null) {
            return "";
        }

        return ContractType.valueOf(getType()).toString()
                + " ("
                + DateUtils.formatDate(getCompletionDate()) + ")";
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.UIModel#getDataType()
     */
    @Override
    public DataType getDataType() {
        return DataType.Contract;
    }
}
