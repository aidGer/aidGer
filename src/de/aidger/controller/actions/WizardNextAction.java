package de.aidger.controller.actions;

import javax.swing.JComponent;
import de.aidger.view.Wizard;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import static de.aidger.utils.Translation._;

/**
 * This action advances to the next action.
 * 
 * @author rmbl
 */
public class WizardNextAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public WizardNextAction() {
        putValue(Action.NAME, _("Next"));
        putValue(Action.SHORT_DESCRIPTION, _("Advance to the next wizard form"));
    }

    public void actionPerformed(ActionEvent e) {
        Wizard dlg = (Wizard) ((JComponent) e.getSource())
            .getTopLevelAncestor();

        dlg.executeNextAction(e);
        dlg.showNextPanel();
    }

}
