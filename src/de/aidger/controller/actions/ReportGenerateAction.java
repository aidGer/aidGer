package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import java.util.Vector;

import de.aidger.view.tabs.BalanceViewerTab;
import de.aidger.model.reports.*;
import de.aidger.utils.reports.BalanceHelper;
import de.aidger.view.UI;

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
                "/de/aidger/view/icons/report.png")));
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        BalanceViewerTab tab = (BalanceViewerTab) UI.getInstance().
                getCurrentTab();

        switch (tab.getType()) {
        case 1:
            tab.clearPanel();
            SemesterBalanceCreator fullBalanceCreator = new
                    SemesterBalanceCreator(tab);
            Vector semesters = new BalanceHelper().getSemesters();
            for (int i = 1; i < semesters.size(); i++) {
                fullBalanceCreator.addSemester((String) semesters.get(i), null);
            }
            break;
        case 2:
            tab.clearPanel();

            Object year = tab.getYear();

            if (year != null) {
                AnnualBalanceCreator annualBalanceCreator = new 
                        AnnualBalanceCreator(tab);
                annualBalanceCreator.addYear((Integer) year, null);
            }
            break;
        case 3:
            tab.clearPanel();

            Object year2 = tab.getYear();
            if (year2 != null) {
                SemesterBalanceCreator semesterBalanceCreator = new
                        SemesterBalanceCreator(tab);
                semesterBalanceCreator.addSemester((String) year2, null);
            }
        }
    }

}
