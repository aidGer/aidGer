package de.aidger.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import de.aidger.controller.ActionRegistry;
import de.aidger.controller.actions.ExitAction;
import de.aidger.view.tabs.Tab;

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
     * Used for logging messages from exceptions.
     */
    private final Logger logger = Logger.getLogger(UI.class.getName());

    /**
     * Creates the main window of the application.
     */
    private UI() {
        super();

        // Load the system specific look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage(), e);
        }

        // Create the menu bar
        setJMenuBar(getMainMenuBar());

        // Build the main panel
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(getToolbar(), BorderLayout.PAGE_START);
        contentPane.add(getNavigation(), BorderLayout.LINE_START);
        contentPane.add(getTabbedPane(), BorderLayout.CENTER);
        contentPane.add(getStatusPane(), BorderLayout.PAGE_END);

        // Set to full screen size
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = (int) tk.getScreenSize().getWidth();
        int ySize = (int) tk.getScreenSize().getHeight();
        setSize(xSize, ySize);

        setTitle("aidGer");

        // Initialize window event handling
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Action action = ActionRegistry.getInstance().get(
                        ExitAction.class.getName());

                if (action != null) {
                    ActionEvent evt = new ActionEvent(e.getSource(), e.getID(),
                            null);

                    action.actionPerformed(evt);
                }
            }
        });
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
     * Sets up the main menu bar.
     */
    private JMenuBar getMainMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(getFileMenu());

        return menuBar;
    }

    /**
     * Sets up the file menu.
     */
    private JMenu getFileMenu() {
        JMenu fileMenu = new JMenu("Datei");

        // fileMenu.add(getFileOpenMenu());
        // fileMenu.add(getFileSaveMenu());
        fileMenu.addSeparator();
        fileMenu.add(getFileExitMenu());

        return fileMenu;
    }

    /**
     * Sets up the exit menu.
     */
    private JMenuItem getFileExitMenu() {
        JMenuItem fileExitMenu = new JMenuItem(ActionRegistry.getInstance()
                .get(ExitAction.class.getName()));

        return fileExitMenu;
    }

    /**
     * Sets up the toolbar.
     */
    private JToolBar getToolbar() {
        JToolBar toolBar = new JToolBar();

        // toolBar.add(ActionRegistry.getInstance().get(OpenAction.class.getName()));

        return toolBar;
    }

    /**
     * Sets up the navigation.
     */
    private JPanel getNavigation() {
        JPanel navigation = new JPanel();

        return navigation;
    }

    /**
     * Sets up the tabbed pane in the middle.
     */
    private JTabbedPane getTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Willkommen", null, getWelcomeScreen(), "Willkommen");

        return tabbedPane;
    }

    /**
     * Sets uo the welcome screen.
     */
    private JComponent getWelcomeScreen() {
        JPanel panel = new JPanel();

        JLabel filler = new JLabel("blobb");
        filler.setHorizontalAlignment(JLabel.CENTER);

        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);

        return panel;
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

    /**
     * Runs and displays the graphical user interface.
     */
    public void run() {
        setVisible(true);
    }

    /**
     * 
     */
    public void addNewTab() {

    }

    /**
     * 
     * 
     * @param tab
     */
    public void addNewTab(Tab tab) {

    }

    /**
     * 
     * 
     * @param tab
     */
    public void setCurrentTab(Tab tab) {

    }
}
