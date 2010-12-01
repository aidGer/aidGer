/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;
import de.aidger.view.DatabaseSettingsWizard;
import de.aidger.view.UI;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author rmbl
 */
public class SettingsDatabaseConfigAction extends AbstractAction {

    public SettingsDatabaseConfigAction() {
        putValue(Action.NAME, _("Open Database Configuration"));
        putValue(Action.SHORT_DESCRIPTION, _("Open the database configuration wizard."));
    }

    public void actionPerformed(ActionEvent e) {
        DatabaseSettingsWizard wizz = new DatabaseSettingsWizard(UI.getInstance());
        wizz.showDialog();
    }

}
