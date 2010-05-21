package de.aidger.view.tabs;

import javax.swing.JPanel;

/**
 * Tab is the abstract class of all tabs that are located in the tabbed pane on
 * the main window.
 * 
 * @author aidGer Team
 */

@SuppressWarnings("serial")
public abstract class Tab extends JPanel {

    /**
     * Returns the name of the panel.
     * 
     * @return The name of the panel
     */
    @Override
    public abstract String getName();

}
