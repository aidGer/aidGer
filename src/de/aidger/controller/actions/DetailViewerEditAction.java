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

import de.aidger.view.UI;
import de.aidger.view.tabs.DetailViewerTab;
import de.aidger.view.tabs.EditorTab;
import de.aidger.view.tabs.Tab;

/**
 * This action replaces the current tab with the model editor tab when user
 * wants to edit the model displayed in detail viewer tab.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class DetailViewerEditAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public DetailViewerEditAction() {
        putValue(Action.NAME, _("Edit"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        DetailViewerTab tab = (DetailViewerTab) UI.getInstance()
            .getCurrentTab();

        Tab next = new EditorTab(tab.getType(), tab.getModel());
        next.markAsNoPredecessor();

        UI.getInstance().replaceCurrentTab(next);
    }
}
