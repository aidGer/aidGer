package de.aidger.view.tabs;

import javax.swing.JPanel;

/**
 * Tab is the base class of all tabs that are located in the tabbed pane on the
 * main window.
 * 
 * @author aidGer Team
 */

public class Tab {

    /**
     * The panel, which will be added to the tabbed plane.
     */
    public JPanel panel = null;

    /**
     * Creates a new panel with the contents for the specific tab. If the panel
     * already exists, returns the already existing panel.
     * 
     * @return The panel to be added to the tabbed plane.
     */
    public JPanel getTab() {
        if (panel == null) {
            panel = new JPanel();
        }
        return panel;
    }

    /**
     * Simply returns the name of the panel.
     * 
     * @return The name of the panel
     */
    public String getName() {
        return "";
    }
}
