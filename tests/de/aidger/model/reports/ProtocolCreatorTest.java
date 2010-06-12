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
public class ProtocolCreatorTest {
	
	private ProtocolCreator protocolCreator = null;
	
	public ProtocolCreatorTest() {
	}
	
	@Before
	public void setUp() {
		protocolCreator = new ProtocolCreator();
	}
	
	/**
	 * Tests the constructor of the class ProtocolCreator.
	 */
	@Test
	public void testConstructor() {
		System.out.println("Constructor");
		assertNotNull(protocolCreator);
	}
	
	/**
	 * Tests the createProtocol method of ProtocolCreator.
	 */
	@Test
	public void testNumberOfDays() {
		System.out.println("createProtocol()");
		protocolCreator.setNumberOfDays(-1);
		assertEquals(-1, protocolCreator.getNumberOfDays());
		protocolCreator.createProtocol();
		assertNotNull(protocolCreator.getViewerTab());
	}
}
