package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.List;

import de.aidger.model.AbstractModel;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
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
        super(new String[] { _("Assistant"), _("Course"), _("Contract"),
                _("Month"), _("Year"), _("Hour count"), _("Cost unit"),
                _("Qualification"), _("Remark"), _("ID") });
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

            return new Object[] {
                    new Assistant(assistant) {
                        @Override
                        public String toString() {
                            return getFirstName() + " " + getLastName();
                        }
                    },
                    new Course(course) {
                        @Override
                        public String toString() {
                            return getDescription() + " (" + getSemester()
                                    + ", " + getLecturer() + ")";
                        }
                    },
                    new Contract(contract) {
                        @Override
                        public String toString() {
                            return getType() + " (" + getStartDate() + " - "
                                    + getEndDate() + ")";
                        }
                    }, employment.getMonth(), employment.getYear(),
                    employment.getHourCount(), employment.getCostUnit(),
                    Qualification.valueOf(employment.getQualification()),
                    employment.getRemark(), employment.getId() };
        } catch (AdoHiveException e1) {
            Logger.error(e1.getMessage());

            return new Object[] {};
        }

    }
}
