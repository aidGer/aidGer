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

import java.util.ArrayList;

/**
 * 
 * @author aidGer Team
 */
public class BudgetFilter {

    /**
     * How the budgets should be filtered.
     * 
     * @author aidGer Team
     */
    public enum Comparison {
        NONE(""), LESS("<"), LESSEQUAL("<="), EQUAL("="), GREATEREQUAL(">="), GREATER(
                ">");

        /**
         * The display name of an item.
         */
        private final String displayName;

        /**
         * Creates a new comparison with the given name as the display name.
         * 
         * @param name
         *            The name to set it to.
         */
        Comparison(String name) {
            displayName = name;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return displayName;
        }
    };

    /**
     * The lecturers to be filtered.
     */
    private final ArrayList<String> lecturers;

    /**
     * The semesters to be filtered.
     */
    private final ArrayList<String> semesters;

    /**
     * The available budget to be filtered.
     */
    private double availableBudget;

    /**
     * How the available budget will be compared.
     */
    private Comparison availableComparison;

    /**
     * The booked budget to be filtered.
     */
    private double bookedBudget;

    /**
     * How the booked budget should be compared.
     */
    private Comparison bookedComparison;

    /**
     * The total budget to be filtered.
     */
    private double totalBudget;

    /**
     * How the total budget should be compared.
     */
    private Comparison totalComparison;

    /**
     * Initializes a new BudgetFilter.
     */
    public BudgetFilter() {
        lecturers = new ArrayList<String>();
        semesters = new ArrayList<String>();
        availableBudget = bookedBudget = totalBudget = 0;
        availableComparison = bookedComparison = totalComparison = Comparison.NONE;
    }

    /**
     * Adds a lecturer to the existing lecturer filters.
     * 
     * @param lecturer
     *            The lecturer to be added.
     */
    public void addLecturer(String lecturer) {
        lecturers.add(lecturer);
    }

    /**
     * Checks if the lecturer exists and removes it.
     * 
     * @param lecturer
     *            The lecturer to be removed.
     */
    public void removeLecturer(String lecturer) {
        if (lecturers.contains(lecturer)) {
            lecturers.remove(lecturer);
        }
    }

    /**
     * Returns the lecturers to be filtered.
     * 
     * @return the lecturers
     */
    public ArrayList<String> getLecturers() {
        return lecturers;
    }

    /**
     * Adds a semester filter.
     * 
     * @param semester
     *            The semester to filter.
     */
    public void addSemester(String semester) {
        semesters.add(semester);
    }

    /**
     * Removes a semester from the filters list.
     * 
     * @param semester
     *            The semester to remove.
     */
    public void removeSemester(String semester) {
        if (semesters.contains(semester)) {
            semesters.remove(semester);
        }
    }

    /**
     * Returns the vector of semester filters.
     * 
     * @return The semester filters.
     */
    public ArrayList<String> getSemesters() {
        return semesters;
    }

    /**
     * Sets the available budget to be filtered.
     * 
     * @param availableBudget
     *            The budget to be filtered.
     */
    public void setAvailableBudget(double availableBudget) {
        this.availableBudget = availableBudget;
    }

    /**
     * Returns the available budget that should be filtered.
     * 
     * @return the available budget
     */
    public double getAvailableBudget() {
        return availableBudget;
    }

    /**
     * Sets how the available budget should be filtered.
     * 
     * @param availableComparison
     *            How the budget should be filtered. enum Comparison
     */
    public void setAvailableComparison(Comparison availableComparison) {
        this.availableComparison = availableComparison;
    }

    /**
     * Returns how the available budget should be filtered.
     * 
     * @return How the budget should be filtered.
     */
    public Comparison getAvailableComparison() {
        return availableComparison;
    }

    /**
     * Sets the booked budget that should be filtered.
     * 
     * @param bookedBudget
     *            The booked budget to set.
     */
    public void setBookedBudget(double bookedBudget) {
        this.bookedBudget = bookedBudget;
    }

    /**
     * Returns the booked budget of this filter.
     * 
     * @return the booked budget
     */
    public double getBookedBudget() {
        return bookedBudget;
    }

    /**
     * Sets how the booked budget should be filtered.
     * 
     * @param bookedComparison
     *            How the booked budget will be filtered. enum Comparison
     */
    public void setBookedComparison(Comparison bookedComparison) {
        this.bookedComparison = bookedComparison;
    }

    /**
     * Returns how the booked budget should be filtered.
     * 
     * @return How the booked budget will be filtered.
     */
    public Comparison getBookedComparison() {
        return bookedComparison;
    }

    /**
     * Sets the total budget to be filtered.
     * 
     * @param totalBudget
     *            The total budget to be filtered.
     */
    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }

    /**
     * Returns the total budget that will be filtered.
     * 
     * @return the total budget
     */
    public double getTotalBudget() {
        return totalBudget;
    }

    /**
     * Sets how the total budget should be filtered.
     * 
     * @param totalComparison
     *            How the total budget should be filtered. enum Comparison
     */
    public void setTotalComparison(Comparison totalComparison) {
        this.totalComparison = totalComparison;
    }

    /**
     * Returns how the total budget will be filtered.
     * 
     * @return How the total budget will be filtered.
     */
    public Comparison getTotalComparison() {
        return totalComparison;
    }
}
