package de.aidger.view.utils;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolTip;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 * The help label is used for displaying helpful user information by opening a
 * styled tool tip.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class HelpLabel extends JLabel {

    /**
     * Constructs a helper label.
     */
    public HelpLabel() {
        setIcon(new ImageIcon(getClass().getResource(
            "/de/aidger/view/icons/question-large.png")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#createToolTip()
     */
    @Override
    public JToolTip createToolTip() {
        return new HelpToolTip();
    }

    /**
     * A custom styled tool tip.
     * 
     * @author aidGer Team
     */
    private class HelpToolTip extends JToolTip {
        public HelpToolTip() {
            UIDefaults defaults = new UIDefaults();

            defaults.put("ToolTip[Enabled].backgroundPainter", UIManager
                .get("PopupMenu[Enabled].backgroundPainter"));
            defaults.put("ToolTip.contentMargins", new Insets(5, 5, 5, 5));

            putClientProperty("Nimbus.Overrides.InheritDefaults", false);
            putClientProperty("Nimbus.Overrides", defaults);

            updateUI();
        }
    }
}
