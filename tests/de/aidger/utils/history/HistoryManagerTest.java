package de.aidger.utils.history;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import de.aidger.model.Runtime;
import java.io.File;
import org.junit.After;

/**
 * Tests the HistoryManager class.
 *
 * @author aidGer Team
 */
public class HistoryManagerTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
        Runtime.getInstance().initialize();
        Runtime.getInstance().setConfigPath("./");
    }

    @Before
    public void setUp() {
        File historyFile = new File(Runtime.getInstance().getConfigPath() + "/history");
        if (historyFile.exists()) {
            historyFile.delete();
        }
    }

    @After
    public void cleanUp() {
    	(new File(Runtime.getInstance().getConfigPath() + "/history")).delete();
    }

    /**
     * Test of getInstance method, of class HistoryManager.
     */
    @Test
    public void testGetInstance() throws HistoryException {
        System.out.println("getInstance");

        assertNotNull(HistoryManager.getInstance());
    }

    /**
     * Test of getEvents method, of class HistoryManager.
     */
    @Test
    public void testGetEvents() throws HistoryException {
        System.out.println("getEvents");

        assertTrue(HistoryManager.getInstance().getEvents().isEmpty());

        HistoryManager.getInstance().addEvent(new HistoryEvent());

        assertTrue(HistoryManager.getInstance().getEvents().size() > 0);

    }

    /**
     * Test of addEvents method, of class HistoryManager.
     */
    @Test
    public void testAddEvent() throws HistoryException {
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
    public void testLoadFromFile() throws HistoryException {
        System.out.println("loadFromFile");

        testAddEvent();

        HistoryManager.getInstance().loadFromFile();

        assertFalse(HistoryManager.getInstance().getEvents().isEmpty());
    }

}