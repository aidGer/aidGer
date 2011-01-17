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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the FormatValidator class.
 *
 * @author aidGer Team
 */
public class FormatValidatorTest {


    /**
     * Test of validateVar method, of class FormatValidator.
     */
    @Test
    public void testValidateVar() {
        System.out.println("validateVar");

        FormatValidator val = new FormatValidator(null, null, null, "[abc]*");

        assertFalse(val.validateVar(null));
        assertFalse(val.validateVar(new Object()));
        assertFalse(val.validateVar("d"));
        assertTrue(val.validateVar("aabbcc"));
        assertTrue(val.validateVar(""));
    }

}