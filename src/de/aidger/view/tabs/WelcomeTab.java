package de.aidger.view.tabs;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeTab implements Tab {

    /**
     * Initializes a new tab with the welcome message on it.
     */
    public WelcomeTab() {
    }

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
    @Override
    public JPanel getTab() {
        if (panel == null) {
            panel = new JPanel();

            JLabel filler = new JLabel("blobb");
            filler.setHorizontalAlignment(JLabel.CENTER);

            panel.setLayout(new GridLayout(1, 1));
            panel.add(filler);
        }

        return panel;
    }

    /**
     * Simply returns the name of the panel.
     * 
     * @return The name of the panel
     */
    @Override
    public String getName() {
        return "Welcome";
    }
}
