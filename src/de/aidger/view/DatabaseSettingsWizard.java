/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2013 The aidGer Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
