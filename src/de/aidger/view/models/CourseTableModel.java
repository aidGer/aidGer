package de.aidger.view.models;

import static de.aidger.utils.Translation._;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

import java.util.List;

import de.aidger.model.models.Course;
import de.unistuttgart.iste.se.adohive.model.ICourse;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                _("Advisor"), _("Number of Groups"), _("Target Audience"),
                _("Granted AWH"), _("Scope"), _("Part"), _("Group"),
                _("Remark"), _("Financial Category"), _("ID") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.MasterDataTableModel#refresh()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void refresh() {
        super.refresh();

        List<ICourse> courses = null;
        try {
            courses = (new Course()).getAll();
        } catch (AdoHiveException ex) {
            Logger.getLogger(CourseTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (ICourse course : courses) {
            masterData.add(new Course(course));

            addRow(new Object[] { course.getDescription(),
                    course.getSemester(), course.getLecturer(),
                    course.getAdvisor(), course.getNumberOfGroups(),
                    course.getTargetAudience(),
                    course.getUnqualifiedWorkingHours(), course.getScope(),
                    course.getPart(), course.getGroup(), course.getRemark(),
                    course.getFinancialCategoryId(), course.getId() });
        }
    }
}
