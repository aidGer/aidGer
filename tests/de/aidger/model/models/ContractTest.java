package de.aidger.model.models;

import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Contract class.
 *
 * @author aidGer Team
 */
public class ContractTest {

    /**
     * Test of clone method, of class Contract.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Contract c = new Contract();
        c.setId(1);
        c.setCompletionDate(new Date(100));
        c.setConfirmationDate(new Date(10));
        c.setDelegation(false);
        c.setEndDate(new Date(1000));
        c.setStartDate(new Date(20));
        c.setType("Type");

        Contract result = c.clone();

        assertEquals(c.getId(), result.getId());
        assertEquals(c.getCompletionDate(), result.getCompletionDate());
        assertEquals(c.getConfirmationDate(), result.getConfirmationDate());
        assertEquals(c.isDelegation(), result.isDelegation());
        assertEquals(c.getEndDate(), result.getEndDate());
        assertEquals(c.getStartDate(), result.getStartDate());
        assertEquals(c.getType(), result.getType());
    }

}