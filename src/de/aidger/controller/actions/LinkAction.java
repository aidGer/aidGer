package de.aidger.controller.actions;

import java.awt.event.MouseListener;

import javax.swing.AbstractAction;

/**
 * Base abstract class for all link actions.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public abstract class LinkAction extends AbstractAction implements
        MouseListener, Cloneable {
    /**
     * A flag whether the action is a link.
     */
    private boolean isLink = true;

    /**
     * Marks the action as no link.
     */
    public void markNoLink() {
        isLink = false;
    }

    /**
     * Returns whether the action is a link.
     * 
     * @return whether the ation is a link.
     */
    public boolean isLink() {
        return isLink;
    }
}
