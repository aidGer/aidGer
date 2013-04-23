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

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import de.aidger.utils.pdf.BalanceReportConverter;
import de.aidger.utils.reports.BalanceHelper;
import de.aidger.view.UI;
import de.aidger.view.tabs.BalanceViewerTab;

/**
 * This action exports a report.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ReportExportAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ReportExportAction() {
        putValue(Action.NAME, _("Export"));
        putValue(Action.SHORT_DESCRIPTION, _("Export the report to a pdf file"));

        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
            "/de/aidger/res/icons/report--arrow.png")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {

        if (new BalanceHelper().getSemesters().size() > 0) {
            BalanceViewerTab tab = (BalanceViewerTab) UI.getInstance()
                .getCurrentTab();

            JFileChooser fileChooser = new JFileChooser();
            File file;
            FileFilter pdfFilter = new FileFilter() {
                @Override
                public boolean accept(File arg0) {
                    String fileName = arg0.getName();
                    int fileExtensionStart = fileName.lastIndexOf('.');
                    String fileExtension = fileName
                        .substring(fileExtensionStart + 1);
                    return arg0.isDirectory() || fileExtension.equals("pdf");
                }

                @Override
                public String getDescription() {
                    return _("PDF files");
                }
            };

            fileChooser.removeChoosableFileFilter(fileChooser
                .getAcceptAllFileFilter());
            fileChooser.addChoosableFileFilter(pdfFilter);

            boolean exit = false;

            do {
                int retVal = fileChooser.showDialog(tab, _("Export"));

                if (retVal == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    if (file.isDirectory()) {
                        // This shouldn't happen but check for it anyways
                        JOptionPane.showMessageDialog(tab,
                            _("Please choose a file."));
                    } else if (file.exists()) {
                        // Ask if the user wants to overwrite the file
                        retVal = JOptionPane.showOptionDialog(tab,
                            _("Are you sure you want to overwrite the file?"),
                            _("Overwrite file?"), JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, null, null);
                        if (retVal == JOptionPane.YES_OPTION) {
                            exit = true;
                        }
                    } else {
                        exit = true;
                    }
                } else {
                    return;
                }
            } while (!exit);

            new BalanceReportConverter(file, tab.getType(), tab.getYear(), tab
                .getBalanceFilter(), tab.getCalculationMethod());
        }
    }

}
