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

/**
 * 
 */
package de.aidger.model.budgets;

import java.math.BigDecimal;

import de.aidger.model.models.Course;

/**
 * Represents a course in the budget report table.
 * 
 * @author aidGer Team
 */
public class CourseBudget {

    /**
     * The name of this course budget.
     */
    private String name;

    /**
     * The semester of this budget's course.
     */
    private String semester;

    /**
     * The lecturer of this course.
     */
    private String lecturer;

    /**
     * The booked budget of this course budget.
     */
    private double bookedBudget;

    /**
     * The available budget of this course budget.
     */
    private double availableBudget;

    /**
     * The total budget of this course budget.
     */
    private double totalBudget;

    /**
     * Whether or not the course is over booked.
     */
    private boolean overBooked;

    /**
     * Initializes a new course budget.
     */
    public CourseBudget() {
        setName(null);
        setSemester(null);
        setLecturer(null);
        setBookedBudget(setAvailableBudget(setTotalBudget(0)));
        setOverBooked(false);
    }

    /**
     * Creates a new course budget from a given course.
     * 
     * @param course
     *            The course of which to create it.
     */
    public CourseBudget(Course course) {
        double totalBudget = course.getUnqualifiedWorkingHours()
                * course.getNumberOfGroups();
        double bookedBudget = BudgetChecker.getActualBudget(course);
        double availableBudget = 0;
        if (totalBudget - bookedBudget > 0) {
            /*
             * If there are more budgets booked for this course than there are
             * in total, the available budget shall be 0.
             */
            availableBudget = totalBudget - bookedBudget;
        }
        setName(course.getDescription());
        setSemester(course.getSemester());
        setLecturer(course.getLecturer());
        setTotalBudget(totalBudget);
        setBookedBudget(bookedBudget);
        setAvailableBudget(availableBudget);
        setOverBooked(!BudgetChecker.checkBudget(course));
    }

    /**
     * Sets the booked budget of this course budget.
     * 
     * @param bookedBudget
     *            What to set the booked budget to.
     */
    public void setBookedBudget(double bookedBudget) {
        this.bookedBudget = bookedBudget;
    }

    /**
     * Returns the booked budget of this budget course.
     * 
     * @return the booked budget
     */
    public double getBookedBudget() {
        return (new BigDecimal(bookedBudget)).setScale(2,
            BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }

    /**
     * Sets the total budget of this course budget.
     * 
     * @param totalBudget
     *            What to set the total budget to.
     */
    public double setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
        return totalBudget;
    }

    /**
     * Returns the total budget of this course budget.
     * 
     * @return the total budget
     */
    public double getTotalBudget() {
        return (new BigDecimal(totalBudget)).setScale(2,
            BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }

    /**
     * Sets the available budget of this course budget.
     * 
     * @param availableBudget
     *            What to set the available budget to.
     */
    public double setAvailableBudget(double availableBudget) {
        this.availableBudget = availableBudget;
        return availableBudget;
    }

    /**
     * Returns the available budget of this course budget.
     * 
     * @return the available budget
     */
    public double getAvailableBudget() {
        return (new BigDecimal(availableBudget)).setScale(2,
            BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }

    /**
     * Sets the name of this course budget's course.
     * 
     * @param name
     *            The name of this course budget's course.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this course budget's course.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets whether this course is over booked or not.
     * 
     * @param overBooked
     *            True if it is over booked, false if not.
     */
    public void setOverBooked(boolean overBooked) {
        this.overBooked = overBooked;
    }

    /**
     * Returns whether this course is over booked or not.
     * 
     * @return True if it is over booked, false if not.
     */
    public boolean isOverBooked() {
        return overBooked;
    }

    /**
     * Sets the semester of this budget's course.
     * 
     * @param semester
     *            The semester.
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Returns the semester of this course.
     * 
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Sets the lecturer of this course.
     * 
     * @param lecturer
     *            The lecturer of this course.
     */
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    /**
     * Returns the lecturer of this course.
     * 
     * @return the lecturer
     */
    public String getLecturer() {
        return lecturer;
    }
}
