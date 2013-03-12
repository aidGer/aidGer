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

package de.aidger.model.models;

import de.aidger.model.validators.ValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;

import org.junit.rules.ExpectedException;
import siena.SienaException;

/**
 * Tests the HourlyWage class.
 *
 * @author aidGer Team
 */
public class HourlyWageTest {

    protected HourlyWage hourly = null;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void beforeClassSetUp() {
        de.aidger.model.Runtime.getInstance().initialize();
    }

    @Before
    public void setUp() {
        hourly = new HourlyWage();
        hourly.setId((long) 1);
        hourly.setMonth((byte) 10);
        hourly.setQualification("g");
        hourly.setWage(200.0);
        hourly.setYear((short) 2010);
    }

    /**
     * Test of constructor, of class HourlyWage.
     */
    @Test
    public void testConstructor() {
        System.out.println("Constructor");

        hourly.clearTable();
        hourly.save();

        HourlyWage result = hourly.getByKeys(hourly.getQualification(), hourly.getMonth(), hourly.getYear());

        assertNotNull(result);
        assertEquals(hourly, result);
    }

    /**
     * Test of validation methods, of class HourlyWage.
     */
    @Test
    public void testValidation() {
        System.out.println("Validation");

        hourly.clearTable();
        hourly.save();
        List<HourlyWage> list = hourly.getAll();
        assertNotNull(list);
        hourly.remove();
        list = hourly.getAll();
        assertTrue(list.size() == 0);

        exception.expect(ValidationException.class);

        hourly.setQualification(null);
        hourly.save();
        list = hourly.getAll();
        assertTrue(list.size() == 0);

        hourly.setQualification("Q");
        hourly.save();
        list = hourly.getAll();
        assertTrue(list.size() == 0);
        hourly.setQualification("g");

        hourly.setMonth((byte) -1);
        hourly.save();
        list = hourly.getAll();
        assertTrue(list.size() == 0);

        hourly.setMonth((byte) 13);
        hourly.save();
        list = hourly.getAll();
        assertTrue(list.size() == 0);
        hourly.setMonth((byte) 10);

        hourly.setYear((short) 999);
        hourly.save();
        list = hourly.getAll();
        assertTrue(list.size() == 0);

        hourly.setYear((short) 10101);
        hourly.save();
        list = hourly.getAll();
        assertTrue(list.size() == 0);

        exception = ExpectedException.none();
    }


    /**
     * Test of clone method, of class HourlyWage.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        HourlyWage result = hourly.clone();

        assertEquals(hourly.getId(), result.getId());
        assertEquals(hourly.getMonth(), result.getMonth());
        assertEquals(hourly.getQualification(), result.getQualification());
        assertEquals(hourly.getWage(), result.getWage());
        assertEquals(hourly.getYear(), result.getYear());
    }

    /**
     * Test of equals method, of class HourlyWage.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        HourlyWage result = hourly.clone();

        assertEquals(hourly, result);
        assertFalse(hourly.equals(new Object()));
    }

    /**
     * Test of hashCode method, of class Assistant.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        HourlyWage result = hourly.clone();

        assertEquals(hourly.hashCode(), result.hashCode());
    }

}