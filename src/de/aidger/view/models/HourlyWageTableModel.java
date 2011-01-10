package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Calendar;
import java.util.Date;

import de.aidger.model.AbstractModel;
import de.aidger.model.models.HourlyWage;
import de.aidger.view.forms.HourlyWageEditorForm.Qualification;
import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IHourlyWage;
import java.util.ArrayList;
import java.util.List;

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
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int column) {
        // sort specific columns properly
        if (column == 1) {
            return Date.class;
        } else if (column == 2) {
            return Double.class;
        }

        return super.getColumnClass(column);
    }

    /**
     * (non-Javadoc)
     *
     * @see de.aidger.view.models.TableModel#getRowValue(de.aidger.model.AbstractModel, int)
     */
    @Override
    protected Object getRowValue(AbstractModel model, int row) {
        HourlyWage wage = (HourlyWage) model;
        switch (row) {
            case 0: return Qualification.valueOf(wage.getQualification());
            case 1:
                Calendar cal = Calendar.getInstance();
                cal.clear();
                cal.set(Calendar.MONTH, wage.getMonth() - 1);
                cal.set(Calendar.YEAR, wage.getYear());
                return cal.getTime();
            case 2: return wage.getWage();
        }
        return null;
    }

    /**
     * (non-Javadoc)
     *
     * @see de.aidger.view.models.TableModel#getModelFromDB(int)
     */
    @Override
    protected AbstractModel getModelFromDB(int idx) {
        try {
            return new HourlyWage(AdoHiveController.getInstance().getHourlyWageManager().get(idx));
        } catch (AdoHiveException ex) {
            return null;
        }
    }

    /**
     * (non-javadoc)
     *
     * @see de.aidger.view.models.TableModel#getModels()
     */
    protected List<AbstractModel> getModels() {
        List<AbstractModel> ret = new ArrayList<AbstractModel>();
        try {
            List<IHourlyWage> lst = (new HourlyWage()).getAll();
            for (IHourlyWage e : lst) {
                ret.add(new HourlyWage(e));
            }
        } catch (AdoHiveException ex) {
        }

        return ret;
    }

    /**
     * (non-Javadoc)
     *
     * @see javax.swing.table.AbstractTableModel#getRowCount()
     */
    public int getRowCount() {
        try {
            return (new HourlyWage()).size();
        } catch (AdoHiveException ex) {
            return 0;
        }
    }
}
