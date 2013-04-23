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
