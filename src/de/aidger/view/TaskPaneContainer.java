package de.aidger.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class TaskPaneContainer extends JPanel {
    Color first /* = new Color(0xFF7BA2E7) */;
    Color second /* = new Color(0xFF6476D6) */;

    TaskPane expanded;

    boolean gradientValid;
    GradientPaint paint;

    boolean animated;

    boolean showRollEffect;
    boolean fadeOut;

    boolean autoCollapse;
    boolean stretch;

    public TaskPaneContainer(Color first, Color second) {
        setLayout(new GridBagLayout());

        this.first = first;
        this.second = second;

        setBorder(new EmptyBorder(10, 10, 10, 10));

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                gradientValid = false;
            }
        });

        setAnimated(true);
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

    public boolean isFadeOut() {
        return fadeOut;
    }

    public void setFadeOut(boolean fadeOut) {
        this.fadeOut = fadeOut;
    }

    public void addTask(TaskPane taskPane) {
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 20, 0);
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;

        add(taskPane, c);

        taskPane.container = this;
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
        return (TaskPane) getComponent(index);
    }

    public int getTaskCount() {
        return getComponentCount();
    }

    /*
     * @Override protected void paintComponent(Graphics g) { Paint p =
     * getPaint(); Graphics2D g2d = (Graphics2D) g; g2d.setPaint(p);
     * g2d.fillRect(0, 0, getWidth(), getHeight()); }
     */

    public boolean isShowRollEffect() {
        return showRollEffect;
    }

    public void setShowRollEffect(boolean showRollEffect) {
        this.showRollEffect = showRollEffect;
    }

    public boolean isStretch() {
        return stretch;
    }

    public void setStretch(boolean stretch) {
        this.stretch = stretch;
    }

    public boolean isAutoCollapse() {
        return autoCollapse;
    }

    public void setAutoCollapse(boolean autoCollapse) {
        this.autoCollapse = autoCollapse;
        if (autoCollapse) {
            checkSizes();
            int count = getTaskCount();
            int cnt = 0;
            for (int i = 0; i < count; i++) {
                TaskPane tp = getTask(i);
                if (tp.expanded && tp != expanded) {
                    cnt++;
                }
            }
            TaskPane[] tps = new TaskPane[cnt];
            cnt = 0;
            for (int i = 0; i < count; i++) {
                TaskPane tp = getTask(i);
                if (tp.expanded && tp != expanded) {
                    tps[cnt++] = tp;
                }
            }
            change(new TaskPane[0], tps);
        }
    }

    protected void checkSizes() {
        int count = getTaskCount();
        int max = 0;
        if (autoCollapse) {
            if (!stretch) {
                for (int i = 0; i < count; i++) {
                    TaskPane tp = getTask(i);
                    max = Math.max(tp.getPreferredHeight(), max);
                }
            } else {
                Dimension d = getSize();
                Insets insets = getInsets();
                d.height -= insets.top + insets.bottom;
                for (int i = 0; i < count; i++) {
                    TaskPane tp = getTask(i);
                    Dimension d0 = tp.titleBar.getPreferredSize();
                    Insets insets0 = tp.getInsets();
                    d.height -= d0.height + insets0.top + insets0.bottom;
                }
                max = Math.max(d.height, 0);
            }
        }

        for (int i = 0; i < count; i++) {
            TaskPane tp = getTask(i);
            tp.setExpandedHeight(max);
        }
    }

    public void collapse(TaskPane tp) {
        checkSizes();
        if (!autoCollapse || expanded == null || expanded != tp) {
            if (animated) {
                tp.prepareToCollapse();
                tp.doCollapse();
            }
            tp.finishCollapse();
        } else {
            int count = getTaskCount();
            for (int i = 0; i < count; i++) {
                if (getTask(i) == tp) {
                    if (i == count - 1) {
                        expand(getTask(i - 1));
                    } else {
                        expand(getTask(i + 1));
                    }
                }
            }
        }
    }

    public void expand(TaskPane tp) {
        checkSizes();
        if (animated) {
            if (!autoCollapse || expanded == null || expanded == tp) {
                tp.prepareToExpand();
                tp.doExpand();
                tp.finishExpand();
            } else {
                change(new TaskPane[] { tp }, new TaskPane[] { expanded });
            }
            expanded = tp;
        } else {
            tp.contentPane.setPreferredSize(null);
            tp.contentPane.setVisible(true);
            tp.finishExpand();

            if (autoCollapse && expanded != null && expanded != tp) {
                expanded.finishCollapse();
            }
            doLayout();
            expanded = tp;
        }
    }

    public void change(TaskPane[] panesToExpand, TaskPane[] panesToCollapse) {
        checkSizes();
        int count = 0;
        for (int i = 0; i < panesToExpand.length; i++) {
            count = Math.max(count, panesToExpand[i].prepareToExpand());
        }
        for (int i = 0; i < panesToCollapse.length; i++) {
            count = Math.max(count, panesToCollapse[i].prepareToCollapse());
        }
        for (int j = 0; j < count; j++) {
            for (int i = 0; i < panesToExpand.length; i++) {
                panesToExpand[i].doExpandStep();
            }
            for (int i = 0; i < panesToCollapse.length; i++) {
                panesToCollapse[i].doCollapseStep();
            }
            doLayout();

            for (int i = 0; i < panesToCollapse.length; i++) {
                panesToCollapse[i].doLayout();
            }
            paintImmediately(0, 0, getWidth(), getHeight());
        }
        for (int i = 0; i < panesToExpand.length; i++) {
            panesToExpand[i].finishExpand();
            this.expanded = panesToExpand[i];
        }
        for (int i = 0; i < panesToCollapse.length; i++) {
            panesToCollapse[i].finishCollapse();
        }
    }

    public void setAnimated(boolean b) {
        this.animated = b;
        int count = getTaskCount();
        for (int i = 0; i < count; i++) {
            TaskPane tp = getTask(i);
            tp.setAnimated(b);
        }
    }

    public boolean isAnimated() {
        return animated;
    }
}
