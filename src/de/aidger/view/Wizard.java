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
import de.aidger.controller.ActionNotFoundException;
import de.aidger.controller.ActionRegistry;
import de.aidger.controller.actions.ExitAction;
import de.aidger.controller.actions.WizardNextAction;
import de.aidger.controller.actions.WizardPreviousAction;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

/**
 * Automatically displays buttons and advances to previous/next forms of the same wizard.
 * 
 * @author rmbl
 */
abstract public class Wizard extends javax.swing.JDialog {

    private List<WizardPanel> panels = new ArrayList<WizardPanel>();

    private int index = 0;

    /** 
     * Creates a new wizard form
     */
    public Wizard(java.awt.Frame parent) {
        super(parent, true);
        initLayout();
    }

    public void addPanel(WizardPanel panel) {
        panels.add(panel);
        cardPanel.add(panel, panel.getClass().getName());
    }

    public void showDialog() {
        showFirstPanel();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showFirstPanel() {
        if (panels.size() > 0) {
            panels.get(index = 0).preparePanel();
            cardLayout.first(cardPanel);

            prevBtn.setEnabled(false);
            if (panels.size() == 1) {
                nextBtn.setText(_("Finish"));
            }
        }
    }

    public void showNextPanel() {
        if (index + 1 != panels.size()) {
            panels.get(++index).preparePanel();
            cardLayout.next(cardPanel);            

            if (index + 1 == panels.size()) {
                nextBtn.setText(_("Finish"));
            }

            prevBtn.setEnabled(true);
        } else {
            setVisible(false);
            dispose();
        }
    }

    public void showNextPanel(ActionEvent e) {
        if (executeNextAction(e)) {
            showNextPanel();
        }
    }

    public void showPrevPanel() {
        if (index - 1 >= 0) {
            panels.get(--index).preparePanel();
            cardLayout.previous(cardPanel);            
            
            if (index == 0) {
                prevBtn.setEnabled(false);
            } else {
                prevBtn.setEnabled(true);
            }

            if (nextBtn.getText().equals(_("Finish"))) {
                nextBtn.setText(_("Next"));
            }
        }
    }

    public WizardPanel getCurrentPanel() {
        return panels.get(index);
    }

    protected void setCancelAction(AbstractAction action) {
        exitBtn.setAction(action);
    }

    private boolean executeNextAction(ActionEvent e) {
        AbstractAction action = panels.get(index).getNextAction();
        if (action != null) {
            action.actionPerformed(e);
            if (e.getSource() == null) {
                return false;
            }
        }
        return true;
    }

    private void initLayout() {
        getContentPane().setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        JSeparator separator = new JSeparator();
        Box buttonBox = new Box(BoxLayout.X_AXIS);

        cardPanel = new JPanel();
        cardPanel.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));

        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        try {
            prevBtn = new JButton();
            prevBtn.setAction(ActionRegistry.getInstance().get(WizardPreviousAction.class.getName()));
            nextBtn = new JButton();
            nextBtn.setAction(ActionRegistry.getInstance().get(WizardNextAction.class.getName()));
            exitBtn = new JButton();
            exitBtn.setAction(ActionRegistry.getInstance().get(ExitAction.class.getName()));
        } catch (ActionNotFoundException ex) {
            UI.displayError(ex.getMessage());
        }

        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(separator, BorderLayout.NORTH);

        buttonBox.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));
        buttonBox.add(exitBtn);
        buttonBox.add(Box.createHorizontalStrut(300));
        buttonBox.add(prevBtn);
        buttonBox.add(Box.createHorizontalStrut(10));
        buttonBox.add(nextBtn);        

        buttonPanel.add(buttonBox, java.awt.BorderLayout.WEST);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);
        getContentPane().add(cardPanel, java.awt.BorderLayout.CENTER);
    }

    private CardLayout cardLayout;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton nextBtn;
    private javax.swing.JButton prevBtn;

}
