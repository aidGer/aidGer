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
import de.aidger.view.SettingsDialog;
import de.aidger.model.Runtime;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * This action browses all files for pdf viewers.
 *
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class SettingsBrowseAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public SettingsBrowseAction() {
        putValue(Action.NAME, _("Browse"));
        putValue(Action.SHORT_DESCRIPTION, _("Browse for a pdf viewer"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        SettingsDialog dlg = (SettingsDialog) ((JComponent) e.getSource())
            .getTopLevelAncestor();

        File chosenFile = new File(Runtime.getInstance().getOption("pdf-viewer"));
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(chosenFile);
        boolean exit = false;
        File file = null;

        

        do {
            int retVal = fileChooser.showOpenDialog(dlg);

            if (retVal == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                if (file.isDirectory() || !file.exists()) {
                    JOptionPane.showMessageDialog(dlg,
                            _("Please choose a file."));
                } else if (!file.canExecute()) {
                    JOptionPane.showMessageDialog(dlg,
                            _("Please select an executable."));
                } else {
                    exit = true;
                }
            } else {
                return;
            }
        } while (!exit);

        dlg.setPdfPath(file.getAbsolutePath());
    }

}
