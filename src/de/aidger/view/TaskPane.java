package de.aidger.view;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * This class represents a single task pane. A task pane is a box with a title
 * bar on the top and a content pane on the bottom which can holds links,
 * buttons, text fields and more. Furthermore the content pane is collapsible.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class TaskPane extends JComponent {
    /**
     * The title bar of the task pane.
     */
    private final TaskPaneTitleBar titleBar;

    /**
     * The collapsible content pane.
     */
    private final JComponent contentPane;

    /**
     * The background color.
     */
    private final Color bg = new Color(0xf2f2f2);

    /**
     * A flag whether the content pane is expanded.
     */
    private boolean expanded = true;

    /**
     * The first color for the gradient painting.
     */
    private final Color firstColor = new Color(0x33628c);

    /**
     * The second color for the gradient painting.
     */
    private final Color secondColor = new Color(0xa7bbcd);

    /**
     * The container which holds this task pane.
     */
    protected TaskPaneContainer container;

    /**
     * The animation speed.
     */
    private final float speed = 16.0f;

    /**
     * The current alpha value for a fade out effect.
     */
    private float alpha = 1.0f;

    /**
     * A flag whether the alpha value should be currently used.
     */
    private boolean useAlpha;

    /**
     * The expanded height for the animation process.
     */
    private int expandedHeight;

    /**
     * The saved height for the animation process.
     */
    private int savedHeight;

    /**
     * The expanding flag for the animation process.
     */
    private boolean expanding;

    /**
     * The collapsing flag for the animation process.
     */
    private boolean collapsing;

    /**
     * A helper object for the animation process.
     */
    private final CObj cObj = new CObj();

    /**
     * A helper object for the animation process.
     */
    private final EObj eObj = new EObj();

    /**
     * Constructs the task pane.
     * 
     * @param title
     *            the title text of the task pane
     */
    public TaskPane(String title) {
        setLayout(new BorderLayout());
        updateUI();

        setBackground(bg);

        contentPane = new ContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.setBorder(new EmptyBorder(5, 8, 5, 8));
        contentPane.setOpaque(false);

        // sets the border of the content pane properly
        Border b = contentPane.getBorder();
        if (b != null) {
            contentPane.setBorder(new CompoundBorder(new TaskPaneBorder(), b));
        } else {
            contentPane.setBorder(new TaskPaneBorder());
        }

        titleBar = new TaskPaneTitleBar(title);
        titleBar.setMargin(new Insets(3, 5, 3, 5));

        // the content pane expands or collapses if user clicks on the title bar
        titleBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setExpanded(!expanded);

                UI.getInstance().validate();
            }
        });

        super.addImpl(titleBar, BorderLayout.NORTH, -1);

        // use different cursors for the title bar
        titleBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                e.getComponent().setCursor(
                        Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setCursor(Cursor.getDefaultCursor());
            }
        });

        super.addImpl(this.contentPane, null, -1);

        finishExpand();
    }

    /**
     * Adds a new component to the task pane.
     * 
     * @param comp
     *            the component that will be added
     */
    public void add(JComponent comp) {
        contentPane.add(comp, BorderLayout.LINE_START);
        contentPane.add(Box.createRigidArea(new Dimension(0, 8)));
    }

    /**
     * Returns whether the content pane is expanded.
     * 
     * @return a flag whether the content pane is expanded
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * Sets the expanded state of the content pane.
     * 
     * @param expanded
     *            a flag whether the content pane should be expanded
     */
    public void setExpanded(boolean expanded) {
        titleBar.setExpanded(expanded);

        if (!expanded) {
            container.collapse(this);
            this.expanded = expanded;
        } else {
            this.expanded = expanded;
            container.expand(this);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        int w = getWidth();
        int h = getHeight();
        int tbh = titleBar.getHeight();

        if (useAlpha) {
            ((Graphics2D) g).setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, alpha));
        }

        g.fillRect(0, tbh, w - 1, h - tbh);

        if (useAlpha) {
            g.setPaintMode();
        }
    }

    /**
     * Turns on/off the animation effect for this task pane.
     * 
     * @param animated
     *            A flag whether the task pane should be animated
     */
    void setAnimated(boolean animated) {
        if (!animated) {
            setPreferredSize(null);

            if (!expanded) {
                contentPane.setVisible(false);
            }
        }
    }

    /**
     * This class represents the border of the task pane.
     * 
     * @author aidGer Team
     */
    class TaskPaneBorder extends AbstractBorder {
        /*
         * (non-Javadoc)
         * 
         * @see
         * javax.swing.border.AbstractBorder#paintBorder(java.awt.Component,
         * java.awt.Graphics, int, int, int, int)
         */
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y,
                int width, int height) {
            int w = width / 2;
            g.setColor(firstColor);
            g.drawLine(x, y, x, y + height);
            g.drawLine(x, y + height - 1, x + w, y + height - 1);

            Graphics2D g2d = (Graphics2D) g.create(w, 0, w, height);
            Paint p = new GradientPaint(0, 0, firstColor, w, 0, secondColor);
            g2d.setPaint(p);
            g2d.drawLine(0, y + height - 1, w, y + height - 1);
            g2d.drawLine(w - 1, y, w - 1, y + height - 1);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * javax.swing.border.AbstractBorder#getBorderInsets(java.awt.Component)
         */
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(0, 1, 1, 1);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * javax.swing.border.AbstractBorder#getBorderInsets(java.awt.Component,
         * java.awt.Insets)
         */
        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            if (insets != null) {
                insets.top = 0;
                insets.left = 1;
                insets.bottom = 1;
                insets.right = 1;
                return insets;
            } else {
                return new Insets(0, 1, 1, 1);
            }
        }
    }

    /**
     * Finishes the collapse process.
     */
    protected void finishCollapse() {
        useAlpha = false;
        expanded = false;
        contentPane.setVisible(false);
        collapsing = false;
        setPreferredSize(null);
        contentPane.setSize(contentPane.getWidth(), savedHeight);
    }

    /**
     * Prepares the collapse process.
     * 
     * @return the count variable for internal use
     */
    protected int prepareToCollapse() {
        collapsing = true;
        expandedHeight = 0;
        savedHeight = contentPane.getHeight();
        int h = getHeight() - titleBar.getHeight();
        Dimension d = getPreferredSize();
        useAlpha = true;
        float k = (h / speed);
        float step = 1.0f / (h / k);
        int count = (int) (h / k);
        alpha = 1.0f;
        cObj.set(count, k, d, step);

        return count;
    }

    /**
     * Performs the whole collapse process.
     */
    protected void doCollapse() {
        for (int i = 0; i < cObj.count; i++) {
            doCollapseStep();
            container.doLayout();
            doLayout();
            Dimension d0 = container.getSize();
            container.paintImmediately(0, 0, d0.width, d0.height);
        }
    }

    /**
     * Performs operations for each collapse step.
     */
    protected void doCollapseStep() {
        cObj.d.height -= cObj.k;
        cObj.d.height = Math.max(cObj.d.height, titleBar.getHeight());
        setPreferredSize(cObj.d);
        alpha = Math.max(alpha - cObj.step, 0.0f);
        // pause();
    }

    /**
     * Finished the expand process.
     */
    protected void finishExpand() {
        useAlpha = false;
        expanded = true;
        expanding = false;
        contentPane.setVisible(true);
        // setPreferredSize(null);
    }

    /**
     * Prepares a expand process.
     * 
     * @return the count variable for internal use
     */
    protected int prepareToExpand() {
        expanding = true;
        setPreferredSize(null);
        contentPane.setVisible(true);
        int h = titleBar.getHeight();
        Dimension d0 = getPreferredSize();
        d0.height = Math.max(d0.height, expandedHeight);
        Dimension d = new Dimension(d0.width, h);
        useAlpha = true;
        float amount = d0.height - h;
        int k = (int) (amount / speed);
        int count = (int) (amount / k);
        float step = 1.0f / (amount / k);
        alpha = 0.0f;
        eObj.set(h, count, k, d, d0, step);

        return count;
    }

    /**
     * Performs a whole expand process.
     */
    protected void doExpand() {
        for (int i = eObj.h; i > 0; i--) {
            doExpandStep();
            container.doLayout();
            Dimension d1 = container.getSize();
            container.paintImmediately(0, 0, d1.width, d1.height);
        }
    }

    /**
     * Performs operations for each expand step.
     */
    protected void doExpandStep() {
        eObj.d.height += eObj.k;
        eObj.d.height = Math.min(eObj.d.height, eObj.d0.height);
        setPreferredSize(eObj.d);
        alpha = Math.min(alpha + eObj.step, 1.0f);
    }

    /**
     * Retrieves the preferred height of the content pane.
     * 
     * @return the preferred height of the content pane
     */
    public int getPreferredHeight() {
        Dimension d = contentPane.getPreferredSize();
        int h = titleBar.getHeight();
        return d.height + h;
    }

    /**
     * Sets the expanded height for the animation process.
     * 
     * @param expandedHeight
     *            the expanded height
     */
    public void setExpandedHeight(int expandedHeight) {
        this.expandedHeight = expandedHeight;
    }

    /**
     * Helper class for the animation effect. It holds some frequently used
     * variables.
     * 
     * @author aidGer Team
     */
    private static class CObj {
        int count;
        float k;
        Dimension d;
        float step;

        public void set(int count, float k, Dimension d, float step) {
            this.count = count;
            this.k = k;
            this.d = d;
            this.step = step;
        }
    }

    /**
     * Helper class for the animation effect. It holds some frequently used
     * variables.
     * 
     * @author aidGer Team
     */
    private static class EObj {
        int h;
        @SuppressWarnings("unused")
        int count;
        float k;
        Dimension d;
        Dimension d0;
        float step;

        public void set(int h, int count, float k, Dimension d, Dimension d0,
                float step) {
            this.h = h;
            this.count = count;
            this.k = k;
            this.d = d;
            this.d0 = d0;
            this.step = step;
        }
    }

    /**
     * This class represents the collapsible content pane of the task pane.
     * 
     * @author aidGer Team
     */
    class ContentPane extends JPanel {
        /**
         * Constructs a content pane. The constructor does nothing at the
         * moment.
         */
        public ContentPane() {
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.JComponent#paintBorder(java.awt.Graphics)
         */
        @Override
        protected void paintBorder(Graphics g) {
            Border border = getBorder();
            int h = TaskPane.this.getHeight();
            int ph = Math.max(TaskPane.this.getPreferredHeight(), h);
            int y = h - ph;

            if (!expanding || collapsing) {
                y = 0;
            }

            if (border != null) {
                int width = getWidth();
                int height = getHeight() + y;
                border.paintBorder(this, g, 0, 0, width, height);
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.JComponent#paintChildren(java.awt.Graphics)
         */
        @Override
        protected void paintChildren(Graphics g) {
            if (useAlpha) {
                ((Graphics2D) g).setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, alpha));
            }

            int ph = Math.max(TaskPane.this.getPreferredHeight(), TaskPane.this
                    .getHeight());
            int h = TaskPane.this.getHeight();
            int w = getWidth();

            if ((expanding || collapsing) && ph != h) {
                int y = h - ph;
                Graphics2D g2D = (Graphics2D) g.create(0, y, w, ph);
                g2D.setClip(0, -y, w, ph);
                super.paintChildren(g2D);
                g2D.dispose();
            } else {
                super.paintChildren(g);
            }

            if (useAlpha) {
                g.setPaintMode();
            }
        }
    }
}
