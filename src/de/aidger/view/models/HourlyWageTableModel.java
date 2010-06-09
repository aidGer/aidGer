package de.aidger.view.models;

import static de.aidger.utils.Translation._;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

import java.util.List;

import de.aidger.model.models.HourlyWage;
import de.aidger.view.forms.HourlyWageEditorForm.Qualification;
import de.unistuttgart.iste.se.adohive.model.IHourlyWage;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        super.refresh();

        List<IHourlyWage> hws = null;
        try {
            hws = (new HourlyWage()).getAll();
        } catch (AdoHiveException ex) {
            Logger.getLogger(HourlyWageTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (IHourlyWage hw : hws) {
            masterData.add(new HourlyWage(hw));

            addRow(new Object[] { Qualification.valueOf(hw.getQualification()),
                    hw.getMonth(), hw.getYear(), hw.getWage() });
        }
    }
}
