package de.aidger.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 * This class represents the title bar of a task pane.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class TaskPaneTitleBar extends JComponent {
    /**
     * The first color for the gradient painting.
     */
    Color firstColor = new Color(0x33628c);

    /**
     * The second color for the gradient painting.
     */
    Color secondColor = new Color(0xa7bbcd);

    /**
     * The foreground of the title bar.
     */
    Color fg = new Color(0xf7f8fa);

    /**
     * The forground if title bar is active.
     */
    Color activeFg = new Color(0xbdc1c8);

    /**
     * The painting object.
     */
    GradientPaint paint;

    /**
     * The title label.
     */
    JLabel titleLabel;

    /**
     * The toggle icon of the title bar.
     */
    JLabel toggleIcon;

    /**
     * Constructs the title bar for the task pane.
     * 
     * @param title
     */
    public TaskPaneTitleBar(String title) {
        setLayout(new BorderLayout());

        // title label in bold font
        titleLabel = new JLabel(title) {
            @Override
            public void updateUI() {
                super.updateUI();

                Font font = getFont();
                if (font != null) {
                    setFont(font.deriveFont(Font.BOLD, font.getSize()));
                }
            }
        };

        // change foreground if mouse hovers or leaves the title bar
        titleLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                titleLabel.setForeground(activeFg);

                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                titleLabel.setForeground(fg);

                repaint();
            }
        });

        titleLabel.setForeground(fg);

        toggleIcon = new JLabel(new ImageIcon(getClass().getResource(
            "/de/aidger/view/icons/toggle.png")));

        add(titleLabel, BorderLayout.LINE_START);
        // need some space between title label and arrow icon
        add(new JLabel("   "), BorderLayout.CENTER);
        add(toggleIcon, BorderLayout.LINE_END);
    }

    /**
     * Returns the gradient painting in order to paint the background.
     * 
     * @return the gradient painting
     */
    protected GradientPaint getPaint() {
        if (paint == null) {
            paint = new GradientPaint(0, 0, firstColor, getWidth() / 2, 0,
                secondColor);
        }

        return paint;
    }

    /**
     * Returns the title label of the bar.
     * 
     * @return the title label of the bar
     */
    public JLabel getTitleLabel() {
        return titleLabel;
    }

    /**
     * Returns the toogle icon of the bar.
     * 
     * @return the toggle icon of the bar
     */
    public JLabel getToogleIcon() {
        return toggleIcon;
    }

    /**
     * Sets the title bar to expanded. Mainly the toggle icon will be changed.
     * 
     * @param expanded
     *            The flag whether title bar should be expanded
     */
    public void setExpanded(boolean expanded) {
        if (expanded) {
            toggleIcon.setIcon(new ImageIcon(getClass().getResource(
                "/de/aidger/view/icons/toggle.png")));
        } else {
            toggleIcon.setIcon(new ImageIcon(getClass().getResource(
                "/de/aidger/view/icons/toggle-expand.png")));
        }
    }

    /**
     * Sets the margin of the title bar.
     * 
     * @param margin
     *            the margin
     */
    public void setMargin(Insets margin) {
        if (margin == null) {
            margin = new Insets(0, 0, 0, 0);
        }

        EmptyBorder outsideBorder = new EmptyBorder(margin);
        setBorder(outsideBorder);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(firstColor);
        int w = getWidth() / 2;

        g.drawLine(2, 1, w, 1);
        g.drawLine(1, 2, w, 2);
        g.fillRect(0, 3, w, getHeight() - 3);

        Graphics2D g2d = (Graphics2D) g.create(w, 0, w, getHeight());
        Paint p = getPaint();
        g2d.setPaint(p);

        g2d.drawLine(0, 1, w - 3, 1);
        g2d.drawLine(0, 2, w - 2, 2);
        g2d.fillRect(0, 3, w, getHeight() - 3);

        g2d.dispose();
    }
}
