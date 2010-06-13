package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;

/**
 * This action performs a shutdown of the application.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ExitAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ExitAction() {
        putValue(Action.NAME, _("Quit"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q,
            ActionEvent.CTRL_MASK));
        putValue(Action.SHORT_DESCRIPTION, _("Quit the program"));

        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
                "/de/aidger/view/icons/door-open-out.png")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

}
