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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import siena.SienaException;
import de.aidger.model.AbstractModel;
import de.aidger.model.models.Course;
import de.aidger.model.models.FinancialCategory;

/**
 * The class represents the table model for the master data courses.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class CourseTableModel extends TableModel {

    private Map<Long, UIFinancialCategory> fcCache = new HashMap<Long, UIFinancialCategory>();

    /**
     * Constructs the table model for courses.
     */
    public CourseTableModel() {
        super(new String[] { _("ID"), _("Description"), _("Semester"),
                _("Lecturer"), _("Advisor"), _("Number of Groups"),
                _("Target Audience"), _("AWH per group"), _("Scope"),
                _("Part"), _("Group"), _("Remark"), _("Financial Category") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int column) {
        // sort specific columns properly
        if (column == 0 || column == 5) {
            return Integer.class;
        } else if (column == 7) {
            return Double.class;
        }

        return super.getColumnClass(column);
    }

    /**
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.TableModel#getRowValue(de.aidger.model.AbstractModel,
     *      int)
     */
    @Override
    protected Object getRowValue(AbstractModel model, int row) {
        try {
            Course course = (Course) model;
            switch (row) {
            case 0:
                return course.getId();
            case 1:
                return course.getDescription();
            case 2:
                return course.getSemester();
            case 3:
                return course.getLecturer();
            case 4:
                return course.getAdvisor();
            case 5:
                return course.getNumberOfGroups();
            case 6:
                return course.getTargetAudience();
            case 7:
                return course.getUnqualifiedWorkingHours();
            case 8:
                return course.getScope();
            case 9:
                return course.getPart();
            case 10:
                return course.getGroup();
            case 11:
                return course.getRemark();
            case 12:
                if (fcCache.containsKey(course.getFinancialCategoryId())) {
                    return fcCache.get(course.getFinancialCategoryId());
                } else {
                    UIFinancialCategory fc = new UIFinancialCategory(
                            (new FinancialCategory()).getById(course
                                    .getFinancialCategoryId()));
                    fcCache.put(fc.getId(), fc);
                    return fc;
                }
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
            List<Course> lst = (new Course()).getAll();
            if (lst.isEmpty())
                return ret;
            for (Course e : lst) {
                ret.add(new Course(e));
            }
        } catch (SienaException ex) {
        }

        return ret;
    }

}
