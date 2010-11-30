package de.aidger.view;

import javax.swing.AbstractAction;
import javax.swing.JPanel;

/**
 *
 * @author rmbl
 */
public abstract class WizardPanel extends JPanel {

    private AbstractAction action;

    /**
     * Prepare the panel before showing it.
     */
    public abstract void preparePanel();

    public AbstractAction getNextAction() {
        return action;
    }

    protected void setNextAction(AbstractAction action) {
        this.action = action;
    }

}
