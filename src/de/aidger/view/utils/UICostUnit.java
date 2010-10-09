package de.aidger.view.utils;

import java.text.DecimalFormat;

/**
 * This class formats an integer as cost unit value.
 * 
 * @author aidGer Team
 */
public class UICostUnit {

    /**
     * Formats an integer as cost unit value.
     * 
     * @param costUnit
     *            the cost unit as integer
     * @return the cost unit as string
     */
    public static String valueOf(Integer costUnit) {
        return (new DecimalFormat("00000000")).format(costUnit);
    }
}
