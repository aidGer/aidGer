package de.aidger.controller;

import java.awt.Font;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import de.aidger.controller.actions.AboutAction;
import de.aidger.controller.actions.DetailViewerCancelAction;
import de.aidger.controller.actions.DetailViewerEditAction;
import de.aidger.controller.actions.DialogAbortAction;
import de.aidger.controller.actions.EditorCancelAction;
import de.aidger.controller.actions.EditorSaveAction;
import de.aidger.controller.actions.ExitAction;
import de.aidger.controller.actions.HelpAction;
import de.aidger.controller.actions.HomepageAction;
import de.aidger.controller.actions.PrintAction;
import de.aidger.controller.actions.ProtocolExportAction;
import de.aidger.controller.actions.ReportExportAction;
import de.aidger.controller.actions.ReportGenerateAction;
import de.aidger.controller.actions.SaveSettingsAction;
import de.aidger.controller.actions.SettingsAction;
import de.aidger.controller.actions.ViewerActivitiesAction;
import de.aidger.controller.actions.ViewerAddAction;
import de.aidger.controller.actions.ViewerDeleteAction;
import de.aidger.controller.actions.ViewerDetailViewAction;
import de.aidger.controller.actions.ViewerEditAction;
import de.aidger.model.Runtime;
import de.aidger.view.UI;

/**
 * Application is a singleton class in which the main() function is located. The
 * application initializes all controller specific classes and registers the
 * actions at the action registry.
 * 
 * @author aidGer Team
 */
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
        if (instance == null) {
            instance = new Application();
        }

        return instance;
    }

    /**
     * Initializes the application by registering the actions.
     */
    public void initialize() {
        Runtime.getInstance().initialize();
        registerActions();
    }

    /**
     * Registers all action classes at ActionRegistry
     */
    private void registerActions() {
        ActionRegistry.getInstance().register(new ExitAction());
        ActionRegistry.getInstance().register(new PrintAction());
        ActionRegistry.getInstance().register(new SettingsAction());
        ActionRegistry.getInstance().register(new SaveSettingsAction());
        ActionRegistry.getInstance().register(new HelpAction());
        ActionRegistry.getInstance().register(new HomepageAction());
        ActionRegistry.getInstance().register(new AboutAction());
        ActionRegistry.getInstance().register(new DialogAbortAction());

        ActionRegistry.getInstance().register(new ViewerDetailViewAction());
        ActionRegistry.getInstance().register(new ViewerEditAction());
        ActionRegistry.getInstance().register(new ViewerAddAction());
        ActionRegistry.getInstance().register(new ViewerDeleteAction());
        ActionRegistry.getInstance().register(new ViewerActivitiesAction());
        ActionRegistry.getInstance().register(new EditorSaveAction());
        ActionRegistry.getInstance().register(new EditorCancelAction());
        ActionRegistry.getInstance().register(new DetailViewerEditAction());
        ActionRegistry.getInstance().register(new DetailViewerCancelAction());

        ActionRegistry.getInstance().register(new ReportGenerateAction());
        ActionRegistry.getInstance().register(new ReportExportAction());
        ActionRegistry.getInstance().register(new ProtocolExportAction());
    }

    /**
     * Starts the application aidGer
     * 
     * @param args
     */
    public static void main(String[] args) {
        // set the look & feel
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager
                    .getSystemLookAndFeelClassName());
            } catch (Exception e_) {

            }

        }

        // set some individual look & feel
        Font oldFont = UIManager.getFont("TitledBorder.font");
        UIManager.put("TitledBorder.font", new Font(oldFont.getName(), oldFont
            .getStyle(), 15));

        Application.getInstance().initialize();

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
