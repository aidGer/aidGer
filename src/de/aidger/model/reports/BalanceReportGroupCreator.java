package de.aidger.model.reports;

import javax.swing.JPanel;

import de.aidger.utils.reports.BalanceHelper;
import de.aidger.view.reports.BalanceReportGroupPanel;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * This class manages the BalanceReportGroupViewer which belongs to it. It adds
 * courses to the viewer's table.
 * 
 * @author aidGer Team
 */
public class BalanceReportGroupCreator {

    /**
     * The balance report group viewer panel, which belongs to this creator.
     */
    private BalanceReportGroupPanel balanceReportGroupViewer = null;

    /**
     * Initializes this BalanceReportGroupCreator and adds the first course.
     * 
     * @param course
     *            The course to be added.
     */
    public BalanceReportGroupCreator(ICourse course) {
        if (balanceReportGroupViewer == null) {
            balanceReportGroupViewer = new BalanceReportGroupPanel(course
                .getGroup());

        }
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
        BalanceCourse balanceCourse = BalanceHelper.getBalanceCourse(course);
        balanceReportGroupViewer.addCourse(balanceCourse.getCourseObject());
    }

    /**
     * Returns the JPanel of the associated group viewer.
     * 
     * @return the JPanel
     */
    public JPanel getPanel() {
        return balanceReportGroupViewer;
    }
}
