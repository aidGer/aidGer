package de.aidger.view;

import java.awt.event.ActionEvent;

import com.apple.eawt.AboutHandler;
import com.apple.eawt.AppEvent.AboutEvent;
import com.apple.eawt.AppEvent.PreferencesEvent;
import com.apple.eawt.Application;
import com.apple.eawt.PreferencesHandler;

import de.aidger.controller.ActionNotFoundException;
import de.aidger.controller.ActionRegistry;
import de.aidger.controller.actions.AboutAction;
import de.aidger.controller.actions.SettingsAction;

public final class MacApplication {
	
	public static void initialize() {
		Application app = Application.getApplication();
		app.setAboutHandler(new MacAboutHandler());
		app.setPreferencesHandler(new MacPreferencesHandler());
	}
	
	protected static ActionEvent getEvent() {
		return new ActionEvent(new Object(), 0, null);
	}
	
	public static class MacAboutHandler implements AboutHandler {

		@Override
		public void handleAbout(AboutEvent arg0) {
			try {
				ActionRegistry.getInstance().get(AboutAction.class.getName()).actionPerformed(getEvent());
			} catch (ActionNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	public static class MacPreferencesHandler implements PreferencesHandler {

		@Override
		public void handlePreferences(PreferencesEvent arg0) {
			try {
				ActionRegistry.getInstance().get(SettingsAction.class.getName()).actionPerformed(getEvent());
			} catch (ActionNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
	}
	
}
