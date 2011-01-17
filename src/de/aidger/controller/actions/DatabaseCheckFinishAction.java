/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2011 The aidGer Team
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
