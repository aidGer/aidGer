/**
 * 
 */
package de.aidger.utils.pdf;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.aidger.model.Runtime;
import de.aidger.model.models.Assistant;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * Tests the class ActivityReportConverter.
 * 
 * @author aidGer Team
 */
public class ActivityReportConverterTest {

    private Assistant assistant;

    /**
     * Sets up these tests.
     */
    @BeforeClass
    public static void beforeClassSetUp() {
        Runtime.getInstance().initialize();
    }

    /**
     * Sets up every test.
     * 
     * @throws AdoHiveException
     */
    @Before
    public void setUp() throws AdoHiveException {
        assistant = new Assistant();
        assistant.setFirstName("Test");
        assistant.setLastName("Tester");
        assistant.setEmail("test@tester.com");
        assistant.setQualification("u");
        assistant.save();
    }

    /**
     * Tests the Constructor of the class ActivityReportConverter.
     */
    @Test
    public void testConstructor() {
        System.out.println("Constructor");

        File testFile = new File("Test_Report");

        ArrayList<String[]> testRow = new ArrayList<String[]>();
        testRow
            .add(new String[] { "01.2010 - 01.2010", "Test Course", "20.0" });

        ActivityReportConverter converter = new ActivityReportConverter(
            testFile, testRow, "blibber");

        File file = new File("Test_Report.pdf");

        assertTrue(file.exists());

        file.delete();

        testFile = new File("Test_Report.pdf");

        converter = new ActivityReportConverter(testFile, testRow, "blibber");

        file = new File("Test_Report.pdf");

        assertTrue(file.exists());
    }

    /**
     * Cleans up after every test.
     * 
     * @throws AdoHiveException
     */
    @After
    public void cleanUp() throws AdoHiveException {
        assistant.remove();
    }
}
