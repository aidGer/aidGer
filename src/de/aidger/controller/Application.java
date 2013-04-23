/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2013 The aidGer Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.aidger.controller;

import java.awt.Font;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import de.aidger.controller.actions.AboutAction;
import de.aidger.controller.actions.ActivityReportExportAction;
import de.aidger.controller.actions.ActivityReportGenerateAction;
import de.aidger.controller.actions.BudgetExportAction;
import de.aidger.controller.actions.BudgetGenerateAction;
import de.aidger.controller.actions.ControllingExportAllAction;
import de.aidger.controller.actions.ControllingExportDifferencesAction;
import de.aidger.controller.actions.ControllingGenerateAction;
import de.aidger.controller.actions.DatabaseCheckFinishAction;
import de.aidger.controller.actions.DatabaseDetailsFinishAction;
import de.aidger.controller.actions.DatabaseSelectionFinishAction;
import de.aidger.controller.actions.DetailViewerCloseAction;
import de.aidger.controller.actions.DetailViewerEditAction;
import de.aidger.controller.actions.DialogAbortAction;
import de.aidger.controller.actions.EditorCancelAction;
import de.aidger.controller.actions.EditorSaveAction;
import de.aidger.controller.actions.ExitAction;
import de.aidger.controller.actions.FinancialControllingGenerateAction;
import de.aidger.controller.actions.FirstStartNameFinishAction;
import de.aidger.controller.actions.HelpAction;
import de.aidger.controller.actions.HomepageAction;
import de.aidger.controller.actions.PrintAction;
import de.aidger.controller.actions.ProtocolExportAction;
import de.aidger.controller.actions.ReportExportAction;
import de.aidger.controller.actions.ReportGenerateAction;
import de.aidger.controller.actions.SettingsAction;
import de.aidger.controller.actions.SettingsBrowseAction;
import de.aidger.controller.actions.SettingsDatabaseConfigAction;
import de.aidger.controller.actions.SettingsSaveAction;
import de.aidger.controller.actions.ViewerActivityExportAction;
import de.aidger.controller.actions.ViewerActivityReportAction;
import de.aidger.controller.actions.ViewerAddAction;
import de.aidger.controller.actions.ViewerAssistantAction;
import de.aidger.controller.actions.ViewerContractAction;
import de.aidger.controller.actions.ViewerCourseAction;
import de.aidger.controller.actions.ViewerDeleteAction;
import de.aidger.controller.actions.ViewerDetailViewAction;
import de.aidger.controller.actions.ViewerEditAction;
import de.aidger.controller.actions.WizardNextAction;
import de.aidger.controller.actions.WizardPreviousAction;
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
    
    boolean isMac = false;

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
     * Returns true if the computer is running Mac OS
     * 
     * @return True if Mac OS is running
     */
    public boolean isMac() {
    	return isMac;
    }

    /**
     * Registers all action classes at ActionRegistry
     */
    private void registerActions() {
        ActionRegistry.getInstance().register(new ExitAction());
        ActionRegistry.getInstance().register(new PrintAction());
        ActionRegistry.getInstance().register(new SettingsAction());
        ActionRegistry.getInstance().register(new SettingsDatabaseConfigAction());
        ActionRegistry.getInstance().register(new SettingsBrowseAction());
        ActionRegistry.getInstance().register(new SettingsSaveAction());
        ActionRegistry.getInstance().register(new HelpAction());
        ActionRegistry.getInstance().register(new HomepageAction());
        ActionRegistry.getInstance().register(new AboutAction());
        ActionRegistry.getInstance().register(new DialogAbortAction());

        ActionRegistry.getInstance().register(new ViewerDetailViewAction());
        ActionRegistry.getInstance().register(new ViewerEditAction());
        ActionRegistry.getInstance().register(new ViewerAddAction());
        ActionRegistry.getInstance().register(new ViewerDeleteAction());
        ActionRegistry.getInstance().register(new ViewerCourseAction());
        ActionRegistry.getInstance().register(new ViewerAssistantAction());
        ActionRegistry.getInstance().register(new ViewerContractAction());
        ActionRegistry.getInstance().register(new ViewerActivityReportAction());
        ActionRegistry.getInstance().register(new ViewerActivityExportAction());

        ActionRegistry.getInstance().register(new EditorSaveAction());
        ActionRegistry.getInstance().register(new EditorCancelAction());
        ActionRegistry.getInstance().register(new DetailViewerEditAction());
        ActionRegistry.getInstance().register(new DetailViewerCloseAction());

        ActionRegistry.getInstance().register(new ReportGenerateAction());
        ActionRegistry.getInstance().register(new ReportExportAction());
        ActionRegistry.getInstance().register(new ProtocolExportAction());
        ActionRegistry.getInstance().register(new ActivityReportGenerateAction());
        ActionRegistry.getInstance().register(new ActivityReportExportAction());

        ActionRegistry.getInstance().register(new ControllingGenerateAction());
        ActionRegistry.getInstance().register(new ControllingExportAllAction());
        ActionRegistry.getInstance().register(new ControllingExportDifferencesAction());

        ActionRegistry.getInstance().register(new FinancialControllingGenerateAction());

        ActionRegistry.getInstance().register(new BudgetGenerateAction());
        ActionRegistry.getInstance().register(new BudgetExportAction());

        ActionRegistry.getInstance().register(new WizardNextAction());
        ActionRegistry.getInstance().register(new WizardPreviousAction());

        ActionRegistry.getInstance().register(new FirstStartNameFinishAction());
        ActionRegistry.getInstance().register(new DatabaseSelectionFinishAction());
        ActionRegistry.getInstance().register(new DatabaseDetailsFinishAction());
        ActionRegistry.getInstance().register(new DatabaseCheckFinishAction());
    }

    /**
     * Starts the application aidGer
     * 
     * @param args
     */
    public static void main(String[] args) {
    	Application app = Application.getInstance();
    	// Check for Mac OS and set some options
    	if (System.getProperty("os.name").toLowerCase().indexOf("mac") > -1) {
    		app.isMac = true;
    		
    		System.setProperty("apple.laf.useScreenMenuBar", "true");
    		System.setProperty("com.apple.macos.useScreenMenuBar", "true");
    		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "aidGer");
    	}    	
    	
        // Set the look & feel
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e_) {

            }

        }

        // Set some individual look & feel
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
