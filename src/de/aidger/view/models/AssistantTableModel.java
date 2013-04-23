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

import de.aidger.model.AbstractModel;
import de.aidger.model.models.Assistant;
import de.aidger.view.forms.HourlyWageEditorForm.Qualification;
import siena.SienaException;

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents the table model for the master data assistants.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class AssistantTableModel extends TableModel {
    /**
     * Constructs the table model for assistants.
     */
    public AssistantTableModel() {
        super(new String[] { _("ID"), _("First Name"), _("Last Name"),
                _("Email"), _("Qualification") });
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
        Assistant assistant = (Assistant) model;
        switch (row) {
            case 0: return assistant.getId();
            case 1: return assistant.getFirstName();
            case 2: return assistant.getLastName();
            case 3: return assistant.getEmail();
            case 4: return Qualification.valueOf(assistant.getQualification());
        }
        return null;
    }

    /**
     * (non-javadoc)
     *
     * @see de.aidger.view.models.TableModel#getModels()
     */
    protected List<AbstractModel> getModels() {
        List<AbstractModel> ret = new ArrayList<AbstractModel>();
        try {
            List<Assistant> lst = (new Assistant()).getAll();
            if(lst.isEmpty()) return ret;
            for (Assistant e : lst) {
                ret.add(new Assistant(e));
            }
        } catch (SienaException ex) {
        }

        return ret;
    }

}
