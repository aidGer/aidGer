package de.aidger.model.models;

import java.util.Date;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Tests the Contract class.
 *
 * @author aidGer Team
 */
public class ContractTest {
    
    protected Contract contract = null;

    @Before
    public void setUp() {
        contract = new Contract();
        contract.setId(1);
        contract.setCompletionDate(new Date(100));
        contract.setConfirmationDate(new Date(10));
        contract.setDelegation(false);
        contract.setEndDate(new Date(1000));
        contract.setStartDate(new Date(20));
        contract.setType("Type");
    }

    /**
     * Test of clone method, of class Contract.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Contract result = contract.clone();

        assertEquals(contract.getId(), result.getId());
        assertEquals(contract.getCompletionDate(), result.getCompletionDate());
        assertEquals(contract.getConfirmationDate(), result.getConfirmationDate());
        assertEquals(contract.isDelegation(), result.isDelegation());
        assertEquals(contract.getEndDate(), result.getEndDate());
        assertEquals(contract.getStartDate(), result.getStartDate());
        assertEquals(contract.getType(), result.getType());
    }

    /**
     * Test of equals method, of class Contract.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Contract result = contract.clone();

        assertEquals(contract, result);
        assertFalse(contract.equals(new Object()));
    }

    /**
     * Test of hashCode method, of class Contract.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Contract result = contract.clone();

        assertEquals(contract.hashCode(), result.hashCode());
    }

}