/**
 * 
 */
package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import de.aidger.model.budgets.BudgetCreator;
import de.aidger.model.budgets.CourseBudget;
import de.aidger.model.models.Course;
import de.aidger.view.UI;
import de.aidger.view.tabs.BudgetViewerTab;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * This action generates a budget report.
 * 
 * @author aidGer Team
 */
public class BudgetGenerateAction extends AbstractAction {
    /**
     * Initializes the action.
     */
    public BudgetGenerateAction() {
        putValue(Action.NAME, _("Generate"));
        putValue(Action.SHORT_DESCRIPTION, _("Generate the budget report"));

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
        BudgetViewerTab viewerTab = (BudgetViewerTab) UI.getInstance()
            .getCurrentTab();
        try {
            viewerTab.clearTable();
            BudgetCreator budgetCreator = new BudgetCreator();
            List<ICourse> courses = (new Course()).getAll();
            /*
             * Get course budgets for all courses along with the filter
             * criteria.
             */
            for (ICourse course : courses) {
                budgetCreator.addCourseBudget(new Course(course), viewerTab
                    .getBudgetFilter());
            }
            Vector<CourseBudget> courseBudgets = budgetCreator
                .getCourseBudgets();
            /*
             * Add a new row for every course that passed the filters.
             */
            for (CourseBudget courseBudget : courseBudgets) {
                viewerTab.addRow(budgetCreator.getObjectArray(courseBudget));
            }
        } catch (AdoHiveException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

}
