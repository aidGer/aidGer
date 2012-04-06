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
import de.aidger.model.models.FinancialCategory;
import java.util.ArrayList;
import java.util.List;

import siena.SienaException;

/**
 * The class represents the table model for the master data financial
 * categories.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class FinancialCategoryTableModel extends TableModel {
    /**
     * Constructs the table model for financial categories.
     */
    public FinancialCategoryTableModel() {
        super(new String[] { _("ID"), _("Name"), _("Year"), _("Cost unit"),
                _("Budget Costs") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int column) {
        // sort specific columns properly
        if (column == 0) {
            return Integer.class;
        }

        return super.getColumnClass(column);
    }

        /**
     * (non-Javadoc)
     *
     * @see de.aidger.view.models.TableModel#getRowValue(de.aidger.model.AbstractModel, int)
     */
    @Override
    protected Object getRowValue(AbstractModel model, int row) {
        FinancialCategory fc = (FinancialCategory) model;
        switch (row) {
            case 0: return fc.getId();
            case 1: return fc.getName();
            case 2: return fc.getYear();
            case 3: 
                String funds = UICostUnit.valueOf(fc.getCostUnits()[0]);
                for (int i = 1; i < fc.getCostUnits().length; i++) {
                    funds += "\n" + UICostUnit.valueOf(fc.getCostUnits()[i]);
                }
                return funds;
            case 4:
                String budgetCosts = String.valueOf(fc.getBudgetCosts()[0]);
                for (int i = 1; i < fc.getCostUnits().length; i++) {
                    budgetCosts += "\n" + fc.getBudgetCosts()[i];
                }
                return budgetCosts;
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
        try {
        	//TODO: rewrite with Siena
            //return new FinancialCategory(AdoHiveController.getInstance().getFinancialCategoryManager().get(idx));
        	return null;
        } catch (SienaException ex) {
            return null;
        }
    }

    /**
     * (non-javadoc)
     *
     * @see de.aidger.view.models.TableModel#getModels()
     */
    protected List<AbstractModel> getModels() {
        List<AbstractModel> ret = new ArrayList<AbstractModel>();
        try {
            List<FinancialCategory> lst = (new FinancialCategory()).getAll();
            for (FinancialCategory e : lst) {
                ret.add(new FinancialCategory(e));
            }
        } catch (SienaException ex) {
        }

        return ret;
    }

}
