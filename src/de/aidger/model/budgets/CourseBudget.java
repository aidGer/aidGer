/**
 * 
 */
package de.aidger.model.budgets;

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
        setBookedBudget(setAvailableBudget(setTotalBudget(0)));
        setOverBooked(false);
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
        return bookedBudget;
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
        return totalBudget;
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
        return availableBudget;
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
}
