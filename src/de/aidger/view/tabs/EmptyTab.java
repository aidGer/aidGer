package de.aidger.view.tabs;

import static de.aidger.utils.Translation._;

import javax.swing.JPanel;

/**
 * An empty tab that will be created when adding a new tab with the "+" button.
 * 
 * @author aidGer Team
 */
public class EmptyTab implements Tab {

    /**
     * The panel which will be added to the tabbed plane.
     */
    public JPanel panel = new JPanel();

    /**
     * Initializes a new empty tab.
     */
    public EmptyTab() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.tabs.Tab#getContent()
     */
    @Override
    public JPanel getContent() {
        return panel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.tabs.Tab#getName()
     */
    @Override
    public String getName() {
        return _("(Unnamed)");
    }
}
