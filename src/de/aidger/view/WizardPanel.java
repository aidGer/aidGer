package de.aidger.view;

import javax.swing.AbstractAction;
import javax.swing.JPanel;

/**
 *
 * @author rmbl
 */
public class WizardPanel extends JPanel {

    private AbstractAction action;

    public AbstractAction getNextAction() {
        return action;
    }

    protected void setNextAction(AbstractAction action) {
        this.action = action;
    }

}
