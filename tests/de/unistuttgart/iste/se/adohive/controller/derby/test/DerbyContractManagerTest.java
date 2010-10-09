package de.unistuttgart.iste.se.adohive.controller.derby.test;

import junit.framework.Assert;
import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.controller.derby.DerbyAdoHiveController;
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

	@Override
	protected AdoHiveController getController() {
		if (controller == null) {
			 try {
				controller = DerbyAdoHiveController.getInstance();
			} catch (AdoHiveException e) {
				Assert.fail("could not load DerbyAdoHiveController");
				e.printStackTrace();
			}
		}
		
		return controller;
	}

}
