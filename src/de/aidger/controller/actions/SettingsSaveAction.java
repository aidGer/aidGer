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

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;

import de.aidger.model.Runtime;
import de.aidger.model.validators.PresenceValidator;
import de.aidger.view.SettingsDialog;
import de.aidger.view.UI;
import de.aidger.utils.Pair;

/**
 * This action saves the settings in the dialog and closes it.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class SettingsSaveAction extends AbstractAction {

    /**
     * Initializes the save settings action.
     */
    public SettingsSaveAction() {
        putValue(Action.NAME, _("Save"));
        putValue(Action.SHORT_DESCRIPTION, _("Save settings"));
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

        String beforeLang = Runtime.getInstance().getOption("language");
        String beforePessimistic = Runtime.getInstance().getOption(
            "pessimistic-factor");
        String beforeHistoric = Runtime.getInstance().getOption(
            "historic-factor");
        String beforeCalc = Runtime.getInstance().getOption("calc-method");

        if (!PresenceValidator.validate(dlg.getUsername())) {
            UI.displayError(_("No name entered"));
            return;
        }
        Runtime.getInstance().setOption("name", dlg.getUsername());

        Runtime.getInstance().setOption("pdf-viewer", dlg.getPDFViewer());
        if (!dlg.getPDFViewer().isEmpty()
                && !(new File(dlg.getPDFViewer())).exists()) {
            UI.displayError(_("PDF Viewer points to nonexistant file."));
            return;
        }

        List<Pair<String, String>> languages = Runtime.getInstance()
            .getLanguages();
        if (dlg.getSelectedLanguage() == -1
                || dlg.getSelectedLanguage() >= languages.size()) {
            UI.displayError(_("No Language selected or incorrect language selected."));
            return;
        }
        Runtime.getInstance().setOption("language",
            languages.get(dlg.getSelectedLanguage()).fst());

        if (dlg.getNumOfActivities() <= 0) {
            UI.displayError(_("The number of activities needs to be bigger than 0."));
            return;
        }
        Runtime.getInstance().setOption("activities",
            Integer.toString(dlg.getNumOfActivities()));

        Runtime.getInstance().setOption("auto-open",
            Boolean.toString(dlg.isOpenSelected()));
        Runtime.getInstance().setOption("auto-save",
            Boolean.toString(dlg.isSaveSelected()));

        try {
            double tmp = Double.parseDouble(dlg.getPessimisticFactor());
            tmp = Double.parseDouble(dlg.getHistoricFactor());
            Runtime.getInstance().setOption("pessimistic-factor",
                dlg.getPessimisticFactor());
            Runtime.getInstance().setOption("historic-factor",
                dlg.getHistoricFactor());
        } catch (NumberFormatException ex) {
            UI.displayError(_("Factors need to be a floating point number."));
            return;
        }

        try {
            int anon = Integer.parseInt(dlg.getAnonymizationTime());
            Runtime.getInstance().setOption("anonymize-time",
                dlg.getAnonymizationTime());
        } catch (NumberFormatException ex) {
            UI.displayError(_("Anonymization time needs to be an integer."));
            return;
        }

        try {
            double tol = Double.parseDouble(dlg.getTolerance());
            Runtime.getInstance().setOption("tolerance", dlg.getTolerance());
        } catch (NumberFormatException ex) {
            UI
                .displayError(_("Tolerance needs to be a floating point number."));
            return;
        }

        Runtime.getInstance().setOption("calc-method",
            Integer.toString(dlg.getSelectedMethod()));

        Runtime.getInstance().setOption("rounding",
            Integer.toString(dlg.getRounding()));

        dlg.setVisible(false);
        dlg.dispose();

        // Refresh Quick Settings
        UI.getInstance().refreshQuickSettings();

        // Display info dialog if language has changed
        if (!beforeLang.equals(Runtime.getInstance().getOption("language"))) {
            UI
                .displayInfo(_("You need to restart the application to finish the language change."));
        }

        if (!beforePessimistic.equals(Runtime.getInstance().getOption(
            "pessimistic-factor"))
                || !beforeHistoric.equals(Runtime.getInstance().getOption(
                    "historic-factor"))
                || !beforeCalc.equals(Runtime.getInstance().getOption(
                    "calc-method"))) {
            UI
                .displayInfo(_("You need to recalculate open reports for the new settings to impact the results."));
        }
    }
}
