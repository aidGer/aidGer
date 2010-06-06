package de.aidger.view.tabs;

import javax.swing.BorderFactory;
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
     * Constructs a tab.
     */
    public Tab() {
        setBorder(BorderFactory.createTitledBorder(""));
    }

    /**
     * Returns the name of the panel.
     * 
     * @return The name of the panel
     */
    public abstract String getTabName();

    /**
     * Tab can perform actions before it is closed.
     */
    public void performBeforeClose() {
    }

}
