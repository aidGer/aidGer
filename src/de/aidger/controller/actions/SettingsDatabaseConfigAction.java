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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;
import de.aidger.view.DatabaseSettingsWizard;
import de.aidger.view.UI;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author rmbl
 */
public class SettingsDatabaseConfigAction extends AbstractAction {

    public SettingsDatabaseConfigAction() {
        putValue(Action.NAME, _("Open Database Configuration"));
        putValue(Action.SHORT_DESCRIPTION, _("Open the database configuration wizard."));
    }

    public void actionPerformed(ActionEvent e) {
        DatabaseSettingsWizard wizz = new DatabaseSettingsWizard(UI.getInstance());
        wizz.showDialog();
    }

}
