package de.aidger.view.forms;

import static de.aidger.utils.Translation._;

import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.view.models.UIAssistant;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAssistant;

/**
 * A form used for viewing contracts in detail.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ContractViewerForm extends JPanel {

    /**
     * Constructs a contract viewer form.
     * 
     * @param contract
     *            the contract that will be displayed
     */
    public ContractViewerForm(Contract contract) {
        initComponents();

        String f = "dd.MM.yyyy";

        try {
            IAssistant a = (new Assistant()).getById(contract.getAssistantId());

            assistant.setText((new UIAssistant(a)).toString());
            completionDate.setText((new SimpleDateFormat(f)).format(contract
                .getCompletionDate()));
            startDate.setText((new SimpleDateFormat(f)).format(contract
                .getStartDate()));
            endDate.setText((new SimpleDateFormat(f)).format(contract
                .getEndDate()));

            type.setText(contract.getType());

            if (contract.getConfirmationDate() != null) {
                confirmationDate.setText((new SimpleDateFormat(f))
                    .format(contract.getConfirmationDate()));
            }

            boolean d = contract.isDelegation();

            String strDelegation = _("no");
            if (d) {
                strDelegation = _("yes");
            }

            delegation.setText(strDelegation);
        } catch (AdoHiveException e) {
        }
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
        assistant = new javax.swing.JLabel();
        completionDate = new javax.swing.JLabel();
        confirmationDate = new javax.swing.JLabel();
        startDate = new javax.swing.JLabel();
        endDate = new javax.swing.JLabel();
        type = new javax.swing.JLabel();
        delegation = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        lblAssistant.setText(_("Assistant"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblAssistant, gridBagConstraints);

        lblCompletionDate.setText(_("Completion date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblCompletionDate, gridBagConstraints);

        lblConfirmationDate.setText(_("Confirmation date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblConfirmationDate, gridBagConstraints);

        lblStartDate.setText(_("Start date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblStartDate, gridBagConstraints);

        lblEndDate.setText(_("End date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblEndDate, gridBagConstraints);

        lblType.setText(_("Type"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblType, gridBagConstraints);

        lblDelegation.setText(_("Delegation"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblDelegation, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(assistant, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(completionDate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(confirmationDate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(startDate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(endDate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(type, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(delegation, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel assistant;
    private javax.swing.JLabel completionDate;
    private javax.swing.JLabel confirmationDate;
    private javax.swing.JLabel delegation;
    private javax.swing.JLabel endDate;
    private javax.swing.JLabel lblAssistant;
    private javax.swing.JLabel lblCompletionDate;
    private javax.swing.JLabel lblConfirmationDate;
    private javax.swing.JLabel lblDelegation;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel startDate;
    private javax.swing.JLabel type;
    // End of variables declaration//GEN-END:variables

}
