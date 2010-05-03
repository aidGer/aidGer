package de.aidger.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import de.aidger.view.SettingsDialog;
import de.aidger.view.UI;

/**
 * Displays the settings dialog.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class SettingsAction extends AbstractAction {

    /**
     * Initializes the settings action.
     */
    public SettingsAction() {
        putValue(Action.NAME, "Einstellungen");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));
        putValue(Action.SHORT_DESCRIPTION, "Programmeinstellungen anzeigen");
    }

    /**
     * Displays the settings dialog.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        SettingsDialog dlg = new SettingsDialog(UI.getInstance());
        dlg.setVisible(true);
    }

}
