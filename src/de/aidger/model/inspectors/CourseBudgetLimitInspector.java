package de.aidger.model.inspectors;

import static de.aidger.controller.actions.EditorSaveAction.round;
import static de.aidger.utils.Translation._;

import java.text.MessageFormat;

import de.aidger.model.budgets.CourseBudget;
import de.aidger.model.models.Course;
import de.aidger.view.models.UICourse;

/**
 * An inspector for the budget limit in h for courses.
 * 
 * @author aidGer Team
 */
public class CourseBudgetLimitInspector extends Inspector {

    /**
     * The course to be checked.
     */
    private final Course course;

    /**
     * Creates a course budget limit inspector.
     * 
     * @param course
     *            the course to be checked
     */
    public CourseBudgetLimitInspector(Course course) {
        this.course = course;
    }

    /*
     * Checks the budget limit in h for the given course.
     */
    @Override
    public void check() {
        CourseBudget courseBudget = new CourseBudget(course);

        if (courseBudget.getBookedBudget() > courseBudget.getTotalBudget()) {
            result = MessageFormat
                .format(
                    _("The budget limit for course {0} is exceeded ({1}h / {2}h)."),
                    new Object[] { (new UICourse(course)).toString(),
                            round(courseBudget.getBookedBudget(), 2),
                            round(courseBudget.getTotalBudget(), 2) });
        }
    }

}
