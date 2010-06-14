package de.aidger.model.reports;

import javax.swing.JPanel;

/**
 * This class manages the BalanceViewerTab and adds years/semesters to it.
 * 
 * @author aidGer Team
 */
public abstract class BalanceCreator {

    /**
     * The associated BalanceViewerTab.
     */
    protected JPanel balanceViewerPanel = null;

    /**
     * Returns the balance viewer tab.
     * 
     * @return the balance viewer tab
     */
    public JPanel getViewerPanel() {
        return balanceViewerPanel;
    }
}
