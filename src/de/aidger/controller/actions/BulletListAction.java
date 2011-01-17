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

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.SwingUtilities;

import de.aidger.model.AbstractModel;
import de.aidger.view.UI;
import de.aidger.view.models.UIModel;
import de.aidger.view.tabs.DetailViewerTab;

/**
 * Base class for all bullet list actions.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class BulletListAction extends LinkAction {

    /**
     * The UI model the action link is refer to.
     */
    private UIModel model;

    /**
     * Constructs a bullet list action.
     * 
     * @param name
     *            the action name
     * @param bullet
     *            the bullet icon
     */
    public BulletListAction(String name, Icon bullet) {
        putValue(Action.NAME, name);
        putValue(Action.SMALL_ICON, bullet);

        markNoLink();
    }

    /**
     * Constructs a bullet list action as link.
     * 
     * @param name
     *            the action name
     * @param bullet
     *            the bullet icon
     * @param model
     *            the UI model the link is refer to
     */
    public BulletListAction(String name, Icon bullet, UIModel model) {
        putValue(Action.NAME, name);
        putValue(Action.SMALL_ICON, bullet);

        this.model = model;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void mouseClicked(MouseEvent e) {
        if (model == null) {
            return;
        }

        if (SwingUtilities.isLeftMouseButton(e)) {
            UI.getInstance()
                .addNewTab(
                    new DetailViewerTab(model.getDataType(),
                        (AbstractModel) model));
        } else if (SwingUtilities.isMiddleMouseButton(e)) {
            UI.getInstance()
                .replaceCurrentTab(
                    new DetailViewerTab(model.getDataType(),
                        (AbstractModel) model));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setCursor(
            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setCursor(Cursor.getDefaultCursor());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // all bullet list actions are handled by the mouse listener
    }
}
