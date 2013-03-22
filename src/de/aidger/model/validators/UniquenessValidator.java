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
import siena.SienaException;

/**
 * Validates the uniqueness of a model.
 *
 * @author aidGer Team
 */
public class UniquenessValidator extends Validator {

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
    public UniquenessValidator(String[] members,
            String[] trans, AbstractModel type) {
        super(members, trans);

        this.message = _("already exists in the database");
        this.type = type;
    }
    
    @Override
    public boolean validate(AbstractModel model) {
        boolean ret = true;
        for (int i = 0; i < members.length; ++i) {
            Object value = getValueOf(model, members[i]);
            if (!validateVar(value, members[i])) {
                ret = false;
                if (model != null) {
                    model.addError(members[i], trans[i], message);
                }
            }
        }
        return ret;
    }

    /**
     * Validate the existance of the object
     *
     * @param o
     *          The object to validate
     * @return True if the model doesn't exist
     */
    public boolean validateVar(Object o, String member) {
        if (o == null) {
            return true;
        } else {
            try {
                return type.all().filter(member, o).get() == null;
            } catch (SienaException ex) {
                return false;
            }
        }
    }

    @Override
    public boolean validateVar(Object o) {
        return false;
    }

}
