package de.unistuttgart.iste.se.adohive.controller.derby.test;

import org.junit.Before;
import org.junit.BeforeClass;

import junit.framework.Assert;
import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.controller.ansi.AnsiAdoHiveController;
import de.unistuttgart.iste.se.adohive.controller.test.IContractManagerTest;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.test.ITestDataProvider;
import de.unistuttgart.iste.se.adohive.model.test.IndependentTestDataProvider;

public class DerbyContractManagerTest extends IContractManagerTest {

	private static AdoHiveController controller;
	private static ITestDataProvider tdp = new IndependentTestDataProvider();

	@Override
	protected ITestDataProvider getTestDataProvider() {
		return tdp;
	}
	
	@BeforeClass
	public static void setController() {
		if (controller == null) {
			try {
				controller = AdoHiveController.getInstance();
				AdoHiveController.getInstance().clearAll();
			} catch (AdoHiveException e) {
				e.printStackTrace();
				Assert.fail("could not load DerbyAdoHiveController");
			}
		}
	}
	
	@Before
	public void initInstance() throws AdoHiveException {
		instance = controller.getContractManager();
		instance.clear();
	}


}
