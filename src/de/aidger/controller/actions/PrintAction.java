package de.aidger.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import static de.aidger.utils.Translation._;

/**
 * PrintAction sends the current contents to the printer.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class PrintAction extends AbstractAction {

    /**
     * Initializes the print action.
     */
    public PrintAction() {
        putValue(Action.NAME, _("Print"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P,
                ActionEvent.CTRL_MASK));
        putValue(Action.SHORT_DESCRIPTION, _("Print program contents"));
    }

    /*
     * (non-Javadoc)
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Add functionality
    }

}