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
 * Tests the InclusionValidator class.
 *
 * @author aidGer Team
 */
public class InclusionValidatorTest {

    /**
     * Test of validateVar method, of class InclusionValidator.
     */
    @Test
    public void testValidateVar() {
        System.out.println("validateVar");

        InclusionValidator val = new InclusionValidator(null, null,
                new String[] { "in", "cl", "us", "ion" });

        assertFalse(val.validateVar(null));
        assertFalse(val.validateVar(""));
        assertFalse(val.validateVar("inclusion"));
        assertTrue(val.validateVar("in"));
    }

}