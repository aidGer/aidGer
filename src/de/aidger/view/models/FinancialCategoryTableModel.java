package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.List;

import de.aidger.model.models.FinancialCategory;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;

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
        masterData.clear();

        List<IFinancialCategory> fcs = (new FinancialCategory()).getAll();

        for (IFinancialCategory fc : fcs) {
            masterData.add(new FinancialCategory(fc));

            addRow(new Object[] { fc.getName(), fc.getBudgetCosts(),
                    fc.getFunds(), fc.getYear(), fc.getId() });
        }
    }
}
