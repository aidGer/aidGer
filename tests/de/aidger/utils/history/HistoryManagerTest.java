package de.aidger.utils.history;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import de.aidger.model.Runtime;
import java.io.File;

/**
 * Tests the HistoryManager class.
 *
 * @author aidGer Team
 */
public class HistoryManagerTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
        Runtime.getInstance().initialize();
    }

    @Before
    public void setUp() {
        File historyFile = new File(Runtime.getInstance().getConfigPath() + "/history");
        if (historyFile.exists()) {
            historyFile.delete();
        }
    }

    /**
     * Test of getInstance method, of class HistoryManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");

        assertNotNull(HistoryManager.getInstance());
    }

    /**
     * Test of getEvents method, of class HistoryManager.
     */
    @Test
    public void testGetEvents() {
        System.out.println("getEvents");

        assertTrue(HistoryManager.getInstance().getEvents().isEmpty());

        HistoryManager.getInstance().addEvent(new HistoryEvent());

        assertTrue(HistoryManager.getInstance().getEvents().size() > 0);

    }

    /**
     * Test of addEvents method, of class HistoryManager.
     */
    @Test
    public void testAddEvent() {
        System.out.println("addEvents");

        HistoryEvent evt = new HistoryEvent();
        evt.date = new java.sql.Date(0);
        evt.id = 0;
        evt.type = "Assistant";
        evt.status = HistoryEvent.Status.Added;

        HistoryManager.getInstance().addEvent(evt);

        assertFalse(HistoryManager.getInstance().getEvents().isEmpty());
    }

    /**
     * Test of loadFromFile method, of class HistoryManager.
     */
    @Test
    public void testLoadFromFile() {
        System.out.println("loadFromFile");

        testAddEvent();

        HistoryManager.getInstance().loadFromFile();

        assertFalse(HistoryManager.getInstance().getEvents().isEmpty());
    }

}