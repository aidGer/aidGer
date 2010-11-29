package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.List;

import de.aidger.model.AbstractModel;
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
        super(new String[] { _("ID"), _("Name"), _("Year"), _("Cost unit"),
                _("Budget Costs") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.TableModel#getAllModels()
     */
    @Override
    @SuppressWarnings("unchecked")
    public void getAllModels() {
        List<IFinancialCategory> fcs = null;

        try {
            fcs = (new FinancialCategory()).getAll();
        } catch (AdoHiveException e) {
            Logger.error(e.getMessage());
        }

        for (IFinancialCategory f : fcs) {
            FinancialCategory fc = new FinancialCategory(f);

            models.add(fc);
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
        FinancialCategory fc = (FinancialCategory) model;

        String funds = UICostUnit.valueOf(fc.getCostUnits()[0]);
        String budgetCosts = String.valueOf(fc.getBudgetCosts()[0]);

        for (int i = 1; i < fc.getCostUnits().length; i++) {
            funds += "\n" + UICostUnit.valueOf(fc.getCostUnits()[i]);
            budgetCosts += "\n" + fc.getBudgetCosts()[i];
        }

        return new Object[] { fc.getId(), fc.getName(), fc.getYear(), funds,
                budgetCosts };
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
        }

        return super.getColumnClass(column);
    }
}
