package de.aidger.model.models;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Tests the Assistant class.
 *
 * @author aidGer Team
 */
public class AssistantTest {

    protected Assistant assistant = null;

    @Before
    public void setUp() {
        assistant = new Assistant();
        assistant.setId(1);
        assistant.setEmail("test@example.com");
        assistant.setFirstName("Test");
        assistant.setLastName("Tester");
        assistant.setQualification("Tester");
    }

    /**
     * Test of clone method, of class Assistant.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Assistant result = assistant.clone();

        assertEquals(assistant.getId(), result.getId());
        assertEquals(assistant.getEmail(), result.getEmail());
        assertEquals(assistant.getFirstName(), result.getFirstName());
        assertEquals(assistant.getLastName(), result.getLastName());
        assertEquals(assistant.getQualification(), result.getQualification());
    }

    /**
     * Test of equals method, of class Assistant.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Assistant result = assistant.clone();

        assertEquals(assistant, result);
        assertFalse(assistant.equals(new Object()));
    }

    /**
     * Test of hashCode method, of class Assistant.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Assistant result = assistant.clone();

        assertEquals(assistant.hashCode(), result.hashCode());
    }

}