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

package de.aidger.model.validators;

import static de.aidger.utils.Translation._;
import de.aidger.model.AbstractModel;

/**
 * Validates the inclusion in the specified model class.
 *
 * @author aidGer Team
 */
public class InclusionValidator extends Validator {

    /**
     * List of strings in which to check for inclusion
     */
    protected String[] list = null;

    /**
     * Initialize the InclusionValidator class.
     *
     * @param members
     *            The members of the model to validate
     * @param trans
     *            The translated names
     * @param inc
     *            Array of possible values
     */
    public InclusionValidator(String[] members, String[] trans,
            String[] inc) {
        super(members, trans);

        message = _("has an invalid value");
        list = inc;
    }

    /**
     * Validate the variable.
     *
     * @param o
     *            The variable to validate
     * @return True if the input validates, false otherwise
     */
    @Override
    public boolean validateVar(Object o) {
        if (o == null || !(o instanceof String)) {
            return false;
        } else {
            String check = (String) o;
            for (String s : list) {
                if (check.equals(s)) {
                    return true;
                }
            }
            return false;
        }
    }

}
