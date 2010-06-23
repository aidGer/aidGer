package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Calendar;
import java.util.List;

import de.aidger.model.AbstractModel;
import de.aidger.model.models.HourlyWage;
import de.aidger.utils.Logger;
import de.aidger.view.forms.HourlyWageEditorForm.Qualification;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IHourlyWage;

/**
 * The class represents the table model for the master data hourly wages.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class HourlyWageTableModel extends TableModel {
    /**
     * Constructs the table model for hourly wages.
     */
    public HourlyWageTableModel() {
        super(new String[] { _("Qualification"), _("Date"), _("Wage") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.TableModel#getAllModels()
     */
    @Override
    @SuppressWarnings("unchecked")
    public void getAllModels() {
        List<IHourlyWage> hws = null;

        try {
            hws = (new HourlyWage()).getAll();
        } catch (AdoHiveException e) {
            Logger.error(e.getMessage());
        }

        for (IHourlyWage h : hws) {
            HourlyWage hw = new HourlyWage(h);

            models.add(hw);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.aidger.view.models.TableModel#addRow(de.aidger.model.AbstractModel)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Object[] convertModelToRow(AbstractModel model) {
        HourlyWage hw = (HourlyWage) model;

        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.MONTH, hw.getMonth() - 1);
        cal.set(Calendar.YEAR, hw.getYear());

        return new Object[] { Qualification.valueOf(hw.getQualification()),
                cal.getTime(), hw.getWage() };
    }
}
