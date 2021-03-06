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

package de.aidger.view.forms;

import static de.aidger.utils.Translation._;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.utils.Anonymizer;
import de.aidger.view.models.ComboBoxModel;
import de.aidger.view.models.UIAssistant;
import de.aidger.view.tabs.EditorTab;
import de.aidger.view.tabs.ViewerTab.DataType;
import de.aidger.view.utils.AutoCompletion;
import de.aidger.view.utils.BooleanListRenderer;
import siena.SienaException;

/**
 * A form used for editing / creating new contracts.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ContractEditorForm extends JPanel {

    public enum ContractType {
        newContract(_("New contract")), increase(_("Increase")), decrease(
                _("Decrease")), rebooking(_("Rebooking"));

        /**
         * The display name of an item.
         */
        private final String displayName;

        /**
         * Constructs a contract type item.
         * 
         * @param displayName
         *            the display name of the item
         */
        ContractType(final String displayName) {
            this.displayName = displayName;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return displayName;
        }
    }

    /**
     * A flag whether the form is in edit mode.
     */
    private boolean editMode = false;

    /**
     * Constructs a contract editor form.
     * 
     * @param contract
     *            the contract that will be edited
     * @tab the parent editor tab
     */
    @SuppressWarnings("unchecked")
    public ContractEditorForm(Contract contract, EditorTab tab) {
        if (contract != null) {
            editMode = true;
        }

        initComponents();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                cmbAssistant.requestFocusInWindow();
            }
        });

        AutoCompletion.enable(cmbAssistant);

        cmbDelegation.setRenderer(new BooleanListRenderer());

        try {
            List<Assistant> assistants = (new Assistant()).getAll();

            ComboBoxModel cmbAssistantModel = new ComboBoxModel(
                DataType.Assistant);

            for (Assistant a : assistants) {
                Assistant assistant = new UIAssistant(a);

                if (assistant.getLastName().equals(Anonymizer.token)
                        && !editMode) {
                    continue;
                }

                cmbAssistantModel.addElement(assistant);

                if (contract != null
                        && assistant.getId().equals(contract.getAssistantId())) {
                    cmbAssistantModel.setSelectedItem(assistant);
                }
            }

            cmbAssistant.setModel(cmbAssistantModel);
            tab.getListModels().add(cmbAssistantModel);
        } catch (SienaException e) {
            //TODO: Handle
        }

        if (editMode) {
            spCompletionDate.setValue(contract.getCompletionDate());

            if (contract.getConfirmationDate() == null) {
                EditorTab.setTimeToNow(spConfirmationDate);
            } else {
                spConfirmationDate.setValue(contract.getConfirmationDate());
            }

            spStartDate.setValue(contract.getStartDate());
            spEndDate.setValue(contract.getEndDate());
            cmbType.setSelectedItem(ContractType.valueOf(contract.getType()));
            cmbDelegation.setSelectedItem(Boolean.valueOf(contract
                .isDelegation()));
        } else {
            EditorTab.setTimeToNow(spCompletionDate);
            EditorTab.setTimeToNow(spStartDate);
            EditorTab.setTimeToNow(spEndDate, Calendar.MONTH, 1);

            // hide confirmation date
            lblConfirmationDate.setVisible(false);
            spConfirmationDate.setVisible(false);

            // add confirmation date hint for user
            tab
                .addHint(_("Confirmation date can be entered after saving the contract."));
        }
    }

    /**
     * Sets the assistant combobox.
     * 
     * @param assistant
     *            the assistant
     */
    public void setAssistant(Assistant assistant) {
        cmbAssistant.setSelectedItem(assistant);
    }

    /**
     * Get the corresponding assistant.
     * 
     * @return The assistant
     */
    public Assistant getAssistant() {
        return (Assistant) cmbAssistant.getSelectedItem();
    }

    /**
     * Get the date the contract was completed.
     * 
     * @return The date the contract was completed
     */
    public java.sql.Date getCompletionDate() {
        return new java.sql.Date(((Date) spCompletionDate.getValue()).getTime());
    }

    /**
     * Get the date the contract was confirmed.
     * 
     * @return The date the contract was confirmed
     */
    public java.sql.Date getConfirmationDate() {
        // ensure that null is returned
        if (!editMode) {
            return null;
        }

        return new java.sql.Date(((Date) spConfirmationDate.getValue())
            .getTime());
    }

    /**
     * Get the date the contract starts.
     * 
     * @return The date the contract starts
     */
    public java.sql.Date getStartDate() {
        return new java.sql.Date(((Date) spStartDate.getValue()).getTime());
    }

    /**
     * Get the date the contract ends.
     * 
     * @return The date the contract ends.
     */
    public java.sql.Date getEndDate() {
        return new java.sql.Date(((Date) spEndDate.getValue()).getTime());
    }

    /**
     * Get the type of the contract.
     * 
     * @return The type of the contract
     */
    public String getType() {
        return ((ContractType) cmbType.getSelectedItem()).name();
    }

    /**
     * Has the contract been delegated.
     * 
     * @return True if the contract has been delegated
     */
    public boolean isDelegation() {
        return (Boolean) cmbDelegation.getSelectedItem();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblAssistant = new javax.swing.JLabel();
        lblCompletionDate = new javax.swing.JLabel();
        lblConfirmationDate = new javax.swing.JLabel();
        lblStartDate = new javax.swing.JLabel();
        lblEndDate = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        lblDelegation = new javax.swing.JLabel();
        cmbAssistant = new javax.swing.JComboBox();
        spCompletionDate = new javax.swing.JSpinner();
        spConfirmationDate = new javax.swing.JSpinner();
        spStartDate = new javax.swing.JSpinner();
        spEndDate = new javax.swing.JSpinner();
        cmbType = new javax.swing.JComboBox();
        cmbDelegation = new javax.swing.JComboBox();

        setLayout(new java.awt.GridBagLayout());

        lblAssistant.setText(_("Assistant"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblAssistant, gridBagConstraints);
        lblAssistant.getAccessibleContext().setAccessibleDescription(
            "assistantId");

        lblCompletionDate.setText(_("Completion date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblCompletionDate, gridBagConstraints);
        lblCompletionDate.getAccessibleContext().setAccessibleDescription(
            "completionDate");

        lblConfirmationDate.setText(_("Confirmation date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblConfirmationDate, gridBagConstraints);
        lblConfirmationDate.getAccessibleContext().setAccessibleDescription(
            "confirmationDate");

        lblStartDate.setText(_("Start date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblStartDate, gridBagConstraints);
        lblStartDate.getAccessibleContext().setAccessibleDescription(
            "startDate");

        lblEndDate.setText(_("End date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblEndDate, gridBagConstraints);
        lblEndDate.getAccessibleContext().setAccessibleDescription("endDate");

        lblType.setText(_("Type"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblType, gridBagConstraints);
        lblType.getAccessibleContext().setAccessibleDescription("type");

        lblDelegation.setText(_("Delegation"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblDelegation, gridBagConstraints);
        lblDelegation.getAccessibleContext().setAccessibleDescription(
            "delegation");

        cmbAssistant.setMinimumSize(new java.awt.Dimension(200, 25));
        cmbAssistant.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(cmbAssistant, gridBagConstraints);

        spCompletionDate.setModel(new javax.swing.SpinnerDateModel());
        spCompletionDate.setEditor(new javax.swing.JSpinner.DateEditor(
            spCompletionDate, "dd.MM.yyyy"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(spCompletionDate, gridBagConstraints);

        spConfirmationDate.setModel(new javax.swing.SpinnerDateModel());
        spConfirmationDate.setEditor(new javax.swing.JSpinner.DateEditor(
            spConfirmationDate, "dd.MM.yyyy"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(spConfirmationDate, gridBagConstraints);

        spStartDate.setModel(new javax.swing.SpinnerDateModel());
        spStartDate.setEditor(new javax.swing.JSpinner.DateEditor(spStartDate,
            "dd.MM.yyyy"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(spStartDate, gridBagConstraints);

        spEndDate.setModel(new javax.swing.SpinnerDateModel());
        spEndDate.setEditor(new javax.swing.JSpinner.DateEditor(spEndDate,
            "dd.MM.yyyy"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(spEndDate, gridBagConstraints);

        cmbType.setModel(new DefaultComboBoxModel(ContractType.values()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(cmbType, gridBagConstraints);

        cmbDelegation.setModel(new DefaultComboBoxModel(new Object[] {
                Boolean.FALSE, Boolean.TRUE }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(cmbDelegation, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbAssistant;
    private javax.swing.JComboBox cmbDelegation;
    private javax.swing.JComboBox cmbType;
    private javax.swing.JLabel lblAssistant;
    private javax.swing.JLabel lblCompletionDate;
    private javax.swing.JLabel lblConfirmationDate;
    private javax.swing.JLabel lblDelegation;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblType;
    private javax.swing.JSpinner spCompletionDate;
    private javax.swing.JSpinner spConfirmationDate;
    private javax.swing.JSpinner spEndDate;
    private javax.swing.JSpinner spStartDate;
    // End of variables declaration//GEN-END:variables

}
