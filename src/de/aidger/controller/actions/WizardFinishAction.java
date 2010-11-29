package de.aidger.controller.actions;

import javax.swing.JComponent;
import de.aidger.view.Wizard;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import static de.aidger.utils.Translation._;

/**
 * This action closes the wizard.
 *
 * @author rmbl
 */
public class WizardFinishAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public WizardFinishAction() {
        putValue(Action.NAME, _("Finish"));
        putValue(Action.SHORT_DESCRIPTION, _("Finish the wizard."));
    }

    public void actionPerformed(ActionEvent e) {
        Wizard dlg = (Wizard) ((JComponent) e.getSource())
            .getTopLevelAncestor();

        AbstractAction action = dlg.getNextAction();

        action.actionPerformed(e);
        dlg.setVisible(false);
        dlg.dispose();
    }

}
