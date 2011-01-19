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

import java.util.Date;
import java.text.MessageFormat;

import static de.aidger.utils.Translation._;
import de.aidger.model.AbstractModel;

/**
 * Validates the range of two dates in the specified model class.
 *
 * @author aidGer Team
 */
public class DateRangeValidator extends Validator {

    /**
     * Initializes the DataRangeValidator class.
     * 
     * @param from
     *            The from date
     * @param to
     *            The to date
     * @param transFrom
     *            Translated from date
     * @param transTo
     *            Translated to date
     */
    public DateRangeValidator(String from, String to,
            String transFrom, String transTo) {
        super(new String[] { from, to },
                new String[] { transFrom, transTo });
        message = _("is an incorrect date range");
    }

    /**
     * Validate the Date Range
     *
     * @param m
     *              The model to validate
     * @return True if the date range validates
     */
    @Override
    public boolean validate(AbstractModel m) {
        if (m == null) {
            return false;
        }
        Date from = (Date)getValueOf(m, members[0]);
        Date to = (Date)getValueOf(m, members[1]);
        if (!validate(from, to)) {
                m.addError(MessageFormat.format(
                        _("The date range {0} and {1} is incorrect."),
                        (Object[])trans));
            return false;
        }
        return true;
    }

    /**
     * Validate the range of the given dates.
     *
     * Note: Only used for testing purposes.
     *
     * @param from
     *            The from date
     * @param to
     *            The to date
     * @return True if the date range validates
     */
    public static boolean validate(Date from, Date to) {
        if (from == null || to == null) {
            return false;
        } else if (from.equals(to) || from.after(to)) {
            return false;
        }
        return true;
    }

    /**
     * Empty overridden function
     */
    @Override
    public boolean validateVar(Object o) {
        return false;
    }

}
