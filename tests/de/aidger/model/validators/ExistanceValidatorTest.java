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

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import org.junit.Test;
import static org.junit.Assert.*;

import de.aidger.model.models.Assistant;
import de.aidger.model.Runtime;

/**
 * Tests the ExistanceValidator class.
 *
 * @author aidGer Team
 */
public class ExistanceValidatorTest {

    public ExistanceValidatorTest() {
        Runtime.getInstance().initialize();
    }

    /**
     * Test of validateVar method, of class ExistanceValidator.
     */
    @Test
    public void testValidateVar() throws AdoHiveException {
        System.out.println("validateVar");

        Assistant a = new Assistant();

        ExistanceValidator val = new ExistanceValidator(null, null, null, a);

        assertFalse(val.validateVar(new Object()));
        assertFalse(val.validateVar(0));

        a.setEmail("test@example.com");
        a.setFirstName("Test");
        a.setLastName("Tester");
        a.setQualification("g");
        a.save();

        assertTrue(val.validateVar(a.getId()));
        assertTrue(val.validateVar(null));
    }

}