package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.List;

import de.aidger.model.models.Employment;
import de.aidger.utils.Logger;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IEmployment;

/**
 * The class represents the table model for the employments data.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class EmploymentTableModel extends TableModel {
    /**
     * Constructs the table model for assistants.
     */
    public EmploymentTableModel() {
        super(new String[] { _("Assistant"), _("Course"), _("Contract"),
                _("Month"), _("Year"), _("Hour count"), _("Cost unit"),
                _("Qualification"), _("Remark"), _("ID") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.TableModel#refresh()
     */
    @Override
    @SuppressWarnings("unchecked")
    public void refresh() {
        super.refresh();

        List<IEmployment> employments = null;

        try {
            employments = (new Employment()).getAll();
        } catch (AdoHiveException e) {
            Logger.error(e.getMessage());
        }

        for (IEmployment e : employments) {
            Employment employment = new Employment(e);
            employment.addObserver(this);

            models.add(employment);

            addRow(new Object[] { employment.getAssistantId(),
                    employment.getContractId(), employment.getContractId(),
                    employment.getMonth(), employment.getYear(),
                    employment.getHourCount(), employment.getCostUnit(),
                    employment.getQualification(), employment.getRemark(),
                    employment.getId() });
        }
    }
}
