package de.aidger.view.tabs;

import java.util.ArrayList;
import java.util.List;

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
     * The predecessor tabs.
     */
    private final List<Tab> predecessors = new ArrayList<Tab>();

    /**
     * Constructs a tab.
     */
    public Tab() {
        setBorder(BorderFactory.createTitledBorder(""));
    }

    /**
     * Get the name of the tab and constructor options if necessary.
     * 
     * @return A string representation of the class
     */
    @Override
    public String toString() {
        return getClass().getName();
    }

    /**
     * Returns the name of the panel.
     * 
     * @return The name of the panel
     */
    public abstract String getTabName();

    /**
     * Tab can perform actions before it is opened.
     */
    public void performBeforeOpen() {
    }

    /**
     * Tab can perform actions before it is closed.
     */
    public void performBeforeClose() {
    }

    /**
     * Adds a predecessor.
     * 
     * @param predecessor
     *            the predecessor to be added
     */
    public void addPredecessor(Tab predecessor) {
        predecessors.remove(predecessor);

        predecessors.add(0, predecessor);
    }

    /**
     * Returns the last predecessor. If there is no predecessor found it is
     * returned null
     * 
     * @return the last predecessor or null
     */
    public Tab getPredecessor() {
        if (predecessors.isEmpty()) {
            return null;
        }

        return predecessors.get(0);
    }

}
