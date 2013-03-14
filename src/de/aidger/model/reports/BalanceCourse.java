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
package de.aidger.model.reports;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Represents a course in a balance report with all it's variables.
 * 
 * @author aidGer Team
 * 
 */
public class BalanceCourse implements Comparable<BalanceCourse> {

    /**
     * The title of the course.
     */
    private String Title;

    /**
     * The part of the course.
     */
    private Character Part;

    /**
     * The lecturer of the course.
     */
    private String Lecturer;

    /**
     * The target audience of the course.
     */
    private String TargetAudience;

    /**
     * The planned AWS of the course.
     */
    private double PlannedAWS;

    /**
     * The basic needed AWS of the course.
     */
    private double BasicAWS;

    /**
     * The budget costs paid with student fees.
     */
    private final ArrayList<BudgetCost> budgetCosts;

    /**
     * Initializes a new BalanceCourse, which contains all the necessary
     * variables of a course in a balance report.
     */
    public BalanceCourse() {
        budgetCosts = new ArrayList<BudgetCost>();
        Title = Lecturer = TargetAudience = "";
        PlannedAWS = BasicAWS = 0.0;
        Part = '-';
    }

    /**
     * Returns the course as an object for putting in a table.
     * 
     * @return The object
     */
    public Object[] getCourseObject() {
        return new Object[] { Title, Part, Lecturer, TargetAudience,
                getPlannedAWS(), getBasicAWS(), budgetCosts };
    }

    /**
     * Sets the title of the course.
     * 
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     * Returns the title of the course.
     * 
     * @return the title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Sets the part of the course.
     * 
     * @param part
     *            the part to set
     */
    public void setPart(String part) {
        Part = part.charAt(0);
    }

    /**
     * Sets the part of the course.
     *
     * @param part
     *            the part to set
     */
    public void setPart(Character part) {
        Part = part;
    }

    /**
     * Returns the part of the course.
     * 
     * @return the part
     */
    public Character getPart() {
        return Part;
    }

    /**
     * Sets the lecturer of the course.
     * 
     * @param lecturer
     *            the lecturer to set
     */
    public void setLecturer(String lecturer) {
        Lecturer = lecturer;
    }

    /**
     * Returns the lecturer of the course.
     * 
     * @return the lecturer
     */
    public String getLecturer() {
        return Lecturer;
    }

    /**
     * Sets the target audience of the course.
     * 
     * @param targetAudience
     *            the targetAudience to set
     */
    public void setTargetAudience(String targetAudience) {
        TargetAudience = targetAudience;
    }

    /**
     * Returns the target audience of the course.
     * 
     * @return the targetAudience
     */
    public String getTargetAudience() {
        return TargetAudience;
    }

    /**
     * Sets the planned AWS of the course.
     * 
     * @param plannedAWS
     *            the plannedAWS to set
     */
    public void setPlannedAWS(double plannedAWS) {
        PlannedAWS = plannedAWS;
    }

    /**
     * Returns the planned AWS of the course.
     * 
     * @return the plannedAWS
     */
    public double getPlannedAWS() {
        return (new BigDecimal(PlannedAWS)).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }

    /**
     * Sets the basic AWS of the course.
     * 
     * @param basicAWS
     *            the basicAWS to set
     */
    public void setBasicAWS(double basicAWS) {
        BasicAWS = basicAWS;
    }

    /**
     * Returns the basic AWS of the course.
     * 
     * @return the basicAWS
     */
    public double getBasicAWS() {
        return (new BigDecimal(BasicAWS)).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }

    public class BudgetCost {
        /**
         * The cost unit of this budget cost.
         */
        int id;

        /**
         * The name of this budget cost.
         */
        String name;

        /**
         * The value of this budget cost.
         */
        double value;

        public BudgetCost() {
            id = 0;
            value = 0;
            name = null;
        }

        /**
         * Sets the cost unit of this budget cost.
         * 
         * @param id
         *            The cost unit to set it to.
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * Returns the cost unit of this budget cost.
         * 
         * @return the cost unit
         */
        public int getId() {
            return id;
        }

        /**
         * Sets the name of this budget cost.
         * 
         * @param name
         *            The name to set it to.
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Returns the name of this budget cost.
         * 
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of this budget cost.
         * 
         * @param budgetCost2
         *            The value to set it to.
         */
        public void setValue(double value) {
            this.value = value;
        }

        /**
         * Returns the unrounded value of this budget cost.
         * 
         * @return The value
         */
        public double getUnroundedValue() {
            return value;
        }

        /**
         * Returns the rounded value of this budget cost.
         * 
         * @return the value
         */
        public double getValue() {
            return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
        }
    }

    /**
     * Add budget costs of the course. If the cost unit exists already, add the
     * value to that cost unit.
     * 
     * @param budgetCost
     *            The budget cost to be added to this course.
     * @param name
     *            The name of the cost unit.
     * @param value
     *            The value to be added to the cost unit.
     */
    public void addBudgetCost(int id, String name, double value) {
        for (BudgetCost budgetCost : budgetCosts) {
            if (budgetCost.getName().equals(name)) {
                budgetCost.setValue(budgetCost.getUnroundedValue() + value);
                return;
            }
        }
        BudgetCost budgetCost = new BudgetCost();
        budgetCost.setId(id);
        budgetCost.setName(name);
        budgetCost.setValue(value);
        budgetCosts.add(budgetCost);
    }

    /**
     * Returns the budget costs of the course.
     * 
     * @return the budget costs
     */
    public ArrayList<BudgetCost> getBudgetCosts() {
        return budgetCosts;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(BalanceCourse arg0) {
        int comparison = Title.toLowerCase().compareTo(
            arg0.getTitle().toLowerCase());
        if (comparison == 0) {
            comparison = Part.compareTo(arg0.getPart());
        }
        return comparison != 0 ? comparison : TargetAudience.toLowerCase()
            .compareTo(arg0.getTargetAudience().toLowerCase());
    }
}
