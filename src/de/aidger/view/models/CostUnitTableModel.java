package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.List;

import de.aidger.model.AbstractModel;
import de.aidger.model.Runtime;
import de.aidger.model.models.CostUnit;

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

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.TableModel#getAllModels()
     */
    @Override
    @SuppressWarnings("unchecked")
    public void getAllModels() {
        List<CostUnit> costUnits = Runtime.getInstance().getDataXMLManager()
            .getCostUnitMap();

        for (CostUnit costUnit : costUnits) {
            models.add(costUnit);
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
        CostUnit costUnit = (CostUnit) model;

        return new Object[] { costUnit.getCostUnit(), costUnit.getFunds(),
                costUnit.getTokenDB() };
    }
}
