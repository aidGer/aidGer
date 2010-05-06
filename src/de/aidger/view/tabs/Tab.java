package de.aidger.view.tabs;

import javax.swing.JPanel;

/**
 * Tab is the base class of all tabs that are located in the tabbed pane on the
 * main window.
 * 
 * @author aidGer Team
 */

public class Tab {

    public JPanel panel;

    public JPanel getNewTab() {
        panel = new JPanel();
        return panel;
    }

    public String getName() {
        return "";
    }

    public JPanel getTab() {
        return panel;
    }
}
