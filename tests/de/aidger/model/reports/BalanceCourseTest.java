/**
 * 
 */
package de.aidger.model.reports;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Phil
 *
 */
public class BalanceCourseTest {
	
	private BalanceCourse balanceCourse = null;
	
	public BalanceCourseTest() {
		new BalanceCourse();
	}
	
	/**
	 * Sets up the balance course for testing.
	 */
	@Before
	public void setUp() {
		balanceCourse = new BalanceCourse();
		balanceCourse.setTitle("Test title");
		balanceCourse.setLecturer("Test Lecturer");
		balanceCourse.setBasicAWS(100);
		balanceCourse.setPart('t');
		balanceCourse.setPlannedAWS(100);
		balanceCourse.setResources(100);
		balanceCourse.setStudentFees(100);
		balanceCourse.setTargetAudience("Test Audience");
	}
	
	/**
	 * Tests the constructor of the BalanceCourse class.
	 */
	@Test
	public void testConstructor() {
		System.out.println("Constructor");
		
		BalanceCourse result = new BalanceCourse();
		
		assertNotNull(result);
		assertEquals(result.getTitle(),"");
		assertEquals(result.getBasicAWS(), 0.0, 0);
		assertEquals(result.getLecturer(), "");
		assertEquals(result.getPart(), '-');
		assertEquals(result.getPlannedAWS(), 0.0, 0);
		assertEquals(result.getResources(), 0);
		assertEquals(result.getStudentFees(), 0);
		assertEquals(result.getTargetAudience(), "");
	}
	
	/**
	 * Tests the getCourseObject() method of the BalanceCourse class.
	 */
	@Test
	public void testGetCourseObject() {
		System.out.println("getCourseObject()");
		
		Object[] values = balanceCourse.getCourseObject();
		
		assertNotNull(values);
		assertEquals(values[0], balanceCourse.getTitle());
		assertEquals(values[1], balanceCourse.getPart());
		assertEquals(values[2], balanceCourse.getLecturer());
		assertEquals(values[3], balanceCourse.getTargetAudience());
		assertEquals(values[4], balanceCourse.getPlannedAWS());
		assertEquals(values[5], balanceCourse.getBasicAWS());
		assertEquals(values[6], balanceCourse.getStudentFees());
		assertEquals(values[7], balanceCourse.getResources());
	}
	
}
