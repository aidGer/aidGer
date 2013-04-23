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

package de.aidger.view.utils;

import de.aidger.model.AbstractModel;

/**
 * Factory for UI models.
 * 
 * @author aidGer Team
 */
public class UIModelFactory {

    /**
     * Creates an UI model from given abstract model.
     * 
     * @param model
     *            the abstract model
     * @return the created UI model. If there was an error, null is returned.
     */
    @SuppressWarnings("unchecked")
    public static Object create(AbstractModel model) {
        try {
            Class classUI = Class.forName("de.aidger.view.models.UI" + model.getClass().getSimpleName());

            return classUI.getConstructor(model.getClass()).newInstance(model);
        } catch (Exception e) {
        }

        return null;
    }
}
