package de.aidger.controller;

import javax.swing.SwingUtilities;

import de.aidger.controller.actions.ExitAction;
import de.aidger.view.UI;

public final class Application {
    /**
     * Holds an instance of this class.
     */
    private static Application instance = null;

    /**
     * Constructor must be private and does nothing.
     */
    private Application() {
    }

    /**
     * Provides access to an instance of this class.
     * 
     * @return instance of this Application
     */
    public synchronized static Application getInstance() {
        if (instance == null)
            instance = new Application();

        return instance;
    }

    /**
     * Registers all action classes at ActionRegistry
     */
    public void registerActions() {
        ActionRegistry.getInstance().register(new ExitAction());
    }

    /**
     * Starts the application aidGer
     * 
     * @param args
     */
    public static void main(String[] args) {
        Application.getInstance().registerActions();

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
