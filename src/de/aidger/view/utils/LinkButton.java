/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2013 The aidGer Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.aidger.view.utils;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.CellRendererPane;
import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.aidger.controller.actions.LinkAction;

/**
 * This class represents a button that reminds of HTML links. Furthermore it is
 * possible to set an icon to the button that is then displayed before the
 * linked text.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class LinkButton extends AbstractButton {
    /**
     * The name of the task pane button.
     */
    private final String name;

    /**
     * The underlined name of the task pane button.
     */
    private String uname;

    /**
     * A flag whether the button is underlined.
     */
    private boolean underlined;

    /**
     * The renderer of the button.
     */
    private final ButtonRenderer renderer;

    /**
     * A helper for the rendering process.
     */
    private final CellRendererPane crp = new CellRendererPane();

    /**
     * Constructs a task pane button.
     * 
     * @param a
     *            the action of the button
     */
    public LinkButton(LinkAction a) {
        renderer = new ButtonRenderer(a);

        setModel(new DefaultButtonModel());
        setAction(a);

        if (a.isLink()) {
            addMouseListener(a);
        }

        this.name = (String) a.getValue(AbstractAction.NAME);
        String action = (String) a.getValue(AbstractAction.ACTION_COMMAND_KEY);
        setActionCommand(action != null ? action : name);

        // add button renderer
        add(crp);
        crp.add(renderer);

        renderer.setBorder(new EmptyBorder(0, 0, 0, 0));
        setBorder(new EmptyBorder(0, 0, 0, 0));

        setRolloverEnabled(true);
        setBorderPainted(false);

        // add mouse handling for button
        if (a.isLink()) {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        setUnderlined(false);

                        if (!model.isEnabled()) {
                            return;
                        }

                        if (!model.isArmed()) {
                            model.setArmed(true);
                        }

                        model.setPressed(true);
                        setUnderlined(true);

                        if (!hasFocus() && isRequestFocusEnabled()) {
                            requestFocus();
                        }
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        setUnderlined(false);
                        model.setPressed(false);
                        model.setArmed(false);
                        setUnderlined(model.isRollover());
                    }
                };

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (isRolloverEnabled()) {
                        model.setRollover(true);
                    }

                    if (model.isPressed()) {
                        model.setArmed(true);
                    }
                };

                @Override
                public void mouseExited(MouseEvent e) {
                    if (isRolloverEnabled()) {
                        model.setRollover(false);
                    }

                    model.setArmed(false);
                };
            });

            model.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    setUnderlined(model.isRollover());
                }
            });
        }
    }

    /**
     * Sets the name of the button.
     * 
     * @param b
     *            the flag whether the name will be underlined
     */
    protected void setUnderlined(boolean b) {
        if (underlined != b) {
            underlined = b;

            if (!underlined) {
                renderer.setText(name);
            } else {
                renderer.setText(getUname());
            }

            repaint();
        }
    }

    /**
     * Returns the underlined name as HTML.
     * 
     * @return the underlined name as HTML
     */
    protected String getUname() {
        if (uname == null) {
            uname = "<html><u>" + name + "</u></html>";
        }

        return uname;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#getPreferredSize()
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension preferredSize = renderer.getPreferredSize();
        tmp = getInsets(tmp);
        preferredSize.width += tmp.left + tmp.right;
        preferredSize.height += tmp.top + tmp.bottom;

        return preferredSize;
    }

    transient Insets tmp = new Insets(0, 0, 0, 0);

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#getMaximumSize()
     */
    @Override
    public Dimension getMaximumSize() {
        Dimension maximumSize = renderer.getMaximumSize();
        tmp = getInsets(tmp);
        maximumSize.width += tmp.left + tmp.right;
        maximumSize.height += tmp.top + tmp.bottom;

        return maximumSize;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#getMinimumSize()
     */
    @Override
    public Dimension getMinimumSize() {
        Dimension d2 = renderer.getMinimumSize();
        Insets i = getInsets(tmp);
        d2.width += i.left + i.right;
        d2.height += i.top + i.bottom;

        return d2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        Insets i = getInsets();

        crp.paintComponent(g, renderer, this, i.left, i.top, getWidth()
                - i.left - i.right, getHeight() - i.top - i.bottom);
    }

    /**
     * The renderer for the task pane button.
     * 
     * @author aidGer Team
     */
    static class ButtonRenderer extends JLabel {
        /**
         * Constructs the button. The button is rendered as a label.
         * 
         * @param a
         *            the action that holds information about the button
         */
        public ButtonRenderer(Action a) {
            super((String) a.getValue(AbstractAction.NAME), (Icon) a
                .getValue(AbstractAction.SMALL_ICON), SwingConstants.LEADING);
        }
    }
}
