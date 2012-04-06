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

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.Employment;
import de.aidger.view.UI;
import de.aidger.view.tabs.DetailViewerTab;
import de.aidger.view.tabs.ViewerTab;
import de.aidger.view.tabs.ViewerTab.DataType;
import siena.SienaException;

/**
 * This action shows the assistant for a given model.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ViewerAssistantAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ViewerAssistantAction() {
        putValue(Action.NAME, _("Assistant"));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
            "/de/aidger/res/icons/user.png")));
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
                Assistant assistant = null;

                switch (tab.getType()) {
                case Employment:
                    Employment employment = (Employment) tab.getTableModel()
                        .getModel(index);

                    assistant = (new Assistant()).getById(employment.getAssistantId());

                    break;
                case Contract:
                    Contract contract = (Contract) tab.getTableModel()
                        .getModel(index);

                    assistant = (new Assistant()).getById(contract.getAssistantId());

                    break;
                case Activity:
                    Activity activity = (Activity) tab.getTableModel()
                        .getModel(index);

                    assistant = (activity.getAssistantId() == null) ? null
                            : ((new Assistant()).getById(activity.getAssistantId()));

                    break;
                }

                if (assistant != null) {
                    UI.getInstance().replaceCurrentTab(
                        new DetailViewerTab(DataType.Assistant, assistant));
                }
            } catch (SienaException e1) {
                UI.displayError(_("The related assistant could not be displayed."));
            }
        }
    }
}