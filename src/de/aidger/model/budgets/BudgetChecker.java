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

/**
 * 
 */
package de.aidger.model.budgets;

import java.util.List;

import siena.SienaException;

import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;

/**
 * This class is used to get the budget of all courses.
 * 
 * @author aidGer Team
 */
public class BudgetChecker {

    /**
     * Initializes a new BudgetChecker
     */
    public BudgetChecker() {
    }

    /**
     * Checks if the budget of this course is over booked.
     * 
     * @param course
     *            The course of which to check the budget.
     * @return False if the course is over booked.
     */
    public static boolean checkBudget(Course course) {
        return checkBudget(course, 0);
    }

    /**
     * Checks if the planned budget plus an amount of hours makes this course
     * over booked.
     * 
     * @param course
     *            The course of which to check the budget.
     * @param value
     *            The amount of hours to add.
     * @return False if the course will be over booked.
     */
    public static boolean checkBudget(Course course, double value) {
        double plannedBudget = course.getUnqualifiedWorkingHours()
                * course.getNumberOfGroups();
        if (plannedBudget >= getActualBudget(course) + value) {
            return true;
        }
        return false;
    }

    /**
     * Calculates the actual budget of the given course.
     * 
     * @param course
     *            The course of which to calculate the actual budget.
     * @return The actual budget.
     */
    public static double getActualBudget(Course course) {
        double actualBudget = 0;
        try {
            List<Employment> employments = new Employment()
                .getEmployments(course);
            for (Employment employment : employments) {
                actualBudget = actualBudget + employment.getHourCount();
            }
        } catch (SienaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return actualBudget;
    }
}
