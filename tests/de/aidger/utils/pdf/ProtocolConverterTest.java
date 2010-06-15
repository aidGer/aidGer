/**
 * 
 */
package de.aidger.utils.pdf;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Date;

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
public class ProtocolConverterTest {

    private static Activity activity = null;

    private static Assistant assistant = null;

    private static Course course = null;

    private static FinancialCategory financial = null;

    private ProtocolConverter protocolConverter = null;

    public ProtocolConverterTest() {

    }

    @After
    public void cleanUp() throws AdoHiveException {

        course.remove();

        assistant.remove();

        activity.remove();

        financial.remove();

        File file = new File("Test_Report.pdf");
        file.delete();
        file = new File("Test_Report.test.pdf");
        file.delete();
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
    }

    /**
     * Tests the constructor of the class ProtocolConverter.
     * 
     * @throws AdoHiveException
     */
    @Test
    public void testConstructor() throws AdoHiveException {
        System.out.println("Constructor");

        File testFile = new File("Test_Report");
        protocolConverter = new ProtocolConverter(testFile, -1);

        File file = new File("Test_Report.pdf");
        assertTrue(file.exists());

        testFile = new File("Test_Report.test");
        protocolConverter = new ProtocolConverter(testFile, -1);

        file = new File("Test_Report.test.pdf");
        assertTrue(file.exists());
    }
}
