package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Iterator;
import java.util.List;

import de.aidger.model.AbstractModel;
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
                _("Wage") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.MasterDataTableModel#refresh()
     */
    @Override
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

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.MasterDataTableModel#getModel(int)
     */
    @Override
    @SuppressWarnings("unchecked")
    public AbstractModel getModel(int i) {
        return null;
    }
}
