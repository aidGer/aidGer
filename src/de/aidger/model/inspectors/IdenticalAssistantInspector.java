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

package de.aidger.model.inspectors;

import static de.aidger.utils.Translation._;

import java.util.List;

import de.aidger.model.models.Assistant;
import de.aidger.view.models.UIAssistant;
import siena.SienaException;

/**
 * Inspector for identical assistants in the database.
 * 
 * @author aidGer Team
 */
public class IdenticalAssistantInspector extends Inspector {

    /**
     * The assistant to be checked.
     */
    private final UIAssistant assistant;

    /**
     * Creates an identical assistant inspector.
     * 
     * @param assistant
     *            the assistant to be checked
     */
    public IdenticalAssistantInspector(UIAssistant assistant) {
        this.assistant = assistant;
    }

    /*
     * Checks for identical assistants in the database.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void check() {
        try {
            List<Assistant> assistants = (new Assistant()).getAll();

            if (assistants.contains(assistant)) {
                result = _("The same assistant exists already in the database.");
            }
        } catch (SienaException e) {
        }
    }

}
