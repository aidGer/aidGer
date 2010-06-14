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
     * Contains all courses of this group.
     */
    private final Vector balanceCourses = new Vector();

    /**
     * Initializes this BalanceReportGroupCreator and adds the first course.
     * 
     * @param course
     *            The course to be added.
     */
    public BalanceReportGroupCreator(ICourse course) {
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
        balanceCourses.add(BalanceHelper.getBalanceCourse(course));
    }

    /**
     * Returns the courses of this group.
     * 
     * @return The courses
     */
    public Vector getBalanceCourses() {
        return balanceCourses;
    }
}
