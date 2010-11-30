package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;
import de.aidger.model.Runtime;
import de.aidger.view.UI;
import de.aidger.view.Wizard;
import de.aidger.view.wizard.FirstStartName;

import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.AbstractAction;

/**
 * This action saves the username before advancing.
 *
 * @author rmbl
 */
public class FirstStartNameFinishAction extends AbstractAction {

    public void actionPerformed(ActionEvent e) {
        Wizard dlg = (Wizard) ((JComponent) e.getSource())
            .getTopLevelAncestor();

        String name = ((FirstStartName) dlg.getCurrentPanel()).getUsername();
        if (!name.isEmpty()) {
            Runtime.getInstance().setOption("name", name);
        } else {
            UI.displayError(_("No name entered"));
            e.setSource(null);
        }
    }

}