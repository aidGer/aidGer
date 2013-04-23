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

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import de.aidger.model.Runtime;
import de.aidger.view.UI;

/**
 * This action displays the help.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class HelpAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public HelpAction() {
        putValue(Action.NAME, _("Help"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H,
        		Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        putValue(Action.SHORT_DESCRIPTION, _("Display the help"));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
            "/de/aidger/res/icons/question.png")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        InputStream input = getClass().getResourceAsStream(
            "/de/aidger/res/pdf/Handbuch.pdf");
        try {
            File tempFile = File.createTempFile("manual", ".pdf");
            tempFile.deleteOnExit();

            FileOutputStream out = new FileOutputStream(tempFile);
            byte buffer[] = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

            out.close();
            input.close();

            /*
             * Open the manual in the specified pdf viewer or try the default
             * one.
             */
            try {
                java.lang.Runtime.getRuntime().exec(
                    new String[] {
                            Runtime.getInstance().getOption("pdf-viewer"),
                            tempFile.getAbsolutePath() });
            } catch (IOException ex) {
                try {
                    Desktop.getDesktop().open(tempFile);
                } catch (IOException e1) {
                    UI.displayError(_("No pdf viewer could be found!"));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(HelpAction.class.getName()).log(Level.SEVERE,
                null, ex);
        }

    }

}
