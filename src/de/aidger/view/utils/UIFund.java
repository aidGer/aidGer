package de.aidger.view.utils;

import java.text.DecimalFormat;

/**
 * This class formats an integer as fund value.
 * 
 * @author aidGer Team
 */
public class UIFund {

    /**
     * Formats an integer as fund value.
     * 
     * @param fund
     *            the fund as integer
     * @return the fund as string
     */
    public static String valueOf(Integer fund) {
        return (new DecimalFormat("00000000")).format(fund);
    }
}
