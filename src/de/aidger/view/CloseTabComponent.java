/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2011 The aidGer Team
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

package de.aidger.view;

import static de.aidger.utils.Translation._;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Component to be used as tabComponent. It contains a JLabel to show the title
 * of the tab and a JButton to close the tab it belongs to
 */
@SuppressWarnings("serial")
public class CloseTabComponent extends JPanel {

    public CloseTabComponent(String text) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setOpaque(false);

        JLabel label = new JLabel(text);
        add(label);

        // add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

        JButton button = new TabButton();
        add(button);

        // add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }

    private class TabButton extends JButton implements ActionListener {
        public TabButton() {
            int size = 17;

            setPreferredSize(new Dimension(size, size));
            setToolTipText(_("Close tab"));

            setUI(new BasicButtonUI());

            // Make it transparent
            setContentAreaFilled(false);

            // No need to be focusable
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);

            // we use the same listener for all buttons
            addMouseListener(buttonMouseListener);
            setRolloverEnabled(true);

            // Close the proper tab by clicking the button
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            int i = UI.getInstance().getTabbedPane().indexOfTabComponent(
                CloseTabComponent.this);

            if (i != -1) {
                UI.getInstance().removeTabAt(i);
            }
        }

        // we don't want to update UI for this button
        @Override
        public void updateUI() {
        }

        // paint the cross
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();

            // shift the image for pressed buttons
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }

            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);

            int delta = 6;

            g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight()
                    - delta - 1);
            g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight()
                    - delta - 1);

            g2.dispose();
        }
    }

    private final static MouseListener buttonMouseListener = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            Component component = e.getComponent();

            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(true);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            Component component = e.getComponent();

            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(false);
            }
        }
    };
}
