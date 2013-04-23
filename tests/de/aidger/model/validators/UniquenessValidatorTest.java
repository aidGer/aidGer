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

import org.junit.Before;
import org.junit.Test;

import siena.SienaException;
import static org.junit.Assert.*;

import de.aidger.model.models.Assistant;
import de.aidger.model.Runtime;

/**
 * Tests the UniquenessValidator class.
 *
 * @author aidGer Team
 */
public class UniquenessValidatorTest {

    public UniquenessValidatorTest() {
        Runtime.getInstance().initialize();
    }
    
    @Before
    public void setUp() throws SienaException{
        new Assistant().clearTable();
    }

    /**
     * Test of validateVar method, of class UniquenessValidator.
     */
    @Test
    public void testValidateVar() throws SienaException {
        System.out.println("validateVar");

        Assistant a = new Assistant();

        UniquenessValidator val = new UniquenessValidator(null, null, a);

        assertFalse(val.validateVar(new Object(), "firstName"));

        a.setEmail("test@example.com");
        a.setFirstName("Test");
        a.setLastName("Tester");
        a.setQualification("g");
        a.save();

        assertFalse(val.validateVar(a.getFirstName(), "firstName"));
        assertTrue(val.validateVar(a.getFirstName() + "2", "firstName"));
        assertTrue(val.validateVar(null, "firstName"));
    }

}
