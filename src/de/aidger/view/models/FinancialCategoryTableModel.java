package de.aidger.view.models;

import static de.aidger.utils.Translation._;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

import java.util.List;

import de.aidger.model.models.FinancialCategory;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class represents the table model for the master data financial
 * categories.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class FinancialCategoryTableModel extends MasterDataTableModel {
    /**
     * Constructs the table model for financial categories.
     */
    public FinancialCategoryTableModel() {
        super(new String[] { _("Name"), _("Budget Costs"), _("Funds"),
                _("Year"), _("ID") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.MasterDataTableModel#refresh()
     */
    @Override
    @SuppressWarnings("unchecked")
    public void refresh() {
        super.refresh();

        List<IFinancialCategory> fcs = null;
        try {
            fcs = (new FinancialCategory()).getAll();
        } catch (AdoHiveException ex) {
            Logger.getLogger(FinancialCategoryTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (IFinancialCategory fc : fcs) {
            for (int i = 0; i < fc.getFunds().length; i++) {
                masterData.add(new FinancialCategory(fc));

                addRow(new Object[] { fc.getName(), fc.getBudgetCosts()[i],
                        fc.getFunds()[i], fc.getYear(), fc.getId() });
            }
        }
    }
}
