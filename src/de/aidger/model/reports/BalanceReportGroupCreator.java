package de.aidger.model.reports;

import java.util.Vector;

import de.aidger.utils.reports.BalanceHelper;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * This class manages the BalanceReportGroupViewer which calls it. It adds
 * courses to the viewer's table.
 * 
 * @author aidGer Team
 */
public class BalanceReportGroupCreator {

    /**
     * The calculation method to be used for this balance report.
     */
    private int calculationMethod = 0;

    /**
     * Contains all courses of this group.
     */
    private final Vector<BalanceCourse> balanceCourses = new Vector<BalanceCourse>();

    /**
     * Initializes this BalanceReportGroupCreator and adds the first course.
     * 
     * @param course
     *            The course to be added.
     */
    public BalanceReportGroupCreator(ICourse course, int calculationMethod) {
        this.calculationMethod = calculationMethod;
        addCourse(course);
    }

    /**
     * Adds the given course details to a new balance course and adds the result
     * to the table in the group viewer.
     * 
     * @param course
     *            The course to be added.
     */
    public void addCourse(ICourse course) {
        balanceCourses.add(BalanceHelper.getBalanceCourse(course,
            calculationMethod));
    }

    /**
     * Returns the courses of this group.
     * 
     * @return The courses
     */
    public Vector<BalanceCourse> getBalanceCourses() {
        return balanceCourses;
    }
}
