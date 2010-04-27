package de.aidger.controller;

import javax.swing.SwingUtilities;
import de.aidger.view.UI;

public final class Application {
	/**
	 * Holds an instance of this class.
	 */
	private static Application instance = null;

	private Application() {
		super();
	}

	/**
	 * Provides access to an instance of this class.
	 * @return
	 */
	public static Application getInstance() {
		if (instance == null)
			instance = new Application();

		return instance;
	}
	
	/**
	 * Starts the application aidGer
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Starts the main UI in the thread that runs the event loop.
		Runnable ui = new Runnable() {
			@Override
			public void run() {
				UI.getInstance().run();
			}
		};

		SwingUtilities.invokeLater(ui);
	}
}
