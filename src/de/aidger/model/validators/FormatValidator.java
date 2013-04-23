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

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Validates the format of member variables in a specified model.
 *
 * @author aidGer Team
 */
public class FormatValidator extends Validator {

    /**
     * Matches the string against the given format.
     */
    private Pattern pattern;

    /**
     * Initializes the FormatValidator class.
     *
     * @param members
     *              The member variables to check
     * @param trans
     *              The translated names
     * @param format
     *              The format to check for
     * @param casesensitive
     *              Is the regex string casesensitive or not
     */
    public FormatValidator(String[] members, String[] trans,
            String format, boolean casesensitive) {
        super(members, trans);

        this.message = _("has an incorrect format");
        
        if (casesensitive) {
            pattern = Pattern.compile(format);
        } else {
            pattern = Pattern.compile(format, Pattern.CASE_INSENSITIVE);
        }


    }

    /**
     * Initializes the FormatValidator class.
     *
     * @param members
     *              The member variables to check
     * @param trans
     *              The translated names
     * @param format
     *              The format to check for
     */
    public FormatValidator(String[] members, String[] trans,
           String format) {
        this(members, trans, format, true);
    }

    /**
     * Validate a string to the format
     *
     * @param o
     *            Variable to validate
     * @return True if the variable validates
     */
    @Override
    public boolean validateVar(Object o) {
        if (o == null || !(o instanceof String)) {
            return false;
        }

        Matcher matcher = pattern.matcher((CharSequence) o);
        return matcher.matches();
    }

}
