package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;
import de.aidger.view.UI;
import de.aidger.view.Wizard;
import de.aidger.view.wizard.DatabaseCheck;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;

/**
 * Check if the connection was successful.
 *
 * @author rmbl
 */
public class DatabaseCheckFinishAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        Wizard dlg = (Wizard) ((JComponent) e.getSource())
            .getTopLevelAncestor();

        if (!((DatabaseCheck) dlg.getCurrentPanel()).hasConnected()) {
            UI.displayError(_("A successful connection needs to be established before the wizard can be finished."));
            e.setSource(null);
        }
    }

}
