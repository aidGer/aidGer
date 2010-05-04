package de.aidger.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import static de.aidger.utils.Translation._;

/**
 * Displays the help.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class HelpAction extends AbstractAction {

    /**
     * Initializes the help action.
     */
    public HelpAction() {
        putValue(Action.NAME, _("Help"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H,
                ActionEvent.CTRL_MASK));
        putValue(Action.SHORT_DESCRIPTION, _("Display the help"));
    }

    /**
     * Displays the help.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Display the help
    }

}