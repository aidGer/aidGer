/**
 * 
 */
package de.aidger.model.reports;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import de.aidger.model.models.FinancialCategory;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * @author Phil
 * 
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
    public void cleanUp() throws AdoHiveException {

        course.remove();

        assistant.remove();

        activity.remove();

        financial.remove();

    }

    /**
     * Prepares this test.
     * 
     * @throws AdoHiveException
     */
    @Before
    public void setUp() throws AdoHiveException {
        de.aidger.model.Runtime.getInstance().initialize();

        assistant = new Assistant();
        assistant.setEmail("test@example.com");
        assistant.setFirstName("Test");
        assistant.setLastName("Tester");
        assistant.setQualification("g");
        assistant.save();

        financial = new FinancialCategory();
        financial.setBudgetCosts(new int[] { 100, 200 });
        financial.setFunds(new int[] { 10001000, 20002000 });
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
        course.setUnqualifiedWorkingHours(100);
        course.save();

        activity = new Activity();
        activity.clearTable();
        activity.setId(1);
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
     * 
     * @throws AdoHiveException
     */
    @Test
    public void testCreateProtocol() throws AdoHiveException {
        System.out.println("createProtocol()");

        Vector activities = protocolCreator.createProtocol(-1);

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
}
