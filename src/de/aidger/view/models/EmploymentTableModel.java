package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.aidger.model.AbstractModel;
import de.aidger.model.Runtime;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.CostUnit;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.utils.Logger;
import de.aidger.view.forms.HourlyWageEditorForm.Qualification;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.IContract;
import de.unistuttgart.iste.se.adohive.model.ICourse;
import de.unistuttgart.iste.se.adohive.model.IEmployment;

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
     * @see de.aidger.view.models.TableModel#getAllModels()
     */
    @Override
    @SuppressWarnings("unchecked")
    public void getAllModels() {
        List<IEmployment> employments = null;

        try {
            employments = (new Employment()).getAll();
        } catch (AdoHiveException e) {
            Logger.error(e.getMessage());
        }

        for (IEmployment e : employments) {
            Employment employment = new Employment(e);

            models.add(employment);
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
        Employment employment = (Employment) model;

        try {
            IAssistant assistant = (new Assistant()).getById(employment
                .getAssistantId());
            ICourse course = (new Course()).getById(employment.getCourseId());
            IContract contract = (new Contract()).getById(employment
                .getContractId());

            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.set(Calendar.MONTH, employment.getMonth() - 1);
            cal.set(Calendar.YEAR, employment.getYear());

            CostUnit costUnit = Runtime.getInstance().getDataXMLManager()
                .fromTokenDB(employment.getFunds());

            return new Object[] { employment.getId(),
                    new UIAssistant(assistant), new UICourse(course),
                    new UIContract(contract), cal.getTime(),
                    employment.getHourCount(),
                    costUnit == null ? employment.getCostUnit() : costUnit,
                    UICostUnit.valueOf(employment.getCostUnit()),
                    Qualification.valueOf(employment.getQualification()),
                    employment.getRemark() };
        } catch (AdoHiveException e1) {
            Logger.error(e1.getMessage());

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
        if (column == 0) {
            return Integer.class;
        } else if (column == 4) {
            return Date.class;
        } else if (column == 5) {
            return Double.class;
        }

        return super.getColumnClass(column);
    }
}
