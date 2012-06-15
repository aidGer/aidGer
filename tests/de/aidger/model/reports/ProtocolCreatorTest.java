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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;

import de.aidger.model.validators.ValidationException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import de.aidger.model.models.FinancialCategory;
import de.aidger.utils.DateUtils;

/**
 * Tests the class ProtocolCreator
 * 
 * @author aidGer Team
 */
public class ProtocolCreatorTest {

    private static Activity activity = null;

    private static Assistant assistant = null;

    private static Course course = null;

    private static FinancialCategory financial = null;

    private static ProtocolCreator protocolCreator = null;

    public ProtocolCreatorTest() {
    }

    @After
    public void cleanUp() throws ValidationException {

        activity.remove();

        course.remove();

        assistant.remove();

        financial.remove();

    }

    @BeforeClass
    public static void beforeClassSetUp() {
        de.aidger.model.Runtime.getInstance().initialize();
    }

    /**
     * Prepares this test.
     */
    @Before
    public void setUp() {

        assistant = new Assistant();
        assistant.setEmail("test@example.com");
        assistant.setFirstName("Test");
        assistant.setLastName("Tester");
        assistant.setQualification("g");
        assistant.save();

        financial = new FinancialCategory();
        financial.setBudgetCosts(new Integer[] { 100, 200 });
        financial.setCostUnits(new Integer[] { 10001000, 20002000 });
        financial.setName("Tester");
        financial.setYear((short) 2010);
        financial.save();

        course = new Course();
        course.setAdvisor("Tester");
        course.setDescription("Description");
        course.setFinancialCategoryId(financial.getId());
        course.setGroup("2");
        course.setLecturer("Test Tester");
        course.setNumberOfGroups(3);
        course.setPart('a');
        course.setRemark("Remark");
        course.setScope("Sniper Scope");
        course.setSemester("SS09");
        course.setTargetAudience("Testers");
        course.setUnqualifiedWorkingHours(100.0);
        course.save();

        activity = new Activity();
        activity.clearTable();
        activity.setId((long) 1);
        activity.setAssistantId(assistant.getId());
        activity.setContent("New assistant");
        activity.setCourseId(course.getId());
        activity.setDate(new Date(100));
        activity.setDocumentType("Test Type");
        activity.setProcessor("T");
        activity.setRemark("Remark");
        activity.setSender("Test Sender");
        activity.setType("Test Type");
        activity.save();

        protocolCreator = new ProtocolCreator();
    }

    /**
     * Tests the constructor of the class ProtocolCreator.
     */
    @Test
    public void testConstructor() {
        System.out.println("Constructor");

        assertNotNull(protocolCreator);
    }

    /**
     * Tests the createProtocol method of ProtocolCreator.
     */
    @Test
    public void testCreateProtocol() {
        System.out.println("createProtocol()");

        ArrayList activities = protocolCreator.createProtocol(-1);

        Object[] resultActivity = {
                (new Assistant().getById(activity.getAssistantId()))
                    .getFirstName()
                        + " "
                        + (new Assistant().getById(activity.getAssistantId()))
                            .getLastName(),
                (new Course().getById(activity.getCourseId())).getDescription(),
                activity.getType(), activity.getDate(), activity.getContent(),
                activity.getSender(), activity.getProcessor(),
                activity.getRemark() };

        boolean resultBoolean = false;

        for (Object listActivity : activities) {
            for (int i = 0; i < 8; i++) {
                if (((Object[]) listActivity)[i].equals(resultActivity[i])) {
                    resultBoolean = true;
                } else {
                    resultBoolean = false;
                }
            }
        }

        assertTrue(resultBoolean);

        activities = protocolCreator.createProtocol(0);

        assertTrue(activities.isEmpty());

    }

    /**
     * Tests the method getObjectArray() of the class ProtocolCreator.
     */
    @Test
    public void testGetObjectArray() {
        Object[] resultActivity = new Object[8];

        String assistantName = (new Assistant().getById(activity
            .getAssistantId())).getFirstName()
                + " "
                + (new Assistant().getById(activity.getAssistantId()))
                    .getLastName();
        resultActivity[0] = assistantName;
        String courseName = (new Course().getById(activity.getCourseId()))
            .getDescription()
                + "("
                + new Course().getById(activity.getCourseId()).getSemester()
                + ")";
        resultActivity[1] = courseName;
        resultActivity[2] = activity.getType();
        resultActivity[3] = DateUtils.formatDate(activity.getDate());
        resultActivity[4] = activity.getContent();
        resultActivity[5] = activity.getSender();
        resultActivity[6] = activity.getProcessor();
        resultActivity[7] = activity.getRemark();

        assertArrayEquals(resultActivity, protocolCreator
            .getObjectArray(activity));
    }
}
