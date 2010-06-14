package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import de.aidger.model.reports.AnnualBalanceCreator;
import de.aidger.model.reports.SemesterBalanceCreator;
import de.aidger.utils.reports.BalanceHelper;
import de.aidger.view.UI;
import de.aidger.view.tabs.BalanceViewerTab;

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
        BalanceViewerTab tab = (BalanceViewerTab) UI.getInstance()
            .getCurrentTab();

        if (new BalanceHelper().getSemesters().size() > 0) {
            switch (tab.getType()) {
            case 1:
                tab.clearPanel();
                SemesterBalanceCreator fullBalanceCreator = new SemesterBalanceCreator();
                Vector semesters = new BalanceHelper().getSemesters();
                for (int i = 0; i < semesters.size(); i++) {
                    fullBalanceCreator.addSemester((String) semesters.get(i),
                        tab.getBalanceFilter());
                    tab.addPanel(fullBalanceCreator.getViewerPanel());
                }
                break;
            case 2:
                tab.clearPanel();

                Object year = tab.getYear();

                if (year != null) {
                    AnnualBalanceCreator annualBalanceCreator = new AnnualBalanceCreator();
                    annualBalanceCreator.addYear((Integer) year, tab
                        .getBalanceFilter());
                    tab.addPanel(annualBalanceCreator.getViewerPanel());
                }
                break;
            case 3:
                tab.clearPanel();

                Object year2 = tab.getYear();
                if (year2 != null) {
                    SemesterBalanceCreator semesterBalanceCreator = new SemesterBalanceCreator();
                    semesterBalanceCreator.addSemester((String) year2, tab
                        .getBalanceFilter());
                    tab.addPanel(semesterBalanceCreator.getViewerPanel());
                }
            }
        }
    }

}
