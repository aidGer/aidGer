/**
 * 
 */
package de.aidger.model.budgets;

import java.util.List;

import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

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
        } catch (AdoHiveException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return actualBudget;
    }
}
