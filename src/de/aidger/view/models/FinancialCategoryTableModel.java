package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Iterator;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import de.aidger.model.models.FinancialCategory;

/**
 * The class represents the table model for the master data financial
 * categories.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class FinancialCategoryTableModel extends DefaultTableModel {

    /**
     * The names of the columns.
     */
    private final String[] columnNames = { _("Name"), _("Budget Costs"),
            _("Fonds"), _("Year") };

    /**
     * Constructs the table model for courses.
     */
    public FinancialCategoryTableModel() {
        setColumnIdentifiers(columnNames);

        refresh();
    }

    /**
     * Refreshs the whole table.
     */
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
}
