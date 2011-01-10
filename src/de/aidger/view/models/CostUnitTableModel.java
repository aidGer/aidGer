package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import de.aidger.model.AbstractModel;
import de.aidger.model.Runtime;
import de.aidger.model.models.CostUnit;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import java.util.List;

/**
 * The class represents the table model for the contracts data.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class CostUnitTableModel extends TableModel {
    /**
     * Constructs the table model for contracts.
     */
    public CostUnitTableModel() {
        super(new String[] { _("Cost unit"), _("Funds"), _("Database token") });
    }

    /**
     * (non-Javadoc)
     *
     * @see de.aidger.view.models.TableModel#getRowValue(de.aidger.model.AbstractModel, int)
     */
    @Override
    protected Object getRowValue(AbstractModel model, int row) {
        CostUnit costunit = (CostUnit) model;
        switch (row) {
            case 0: return costunit.getCostUnit();
            case 1: return costunit.getFunds();
            case 2: return costunit.getTokenDB();
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
        return Runtime.getInstance().getDataXMLManager().getCostUnitMap().get(idx);
    }

    /**
     * (non-javadoc)
     *
     * @see de.aidger.view.models.TableModel#getModels()
     */
    protected List<AbstractModel> getModels() {
        List<CostUnit> lst = Runtime.getInstance().getDataXMLManager().getCostUnitMap();

        return (List) lst;
    }

    /**
     * (non-Javadoc)
     *
     * @see javax.swing.table.AbstractTableModel#getRowCount()
     */
    public int getRowCount() {
        try {
            return (new CostUnit()).size();
        } catch (AdoHiveException ex) {
            return 0;
        }
    }
}
