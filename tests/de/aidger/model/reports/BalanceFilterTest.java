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

/**
 *
 */
package de.aidger.model.reports;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

/**
 * Tests the class BalanceFilter.
 * 
 * @author aidGer Team
 */
public class BalanceFilterTest {
    private BalanceFilter balanceFilter = null;

    @After
    public void cleanUp() {
    }

    /**
     * Test the constructor of the class BalanceFilter.
     */
    @Test
    public void testConstructor() {
        System.out.println("Constructor");

        balanceFilter = new BalanceFilter();

        assertNotNull(balanceFilter);
        assertNotNull(balanceFilter.getGroups());
        assertNotNull(balanceFilter.getLecturers());
        assertNotNull(balanceFilter.getTargetAudiences());
    }

    /**
     * Tests the addGroup() method of the class BalanceFilter.
     */
    @Test
    public void testAddGroup() {
        System.out.println("addGroup()");

        balanceFilter = new BalanceFilter();

        balanceFilter.addGroup("Test group");

        assertTrue(balanceFilter.getGroups().contains("Test group"));
    }

    /**
     * Tests the addLecturer() method of the class BalanceFilter.
     */
    @Test
    public void testAddLecturer() {
        System.out.println("addLecturer()");

        balanceFilter = new BalanceFilter();

        balanceFilter.addLecturer("Test lecturer");

        assertTrue(balanceFilter.getLecturers().contains("Test lecturer"));
    }

    /**
     * Tests the addTargetAudience() method of the class BalanceFilter.
     */
    @Test
    public void testAddTargetAudience() {
        System.out.println("addTargetAudience()");

        balanceFilter = new BalanceFilter();

        balanceFilter.addTargetAudience("Test audience");

        assertTrue(balanceFilter.getTargetAudiences().contains("Test audience"));
    }

    /**
     * Tests the removeGroup() method of the class BalanceFilter.
     */
    @Test
    public void testRemoveGroup() {
        System.out.println("removeGroup()");

        balanceFilter = new BalanceFilter();

        balanceFilter.addGroup("Test group");

        assertTrue(balanceFilter.getGroups().contains("Test group"));

        balanceFilter.removeGroup("Not Test group");
        balanceFilter.removeGroup("Test group");

        assertTrue(balanceFilter.getGroups().isEmpty());
    }

    /**
     * Tests the removeLecturer() method of the class BalanceFilter.
     */
    @Test
    public void testRemoveLecturer() {
        System.out.println("removeLecturer()");

        balanceFilter = new BalanceFilter();

        balanceFilter.addLecturer("Test lecturer");

        assertTrue(balanceFilter.getLecturers().contains("Test lecturer"));

        balanceFilter.removeLecturer("Not Test lecturer");
        balanceFilter.removeLecturer("Test lecturer");

        assertTrue(balanceFilter.getLecturers().isEmpty());
    }

    /**
     * Tests the method removeTargetAudience of the class BalanceFilter.
     */
    @Test
    public void testRemoveTargetAudience() {
        System.out.println("removeTargetAudience()");

        balanceFilter = new BalanceFilter();

        balanceFilter.addTargetAudience("Test audience");

        assertTrue(balanceFilter.getTargetAudiences().contains("Test audience"));

        balanceFilter.removeTargetAudience("Not Test audience");
        balanceFilter.removeTargetAudience("Test audience");

        assertTrue(balanceFilter.getTargetAudiences().isEmpty());
    }
}
