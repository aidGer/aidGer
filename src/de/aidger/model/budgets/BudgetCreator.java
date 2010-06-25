/**
 * 
 */
package de.aidger.model.budgets;

import java.util.Vector;

import de.aidger.model.budgets.BudgetFilter.Comparison;
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
    public void addCourseBudget(Course course, BudgetFilter budgetFilter) {
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
        boolean foundLecturer = false;
        Vector<String> lecturers = budgetFilter.getLecturers();
        for (String lecturer : lecturers) {
            /*
             * If the lecturer of this course is included in the filter list, it
             * passed this filter.
             */
            if (course.getLecturer().equals(lecturer)) {
                foundLecturer = true;
                break;
            }
        }
        boolean foundSemester = false;
        Vector<String> semesters = budgetFilter.getSemesters();
        for (String semester : semesters) {
            /*
             * If the semester of this course is included in the filter list, it
             * passed this filter.
             */
            if (course.getSemester().equals(semester)) {
                foundSemester = true;
                break;
            }
        }
        if ((foundSemester || semesters.isEmpty())
                && (foundLecturer || lecturers.isEmpty())
                && (budgetFilter.getAvailableComparison().equals(
                    Comparison.NONE) || compareBudget(availableBudget,
                    budgetFilter.getAvailableBudget(), budgetFilter
                        .getAvailableComparison()))
                && (budgetFilter.getBookedComparison().equals(Comparison.NONE) || compareBudget(
                    bookedBudget, budgetFilter.getBookedBudget(), budgetFilter
                        .getBookedComparison()))
                && (budgetFilter.getTotalComparison().equals(Comparison.NONE) || compareBudget(
                    totalBudget, budgetFilter.getTotalBudget(), budgetFilter
                        .getTotalComparison()))) {
            /*
             * If this course passed all the filters, it can be added to the
             * list of courses that passed as well.
             */
            CourseBudget courseBudget = new CourseBudget(course);
            courseBudgets.add(courseBudget);
        }
    }

    /**
     * Compares a given actual budget with a given wanted budget by the given
     * comparison method.
     * 
     * @param actual
     *            The actual budget that shall be compared to the wanted budget.
     * @param wanted
     *            The wanted budget, to which the actual budget will be compared
     *            to.
     * @param comparison
     *            How the budgets should be compared. enum Comparison
     * @return Whether the budget comparison was succesful or not.
     */
    private boolean compareBudget(double actual, double wanted,
            Comparison comparison) {
        switch (comparison) {
        case LESS:
            return actual < wanted;
        case LESSEQUAL:
            return actual <= wanted;
        case EQUAL:
            return actual == wanted;
        case GREATEREQUAL:
            return actual >= wanted;
        case GREATER:
            return actual > wanted;
        default:
            return true;
        }
    }

    /**
     * Returns the course budget as an object array.
     * 
     * @param courseBudget
     *            The course budget to be converted.
     * @return The object array of the course budget.
     */
    public Object[] getObjectArray(CourseBudget courseBudget) {
        Object[] objectArray = { courseBudget.getName(),
                courseBudget.getSemester(), courseBudget.getLecturer(),
                courseBudget.getBookedBudget(),
                courseBudget.getAvailableBudget(),
                courseBudget.getTotalBudget() };
        return objectArray;
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
