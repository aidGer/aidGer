package de.aidger.model.models;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

/**
 * Tests the Activity class.
 *
 * @author aidGer Team
 */
public class ActivityTest {


    private Activity activity = null;

    @org.junit.BeforeClass
    public static void beforeClass() {
        de.aidger.model.Runtime.getInstance().initialize();
    }

    /**
     * Sets the Activity class up for tests.
     */
    @Before
    public void setUp() {
        activity = new Activity();
        activity.setId(1);
        activity.setAssistantId(1);
        activity.setContent("New assistant");
        activity.setCourseId(1);
        activity.setDate(new Date(2010, 10, 10));
        activity.setDocumentType("Test Type");
        activity.setProcessor("Test User");
        activity.setRemark("Remark");
        activity.setSender("Test Sender");
        activity.setType("Test Type");
    }

    /**
     * Test of clone method, of class Activity.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Activity result = activity.clone();

        assertEquals(activity.getId(), result.getId());
        assertEquals(activity.getAssistantId(), result.getAssistantId());
        assertEquals(activity.getContent(), result.getContent());
        assertEquals(activity.getCourseId(), result.getCourseId());
        assertEquals(activity.getDate(), result.getDate());
        assertEquals(activity.getDocumentType(), result.getDocumentType());
        assertEquals(activity.getProcessor(), result.getProcessor());
        assertEquals(activity.getRemark(), result.getRemark());
        assertEquals(activity.getSender(), result.getSender());
        assertEquals(activity.getType(), result.getType());
    }

    /**
     * Test of equals method, of class Activity.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Activity result = activity.clone();
        
        assertEquals(activity, result);
    }
    /**
     * Test of getActivities method, of class Activity.
     */
    @Test
    public void testGetActivities_Assistant() throws AdoHiveException {
        System.out.println("getActivities");

        Assistant assistant = new Assistant();
        assistant.save();

        activity.setAssistantId(assistant.getId());
        activity.setId(-1);
        activity.save();
        List result = activity.getActivities(assistant);

        assertNotNull(result);
        assertTrue(result.size() == 1);
    }

    /**
     * Test of getActivities method, of class Activity.
     */
    @Test
    public void testGetActivities_Course() throws AdoHiveException {
        System.out.println("getActivities");

        Course course = new Course();
        course.save();

        activity.setCourseId(course.getId());
        activity.setId(-1);
        activity.save();

        List result = activity.getActivities(course);

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

}
