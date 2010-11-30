package de.aidger.view;

import de.aidger.controller.ActionNotFoundException;
import de.aidger.controller.ActionRegistry;
import de.aidger.controller.actions.DialogAbortAction;
import de.aidger.view.wizard.DatabaseCheck;
import de.aidger.view.wizard.DatabaseDetails;
import de.aidger.view.wizard.DatabaseSelection;
import javax.swing.AbstractAction;

/**
 * A wizard to enter/change the database settings
 *
 * @author rmbl
 */
public class DatabaseSettingsWizard extends Wizard {

    public DatabaseSettingsWizard(java.awt.Frame parent) {
        super(parent);

        addPanel(new DatabaseSelection());
        addPanel(new DatabaseDetails());
        addPanel(new DatabaseCheck());

        try {
            setCancelAction((AbstractAction) ActionRegistry.getInstance().get(DialogAbortAction.class.getName()));
        } catch (ActionNotFoundException ex) {
            UI.displayError(ex.getMessage());
        }
    }

}
