/**
 * 
 */
package de.aidger.model.budgets;

import java.util.Vector;

import de.aidger.model.models.Course;

/**
 * This class manages the course budgets for a budget report.
 * 
 * @author aidGer Team
 */
public class BudgetCreator {

    /**
     * The course budgets of this creator.
     */
    private final Vector<CourseBudget> courseBudgets = new Vector<CourseBudget>();

    /**
     * Initializes a new BudgetCreator.
     */
    public BudgetCreator() {
    }

    /**
     * Adds a course budget to the vector of course budgets.
     * 
     * @param course
     *            The course of which to add the budget.
     */
    public void addCourseBudget(Course course) {
        CourseBudget courseBudget = new CourseBudget();
        String courseName = course.getDescription() + "("
                + course.getSemester() + ", " + course.getLecturer() + ")";
        courseBudget.setName(courseName);
        double totalBudget = course.getUnqualifiedWorkingHours()
                * course.getNumberOfGroups();
        double bookedBudget = BudgetChecker.getActualBudget(course);
        double availableBudget = totalBudget - bookedBudget;
        courseBudget.setTotalBudget(totalBudget);
        courseBudget.setBookedBudget(bookedBudget);
        courseBudget.setAvailableBudget(availableBudget);
        courseBudget.setOverBooked(BudgetChecker.checkBudget(course));
        courseBudgets.add(courseBudget);
    }

    /**
     * Returns the vector of course budgets.
     * 
     * @return the course budgets
     */
    public Vector<CourseBudget> getCourseBudgets() {
        return courseBudgets;
    }
}
