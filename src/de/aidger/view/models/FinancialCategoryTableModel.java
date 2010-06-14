package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.List;

import de.aidger.model.models.FinancialCategory;
import de.aidger.utils.Logger;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;

/**
 * The class represents the table model for the master data financial
 * categories.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class FinancialCategoryTableModel extends TableModel {
    /**
     * Constructs the table model for financial categories.
     */
    public FinancialCategoryTableModel() {
        super(new String[] { _("Name"), _("Year"), _("Funds"),
                _("Budget Costs"), _("ID") });
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
        } catch (AdoHiveException e) {
            Logger.error(e.getMessage());
        }

        for (IFinancialCategory f : fcs) {
            FinancialCategory fc = new FinancialCategory(f);
            fc.addObserver(this);

            String funds = String.valueOf(fc.getFunds()[0]);
            String budgetCosts = String.valueOf(fc.getBudgetCosts()[0]);

            models.add(fc);

            for (int i = 1; i < fc.getFunds().length; i++) {
                funds += "\n" + fc.getFunds()[i];
                budgetCosts += "\n" + fc.getBudgetCosts()[i];
            }

            addRow(new Object[] { fc.getName(), fc.getYear(), funds,
                    budgetCosts, fc.getId() });
        }
    }
}
