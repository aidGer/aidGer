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

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import de.aidger.model.reports.BalanceReportSemesterCreator;
import de.aidger.utils.reports.BalanceHelper;
import de.aidger.view.UI;
import de.aidger.view.reports.BalanceReportSemesterPanel;
import de.aidger.view.tabs.BalanceViewerTab;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * This action generates a report.
 * 
 * @author aidGer Team
 */
public class ReportGenerateAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ReportGenerateAction() {
        putValue(Action.NAME, _("Generate"));
        putValue(Action.SHORT_DESCRIPTION, _("Generate the report"));

        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
            "/de/aidger/res/icons/calculator.png")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        BalanceViewerTab tab = (BalanceViewerTab) UI.getInstance()
            .getCurrentTab();

        if (new BalanceHelper().getSemesters().size() > 0) {
            switch (tab.getType()) {
            case 1:
                tab.clearPanel();
                ArrayList semesters = new BalanceHelper().getSemesters();
                for (int i = 0; i < semesters.size(); i++) {
                    if (new BalanceHelper().courseExists((String) semesters
                        .get(i), tab.getBalanceFilter())) {
                        BalanceReportSemesterCreator fullBalanceCreator = null;
                        try {
                            fullBalanceCreator = new BalanceReportSemesterCreator(
                                (String) semesters.get(i), tab
                                    .getBalanceFilter(), tab
                                    .getCalculationMethod());
                        } catch (AdoHiveException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        tab.addPanel(new BalanceReportSemesterPanel(
                            (String) semesters.get(i), fullBalanceCreator));
                    }
                }
                break;
            case 2:
                tab.clearPanel();

                Object year = tab.getYear();
                ArrayList<String> yearSemesters = new BalanceHelper()
                    .getYearSemesters((Integer) year);
                for (String yearSemester : yearSemesters) {
                    if (new BalanceHelper().courseExists(yearSemester, tab
                        .getBalanceFilter())) {
                        try {
                            BalanceReportSemesterCreator yearBalanceCreator = new BalanceReportSemesterCreator(
                                yearSemester, tab.getBalanceFilter(), tab
                                    .getCalculationMethod());
                            tab.addPanel(new BalanceReportSemesterPanel(
                                yearSemester, yearBalanceCreator));
                        } catch (AdoHiveException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
                break;
            case 3:
                tab.clearPanel();

                Object year2 = tab.getYear();
                BalanceReportSemesterCreator fullBalanceCreator = null;
                try {
                    fullBalanceCreator = new BalanceReportSemesterCreator(
                        (String) year2, tab.getBalanceFilter(), tab
                            .getCalculationMethod());
                } catch (AdoHiveException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                tab.addPanel(new BalanceReportSemesterPanel((String) year2,
                    fullBalanceCreator));
            }
        }
    }

}
