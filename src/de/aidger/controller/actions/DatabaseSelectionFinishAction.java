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
