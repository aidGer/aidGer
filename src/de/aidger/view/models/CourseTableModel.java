package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import de.aidger.model.AbstractModel;
import de.aidger.model.models.Course;
import de.aidger.model.models.FinancialCategory;
import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
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
        super(new String[] { _("ID"), _("Description"), _("Semester"),
                _("Lecturer"), _("Advisor"), _("Number of Groups"),
                _("Target Audience"), _("AWH per group"), _("Scope"),
                _("Part"), _("Group"), _("Remark"), _("Financial Category") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int column) {
        // sort specific columns properly
        if (column == 0 || column == 5) {
            return Integer.class;
        } else if (column == 7) {
            return Double.class;
        }

        return super.getColumnClass(column);
    }

    /**
     * (non-Javadoc)
     *
     * @see de.aidger.view.models.TableModel#getRowValue(de.aidger.model.AbstractModel, int)
     */
    @Override
    protected Object getRowValue(AbstractModel model, int row) {
        try {
            Course course = (Course) model;
            switch (row) {
                case 0: return course.getId();
                case 1: return course.getDescription();
                case 2: return course.getSemester();
                case 3: return course.getLecturer();
                case 4: return course.getAdvisor();
                case 5: return course.getNumberOfGroups();
                case 6: return course.getTargetAudience();
                case 7: return course.getUnqualifiedWorkingHours();
                case 8: return course.getScope();
                case 9: return course.getPart();
                case 10: return course.getGroup();
                case 11: return course.getRemark();
                case 12:
                    IFinancialCategory fc = (new FinancialCategory())
                            .getById(course.getFinancialCategoryId());
                    return new UIFinancialCategory(fc);
            }
        } catch (AdoHiveException ex) {
        }
        return null;
    }

    /**
     * (non-Javadoc)
     *
     * @see de.aidger.view.models.TableModel#getModelFromDB(int)
     */
    @Override
    protected AbstractModel getModelFromDB(int idx) {
        try {
            return new Course(AdoHiveController.getInstance().getCourseManager().get(idx));
        } catch (AdoHiveException ex) {
            return null;
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see javax.swing.table.AbstractTableModel#getRowCount()
     */
    public int getRowCount() {
        try {
            int count = (new Course()).size();
            System.out.println(count);
            return (new Course()).size();
        } catch (AdoHiveException ex) {
            System.out.println("AHHHHH");
            System.out.println(ex);
            return 0;
        }
    }
}
