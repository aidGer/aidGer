package de.aidger.model.reports;

import de.aidger.view.tabs.BalanceViewerTab;
import de.aidger.view.tabs.Tab;

/**
 * This class manages the BalanceViewerTab and adds years/semesters to it.
 * 
 * @author aidGer Team
 */
public abstract class BalanceCreator {

    /**
     * The associated BalanceViewerTab.
     */
    protected BalanceViewerTab balanceViewerTab = null;

    /**
     * Returns the balance viewer tab.
     * 
     * @return the balance viewer tab
     */
    public Tab getViewerTab() {
        return balanceViewerTab;
    }
}
