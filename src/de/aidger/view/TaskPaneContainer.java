package de.aidger.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class TaskPaneContainer extends JPanel {
    Color first;
    Color second;
    TaskPane expanded;
    boolean gradientValid;
    GradientPaint paint;

    public TaskPaneContainer(Color first, Color second) {
        setLayout(new GridBagLayout());

        this.first = first;
        this.second = second;

        setBorder(new EmptyBorder(10, 10, 0, 10));

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                gradientValid = false;
            }
        });

        // use animation
        int count = getTaskCount();

        for (int i = 0; i < count; i++) {
            TaskPane tp = getTask(i);
            tp.setAnimated(true);
        }
    }

    public TaskPaneContainer() {
        this(new Color(0xFF7BA2E7), new Color(0xFF6476D6));
    }

    protected GradientPaint getPaint() {
        if (paint == null || !gradientValid) {
            paint = new GradientPaint(0, 0, first, 0, getHeight(), second);
        }

        return paint;
    }

    public void addTask(TaskPane taskPane) {
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 15, 0);
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;

        add(taskPane, c);

        taskPane.container = this;
    }

    public void addFiller() {
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.weighty = 1.0;

        add(javax.swing.Box.createVerticalGlue(), c);
    }

    public void removeTask(TaskPane taskPane) {
        super.remove(taskPane);

        if (taskPane.container == this) {
            taskPane.container = null;
        }
    }

    public void removeTask(int i) {
        TaskPane tp = getTask(i);

        if (tp.container == this) {
            tp.container = null;
        }

        super.remove(i);
    }

    public TaskPane getTask(int index) {
        if (getComponent(index) instanceof TaskPane) {
            return (TaskPane) getComponent(index);
        }

        return null;
    }

    public int getTaskCount() {
        return getComponentCount();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Paint p = getPaint();
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(p);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

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

    public void collapse(TaskPane tp) {
        checkSizes();

        tp.prepareToCollapse();
        tp.doCollapse();

        tp.finishCollapse();
    }

    public void expand(TaskPane tp) {
        checkSizes();

        tp.prepareToExpand();
        tp.doExpand();
        tp.finishExpand();

        expanded = tp;
    }
}
