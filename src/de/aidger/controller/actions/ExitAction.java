package de.aidger.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 * ExitAction performs a shutdown of the application.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ExitAction extends AbstractAction {
    /**
     * Initializes the exit action.
     */
    public ExitAction() {
        super();

        putValue(Action.NAME, "Beenden");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                ActionEvent.CTRL_MASK));
        putValue(Action.SHORT_DESCRIPTION, "Programm beenden");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        exitApplication();
    }

    /**
     * This method shuts down the application by freeing all resources and
     * returning an exit code to the operating system.
     */
    private void exitApplication() {
        System.exit(0);
    }
}
