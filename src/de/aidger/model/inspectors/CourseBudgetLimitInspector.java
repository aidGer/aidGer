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

package de.aidger.model.inspectors;

import static de.aidger.controller.actions.EditorSaveAction.round;
import static de.aidger.utils.Translation._;

import java.text.MessageFormat;

import de.aidger.model.budgets.CourseBudget;
import de.aidger.model.models.Course;
import de.aidger.view.models.UICourse;

/**
 * An inspector for the budget limit in h for courses.
 * 
 * @author aidGer Team
 */
public class CourseBudgetLimitInspector extends Inspector {

    /**
     * The course to be checked.
     */
    private final Course course;

    /**
     * Creates a course budget limit inspector.
     * 
     * @param course
     *            the course to be checked
     */
    public CourseBudgetLimitInspector(Course course) {
        this.course = course;

        updatedDBRequired = true;
    }

    /*
     * Checks the budget limit in h for the given course.
     */
    @Override
    public void check() {
        CourseBudget courseBudget = new CourseBudget(course);

        if (courseBudget.getBookedBudget() > courseBudget.getTotalBudget()) {
            result = MessageFormat
                .format(
                    _("The budget limit for course {0} is exceeded ({1}h / {2}h)."),
                    new Object[] { (new UICourse(course)).toString(),
                            round(courseBudget.getBookedBudget(), 2),
                            round(courseBudget.getTotalBudget(), 2) });
        }
    }

}
