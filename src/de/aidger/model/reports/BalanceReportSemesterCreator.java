package de.aidger.model.reports;

import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import de.aidger.model.models.Course;
import de.aidger.view.reports.BalanceReportSemesterPanel;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * This class manages the BalanceReportSemesterViewer which belongs to it. It
 * adds groups to the viewer's panel.
 * 
 * @author aidGer Team
 */
public class BalanceReportSemesterCreator {

    /**
     * The vector containing the balanceReportGroupCreators and the names of
     * their groups.
     */
    private final Vector balanceReportGroupCreators = new Vector<Vector>();

    /**
     * The associated BalanceReportSemesterViewer.
     */
    private BalanceReportSemesterPanel balanceReportSemesterViewer = null;

    /**
     * Initializes this BalanceReportSemesterCreator and adds the groups of the
     * given semester.
     * 
     * @param semester
     *            The semester of which the groups shall be added.
     * @throws AdoHiveException
     */
    public BalanceReportSemesterCreator(String semester)
            throws AdoHiveException {
        if (balanceReportSemesterViewer == null) {
            balanceReportSemesterViewer = new BalanceReportSemesterPanel(
                semester);

        }
        addGroups(semester);
    }

    /**
     * Adds the groups to the semester viewer.
     * 
     * @param semester
     *            The semester of which to add the groups.
     */
    private void addGroups(String semester) {
        List<ICourse> courses = null;
        try {
            courses = (new Course()).getAll();
        } catch (AdoHiveException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (ICourse course : courses) {
            if (course.getSemester().equals(semester)) {
                if (balanceReportGroupCreators.isEmpty()) {
                    createGroup(course);
                } else {
                    boolean foundGroup = false;
                    for (int i = 0; i <= balanceReportGroupCreators.size() - 1; i++) {
                        if (((Vector) balanceReportGroupCreators.get(i)).get(1)
                            .equals(course.getGroup())) {
                            ((BalanceReportGroupCreator) ((Vector) balanceReportGroupCreators
                                .get(i)).get(0)).addCourse(course);
                            foundGroup = true;
                            break;
                        }
                    }
                    if (!foundGroup) {
                        createGroup(course);
                    }
                }
            }
        }
    }

    /**
     * Creates a new group and adds it to the balanceReportGroupCreators vector.
     * 
     * @param course
     *            The first course, which the group contains.
     */
    private void createGroup(ICourse course) {
        BalanceReportGroupCreator balanceReportGroupCreator = new BalanceReportGroupCreator(
            course);
        balanceReportGroupCreators.add(new Vector<Object>());
        int i = balanceReportGroupCreators.size() - 1;
        ((Vector) balanceReportGroupCreators.get(i))
            .add(balanceReportGroupCreator);
        ((Vector) balanceReportGroupCreators.get(i)).add(course.getGroup());
        balanceReportSemesterViewer.createGroup(balanceReportGroupCreator
            .getPanel());
    }

    /**
     * Returns the JPanel of the associated viewer.
     * 
     * @return the JPanel
     */
    public JPanel getPanel() {
        return balanceReportSemesterViewer;
    }
}
