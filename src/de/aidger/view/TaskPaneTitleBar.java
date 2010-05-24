package de.aidger.view;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
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
     * The arrow icon on the title bar.
     */
    ArrowIcon arrowIcon = new ArrowIcon();

    /**
     * The painting object.
     */
    GradientPaint paint;

    /**
     * The title label.
     */
    JLabel titleLabel;

    /**
     * Constructs the title bar for the task pane.
     * 
     * @param title
     */
    public TaskPaneTitleBar(String title) {
        setLayout(new BorderLayout());

        // change foreground if mouse hovers or leaves the title bar
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                titleLabel.setForeground(activeFg);
                arrowIcon.setForeground(activeFg);

                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                titleLabel.setForeground(fg);
                arrowIcon.setForeground(firstColor);

                repaint();
            }
        });

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

        titleLabel.setForeground(fg);

        add(titleLabel, BorderLayout.LINE_START);
        // need some space between title label and arrow icon
        add(new JLabel("  "), BorderLayout.CENTER);
        add(new JLabel(arrowIcon), BorderLayout.LINE_END);
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
     * Sets the title bar to expanded. Mainly the arrow icon will be changed.
     * 
     * @param expanded
     *            The flag whether title bar should be expanded
     */
    public void setExpanded(boolean expanded) {
        arrowIcon.setExpanded(expanded);
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

    /**
     * This class represents an arrow icon for the title bar.
     * 
     * @author aidGer Team
     */
    public class ArrowIcon implements Icon {
        /**
         * Constant that represents the direction up
         */
        public static final int UP = 0;

        /**
         * Constant that stands for the direction down
         */
        public static final int DOWN = 1;

        /**
         * The current direction of the arrow icon.
         */
        int direction;

        /**
         * The fix weight of the icon.
         */
        int iconWidth = 19;

        /**
         * The fix height of the icon.
         */
        int iconHeight = 19;

        /**
         * The foreground color.
         */
        Color foreground = firstColor;

        /**
         * The circle color.
         */
        Color cirleColor = Color.white;

        /**
         * The shadow color.
         */
        Color shadowColor = new Color(160, 160, 200);

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.Icon#paintIcon(java.awt.Component,
         * java.awt.Graphics, int, int)
         */
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g.setColor(cirleColor);
            g.fillOval(1, 1, iconWidth - 3, iconHeight - 3);

            g.setColor(shadowColor);
            g2d.setStroke(new BasicStroke(2));

            g2d.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawOval(1, 1, iconWidth - 3, iconHeight - 3);

            g.setPaintMode();
            g2d.setStroke(new BasicStroke(1));
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_OFF);

            g2d.setStroke(new BasicStroke(1));
            g.setColor(foreground);

            if (direction == UP) {
                drawUpArrow(g);
            } else if (direction == DOWN) {
                drawDownArrow(g);
            }
        }

        /**
         * Draws an arrow in down direction.
         * 
         * @param g
         *            the graphics the arrow icon is painted on
         */
        private void drawDownArrow(Graphics g) {
            g.drawLine(6, 8 - 2, 9, 8 + 1);
            g.drawLine(7, 8 - 2, 9, 8);

            g.drawLine(9, 8 + 1, 12, 8 - 2);
            g.drawLine(9, 8, 11, 8 - 2);

            g.drawLine(6, 12 - 2, 9, 12 + 1);
            g.drawLine(7, 12 - 2, 9, 12);

            g.drawLine(9, 12 + 1, 12, 12 - 2);
            g.drawLine(9, 12, 11, 12 - 2);
        }

        /**
         * Draws an arrow icon in up direction.
         * 
         * @param g
         *            the graphics the arrow icon is painted on
         */
        private void drawUpArrow(Graphics g) {
            g.drawLine(6, 8, 9, 8 - 3);
            g.drawLine(7, 8, 9, 8 - 2);

            g.drawLine(9, 8 - 3, 12, 8);
            g.drawLine(9, 8 - 2, 11, 8);

            g.drawLine(6, 12, 9, 12 - 3);
            g.drawLine(7, 12, 9, 12 - 2);

            g.drawLine(9, 12 - 3, 12, 12);
            g.drawLine(9, 12 - 2, 11, 12);
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.Icon#getIconWidth()
         */
        public int getIconWidth() {
            return iconWidth;
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.Icon#getIconHeight()
         */
        public int getIconHeight() {
            return iconHeight;
        }

        /**
         * Sets the foreground.
         * 
         * @param foreground
         *            the color of the foreground
         */
        public void setForeground(Color foreground) {
            this.foreground = foreground;
        }

        /**
         * Sets the expanded state of the arrow icon.
         * 
         * @param expanded
         *            A flag whether the arrow icon should be expanded
         */
        public void setExpanded(boolean expanded) {
            if (expanded) {
                direction = 0;
            } else {
                direction = 1;
            }
        }
    }
}
