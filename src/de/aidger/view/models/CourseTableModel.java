package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.List;

import de.aidger.model.AbstractModel;
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
                _("AWH per group"), _("Scope"), _("Part"), _("Group"),
                _("Remark"), _("Financial Category"), _("ID") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.TableModel#getAllModels()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void getAllModels() {
        List<ICourse> courses = null;

        try {
            courses = (new Course()).getAll();
        } catch (AdoHiveException e) {
            Logger.error(e.getMessage());
        }

        for (ICourse c : courses) {
            Course course = new Course(c);

            models.add(course);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @seede.aidger.view.models.TableModel#convertModelToRow(de.aidger.model.
     * AbstractModel)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Object[] convertModelToRow(AbstractModel model) {
        Course course = (Course) model;

        try {
            IFinancialCategory fc = (new FinancialCategory()).getById(course
                .getFinancialCategoryId());

            return new Object[] { course.getDescription(),
                    course.getSemester(), course.getLecturer(),
                    course.getAdvisor(), course.getNumberOfGroups(),
                    course.getTargetAudience(),
                    course.getUnqualifiedWorkingHours(), course.getScope(),
                    course.getPart(), course.getGroup(), course.getRemark(),
                    new UIFinancialCategory(fc), course.getId() };
        } catch (AdoHiveException e) {
            Logger.error(e.getMessage());

            return new Object[] {};
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int column) {
        // sort specific columns properly
        if (column == 4 || column == 12) {
            return Integer.class;
        } else if (column == 6) {
            return Double.class;
        }

        return super.getColumnClass(column);
    }
}
