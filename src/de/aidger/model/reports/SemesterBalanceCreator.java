/**
 * 
 */
package de.aidger.model.reports;

import java.util.List;

import de.aidger.model.models.Course;
import de.aidger.view.tabs.BalanceViewerTab;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * @author Phil
 * 
 */
public class SemesterBalanceCreator extends BalanceCreator {

    public SemesterBalanceCreator() {
        if (balanceViewerTab == null) {
            balanceViewerTab = new BalanceViewerTab();
        }
    }

    /**
     * Adds a semester panel, containing the groups of that semester, to the
     * tab.
     * 
     * @param semester
     *            The semester to be added.
     */
    public boolean addSemester(String semester, BalanceFilter filters) {
        try {
            if (courseExists(semester, filters)) {
                balanceViewerTab.add(new BalanceReportSemesterCreator(semester,
                    filters).getPanel());
                return true;
            }
        } catch (AdoHiveException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks if the given semester contains any courses.
     * 
     * @param semester
     *            The semester to check
     * @return true if the semester contains one or more courses.
     */
    private boolean courseExists(String semester, BalanceFilter filters) {
        List<ICourse> courses = null;
        try {
            courses = (new Course()).getAll();
        } catch (AdoHiveException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Check for every course, if it belongs to the given semester.
        for (ICourse course : courses) {
            if (course.getSemester().equals(semester)) {
                return true;
            }
        }
        return false;
    }

}
