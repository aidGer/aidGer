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

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import de.aidger.model.models.Activity;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.view.UI;
import de.aidger.view.tabs.DetailViewerTab;
import de.aidger.view.tabs.ViewerTab;
import de.aidger.view.tabs.ViewerTab.DataType;
import siena.SienaException;

/**
 * This action shows the course for a given model.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ViewerCourseAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ViewerCourseAction() {
        putValue(Action.NAME, _("Course"));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
            "/de/aidger/res/icons/bank.png")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ViewerTab tab = (ViewerTab) UI.getInstance().getCurrentTab();

        if (tab.getTable().getSelectedRow() == -1) {
            UI.displayError(_("Please select an entry from the table."));
        } else if (tab.getTable().getSelectedRowCount() > 1) {
            UI.displayError(_("Please select only one entry from the table."));
        } else {
            int index = tab.getTable().getRowSorter().convertRowIndexToModel(
                tab.getTable().getSelectedRow());

            try {
                Course course = null;

                switch (tab.getType()) {
                case Employment:
                    Employment employment = (Employment) tab.getTableModel()
                        .getModel(index);

                    course = (new Course()).getById(employment.getCourseId());

                    break;
                case Activity:
                    Activity activity = (Activity) tab.getTableModel()
                        .getModel(index);

                    course = (activity.getCourseId() == null) ? null
                            : (new Course()).getById(activity.getCourseId());

                    break;
                }

                if (course != null) {
                    UI.getInstance().replaceCurrentTab(
                        new DetailViewerTab(DataType.Course, course));
                }
            } catch (SienaException e1) {
                UI.displayError(_("The related course could not be displayed."));
            }
        }
    }
}
