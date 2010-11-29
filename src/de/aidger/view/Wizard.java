package de.aidger.view;

import de.aidger.controller.ActionNotFoundException;
import de.aidger.controller.ActionRegistry;
import de.aidger.controller.actions.ExitAction;
import de.aidger.controller.actions.WizardFinishAction;
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
            cardLayout.first(cardPanel);
            index = 0;
            try {
                if (panels.size() == 1) {
                    nextBtn.setAction(ActionRegistry.getInstance().get(WizardFinishAction.class.getName()));
                }
                prevBtn.setEnabled(false);
            } catch (ActionNotFoundException ex) {
                UI.displayError(ex.getMessage());
            }
        }
    }

    public void showNextPanel() {
        if (index + 1 != panels.size()) {
            cardLayout.next(cardPanel);
            ++index;

            if (index + 1 == panels.size()) {
                try {
                    nextBtn.setAction(ActionRegistry.getInstance().get(WizardFinishAction.class.getName()));
                } catch (ActionNotFoundException ex) {
                    UI.displayError(ex.getMessage());
                }
            }
            prevBtn.setEnabled(true);
        }
    }

    public void showPrevPanel() {
        if (index - 1 >= 0) {
            cardLayout.previous(cardPanel);
            --index;
            
            if (index == 0) {
                prevBtn.setEnabled(false);
            } else {
                prevBtn.setEnabled(true);
            }

            try {
                if (nextBtn.getAction() == ActionRegistry.getInstance().get(WizardFinishAction.class.getName())) {
                    nextBtn.setAction(ActionRegistry.getInstance().get(WizardNextAction.class.getName()));
                }
            } catch (ActionNotFoundException ex) {
                UI.displayError(ex.getMessage());
            }
        }
    }

    public void executeNextAction(ActionEvent e) {
        AbstractAction action = panels.get(index).getNextAction();
        if (action != null) {
            action.actionPerformed(e);
        }
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
