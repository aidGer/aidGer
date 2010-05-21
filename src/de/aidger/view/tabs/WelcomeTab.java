package de.aidger.view.tabs;

import static de.aidger.utils.Translation._;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A tab containing a welcome message, which is opened when the program is
 * started and no other tabs are active.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class WelcomeTab extends Tab {

    /**
     * The panel which will be added to the tabbed plane.
     */
    public JPanel panel = null;

    /**
     * Initializes a new tab with the welcome messages on it.
     */
    public WelcomeTab() {
        JLabel filler = new JLabel("blobb");
        filler.setHorizontalAlignment(JLabel.CENTER);

        setLayout(new GridLayout(1, 1));
        add(filler);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.tabs.Tab#getName()
     */
    @Override
    public String getName() {
        return _("Welcome");
    }
}
