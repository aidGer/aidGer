package de.aidger.view.tabs;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeTab extends Tab {
    public WelcomeTab() {

    }

    @Override
    public JPanel getNewTab() {
        panel = new JPanel();

        JLabel filler = new JLabel("blobb");
        filler.setHorizontalAlignment(JLabel.CENTER);

        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);

        return panel;
    }

    @Override
    public String getName() {
        return "Welcome";
    }
}
