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

package de.aidger.model.reports;

import java.util.ArrayList;

import de.aidger.model.models.Course;
import de.aidger.utils.reports.BalanceHelper;

/**
 * This class manages the BalanceReportGroupViewer which calls it. It adds
 * courses to the viewer's table.
 * 
 * @author aidGer Team
 */
public class BalanceReportGroupCreator {

    /**
     * The calculation method to be used for this balance report.
     */
    private int calculationMethod = 0;

    /**
     * Contains all courses of this group.
     */
    private final ArrayList<BalanceCourse> balanceCourses = new ArrayList<BalanceCourse>();

    /**
     * Initializes this BalanceReportGroupCreator and adds the first course.
     * 
     * @param course
     *            The course to be added.
     */
    public BalanceReportGroupCreator(Course course, int calculationMethod) {
        this.calculationMethod = calculationMethod;
        addCourse(course);
    }

    /**
     * Adds the given course details to a new balance course and adds the result
     * to the table in the group viewer.
     * 
     * @param course
     *            The course to be added.
     */
    public void addCourse(Course course) {
        BalanceCourse balanceCourse = BalanceHelper.getBalanceCourse(course);
        for (BalanceCourse currentCourse : balanceCourses) {
            if (balanceCourse.compareTo(currentCourse) <= 0) {
                balanceCourses.add(balanceCourses.indexOf(currentCourse),
                    balanceCourse);
                return;
            }
        }
        balanceCourses.add(balanceCourse);
    }

    /**
     * Returns the courses of this group.
     * 
     * @return The courses
     */
    public ArrayList<BalanceCourse> getBalanceCourses() {
        return balanceCourses;
    }
}
