package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Iterator;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import de.aidger.model.models.Course;

/**
 * 
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class CourseTableModel extends DefaultTableModel {

    /**
     * The names of the columns.
     */
    private final String[] columnNames = { _("Description"), _("Semester"),
            _("Lecturer"), _("Number of Groups"), _("Target Audience"),
            _("Granted AWH"), _("Scope"), _("Part"), _("Group"), _("Remark"),
            _("Financial Category") };

    /**
     * Constructs the table model for courses.
     */
    public CourseTableModel() {
        setColumnIdentifiers(columnNames);

        refresh();
    }

    /**
     * Refreshs the whole table.
     */
    @SuppressWarnings("unchecked")
    public void refresh() {
        List<Course> courses = (new Course()).getAll();

        Iterator<Course> it = courses.iterator();
        while (it.hasNext()) {
            Course course = it.next();
            addRow(new Object[] { course.getDescription(),
                    course.getSemester(), course.getNumberOfGroups(),
                    course.getTargetAudience(),
                    course.getUnqualifiedWorkingHours(), course.getScope(),
                    course.getPart(), course.getGroup(), course.getRemark(),
                    course.getFinancialCategoryId() });
        }
    }
}
