package de.aidger.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

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
        putValue(Action.NAME, "Hilfe");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H,
                ActionEvent.CTRL_MASK));
        putValue(Action.SHORT_DESCRIPTION, "Hilfe anzeigen");
    }

    /**
     * Displays the help.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Display the help
    }

}