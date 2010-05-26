package de.aidger.model.models;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Assistant class.
 *
 * @author aidGer Team
 */
public class AssistantTest {

    /**
     * Test of clone method, of class Assistant.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Assistant a = new Assistant();
        a.setId(1);
        a.setEmail("test@example.com");
        a.setFirstName("Test");
        a.setLastName("Tester");
        a.setQualification("Tester");

        Assistant result = a.clone();

        assertEquals(a.getId(), result.getId());
        assertEquals(a.getEmail(), result.getEmail());
        assertEquals(a.getFirstName(), result.getFirstName());
        assertEquals(a.getLastName(), result.getLastName());
        assertEquals(a.getQualification(), result.getQualification());
    }

}