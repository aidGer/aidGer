package de.aidger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.aidger.model.models.Assistant;

public class ObserverManagerTest {

	private static Assistant assi;
	private static String identifier;
	protected static String triggered = "";
	
	@BeforeClass
	public static void setUpClass() {
		Runtime.getInstance().initialize();
		assi = new Assistant();
        assi.setEmail("test@example.com");
        assi.setFirstName("Test");
        assi.setLastName("Tester");
        assi.setQualification("g");
        assi.save();
        
        identifier = ObserverManager.getInstance().getIdentifier(assi);
	}
	
	@Before
	public void setUp() {
		triggered = "";
	}
	
	@Test
	public void testRegister() {
		System.out.println("register");
		
		Observer<Assistant> obs = new ObserverImpl();
		ObserverManager.getInstance().register(assi, obs);
		
		assertTrue(ObserverManager.getInstance().observers.containsKey(identifier));
		assertTrue(ObserverManager.getInstance().observers.get(identifier).contains(obs));
		
		ObserverManager.getInstance().unregister(assi, obs);
	}
	
	@Test
	public void testUnregister() {
		System.out.println("unregister");
		
		Observer<Assistant> obs = new ObserverImpl();
		ObserverManager.getInstance().register(assi, obs);
		ObserverManager.getInstance().unregister(assi, obs);
		
		assertTrue(ObserverManager.getInstance().observers.containsKey(identifier));
		assertFalse(ObserverManager.getInstance().observers.get(identifier).contains(obs));
	}
	
	@Test
	public void testTriggerSave() {
		System.out.println("triggerSave");
		
		Observer<Assistant> obs = new ObserverImpl();
		ObserverManager.getInstance().register(assi, obs);
		
		ObserverManager.getInstance().triggerSave(assi);
		assertEquals(triggered, "save");
		
		ObserverManager.getInstance().unregister(assi, obs);
	}
	
	@Test
	public void testTriggerRemove() {
		System.out.println("triggerRemove");
		
		Observer<Assistant> obs = new ObserverImpl();
		ObserverManager.getInstance().register(assi, obs);
		
		ObserverManager.getInstance().triggerRemove(assi);
		assertEquals(triggered, "remove");
		
		ObserverManager.getInstance().unregister(assi, obs);
	}

	public class ObserverImpl implements Observer<Assistant> {

		@Override
		public void onSave(AbstractModel<Assistant> model) {
			triggered = "save";
		}

		@Override
		public void onRemove(AbstractModel<Assistant> model) {
			triggered = "remove";			
		}
		
	}
}
