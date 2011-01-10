package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Calendar;
import java.util.Date;

import de.aidger.model.AbstractModel;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.view.forms.ContractEditorForm.ContractType;
import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.IContract;
import java.util.ArrayList;
import java.util.List;

/**
 * The class represents the table model for the contracts data.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ContractTableModel extends TableModel {
    /**
     * Constructs the table model for contracts.
     */
    public ContractTableModel() {
        super(new String[] { _("ID"), _("Assistant"), _("Completion date"),
                _("Confirmation date"), _("Start date"), _("End date"),
                _("Type"), _("Delegation") });
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
        } else if (column == 2 || column == 3 || column == 4 || column == 5) {
            return Date.class;
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
            Contract contract = (Contract) model;
            switch (row) {
                case 0: return contract.getId();
                case 1:
                    IAssistant assistant = (new Assistant()).getById(contract.getAssistantId());
                    return new UIAssistant(assistant);
                case 2: return contract.getCompletionDate();
                case 3:
                    Date confirmationDate = contract.getConfirmationDate();
                    Calendar cal = Calendar.getInstance();
                    cal.clear();
                    return confirmationDate == null ? cal.getTime() : confirmationDate;
                case 4: return contract.getStartDate();
                case 5: return contract.getEndDate();
                case 6: return ContractType.valueOf(contract.getType());
                case 7: return contract.isDelegation();
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
            return new Contract(AdoHiveController.getInstance().getContractManager().get(idx));
        } catch (AdoHiveException ex) {
            return null;
        }
    }

    /**
     * (non-javadoc)
     *
     * @see de.aidger.view.models.TableModel#getModels()
     */
    protected List<AbstractModel> getModels() {
        List<AbstractModel> ret = new ArrayList<AbstractModel>();
        try {
            List<IContract> lst = (new Contract()).getAll();
            for (IContract e : lst) {
                ret.add(new Contract(e));
            }
        } catch (AdoHiveException ex) {
        }

        return ret;
    }

    /**
     * (non-Javadoc)
     *
     * @see javax.swing.table.AbstractTableModel#getRowCount()
     */
    public int getRowCount() {
        try {
            return (new Contract()).size();
        } catch (AdoHiveException ex) {
            return 0;
        }
    }
}
