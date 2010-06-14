/**
 * 
 */
package de.aidger.model.reports;

import java.util.List;
import java.util.Vector;

import de.aidger.model.models.Course;
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
     * Initializes a new SemesterBalanceCreator along with its viewer tab.
     */
    public SemesterBalanceCreator(BalanceViewerTab balanceViewerTab) {
        if (this.balanceViewerTab == null) {
            this.balanceViewerTab = balanceViewerTab;
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
        List<ICourse> filteredCourses = new Vector();
        /*
         * Only use courses, which have the filtered criteria.
         */
        if (!(filters == null)) {
            /*
             * There are existing filters.
             */
            if (!filters.getGroups().isEmpty()) {
                /*
                 * There are existing group filters.
                 */
                for (Object group : filters.getGroups()) {
                    for (ICourse course : courses) {
                        if (!filteredCourses.contains(course)) {
                            if (course.getGroup().equals(group)) {
                                /*
                                 * The course is not already in the filtered
                                 * courses and meets the group criteria.
                                 */
                                filteredCourses.add(course);
                            }
                        }
                    }
                }
            }
            if (!filters.getLecturers().isEmpty()) {
                /*
                 * There are existing lecture filters.
                 */
                for (Object lecturer : filters.getLecturers()) {
                    for (ICourse course : courses) {
                        if (!filteredCourses.contains(course)) {
                            if (course.getLecturer().equals(lecturer)) {
                                /*
                                 * The course is not already in the filtered
                                 * courses and meets the lecturer criteria.
                                 */
                                filteredCourses.add(course);
                            }
                        }
                    }
                }
            }
            if (!filters.getTargetAudiences().isEmpty()) {
                /*
                 * There are existing target audience filters.
                 */
                for (Object lecturer : filters.getTargetAudiences()) {
                    for (ICourse course : courses) {
                        if (!filteredCourses.contains(course)) {
                            if (course.getTargetAudience().equals(lecturer)) {
                                /*
                                 * The course is not already in the filtered
                                 * courses and meets the target audience
                                 * criteria.
                                 */
                                filteredCourses.add(course);
                            }
                        }
                    }
                }
            }
        } else {
            filteredCourses = courses;
        }
        // Check for every course, if it belongs to the given semester.
        for (ICourse course : filteredCourses) {
            if (course.getSemester().equals(semester)) {
                return true;
            }
        }
        return false;
    }

}
