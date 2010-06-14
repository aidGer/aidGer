package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.List;

import de.aidger.model.models.Course;
import de.aidger.model.models.FinancialCategory;
import de.aidger.utils.Logger;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.ICourse;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;

/**
 * The class represents the table model for the master data courses.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class CourseTableModel extends TableModel {
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
        } catch (AdoHiveException e) {
            Logger.error(e.getMessage());
        }

        for (ICourse c : courses) {
            Course course = new Course(c);
            course.addObserver(this);

            models.add(course);

            try {
                IFinancialCategory fc = (new FinancialCategory())
                    .getById(course.getFinancialCategoryId());

                addRow(new Object[] { course.getDescription(),
                        course.getSemester(), course.getLecturer(),
                        course.getAdvisor(), course.getNumberOfGroups(),
                        course.getTargetAudience(),
                        course.getUnqualifiedWorkingHours(), course.getScope(),
                        course.getPart(), course.getGroup(),
                        course.getRemark(), new FinancialCategory(fc) {
                            @Override
                            public String toString() {
                                return getName() + " (" + getYear() + ")";
                            }
                        }, course.getId() });
            } catch (AdoHiveException e) {
                Logger.error(e.getMessage());
            }
        }
    }
}
