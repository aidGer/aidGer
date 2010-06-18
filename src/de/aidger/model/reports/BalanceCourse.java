/**
 * 
 */
package de.aidger.model.reports;

import java.math.BigDecimal;
import java.util.Vector;

/**
 * Represents a course in a balance report with all it's variables.
 * 
 * @author aidGer Team
 * 
 */
public class BalanceCourse {

    /**
     * The title of the course.
     */
    private String Title;

    /**
     * The part of the course.
     */
    private char Part;

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
    private final Vector<BudgetCost> budgetCosts;

    /**
     * Initializes a new BalanceCourse, which contains all the necessary
     * variables of a course in a balance report.
     */
    public BalanceCourse() {
        budgetCosts = new Vector<BudgetCost>();
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
                PlannedAWS, BasicAWS, budgetCosts };
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
    public void setPart(char part) {
        Part = part;
    }

    /**
     * Returns the part of the course.
     * 
     * @return the part
     */
    public char getPart() {
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
        return PlannedAWS;
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
        return BasicAWS;
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
        BigDecimal value;

        public BudgetCost() {
            id = 0;
            value = null;
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
        public void setValue(double budgetCost2) {
            this.value = new BigDecimal(budgetCost2).setScale(2,
                BigDecimal.ROUND_HALF_EVEN);
        }

        /**
         * Returns the value of this budget cost.
         * 
         * @return the value
         */
        public BigDecimal getValue() {
            return value;
        }
    }

    /**
     * Add budget costs of the course.
     * 
     * @param budgetCost
     *            The budget cost to be added to this course.
     */
    public void addBudgetCost(int id, String name, double value) {
        BudgetCost budgetCost = new BudgetCost();
        budgetCost.setId(id);
        budgetCost.setName(name);
        budgetCost.setValue(value);
        budgetCosts.add(budgetCost);
    }

    public void addBudgetCostvalue(int id, double value) {
        for (BudgetCost budgetCost : budgetCosts) {
            if (budgetCost.getId() == id) {
                budgetCost
                    .setValue(budgetCost.getValue().doubleValue() + value);
            }
        }
    }

    /**
     * Checks if the budget cost of the given id already exists.
     * 
     * @param id
     *            The id to check
     * @return Wheter the budget cost exists or not
     */
    public boolean budgetCostExists(int id) {
        for (BudgetCost budgetCost : budgetCosts) {
            if (budgetCost.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the budget costs of the course.
     * 
     * @return the budget costs
     */
    public Vector<BudgetCost> getBudgetCosts() {
        return budgetCosts;
    }
}
