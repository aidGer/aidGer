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

package de.aidger.view;

import javax.swing.AbstractAction;
import javax.swing.JPanel;

/**
 *
 * @author rmbl
 */
public abstract class WizardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private AbstractAction action;

    /**
     * Prepare the panel before showing it.
     */
    public abstract void preparePanel();

    public AbstractAction getNextAction() {
        return action;
    }

    protected void setNextAction(AbstractAction action) {
        this.action = action;
    }

}
