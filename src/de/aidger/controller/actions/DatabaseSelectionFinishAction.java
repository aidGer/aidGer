package de.aidger.controller.actions;

import de.aidger.model.Runtime;
import de.aidger.view.Wizard;
import de.aidger.view.wizard.DatabaseSelection;

import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.AbstractAction;

/**
 * This action saves the selected database before advancing.
 *
 * @author rmbl
 */
public class DatabaseSelectionFinishAction extends AbstractAction {

    public void actionPerformed(ActionEvent e) {
        Wizard dlg = (Wizard) ((JComponent) e.getSource())
            .getTopLevelAncestor();

        int selection = ((DatabaseSelection) dlg.getCurrentPanel()).getDBSelection();
        Runtime.getInstance().setOption("database-type", Integer.toString(selection));
    }

}