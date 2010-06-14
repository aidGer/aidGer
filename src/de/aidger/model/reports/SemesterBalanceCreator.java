/**
 * 
 */
package de.aidger.model.reports;

import java.util.List;

import de.aidger.model.models.Course;
import de.aidger.utils.reports.BalanceHelper;
import de.aidger.view.tabs.BalanceViewerTab;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * This class creates a semester for balance reports and adds it to a panel.
 * 
 * @author aidGer Team
 */
public class SemesterBalanceCreator extends BalanceCreator {

    /**
     * The balance helper used to filter the courses in this creator.
     */
    private BalanceHelper balanceHelper = null;

    /**
     * Initializes a new SemesterBalanceCreator along with its viewer tab.
     */
    public SemesterBalanceCreator(BalanceViewerTab balanceViewerTab) {
        if (this.balanceViewerTab == null) {
            this.balanceViewerTab = balanceViewerTab;
        }
        if (balanceHelper == null) {
            balanceHelper = new BalanceHelper();
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
                /*
                 * Only create a new semester panel, if a course for this
                 * semester exists.
                 */
                balanceViewerTab.addPanel(new BalanceReportSemesterCreator(
                    semester, filters).getPanel());
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
        List<ICourse> filteredCourses = balanceHelper.filterCourses(courses,
            filters);
        for (ICourse course : filteredCourses) {
            if (course.getSemester().equals(semester)) {
                return true;
            }
        }
        return false;
    }

}
