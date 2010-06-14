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
    public BalanceReportSemesterCreator(String semester, BalanceFilter filters)
            throws AdoHiveException {
        if (balanceReportSemesterViewer == null) {
            balanceReportSemesterViewer = new BalanceReportSemesterPanel(
                semester);

        }
        addGroups(semester, filters);
    }

    /**
     * Adds the groups to the semester viewer.
     * 
     * @param semester
     *            The semester of which to add the groups.
     */
    private void addGroups(String semester, BalanceFilter filters) {
        List<ICourse> courses = null;
        try {
            courses = (new Course()).getAll();
        } catch (AdoHiveException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<ICourse> filteredOnceCourses = new Vector();
        List<ICourse> filteredTwiceCourses = new Vector();
        List<ICourse> filteredTriceCourses = new Vector();
        /*
         * Only use courses, which have the filtered criteria.
         */
        if (!(filters == null)) {
            boolean filterExists = false;
            /*
             * There are existing filters.
             */
            if (!filters.getGroups().isEmpty()) {
                /*
                 * There are existing group filters.
                 */
                for (Object group : filters.getGroups()) {
                    for (ICourse course : courses) {
                        if (!filteredOnceCourses.contains(course)
                                && course.getGroup().equals(group)) {
                            /*
                             * The course is not already in the filtered courses
                             * and meets the group criteria.
                             */
                            filteredOnceCourses.add(course);
                        }
                    }
                }
                filterExists = true;
            } else {
                filteredOnceCourses = courses;
            }
            if (!filters.getLecturers().isEmpty()) {
                /*
                 * There are existing lecture filters.
                 */
                for (Object lecturer : filters.getLecturers()) {
                    for (ICourse course : filteredOnceCourses) {
                        if (!filteredTwiceCourses.contains(course)
                                && course.getLecturer().equals(lecturer)) {
                            /*
                             * The course is not already in the filtered courses
                             * and meets the lecturer criteria.
                             */
                            filteredTwiceCourses.add(course);
                        }
                    }
                }
                filterExists = true;
            } else {
                filteredTwiceCourses = filteredOnceCourses;
            }
            if (!filters.getTargetAudiences().isEmpty()) {
                /*
                 * There are existing target audience filters.
                 */
                for (Object lecturer : filters.getTargetAudiences()) {
                    for (ICourse course : filteredTwiceCourses) {
                        if (!filteredTriceCourses.contains(course)
                                && course.getTargetAudience().equals(lecturer)) {
                            /*
                             * The course is not already in the filtered courses
                             * and meets the target audience criteria.
                             */
                            filteredTriceCourses.add(course);
                        }
                    }
                }
                filterExists = true;
            } else {
                filteredTriceCourses = filteredTwiceCourses;
            }
            if (!filterExists) {
                filteredTriceCourses = courses;
            }
        } else {
            /*
             * If there are no filters, use the normal courses.
             */
            filteredTriceCourses = courses;
        }
        for (ICourse course : filteredTriceCourses) {
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
                        if (((Vector) balanceReportGroupCreators.get(i)).get(1)
                            .equals(course.getGroup())) {
                            /*
                             * If the course group already exists in this
                             * semester, add another row to it with this course.
                             */
                            ((BalanceReportGroupCreator) ((Vector) balanceReportGroupCreators
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
     * 
     * @param course
     *            The first course, which the group contains.
     */
    private void createGroup(ICourse course) {
        BalanceReportGroupCreator balanceReportGroupCreator = new BalanceReportGroupCreator(
            course);
        balanceReportGroupCreators.add(new Vector<Object>());
        int i = balanceReportGroupCreators.size() - 1;
        /*
         * Add the group creator of this course's group as the first entry of
         * the vector.
         */
        ((Vector) balanceReportGroupCreators.get(i))
            .add(balanceReportGroupCreator);
        /*
         * Add the name of the group as the second entry of the vector.
         */
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
