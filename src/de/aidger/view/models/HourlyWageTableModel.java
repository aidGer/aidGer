package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Iterator;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import de.aidger.model.models.HourlyWage;

/**
 * The class represents the table model for the master data hourly wages.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class HourlyWageTableModel extends DefaultTableModel {

    /**
     * The names of the columns.
     */
    private final String[] columnNames = { _("Qualification"), _("Month"),
            _("Year"), _("Wage") };

    /**
     * Constructs the table model for courses.
     */
    public HourlyWageTableModel() {
        setColumnIdentifiers(columnNames);

        refresh();
    }

    /**
     * Refreshs the whole table.
     */
    @SuppressWarnings("unchecked")
    public void refresh() {
        List<HourlyWage> hws = (new HourlyWage()).getAll();

        Iterator<HourlyWage> it = hws.iterator();
        while (it.hasNext()) {
            HourlyWage hw = it.next();

            addRow(new Object[] { hw.getQualification(), hw.getMonth(),
                    hw.getYear(), hw.getWage() });
        }
    }
}
