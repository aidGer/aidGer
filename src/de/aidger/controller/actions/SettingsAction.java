package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;

import de.aidger.view.SettingsDialog;
import de.aidger.view.UI;

/**
 * This action displays the settings dialog.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class SettingsAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public SettingsAction() {
        putValue(Action.NAME, _("Settings"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,
            ActionEvent.CTRL_MASK));
        putValue(Action.SHORT_DESCRIPTION, _("Display program settings"));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
                "/de/aidger/view/icons/switch.png")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        SettingsDialog dlg = new SettingsDialog(UI.getInstance());
        dlg.setVisible(true);
    }

}
