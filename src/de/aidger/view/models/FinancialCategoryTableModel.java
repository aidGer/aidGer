package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import de.aidger.model.AbstractModel;
import de.aidger.model.models.FinancialCategory;

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
        super(new String[] { _("Name"), _("Budget Costs"), _("Fonds"),
                _("Year") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.MasterDataTableModel#refresh()
     */
    @Override
    @SuppressWarnings("unchecked")
    public void refresh() {
        List<FinancialCategory> fcs = (new FinancialCategory()).getAll();

        Iterator<FinancialCategory> it = fcs.iterator();
        while (it.hasNext()) {
            FinancialCategory fc = it.next();

            addRow(new Object[] { fc.getName(), fc.getBugdetCosts(),
                    fc.getFonds(), fc.getYear() });
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.MasterDataTableModel#getModel(int)
     */
    @Override
    @SuppressWarnings("unchecked")
    public AbstractModel getModel(int i) {
        Vector row = (Vector) getDataVector().elementAt(i);

        FinancialCategory fc = new FinancialCategory();
        fc.setName((String) row.get(0));
        fc.setBugdetCosts((int[]) row.get(1));
        fc.setFonds((int[]) row.get(2));
        fc.setYear((Short) row.get(3));

        return fc;
    }
}
