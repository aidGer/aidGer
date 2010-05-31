 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.aidger.model;

import de.aidger.model.models.Course;
import de.aidger.model.models.HourlyWage;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rmbl
 */
public class AbstractModelTest {

    /**
     * Test of getAll method, of class AbstractModel.
     */
    @Test
    public void testGetAll() throws AdoHiveException {
        System.out.println("getAll");
        Course c = new Course();
        c.save();

        Course d = new Course();
        d.save();

        List<Course> list = d.getAll();

        assertNotNull(list);
        assertEquals(list.size(), 2);
        assertEquals(list.get(0), c);
        assertEquals(list.get(1), d);
    }

    /**
     * Test of getById method, of class AbstractModel.
     */
    @Test
    public void testGetById() throws AdoHiveException {
        System.out.println("getById");

        Course c = new Course();
        c.save();

        Course course = (Course) c.getById(c.getId());

        assertNotNull(course);
        assertEquals(c.getId(), course.getId());
    }

    /**
     * Test of getByKeys method, of class AbstractModel.
     */
    @Test
    public void testGetByKeys() throws AdoHiveException {
        System.out.println("getByKeys");

        HourlyWage h = new HourlyWage();
        h.setQualification("Tester");
        h.setMonth((byte) 10);
        h.setYear((short) 2010);
        h.save();

        //TODO: Wait for good documentation. Current one doesn't specify correct keys
        HourlyWage result = (HourlyWage) h.getByKeys("Tester", (byte) 10,
                (short) 2010);

        assertNotNull(result);
        assertEquals(h, result);
    }

    /**
     * Test of save method, of class AbstractModel.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");

        Course c = new Course();
        c.save();

        assertTrue(c.getId() > 0);
        assertEquals(c, c.getById(c.getId()));

        c.setDescription("Test");
        c.save();

        Course result = (Course) c.getById(c.getId());

        assertEquals(c.getDescription(), result.getDescription());
    }

    /**
     * Test of remove method, of class AbstractModel.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove");

        Course c = new Course();
        c.save();
        int id = c.getId();

        c.remove();

        assertNull(c.getById(id));
        assertTrue(c.getId() <= 0);
    }

}