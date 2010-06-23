/**
 * 
 */
package de.aidger.view.tabs;

import javax.swing.JPanel;

/**
 * @author aidGer Team
 */
public abstract class ReportTab extends Tab {

    /**
     * Removes the specified filter from the filters list.
     * 
     * @param type
     *            The type of filter.
     * @param value
     *            The value of the filter.
     */
    public void removeFilter(String name, String value) {
    }

    /**
     * Removes the given panel from the filter panel.
     * 
     * @param panel
     *            The panel to remove
     */
    public void removeFilterPanel(JPanel panel) {
    }
}
