package de.aidger.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * TaskPaneContainer is a container that holds and manages all added task panes.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class TaskPaneContainer extends JPanel {
    /**
     * The first color for the gradient painting.
     */
    Color first;

    /**
     * The second color for the gradient painting.
     */
    Color second;

    /**
     * The gradient painting.
     */
    GradientPaint paint;

    /**
     * Constructs a task pane container.
     * 
     * @param first
     *            the first gradient color
     * @param second
     *            the second gradient color
     */
    public TaskPaneContainer(Color first, Color second) {
        setLayout(new GridBagLayout());

        this.first = first;
        this.second = second;

        setBorder(new EmptyBorder(10, 10, 0, 10));

        // use animation
        int count = getTaskCount();

        for (int i = 0; i < count; i++) {
            TaskPane tp = getTask(i);
            tp.setAnimated(true);
        }
    }

    /**
     * Constructs a task pane container with default gradient colors.
     */
    public TaskPaneContainer() {
        this(new Color(0xFF7BA2E7), new Color(0xFF6476D6));
    }

    /**
     * Returns the gradient painting.
     * 
     * @return the gradient painting
     */
    protected GradientPaint getPaint() {
        if (paint == null) {
            paint = new GradientPaint(0, 0, first, 0, getHeight(), second);
        }

        return paint;
    }

    /**
     * Adds a new task pane to the container.
     * 
     * @param taskPane
     *            the task pane that will be added
     */
    public void addTask(TaskPane taskPane) {
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 15, 0);
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;

        add(taskPane, c);

        taskPane.container = this;

        taskPane.setExpanded(false);
    }

    /**
     * Adds an empty filler to the container. Basically it is used to set the
     * task panes to the top of the frame. This method must be called at last.
     */
    public void addFiller() {
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.weighty = 1.0;

        add(javax.swing.Box.createVerticalGlue(), c);
    }

    /**
     * Removes a task pane from the container.
     * 
     * @param taskPane
     *            the task pane that will be removed
     */
    public void removeTask(TaskPane taskPane) {
        super.remove(taskPane);

        if (taskPane.container == this) {
            taskPane.container = null;
        }
    }

    /**
     * Removes a task pane from the container. The task pane is identified by
     * its position on the container.
     * 
     * @param i
     *            the position of the task pane that will be removed
     */
    public void removeTask(int i) {
        TaskPane tp = getTask(i);

        if (tp.container == this) {
            tp.container = null;
        }

        super.remove(i);
    }

    /**
     * Returns the task pane at the given position.
     * 
     * @param index
     *            the position of the task pane on the container
     * @return the task pane at the given position
     */
    public TaskPane getTask(int index) {
        if (getComponent(index) instanceof TaskPane) {
            return (TaskPane) getComponent(index);
        }

        return null;
    }

    /**
     * Retrieves the count of the task panes.
     * 
     * @return the count of the task panes
     */
    public int getTaskCount() {
        return getComponentCount();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    /*
     * @Override protected void paintComponent(Graphics g) { Paint p =
     * getPaint(); Graphics2D g2d = (Graphics2D) g;
     * 
     * g2d.setPaint(p); g2d.fillRect(0, 0, getWidth(), getHeight()); }
     */

    /**
     * Checks the size of all task panes.
     */
    protected void checkSizes() {
        int count = getTaskCount();
        int max = 0;

        for (int i = 0; i < count; i++) {
            TaskPane tp = getTask(i);

            if (tp != null) {
                tp.setExpandedHeight(max);
            }
        }
    }

    /**
     * Collapses the given task pane.
     * 
     * @param tp
     *            the task pane that will be collapsed
     */
    public void collapse(TaskPane tp) {
        checkSizes();

        tp.prepareToCollapse();
        tp.doCollapse();

        tp.finishCollapse();
    }

    /**
     * Expands the given task pane.
     * 
     * @param tp
     *            the task pane that will be expanded
     */
    public void expand(TaskPane tp) {
        checkSizes();

        tp.prepareToExpand();
        tp.doExpand();
        tp.finishExpand();
    }
}
