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

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;

/**
 * This action performs a shutdown of the application.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ExitAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ExitAction() {
        putValue(Action.NAME, _("Quit"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q,
        		Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        putValue(Action.SHORT_DESCRIPTION, _("Quit the program"));

        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
                "/de/aidger/res/icons/door-open-out.png")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

}
