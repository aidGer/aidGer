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

import javax.swing.JComponent;
import de.aidger.view.Wizard;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import static de.aidger.utils.Translation._;

/**
 * This action goes back to the previous form of the wizard.
 *
 * @author rmbl
 */
public class WizardPreviousAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public WizardPreviousAction() {
        putValue(Action.NAME, _("Back"));
        putValue(Action.SHORT_DESCRIPTION, _("Go back to the previous wizard form."));
    }

    public void actionPerformed(ActionEvent e) {
        Wizard dlg = (Wizard) ((JComponent) e.getSource())
            .getTopLevelAncestor();

        dlg.showPrevPanel();
    }

}
