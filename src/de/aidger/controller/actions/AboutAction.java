package de.aidger.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 * Displays the about dialog.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class AboutAction extends AbstractAction {

    /**
     * Initializes the about action.
     */
    public AboutAction() {
        putValue(Action.NAME, "Über");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A,
                ActionEvent.CTRL_MASK));
        putValue(Action.SHORT_DESCRIPTION, "Autoren anzeigen");
    }

    /**
     * Displays the about dialog.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Display the about dialog
    }

}