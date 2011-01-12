package de.aidger.view.models;

import java.text.DecimalFormat;

import de.aidger.model.models.CostUnit;
import de.aidger.view.tabs.ViewerTab.DataType;

/**
 * The UI cost unit is used for prettier rendering of the model.
 * 
 * @author aidGer Team
 */
public class UICostUnit extends CostUnit implements UIModel {

    /**
     * Only one instance for all cost units because of performance problems.
     */
    private static final DecimalFormat format = new DecimalFormat("00000000");

    /**
     * Initializes the CostUnit class.
     */
    public UICostUnit() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.AbstractModel#toString()
     */
    @Override
    public String toString() {
        return getFunds() + " (" + getTokenDB() + ")";
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.UIModel#getDataType()
     */
    @Override
    public DataType getDataType() {
        return DataType.CostUnit;
    }

    /**
     * Formats an integer as cost unit value.
     * 
     * @param costUnit
     *            the cost unit as integer
     * @return the cost unit as string
     */
    public static String valueOf(Integer costUnit) {
        return format.format(costUnit);
    }
}
