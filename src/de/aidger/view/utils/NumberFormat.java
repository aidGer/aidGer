package de.aidger.view.utils;

/**
 * This class represents a customized number format.
 * 
 * @author aidGer Team
 */
public class NumberFormat {

    /**
     * Returns a customized instance of number format.
     * 
     * @return a customized instance of number format
     */
    public synchronized static java.text.NumberFormat getInstance() {
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);

        return nf;
    }
}
