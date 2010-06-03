package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Iterator;
import java.util.List;

import de.aidger.model.models.HourlyWage;

/**
 * The class represents the table model for the master data hourly wages.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class HourlyWageTableModel extends MasterDataTableModel {
    /**
     * Constructs the table model for hourly wages.
     */
    public HourlyWageTableModel() {
        super(new String[] { _("Qualification"), _("Month"), _("Year"),
                _("Wage"), _("ID") });
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

        List<HourlyWage> hws = (new HourlyWage()).getAll();

        Iterator<HourlyWage> it = hws.iterator();
        while (it.hasNext()) {
            HourlyWage hw = it.next();

            masterData.add(hw);

            addRow(new Object[] { hw.getQualification(), hw.getMonth(),
                    hw.getYear(), hw.getWage(), hw.getId() });
        }
    }
}
