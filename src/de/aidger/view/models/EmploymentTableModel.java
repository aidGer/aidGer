package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Calendar;
import java.util.Date;

import de.aidger.model.AbstractModel;
import de.aidger.model.Runtime;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.CostUnit;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.view.forms.HourlyWageEditorForm.Qualification;
import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.IContract;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * The class represents the table model for the employments data.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class EmploymentTableModel extends TableModel {
    /**
     * Constructs the table model for employments.
     */
    public EmploymentTableModel() {
        super(new String[] { _("ID"), _("Assistant"), _("Course"),
                _("Contract"), _("Date"), _("Hour count"), _("Funds"),
                _("Cost unit"), _("Qualification"), _("Remark") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int column) {
        // sort specific columns properly
        if (column == 0) {
            return Integer.class;
        } else if (column == 4) {
            return Date.class;
        } else if (column == 5) {
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
            Employment employment = (Employment) model;
            switch (row) {
                case 0:
                    return employment.getId();
                case 1:
                    IAssistant assistant = (new Assistant()).getById(employment.getAssistantId());
                    return new UIAssistant(assistant);
                case 2:
                    ICourse course = (new Course()).getById(employment.getCourseId());
                    return new UICourse(course);
                case 3:
                    IContract contract = (new Contract()).getById(employment.getContractId());
                    return new UIContract(contract);
                case 4:
                    Calendar cal = Calendar.getInstance();
                    cal.clear();
                    cal.set(Calendar.MONTH, employment.getMonth() - 1);
                    cal.set(Calendar.YEAR, employment.getYear());
                    return cal.getTime();
                case 5: return employment.getHourCount();
                case 6: 
                    CostUnit costUnit = Runtime.getInstance().getDataXMLManager()
                            .fromTokenDB(employment.getFunds());
                    return costUnit == null ? employment.getCostUnit() : costUnit;
                case 7: return UICostUnit.valueOf(employment.getCostUnit());
                case 8: return Qualification.valueOf(employment.getQualification());
                case 9: return employment.getRemark();
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
            return new Employment(AdoHiveController.getInstance().getEmploymentManager().get(idx));
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
            return (new Employment()).size();
        } catch (AdoHiveException ex) {
            return 0;
        }
    }
}
