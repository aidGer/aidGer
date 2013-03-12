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

import java.util.Calendar;
import java.util.Date;

import de.aidger.model.AbstractModel;
import de.aidger.model.Runtime;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.CostUnit;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.view.forms.HourlyWageEditorForm.Qualification;
import siena.SienaException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class represents the table model for the employments data.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class EmploymentTableModel extends TableModel {

    private static Map<Long, UIAssistant> assistantCache = new HashMap<Long, UIAssistant>();

    private static Map<Long, UICourse> courseCache = new HashMap<Long, UICourse>();

    private static Map<Long, UIContract> contractCache = new HashMap<Long, UIContract>();

    /**
     * Constructs the table model for employments.
     */
    public EmploymentTableModel() {
        super(new String[] { _("ID"), _("Assistant"), _("Course"),
                _("Contract"), _("Date"), _("Hour count"), _("Funds"),
                _("Cost unit"), _("Qualification"), _("Remark") });
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
        } else if (column == 4) {
            return Date.class;
        } else if (column == 5) {
            return Double.class;
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
        try {
            Employment employment = (Employment) model;
            switch (row) {
                case 0:
                    return employment.getId();
                case 1:
                    if (assistantCache.containsKey(employment.getAssistantId())) {
                        return assistantCache.get(employment.getAssistantId());
                    } else {
                        Assistant assistant = (new Assistant()).getById(employment.getAssistantId());
                        UIAssistant a = new UIAssistant(assistant);

                        assistantCache.put(a.getId(), a);
                        return a;
                    }
                case 2:
                    if (courseCache.containsKey(employment.getCourseId())) {
                        return courseCache.get(employment.getCourseId());
                    } else {
                        Course course = (new Course()).getById(employment.getCourseId());
                        UICourse c = new UICourse(course);
                        
                        courseCache.put(c.getId(), c);
                        return c;
                    }
                case 3:
                    if (contractCache.containsKey(employment.getContractId())) {
                        return contractCache.get(employment.getContractId());
                    } else {
                        Contract contract = (new Contract()).getById(employment.getContractId());
                        UIContract c = new UIContract(contract);

                        contractCache.put(c.getId(), c);
                        return c;
                    }
                case 4:
                    Calendar cal = Calendar.getInstance();
                    cal.clear();
                    cal.set(Calendar.MONTH, employment.getMonth() - 1);
                    cal.set(Calendar.YEAR, employment.getYear());
                    return cal.getTime();
                case 5: return employment.getHourCount();
                case 6: 
                    CostUnit costUnit = Runtime.getInstance().getDataXMLManager()
                            .fromTokenDB(employment.getFunds());
                    return costUnit == null ? employment.getCostUnit() : costUnit;
                case 7: return UICostUnit.valueOf(employment.getCostUnit());
                case 8: return Qualification.valueOf(employment.getQualification());
                case 9: return employment.getRemark();
            }
        } catch (SienaException ex) {
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
            List<Employment> lst = (new Employment()).getAll();
            if(lst == null) return ret;
            for (Employment e : lst) {
                ret.add(new Employment(e));
            }
        } catch (SienaException ex) {
        }

        return ret;
    }

}
