package de.unistuttgart.iste.se.adohive.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

import de.unistuttgart.iste.se.adohive.controller.IAdoHiveManager;
import de.unistuttgart.iste.se.adohive.controller.IContractManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IContract;

public abstract class IContractManagerTest extends IAdoHiveManagerTest<IContract> {


	@Override
	protected int getItemId(IContract item) {
		return item.getId();
	}

	@Override
	protected List<Object> getItemKeys(IContract item) {
		return null;
	}

	@Override
	protected boolean itemClassHasId() {
		return true;
	}

	@Override
	protected void modifyItem(IContract item) {
		if ("Anschluss".equals(item.getType())) {
			item.setType("Neuvertrag");
		} else {
			item.setType("Anschluss");
		}
		
	}

	@Override
	public IContract newE() throws AdoHiveException {
		return getTestDataProvider().newIContract();
	}
	
	//TODO: Rewrite this test
	//@Test
	/*public void testGetContracts() throws AdoHiveException {
		IContractManager instance = (IContractManager) getInstance();
		
		IContract[] c = new IContract[15];
		for (int i = 0; i< 15; i++) {
			c[i] = newE();
			c[i].setCompletionDate(new Date(i));
			c[i].setConfirmationDate(new Date(14 - i));
			instance.add(c[i]);
		}
		
		for (int i = 0; i < 15; i++) {
			for (int j = i; i < 15; j++) {
				List<IContract> l = instance.getContracts(new Date(i), new Date(j));
				assertEquals((j - i + 1) * 2, l.size());
				for (IContract co : l) {
					assertTrue((co.getCompletionDate().getTime() >= i && co.getCompletionDate().getTime() <= j) &&
							(co.getConfirmationDate().getTime() >= i && co.getConfirmationDate().getTime() <= j));
				}
			}
		}
		
		
	}*/
}
