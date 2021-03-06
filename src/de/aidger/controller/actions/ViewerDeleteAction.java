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
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import de.aidger.model.AbstractModel;
import de.aidger.model.validators.ValidationException;
import de.aidger.view.UI;
import de.aidger.view.tabs.ViewerTab;
import siena.SienaException;

/**
 * This action removes a model from the table and the database.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ViewerDeleteAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ViewerDeleteAction() {
        putValue(Action.NAME, _("Delete"));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
            "/de/aidger/res/icons/minus.png")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void actionPerformed(ActionEvent e) {
        ViewerTab tab = (ViewerTab) UI.getInstance().getCurrentTab();

        if (tab.getTable().getSelectedRow() > -1) {
            String confirmMsg = _("Do you really want to delete this entry?");
            if (tab.getTable().getSelectedRowCount() > 1) {
                confirmMsg = _("Do you really want to delete the selected entries?");
            }

            int ret = JOptionPane.showConfirmDialog(tab, confirmMsg, null,
                JOptionPane.YES_NO_OPTION);

            if (ret == JOptionPane.YES_OPTION) {
                //AbstractModel model = null;
                try {
                    List<AbstractModel> models = new ArrayList<AbstractModel>();

                    for (int row : tab.getTable().getSelectedRows()) {
                        int index = tab.getTable().convertRowIndexToModel(row);

                        models.add(tab.getTableModel().getModel(index));
                    }

                    for (AbstractModel model : models) {
                        UI.getInstance().addObserversTo(model,
                            tab.getType());

                        try {
                            model.remove();
                        } catch (ValidationException e1) {
                            List<String> errors = model.getErrors();
                            String errorMessage = "";

                            for (String error : errors) {
                                errorMessage += "- " + error + "\n";
                            }

                            model.resetErrors();

                            UI.displayError(MessageFormat
                                    .format(_("Could not remove the entity {0}:"),
                                            new Object[] { tab.getType().getDisplayName() })
                                    + "\n\n" + errorMessage);
                            return;
                        }

                        UI.getInstance().setStatusMessage(
                            MessageFormat.format(
                                _("The entity {0} was removed successfully."),
                                new Object[] { tab.getType().getDisplayName() }));
                    }
                } catch (SienaException e1) {
                    UI.displayError(_("A database error occurred during removing."));
                }
            }
        } else {
            UI.displayError(_("Please select at least one entry from the table."));
        }
    }
}
