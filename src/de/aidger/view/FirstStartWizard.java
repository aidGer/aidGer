package de.aidger.view;

import de.aidger.view.wizard.DatabaseCheck;
import de.aidger.view.wizard.DatabaseDetails;
import de.aidger.view.wizard.DatabaseSelection;
import de.aidger.view.wizard.FirstStartName;
import de.aidger.view.wizard.FirstStartWelcome;

/**
 * Display the first start wizard.
 *
 * @author rmbl
 */
public class FirstStartWizard extends Wizard {

    public FirstStartWizard(java.awt.Frame parent) {
        super(parent);

        addPanel(new FirstStartWelcome());
        addPanel(new FirstStartName());
        addPanel(new DatabaseSelection());
        addPanel(new DatabaseDetails());
        addPanel(new DatabaseCheck());
    }

}
