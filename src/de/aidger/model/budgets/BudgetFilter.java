/**
 * 
 */
package de.aidger.model.budgets;

import java.util.Vector;

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
        LESS("<"), LESSEQUAL("<="), EQUAL("="), GREATEREQUAL(">="), GREATER(">");

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
    private final Vector<String> lecturers;

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
        lecturers = new Vector<String>();
        availableBudget = bookedBudget = totalBudget = 0;
        availableComparison = bookedComparison = totalComparison = Comparison.GREATER;
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
    public Vector<String> getLecturers() {
        return lecturers;
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
