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

package de.aidger.model.validators;

import static de.aidger.utils.Translation._;
import de.aidger.model.AbstractModel;
import siena.SienaException;

/**
 * Validates the existence of an id in the database.
 *
 * @author aidGer Team
 */
public class ExistenceValidator extends Validator {

    /**
     * The type of model to check for.
     */
    private AbstractModel type = null;

    /**
     * Initializes the ExistenceValidator class.
     *
     * @param members
     *              The member variables to check
     * @param trans
     *              The translated names
     * @param type
     *              The type of model to check for
     */
    public ExistenceValidator(String[] members,
            String[] trans, AbstractModel type) {
        super(members, trans);

        this.message = _("doesn't exist in the database");
        this.type = type;
    }

    /**
     * Validate the existance of the object
     *
     * @param o
     *          The object to validate
     * @return True if the model exists
     */
    @Override
    public boolean validateVar(Object o) {
        if (o == null) {
            return true;
        } else if (!(o instanceof Long)) {
            return false;
        } else {
            try {
                return type.getById((Long) o) != null;
            } catch (SienaException ex) {
                return false;
            }
        }
    }

}
