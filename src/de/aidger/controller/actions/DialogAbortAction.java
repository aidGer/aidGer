package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

/**
 * This action closes the currently open dialog.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class DialogAbortAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public DialogAbortAction() {
        putValue(Action.NAME, _("Cancel"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A,
                ActionEvent.CTRL_MASK));
        putValue(Action.SHORT_DESCRIPTION, _("Cancel the operation"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dlg = (JDialog) ((JComponent) e.getSource())
                .getTopLevelAncestor();
        dlg.setVisible(false);
        dlg.dispose();
    }
}