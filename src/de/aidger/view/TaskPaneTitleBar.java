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

@SuppressWarnings("serial")
public class TaskPaneTitleBar extends JComponent {

    Color firstColor = new Color(0xFFFAFAFA);
    Color secondColor = new Color(0xFFC7D4F7);
    Color fg = new Color(0xFF215DC6);
    Color activeFg = new Color(0xFF4288FF);

    ArrowIcon arrowIcon = new ArrowIcon();
    GradientPaint paint;
    JLabel titleLabel;

    public TaskPaneTitleBar(String title) {
        setLayout(new BorderLayout());

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
                arrowIcon.setForeground(fg);

                repaint();
            }
        });

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
        add(new JLabel("  "), BorderLayout.CENTER);
        add(new JLabel(arrowIcon), BorderLayout.LINE_END);
    }

    protected GradientPaint getPaint() {
        if (paint == null) {
            paint = new GradientPaint(0, 0, firstColor, getWidth() / 2, 0,
                    secondColor);
        }

        return paint;
    }

    public void setExpanded(boolean expanded) {
        arrowIcon.setExpanded(expanded);
    }

    public void setMargin(Insets margin) {
        if (margin == null) {
            margin = new Insets(0, 0, 0, 0);
        }

        EmptyBorder outsideBorder = new EmptyBorder(margin);
        setBorder(outsideBorder);
    }

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
     * ArrowIcon for TaskPane.
     * 
     * @author Andrey Kuznetsov
     */
    public class ArrowIcon implements Icon {

        public static final int UP = 0;
        public static final int DOWN = 1;

        public static final int VERTICAL = 0;
        public static final int HORIZONTAL = 1;

        int axis;

        int direction;

        int iconWidth = 19;
        int iconHeight = 19;

        Color background = new Color(200, 217, 247);
        Color cirleColor = Color.white;
        Color shadowColor = new Color(160, 160, 200);
        Color foreground = new Color(0, 60, 165);

        boolean opaque;
        boolean contentOpaque = true;

        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g;

            if (opaque) {
                g.setColor(background);
                g.fillRect(0, 0, iconWidth, iconHeight);
            }

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            if (contentOpaque) {
                g.setColor(cirleColor);
                g.fillOval(1, 1, iconWidth - 3, iconHeight - 3);
            }

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

            if (axis == VERTICAL) {
                if (direction == UP) {
                    drawUpArrow(g);
                } else if (direction == DOWN) {
                    drawDownArrow(g);
                }
            } else {
                if (direction == UP) {
                    drawRightArrow(g);
                } else if (direction == DOWN) {
                    drawLeftArrow(g);
                }
            }
        }

        private void drawLeftArrow(Graphics g) {
            g.drawLine(8 - 2, 6, 8 + 1, 9);
            g.drawLine(8 - 2, 7, 8, 9);

            g.drawLine(8 + 1, 9, 8 - 2, 12);
            g.drawLine(8, 9, 8 - 2, 11);

            g.drawLine(12 - 2, 6, 12 + 1, 9);
            g.drawLine(12 - 2, 7, 12, 9);

            g.drawLine(12 + 1, 9, 12 - 2, 12);
            g.drawLine(12, 9, 12 - 2, 11);
        }

        private void drawRightArrow(Graphics g) {
            g.drawLine(8, 6, 8 - 3, 9);
            g.drawLine(8, 7, 8 - 2, 9);

            g.drawLine(8 - 3, 9, 8, 12);
            g.drawLine(8 - 2, 9, 8, 11);

            g.drawLine(12, 6, 12 - 3, 9);
            g.drawLine(12, 7, 12 - 2, 9);

            g.drawLine(12 - 3, 9, 12, 12);
            g.drawLine(12 - 2, 9, 12, 11);
        }

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

        public int getIconWidth() {
            return iconWidth;
        }

        public int getIconHeight() {
            return iconHeight;
        }

        public Color getBackground() {
            return background;
        }

        public void setBackground(Color background) {
            this.background = background;
        }

        public Color getCirleColor() {
            return cirleColor;
        }

        public void setCirleColor(Color cirleColor) {
            this.cirleColor = cirleColor;
        }

        public Color getShadowColor() {
            return shadowColor;
        }

        public void setShadowColor(Color shadowColor) {
            this.shadowColor = shadowColor;
        }

        public Color getForeground() {
            return foreground;
        }

        public void setForeground(Color foreground) {
            this.foreground = foreground;
        }

        public void flip() {
            direction = Math.abs(direction - 1);
        }

        public void setExpanded(boolean expanded) {
            if (expanded) {
                direction = 0;
            } else {
                direction = 1;
            }
        }

        public boolean isExpanded() {
            return direction == 0;
        }

        public void flipAxis() {
            axis = Math.abs(axis - 1);
        }

        public int getAxis() {
            return axis;
        }

        public void setAxis(int axis) {
            this.axis = axis;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public boolean isOpaque() {
            return opaque;
        }

        public void setOpaque(boolean opaque) {
            this.opaque = opaque;
        }

        public boolean isContentOpaque() {
            return contentOpaque;
        }

        public void setContentOpaque(boolean contentOpaque) {
            this.contentOpaque = contentOpaque;
        }
    }
}
