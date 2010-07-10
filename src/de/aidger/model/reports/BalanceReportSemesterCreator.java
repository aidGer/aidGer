package de.aidger.model.reports;

import java.util.List;
import java.util.ArrayList;

import de.aidger.model.models.Course;
import de.aidger.utils.reports.BalanceHelper;
import de.aidger.view.UI;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * This class manages the BalanceReportSemesterViewer which calls it. It adds
 * groups to the viewer's panel.
 * 
 * @author aidGer Team
 */
public class BalanceReportSemesterCreator {

    /**
     * The calculation method to be used for this balance report.
     */
    private int calculationMethod = 0;

    /**
     * The vector containing the balanceReportGroupCreators and the names of
     * their groups.
     */
    private final ArrayList<ArrayList<Object>> balanceReportGroupCreators = new ArrayList<ArrayList<Object>>();

    /**
     * The balance helper used to filter the courses in this creator.
     */
    private BalanceHelper balanceHelper = null;

    /**
     * Initializes this BalanceReportSemesterCreator and adds the groups of the
     * given semester.
     * 
     * @param semester
     *            The semester of which the groups shall be added.
     * @throws AdoHiveException
     */
    public BalanceReportSemesterCreator(String semester, BalanceFilter filters,
            int calculationMethod) throws AdoHiveException {
        this.calculationMethod = calculationMethod;
        balanceHelper = new BalanceHelper();
        addGroups(semester, filters);
    }

    /**
     * Adds the groups to the semester viewer.
     * 
     * @param semester
     *            The semester of which to add the groups.
     */
    private void addGroups(String semester, BalanceFilter filters) {
        List<Course> courses = null;
        try {
            courses = (new Course()).getCoursesBySemester(semester);
        } catch (AdoHiveException e) {
            UI.displayError(e.toString());
        }
        List<Course> filteredCourses = balanceHelper.filterCourses(courses,
            filters);
        for (ICourse course : filteredCourses) {
            if (course.getSemester().equals(semester)) {
                if (balanceReportGroupCreators.isEmpty()) {
                    /*
                     * If there are no groups in the semester yet, add a new
                     * one.
                     */
                    createGroup(course);
                } else {
                    boolean foundGroup = false;
                    for (int i = 0; i <= balanceReportGroupCreators.size() - 1; i++) {
                        if ((balanceReportGroupCreators.get(i)).get(1).equals(
                            course.getGroup())) {
                            /*
                             * If the course group already exists in this
                             * semester, add another row to it with this course.
                             */
                            ((BalanceReportGroupCreator) (balanceReportGroupCreators
                                .get(i)).get(0)).addCourse(course);
                            foundGroup = true;
                            break;
                        }
                    }
                    if (!foundGroup) {
                        /*
                         * If the course group wasn't in the semester yet, add a
                         * new group.
                         */
                        createGroup(course);
                    }
                }
            }
        }
    }

    /**
     * Creates a new group and adds it to the balanceReportGroupCreators vector.
     * Also adds the first course of the group to the group.
     * 
     * @param course
     *            The first course, which the group contains.
     */
    private void createGroup(ICourse course) {
        BalanceReportGroupCreator balanceReportGroupCreator = new BalanceReportGroupCreator(
            course, calculationMethod);
        balanceReportGroupCreators.add(new ArrayList<Object>());
        int i = balanceReportGroupCreators.size() - 1;
        /*
         * Add the group creator of this course's group as the first entry of
         * the vector.
         */
        (balanceReportGroupCreators.get(i)).add(balanceReportGroupCreator);
        /*
         * Add the name of the group as the second entry of the vector.
         */
        (balanceReportGroupCreators.get(i)).add(course.getGroup());
    }

    /**
     * Returns all of the group creators of this semester.
     * 
     * @return The group creators.
     */
    public ArrayList<ArrayList<Object>> getGroupCreators() {
        return balanceReportGroupCreators;
    }
}
