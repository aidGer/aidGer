package de.aidger.view;

import static de.aidger.utils.Translation._;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.text.MessageFormat;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.aidger.controller.ActionNotFoundException;
import de.aidger.controller.ActionRegistry;
import de.aidger.controller.actions.AboutAction;
import de.aidger.controller.actions.ExitAction;
import de.aidger.controller.actions.HelpAction;
import de.aidger.controller.actions.PrintAction;
import de.aidger.controller.actions.SettingsAction;
import de.aidger.utils.Logger;
import de.aidger.view.tabs.EmptyTab;
import de.aidger.view.tabs.Tab;
import de.aidger.view.tabs.WelcomeTab;
import de.aidger.view.tabs.AssistantEditorTab;
import de.aidger.view.tabs.MasterDataViewerTab;
import de.aidger.view.tabs.CourseEditorTab;
import de.aidger.view.tabs.FinancialCategoryEditorTab;

/**
 * The UI manages the main window and all its tabs. The main window consists of
 * the menu bar, the navigation and a tabbed pane which holds all tabs in the
 * center of the main window.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public final class UI extends JFrame {
    /**
     * Holds an instance of this class.
     */
    private static UI instance = null;

    /**
     * The tabbed plane that will contain the tabs.
     */
    private JTabbedPane tabbedPane;

    /**
     * A change listener for the tabbed pane
     */
    private ChangeListener tabbedPaneListener;

    /**
     * The currently selected Tab
     */
    private Tab currentTab;

    /**
     * Creates the main window of the application.
     */
    private UI() {
        super();

        // Create the menu bar
        setJMenuBar(getMainMenuBar());

        // Build the main panel
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(getToolbar(), BorderLayout.PAGE_START);
        contentPane.add(getNavigationBar(), BorderLayout.LINE_START);
        contentPane.add(getTabbedPane(), BorderLayout.CENTER);
        contentPane.add(getStatusPane(), BorderLayout.PAGE_END);

        pack();

        setTitle("aidGer");

        // Initialize window event handling
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                Action action;
                try {
                    action = ActionRegistry.getInstance().get(
                            ExitAction.class.getName());

                    if (action != null) {
                        ActionEvent evt = new ActionEvent(event.getSource(),
                                event.getID(), null);

                        action.actionPerformed(evt);
                    }
                } catch (ActionNotFoundException e) {
                    displayError(e.getMessage());
                }
            }
        });

        // Add the welcome tab to the UI.
        addNewTab(new WelcomeTab());
        addNewTab(new AssistantEditorTab());
        addNewTab(new MasterDataViewerTab());
        addNewTab(new CourseEditorTab());
        addNewTab(new FinancialCategoryEditorTab());
    }

    /**
     * Provides access to an instance of this class.
     * 
     * @return instance of this UI
     */
    public synchronized static UI getInstance() {
        if (instance == null) {
            instance = new UI();
        }

        return instance;
    }

    /**
     * Display an error message in a dialog window.
     *
     * @param error
     *            The error message to display
     */
    public static void displayError(String error) {
        JOptionPane.showMessageDialog(instance, error, _("Error"),
                JOptionPane.ERROR_MESSAGE);
        Logger.error(error);
    }


    /**
     * Runs and displays the graphical user interface.
     */
    public void run() {
        setVisible(true);

        // start maximized
        setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    /**
     * Adds a new empty tab and focus it if "+" button was clicked
     */
    public void addNewTab() {
        int index = tabbedPane.getTabCount() - 1;

        if (tabbedPane.getSelectedIndex() == index) {
            Logger.debug(_("Adding new empty tab"));

            tabbedPane.removeChangeListener(tabbedPaneListener);

            Tab emptyTab = new EmptyTab();
            tabbedPane.add(emptyTab.getContent(), index);
            tabbedPane.setTabComponentAt(index, new CloseTabComponent(
                    tabbedPane, tabbedPaneListener, emptyTab.getName()));

            tabbedPane.setSelectedIndex(index);
            currentTab = emptyTab;

            tabbedPane.addChangeListener(tabbedPaneListener);
        }
    }

    /**
     * Adds a new tab, specified by tab, to the tabbed plane.
     *
     * @param tab
     *            The tab to be added.
     */
    public void addNewTab(Tab tab) {
        Logger.debug(MessageFormat.format(_("Adding new Tab \"{0}\""),
                new Object[] { tab.getName() }));

        int index = tabbedPane.getTabCount() - 1;

        tabbedPane.removeChangeListener(tabbedPaneListener);

        tabbedPane.add(tab.getContent(), index);
        tabbedPane.setTabComponentAt(index, new CloseTabComponent(tabbedPane,
                tabbedPaneListener, tab.getName()));

        tabbedPane.setSelectedIndex(index);
        currentTab = tab;

        tabbedPane.addChangeListener(tabbedPaneListener);
    }

    /**
     * Get the currently selected tab.
     *
     * @return The selected tab
     */
    public Tab getCurrentTab() {
        return currentTab;
    }

    /**
     * Sets the current tab on the tabbed plane to the one specified.
     *
     * @param tab
     *            The tab to be set as current.
     */
    public void setCurrentTab(Tab tab) {
        /* Check if the tab to be set as current is even on the tabbed plane. */
        if (tabbedPane.indexOfComponent(tab.getContent()) != -1) {
            Logger.debug(MessageFormat.format(_("Setting current tab to \"{0}\""),
                    new Object[] { tab.getName() }));
            currentTab = tab;
            tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(tab
                    .getContent()));
        }
    }

    /**
     * Sets up the main menu bar.
     */
    private JMenuBar getMainMenuBar() {
        try {
            JMenuBar menuBar = new JMenuBar();
            menuBar.add(getFileMenu());
            menuBar.add(getHelpMenu());

            return menuBar;
        } catch (ActionNotFoundException e) {
            displayError(e.getMessage());
            return null;
        }
    }

    /**
     * Sets up the file menu.
     * 
     * @throws ActionNotFoundException
     */
    private JMenu getFileMenu() throws ActionNotFoundException {
        JMenu fileMenu = new JMenu(_("File"));

        fileMenu.add(new JMenuItem(ActionRegistry.getInstance().get(
                PrintAction.class.getName())));
        fileMenu.add(new JMenuItem(ActionRegistry.getInstance().get(
                SettingsAction.class.getName())));
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem(ActionRegistry.getInstance().get(
                ExitAction.class.getName())));

        return fileMenu;
    }

    /**
     * Sets up the help menu.
     * 
     * @throws ActionNotFoundException
     */
    private JMenu getHelpMenu() throws ActionNotFoundException {
        JMenu helpMenu = new JMenu(_("Help"));

        helpMenu.add(new JMenuItem(ActionRegistry.getInstance().get(
                HelpAction.class.getName())));
        helpMenu.add(new JMenuItem(ActionRegistry.getInstance().get(
                AboutAction.class.getName())));

        return helpMenu;
    }

    /**
     * Sets up the toolbar.
     */
    private JToolBar getToolbar() {
        JToolBar toolBar = new JToolBar();

        // toolBar.add(ActionRegistry.getInstance().get(OpenAction.class.getName()));

        return toolBar;
    }

    private JList getDummyList(String... items) {
        return new JList(items);
    }

    /**
     * Sets up the navigation bar.
     */
    private JPanel getNavigationBar() {
        NavigationBar navigationBar = new NavigationBar();

        // TODO: create own JPanel's for the components of the bars
        navigationBar.addBar(_("Master Data Management"), getDummyList(
                _("Courses"), _("Assistants"), _("Contracts"),
                _("Financial Categories"), _("Hourly Wages")));
        navigationBar.addBar(_("Employments"), getDummyList("bla"));
        navigationBar.addBar(_("Activities"), getDummyList("blu"));
        navigationBar.addBar(_("Reports"), getDummyList("lal"));
        navigationBar.addBar(_("Controlling"), getDummyList("he"));
        navigationBar.addBar(_("Budget Check"), getDummyList("lal"));

        return navigationBar;
    }

    /**
     * Sets up the tabbed pane in the middle.
     */
    private JTabbedPane getTabbedPane() {
        tabbedPane = new JTabbedPane();

        tabbedPaneListener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                addNewTab();
            }
        };

        tabbedPane.add(new JPanel(), "+");
        tabbedPane.setToolTipTextAt(0, _("Open a new tab"));

        tabbedPane.addChangeListener(tabbedPaneListener);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);

        return tabbedPane;
    }

    /**
     * Sets up the panel that contains the status label.
     */
    private JPanel getStatusPane() {
        JPanel statusPane = new JPanel();
        statusPane.setLayout(new FlowLayout(FlowLayout.LEFT));

        // statusPane.add(getStatusLabel());

        return statusPane;
    }

}
