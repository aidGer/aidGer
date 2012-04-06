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

/**
 * 
 */
package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import de.aidger.model.budgets.BudgetCreator;
import de.aidger.model.budgets.CourseBudget;
import de.aidger.model.models.Course;
import de.aidger.view.UI;
import de.aidger.view.tabs.BudgetViewerTab;
import siena.SienaException;

/**
 * This action generates a budget report.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class BudgetGenerateAction extends AbstractAction {
    /**
     * Initializes the action.
     */
    public BudgetGenerateAction() {
        putValue(Action.NAME, _("Generate"));
        putValue(Action.SHORT_DESCRIPTION, _("Generate the budget report"));

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
        BudgetViewerTab viewerTab = (BudgetViewerTab) UI.getInstance()
            .getCurrentTab();
        try {
            viewerTab.clearTable();
            BudgetCreator budgetCreator = new BudgetCreator();
            List<Course> courses = (new Course()).getAll();
            /*
             * Get course budgets for all courses along with the filter
             * criteria.
             */
            for (Course course : courses) {
                budgetCreator.addCourseBudget(new Course(course), viewerTab
                    .getBudgetFilter());
            }
            ArrayList<CourseBudget> courseBudgets = budgetCreator
                .getCourseBudgets();
            /*
             * Add a new row for every course that passed the filters.
             */
            for (CourseBudget courseBudget : courseBudgets) {
                viewerTab.addRow(budgetCreator.getObjectArray(courseBudget));
            }
        } catch (SienaException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

}
