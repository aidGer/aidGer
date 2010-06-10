package de.aidger.model.reports;

import java.util.List;

import javax.swing.JPanel;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Employment;
import de.aidger.view.reports.BalanceReportGroupViewer;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.ICourse;
import de.unistuttgart.iste.se.adohive.model.IEmployment;

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
    private BalanceReportGroupViewer balanceReportGroupViewer = null;

    /**
     * Initializes this BalanceReportGroupCreator and adds the first course.
     * 
     * @param course
     *            The course to be added.
     */
    public BalanceReportGroupCreator(ICourse course) {
        if (balanceReportGroupViewer == null) {
            balanceReportGroupViewer = new BalanceReportGroupViewer(course
                .getDescription());

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
        BalanceCourse balanceCourse = new BalanceCourse();
        balanceCourse.setTitle(course.getDescription());
        balanceCourse.setPart(course.getPart());
        balanceCourse.setLecturer(course.getLecturer());
        balanceCourse.setTargetAudience(course.getTargetAudience());
        double plannedAWS = 0;
        double basicAWS = course.getNumberOfGroups()
                * course.getUnqualifiedWorkingHours();
        balanceCourse.setBasicAWS(basicAWS);
        int studentFees = 0;
        int resources = 0;
        List<IEmployment> employments = null;
        List<IAssistant> assistants = null;
        try {
            employments = (new Employment()).getAll();
            assistants = (new Assistant()).getAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (IEmployment employment : employments) {
            if (employment.getCourseId() == course.getId()) {
                //TODO find out int for student fee funds
                if (employment.getFunds() == 0) {
                    for (IAssistant assistant : assistants) {
                        if (employment.getAssistantId() == assistant.getId()) {
                            //TODO change to get correct hourly wage
                            studentFees = studentFees
                                    + (int) (10.0 * employment.getHourCount() * 1.28);
                        }
                    }
                    //TODO find out int for student fee funds
                } else if (employment.getFunds() == 1) {
                    for (IAssistant assistant : assistants) {
                        if (employment.getAssistantId() == assistant.getId()) {
                            //TODO change to get correct hourly wage
                            resources = resources
                                    + (int) (10.0 * employment.getHourCount() * 1.28);
                        }
                    }
                }
                //TODO find out correct calculation
                plannedAWS = plannedAWS + employment.getHourCount();
            }
        }
        balanceCourse.setPlannedAWS(plannedAWS);
        balanceCourse.setStudentFees(studentFees);
        balanceCourse.setResources(resources);
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
