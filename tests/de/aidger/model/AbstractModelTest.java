/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.aidger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import de.aidger.model.models.Employment;
import de.aidger.model.models.HourlyWage;
import de.aidger.model.models.Assistant;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IHourlyWage;
import de.unistuttgart.iste.se.adohive.model.IAssistant;

/**
 * 
 * @author rmbl
 */
public class AbstractModelTest {

    public AbstractModelTest() {
        Runtime.getInstance().initialize();
    }

    /**
     * Test of getAll method, of class AbstractModel.
     */
    @Test
    public void testGetAll() throws AdoHiveException {
        System.out.println("getAll");

        HourlyWage h = new HourlyWage();
        h.clearTable();

        h.setMonth((byte) 10);
        h.setQualification("Q");
        h.setWage(new java.math.BigDecimal(200));
        h.setYear((short) 2010);
        h.save();

        HourlyWage g = new HourlyWage();
        g.setMonth((byte) 10);
        g.setQualification("W");
        g.setWage(new java.math.BigDecimal(200));
        g.setYear((short) 2010);
        g.save();

        List<IHourlyWage> list = h.getAll();

        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals(h, new HourlyWage(list.get(0)));
        assertEquals(g, new HourlyWage(list.get(1)));

        /*Assistant a = new Assistant();
        a.clearTable();

        a.setEmail("test@example.com");
        a.setFirstName("Test");
        a.setLastName("Tester");
        a.setQualification("Q");
        a.save();

        Assistant b = a.clone();
        b.setId(-1);
        b.save();

        List<IAssistant> list = a.getAll();

        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals(a, new Assistant(list.get(0)));
        assertEquals(b, new Assistant(list.get(1)));*/
    }

    /**
     * Test of getById method, of class AbstractModel.
     */
    @Test
    public void testGetById() throws AdoHiveException {
        System.out.println("getById");

        HourlyWage h = new HourlyWage();
        h.clearTable();

        h.setMonth((byte) 10);
        h.setQualification("Q");
        h.setWage(new java.math.BigDecimal(200));
        h.setYear((short) 2010);
        h.save();

        IHourlyWage result = h.getById(h.getId());

        assertNotNull(result);
        assertEquals(h.getId(), result.getId());
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

        IHourlyWage result = h.getByKeys("Tester", (byte) 10,
                (short) 2010);

        assertNotNull(result);
        assertEquals(h, result);

        Employment e = new Employment();
        e.setAssistantId(1);
        e.setContractId(1);
        e.setCourseId(0);
        e.setCostUnit("0711");
        e.setFunds(1);
        e.setHourCount(40);
        e.setMonth((byte) 10);
        e.setQualification("O");
        e.setRemark("Remark");
        e.setYear((short) 2010);
        e.save();

        Employment res = (Employment) e.getByKeys(e.getId());

        assertEquals(e, res);
    }

    /**
     * Test of size method, of class AbstractModel.
     */
    @Test
    public void testSize() throws AdoHiveException {
        System.out.println("size");

        Assistant a = new Assistant();
        
        int size = a.size();

        a.setEmail("test@example.com");
        a.setFirstName("Test");
        a.setLastName("Tester");
        a.setQualification("Q");
        a.save();

        assertTrue(a.size() == size + 1);
    }

    /**
     * Test of isEmpty method, of class AbstractModel.
     */
    @Test
    public void testIsEmpty() throws AdoHiveException {
        System.out.println("isEmpty");

        Assistant a = new Assistant();
        a.setEmail("test@example.com");
        a.setFirstName("Test");
        a.setLastName("Tester");
        a.setQualification("Q");
        a.save();

        assertTrue(!a.isEmpty());

        a.clearTable();
        assertTrue(a.isEmpty());
    }

    /**
     * Test of isInDatabase method, of class AbstractModel.
     */
    @Test
    public void testIsInDatabase() throws AdoHiveException {
        System.out.println("isInDatabase");

        Assistant a = new Assistant();
        a.setEmail("test@example.com");
        a.setFirstName("Test");
        a.setLastName("Tester");
        a.setQualification("Q");

        assertTrue(!a.isInDatabase());

        a.save();
        assertTrue(a.isInDatabase());
    }

    /**
     * Test of clearTable method, of class AbstractModel.
     */
    @Test
    public void testClearTable() throws AdoHiveException {
        System.out.println("clearTable");

        Assistant a = new Assistant();
        a.setEmail("test@example.com");
        a.setFirstName("Test");
        a.setLastName("Tester");
        a.setQualification("Q");
        a.save();

        assertTrue(a.size() > 0);

        a.clearTable();
        assertTrue(a.size() == 0);
    }

    /**
     * Test of save method, of class AbstractModel.
     */
    @Test
    public void testSave() throws AdoHiveException {
        System.out.println("save");

        Employment e = new Employment();
        e.setCostUnit("0711");
        e.setQualification("none");
        e.save();

        assertTrue(e.getId() > 0);
        assertEquals(e, e.getById(e.getId()));

        e.setAssistantId(1);
        e.setContractId(1);
        e.save();

        assertEquals(e, e.getById(e.getId()));
    }

    /**
     * Test of remove method, of class AbstractModel.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove");

        Employment e = new Employment();
        e.setCostUnit("0711");
        e.setQualification("none");
        e.save();
        int id = e.getId();

        e.remove();

        assertNull(e.getById(id));
        assertTrue(e.getId() <= 0);
    }

    /**
     * Test of addError method, of class AbstractModel.
     */
    @Test
    public void testAddError() {
        System.out.println("addError");

        Employment e = new Employment();
        e.addError("error message");

        List<String> result = e.getErrors();
        assertTrue(result.size() == 1);
        assertTrue(result.get(0).equals("error message"));
    }

    /**
     * Test of addError method, of class AbstractModel.
     */
    @Test
    public void testAddError_field() {
        System.out.println("addError_field");

        Employment e = new Employment();
        e.addError("field", "error message");
        e.addError("other-field", "other error message");

        List<String> result = e.getErrorsFor("field");

        assertTrue(result.size() == 1);
        assertTrue(e.getErrors().size() == 2);
        assertTrue(result.get(0).equals("field error message"));
    }

    @Test
    public void testResetErrors() {
        System.out.println("resetErrors");

        Employment e = new Employment();
        e.addError("error message");
        e.addError("field", "error message");

        assertTrue(e.getErrors().size() == 2);
        assertTrue(e.getErrorsFor("field").size() == 1);

        e.resetErrors();

        assertTrue(e.getErrors().isEmpty());
        assertNull(e.getErrorsFor("field"));
    }

}
