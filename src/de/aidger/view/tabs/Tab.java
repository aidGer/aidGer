package de.aidger.view.tabs;

import javax.swing.JPanel;

/**
 * Tab is the interface class of all tabs that are located in the tabbed pane on
 * the main window.
 * 
 * @author aidGer Team
 */

public interface Tab {

    /**
     * Retrieves the content of the specific tab.
     * 
     * @return The panel to be added to the tabbed plane.
     */
    public JPanel getContent();

    /**
     * Returns the name of the panel.
     * 
     * @return The name of the panel
     */
    public String getName();
}
