package de.aidger.model.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.aidger.model.validators.ValidationException;

import siena.SienaException;

public class CostUnitTest {
	
	protected CostUnit costUnit = null;
	
	@Rule
    public ExpectedException exception = ExpectedException.none();
	
    @BeforeClass
    public static void beforeClassSetUp() throws SienaException {
        de.aidger.model.Runtime.getInstance().initialize();
    }
    
    @Before
    public void setUp() {
    	costUnit = new CostUnit();
    	costUnit.setCostUnit("10101010");
    	costUnit.setFunds("Test");
    	costUnit.setTokenDB("a");
    }
    
    /**
     * Cleans up after the tests.
     */
    @After
    public void cleanUp() throws ValidationException {
        costUnit.remove();
    }
    
    /**
     * Test of constructor, of class CostUnit.
     */
    @Test
    public void testConstructor() throws SienaException {
        System.out.println("Constructor");

        costUnit.save();

        CostUnit result = costUnit.getById(costUnit.getId());

        assertNotNull(result);
        assertEquals(costUnit, result);
    }

    /**
     * Test of validation, of class Contract.
     */
    @Test
    public void testValidation() throws SienaException {
    	System.out.println("validate");
    	
    	costUnit.save();
        assertFalse(costUnit.getAll().isEmpty());
        costUnit.clearTable();
        
        exception.expect(ValidationException.class);
        
        costUnit.setCostUnit(null);
        costUnit.save();
        assertTrue(costUnit.getAll().isEmpty());
        costUnit.resetErrors();
       
        costUnit.setCostUnit("101010");
        costUnit.save();
        assertTrue(costUnit.getAll().isEmpty());
        costUnit.resetErrors();
        costUnit.setCostUnit("10101010");
        
        costUnit.setFunds(null);
        costUnit.save();
        assertTrue(costUnit.getAll().isEmpty());
        costUnit.resetErrors();
        costUnit.setFunds("Test");
        
        costUnit.setTokenDB(null);
        costUnit.save();
        assertTrue(costUnit.getAll().isEmpty());
        costUnit.resetErrors();
        costUnit.setTokenDB("a");
        
        exception = ExpectedException.none();
    }
    
    /**
     * Test of clone method, of class CostUnit.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        CostUnit result = costUnit.clone();

        assertEquals(costUnit.getId(), result.getId());
        assertEquals(costUnit.getCostUnit(), result.getCostUnit());
        assertEquals(costUnit.getFunds(), result.getFunds());
        assertEquals(costUnit.getTokenDB(), result.getTokenDB());
    }
    
    /**
     * Test of equals method, of class Contract.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        CostUnit result = costUnit.clone();

        assertEquals(costUnit, result);
        assertNotEquals(costUnit, new Object());
    }

    /**
     * Test of hashCode method, of class Contract.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CostUnit result = costUnit.clone();

        assertEquals(costUnit.hashCode(), result.hashCode());
    }
    
    @Test
    public void testGetAllCostUnits() {
    	System.out.println("getAllCostUnits");
    	
    	costUnit.clearTable();
    	costUnit.save();
    	
    	CostUnit c2 = costUnit.clone();
    	c2.markAsNew();
    	c2.save();
    	
    	assertEquals(1, costUnit.getAllCostUnits().size());
    	
    	c2.setCostUnit("20202020");
    	c2.save();
    	
    	assertEquals(2, costUnit.getAllCostUnits().size());
    	
    	c2.remove();
    }
    
    @Test
    public void testCompareTo() {
    	System.out.println("compareTo");
    	
    	CostUnit c2 = costUnit.clone();
    	assertEquals(0, costUnit.compareTo(c2));
    	
    	c2.setFunds("Not Test");
    	assertTrue(costUnit.compareTo(c2) > 0);
    }
}
