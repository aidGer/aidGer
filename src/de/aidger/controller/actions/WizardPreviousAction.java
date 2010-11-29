package de.aidger.controller.actions;

import javax.swing.JComponent;
import de.aidger.view.Wizard;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import static de.aidger.utils.Translation._;

/**
 * This action goes back to the previous form of the wizard.
 *
 * @author rmbl
 */
public class WizardPreviousAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public WizardPreviousAction() {
        putValue(Action.NAME, _("Back"));
        putValue(Action.SHORT_DESCRIPTION, _("Go back to the previous wizard form."));
    }

    public void actionPerformed(ActionEvent e) {
        Wizard dlg = (Wizard) ((JComponent) e.getSource())
            .getTopLevelAncestor();

        dlg.showPrevPanel();
    }

}
