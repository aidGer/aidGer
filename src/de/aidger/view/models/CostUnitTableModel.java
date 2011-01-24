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

import de.aidger.model.AbstractModel;
import de.aidger.model.Runtime;
import de.aidger.model.models.CostUnit;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import java.util.List;

/**
 * The class represents the table model for the contracts data.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class CostUnitTableModel extends TableModel {
    /**
     * Constructs the table model for contracts.
     */
    public CostUnitTableModel() {
        super(new String[] { _("Cost unit"), _("Funds"), _("Database token") });
    }

    /**
     * (non-Javadoc)
     *
     * @see de.aidger.view.models.TableModel#getRowValue(de.aidger.model.AbstractModel, int)
     */
    @Override
    protected Object getRowValue(AbstractModel model, int row) {
        CostUnit costunit = (CostUnit) model;
        switch (row) {
            case 0: return costunit.getCostUnit();
            case 1: return costunit.getFunds();
            case 2: return costunit.getTokenDB();
        }
        return null;
    }

    /**
     * (non-Javadoc)
     *
     * @see de.aidger.view.models.TableModel#getModelFromDB(int)
     */
    @Override
    protected AbstractModel getModelFromDB(int idx) {
        return Runtime.getInstance().getDataXMLManager().getCostUnitMap().get(idx);
    }

    /**
     * (non-javadoc)
     *
     * @see de.aidger.view.models.TableModel#getModels()
     */
    protected List<AbstractModel> getModels() {
        List<CostUnit> lst = Runtime.getInstance().getDataXMLManager().getCostUnitMap();

        return (List) lst;
    }

}
