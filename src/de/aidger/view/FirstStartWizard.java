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

import de.aidger.view.wizard.DatabaseCheck;
import de.aidger.view.wizard.DatabaseDetails;
import de.aidger.view.wizard.DatabaseSelection;
import de.aidger.view.wizard.FirstStartName;
import de.aidger.view.wizard.FirstStartWelcome;

/**
 * Display the first start wizard.
 *
 * @author rmbl
 */
public class FirstStartWizard extends Wizard {

    public FirstStartWizard(java.awt.Frame parent) {
        super(parent);

        addPanel(new FirstStartWelcome());
        addPanel(new FirstStartName());
        addPanel(new DatabaseSelection());
        addPanel(new DatabaseDetails());
        addPanel(new DatabaseCheck());
    }

}
