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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.aidger.model.AbstractModel;
import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import siena.SienaException;

/**
 * The class represents the table model for the activities.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ActivityTableModel extends TableModel {

    private static Map<Long, UIAssistant> assistantCache = new HashMap<Long, UIAssistant>();

    private static Map<Long, UICourse> courseCache = new HashMap<Long, UICourse>();

    /**
     * Constructs the table model for activities.
     */
    public ActivityTableModel() {
        super(new String[] { _("ID"), _("Assistant"), _("Course"), _("Date"),
                _("Initiator"), _("Processor"), _("Type"), _("Document Type"),
                _("Content"), _("Remark") });
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
        } else if (column == 3) {
            return Date.class;
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
            Activity activity = (Activity) model;
            switch (row) {
            case 0:
                return activity.getId();
            case 1:
                if (assistantCache.containsKey(activity.getAssistantId())) {
                    return assistantCache.get(activity.getAssistantId());
                } else {
                    UIAssistant assistant = (activity.getAssistantId() == null) ? new UIAssistant()
                            : new UIAssistant((new Assistant())
                                .getById(activity.getAssistantId()));
                    if (assistant != null) {
                        assistantCache.put(assistant.getId(), assistant);
                    }
                    return assistant;
                }
            case 2:
                if (courseCache.containsKey(activity.getCourseId())) {
                    return courseCache.get(activity.getCourseId());
                } else {
                    UICourse course = (activity.getCourseId() == null) ? new UICourse()
                            : new UICourse((new Course()).getById(activity
                                .getCourseId()));
                    if (course != null) {
                        courseCache.put(course.getId(), course);
                    }
                    return course;
                }
            case 3:
                return activity.getDate();
            case 4:
                return activity.getSender();
            case 5:
                return activity.getProcessor();
            case 6:
                return activity.getType();
            case 7:
                return activity.getDocumentType();
            case 8:
                return activity.getContent();
            case 9:
                return activity.getRemark();
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
    @Override
    protected List<AbstractModel> getModels() {
        List<AbstractModel> ret = new ArrayList<AbstractModel>();
        try {
            List<Activity> lst = (new Activity()).getAll();
            if(lst.isEmpty()) return ret;
            for (Activity e : lst) {
                ret.add(new Activity(e));
            }
        } catch (SienaException ex) {
        }

        return ret;
    }

}
