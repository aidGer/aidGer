package de.aidger.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * The navigation bar is located at the left side on the main window. It is used
 * to navigate through the whole application.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public final class NavigationBar extends JPanel implements ActionListener {
    /**
     * The top panel contains the buttons displayed on top of the bar
     */
    private final JPanel topPanel = new JPanel(new GridLayout(1, 1));

    /**
     * The bottom panel contains the buttons displayed on the bottom of the bar.
     */
    private final JPanel bottomPanel = new JPanel(new GridLayout(1, 1));

    /**
     * a map of the bars in the navigation
     */
    private final Map<String, BarInfo> bars = new LinkedHashMap<String, BarInfo>();

    /**
     * The currently visible / clicked bar
     */
    private int visibleBar;

    /**
     * The currently visible component
     */
    private JComponent visibleComponent;

    /**
     * Creates a new navigation bar.
     */
    public NavigationBar() {
        setLayout(new BorderLayout());

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds the specified component to the navigation and sets the bar's name.
     * 
     * @param name
     *            The name of the bar
     * @param component
     *            The component to add to the bar
     */
    public void addBar(String name, JComponent component) {
        BarInfo barInfo = new BarInfo(name, component);
        barInfo.getButton().addActionListener(this);
        bars.put(name, barInfo);

        render();
    }

    /**
     * Removes the specified bar from the navigation
     * 
     * @param name
     *            The name of the bar to remove
     */
    public void removeBar(String name) {
        bars.remove(name);

        render();
    }

    /**
     * Renders the top and bottom panels of bars as well as making the currently
     * selected bar's component visible.
     */
    private void render() {
        // Compute how many bars we will have
        int totalBars = bars.size(), topBars = visibleBar + 1;
        int bottomBars = totalBars - topBars;

        // iterator to walk through the bars
        Iterator<String> it = bars.keySet().iterator();

        // renders the top bars

        topPanel.removeAll();
        GridLayout topLayout = (GridLayout) topPanel.getLayout();
        topLayout.setRows(topBars);
        BarInfo barInfo = null;

        for (int i = 0; i < topBars; i++) {
            String barName = it.next();
            barInfo = bars.get(barName);

            topPanel.add(barInfo.getButton());
        }

        topPanel.validate();

        // renders the center component of the clicked bar

        if (visibleComponent != null) {
            remove(visibleComponent);
        }

        visibleComponent = barInfo.getComponent();
        add(visibleComponent, BorderLayout.CENTER);

        // renders the bottom bars

        bottomPanel.removeAll();
        GridLayout bottomLayout = (GridLayout) bottomPanel.getLayout();
        bottomLayout.setRows(bottomBars);

        for (int i = 0; i < bottomBars; i++) {
            String barName = it.next();
            barInfo = bars.get(barName);

            bottomPanel.add(barInfo.getButton());
        }

        bottomPanel.validate();

        // re-layout everything
        validate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        int currentBar = 0;

        for (Iterator<String> it = bars.keySet().iterator(); it.hasNext();) {
            String barName = it.next();
            BarInfo barInfo = bars.get(barName);

            // find the clicked button
            if (barInfo.getButton() == e.getSource()) {
                visibleBar = currentBar;

                render();

                return;
            }

            currentBar++;
        }
    }

    /**
     * Internal class that maintains information about a bar.
     * 
     * @author aidGer Team
     */
    class BarInfo {
        /**
         * The name of the bar
         */
        private final String name;

        /**
         * The button of the bar
         */
        private final JButton button;

        /**
         * The component that is the body of the bar
         */
        private final JComponent component;

        /**
         * Creates a new BarInfo.
         * 
         * @param name
         *            The name of the bar
         * @param component
         *            The component that is the body of the bar
         */
        public BarInfo(String name, JComponent component) {
            this.name = name;
            this.component = component;
            this.button = new JButton(name);
        }

        /**
         * Returns the name of the bar.
         * 
         * @return the name of the bar
         */
        public String getName() {
            return name;
        }

        /**
         * Retrieves the button of the bar.
         * 
         * @return the button of the bar
         */
        public JButton getButton() {
            return button;
        }

        /**
         * Returns the component that is the body of the bar.
         * 
         * @return the component that is the body of the bar
         */
        public JComponent getComponent() {
            return component;
        }

    }
}
