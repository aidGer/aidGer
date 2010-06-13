package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;

/**
 * This action displays the help.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class HelpAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public HelpAction() {
        putValue(Action.NAME, _("Help"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H,
            ActionEvent.CTRL_MASK));
        putValue(Action.SHORT_DESCRIPTION, _("Display the help"));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
                "/de/aidger/view/icons/question.png")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Display the help
    }

}