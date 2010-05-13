package de.aidger.view.tabs;

import static de.aidger.utils.Translation._;

import javax.swing.JPanel;

/**
 * An empty tab. Mostly used when adding a new tab with "+" button.
 * 
 * @author aidGer Team
 */
public class EmptyTab implements Tab {

    /**
     * Initializes a new tab with the welcome message on it.
     */
    public EmptyTab() {
    }

    /**
     * The panel, which will be added to the tabbed plane.
     */
    public JPanel panel = new JPanel();

    /**
     * Creates a new panel with the contents for the specific tab. If the panel
     * already exists, returns the already existing panel.
     * 
     * @return The panel to be added to the tabbed plane.
     */
    @Override
    public JPanel getContent() {
        return panel;
    }

    /**
     * Simply returns the name of the panel.
     * 
     * @return The name of the panel
     */
    @Override
    public String getName() {
        return _("(Unnamed)");
    }
}
