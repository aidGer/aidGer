package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import de.aidger.model.AbstractModel;
import de.aidger.model.models.Course;

/**
 * The class represents the table model for the master data courses.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class CourseTableModel extends MasterDataTableModel {
    /**
     * Constructs the table model for courses.
     */
    public CourseTableModel() {
        super(new String[] { _("Description"), _("Semester"), _("Lecturer"),
                _("Number of Groups"), _("Target Audience"), _("Granted AWH"),
                _("Scope"), _("Part"), _("Group"), _("Remark"),
                _("Financial Category") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.MasterDataTableModel#refresh()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void refresh() {
        List<Course> courses = (new Course()).getAll();

        Iterator<Course> it = courses.iterator();
        while (it.hasNext()) {
            Course course = it.next();

            addRow(new Object[] { course.getDescription(),
                    course.getSemester(), course.getLecturer(),
                    course.getNumberOfGroups(), course.getTargetAudience(),
                    course.getUnqualifiedWorkingHours(), course.getScope(),
                    course.getPart(), course.getGroup(), course.getRemark(),
                    course.getFinancialCategoryId() });
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.MasterDataTableModel#getModel(int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public AbstractModel getModel(int i) {
        Vector row = (Vector) getDataVector().elementAt(i);

        Course course = new Course();
        course.setDescription((String) row.get(0));
        course.setSemester((String) row.get(1));
        course.setLecturer((String) row.get(2));
        course.setNumberOfGroups((Integer) row.get(3));
        course.setTargetAudience((String) row.get(4));
        course.setUnqualifiedWorkingHours((Double) row.get(5));
        course.setScope((String) row.get(6));
        course.setPart((Character) row.get(7));
        course.setGroup((String) row.get(8));
        course.setRemark((String) row.get(9));
        course.setFinancialCategoryId((Integer) row.get(10));

        return course;
    }
}
