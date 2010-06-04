package de.aidger.view.forms;

import static de.aidger.utils.Translation._;

import javax.swing.JPanel;

import de.aidger.model.models.FinancialCategory;

/**
 * A form used for editing / creating new financial categories.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class FinancialCategoryEditorForm extends JPanel {

    /**
     * Constructs a financial category editor form.
     * 
     * @param fc
     *            the financial category that will be edited
     */
    public FinancialCategoryEditorForm(FinancialCategory fc) {
        initComponents();

        // add input filters
        InputPatternFilter.addFilter(txtBudgetCosts, "[0-9]+[.,]?[0-9]{0,2}");
        InputPatternFilter.addFilter(txtFonds, "[0-9]{0,8}");
        InputPatternFilter.addFilter(txtYear, "[0-9]{0,4}");

        if (fc != null) {
            txtName.setText(fc.getName());
            txtYear.setText(String.valueOf(fc.getYear()));

            // TODO
        }
    }

    /**
     * Get the budget costs of the category.
     * 
     * @return The budget costs of the category
     */
    public int[] getBudgetCosts() {
        return new int[] { 0 }; // TODO
    }

    /**
     * Get the fonds of the category.
     * 
     * @return The fonds of the category
     */
    public int[] getFonds() {
        return new int[] { 0 }; // TODO
    }

    /**
     * Get the name of the category.
     * 
     * @return The name of the category
     */
    public String getFCName() {
        return txtName.getText();
    }

    /**
     * Get the year the category is valid.
     * 
     * @return The year the category is valid
     */
    public short getYear() {
        String year = txtYear.getText();

        if (year.isEmpty()) {
            ;
        }

        return Short.valueOf(year);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblName = new javax.swing.JLabel();
        lblBudgetCosts = new javax.swing.JLabel();
        lblFonds = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtBudgetCosts = new javax.swing.JTextField();
        txtFonds = new javax.swing.JTextField();
        txtYear = new javax.swing.JFormattedTextField();

        setLayout(new java.awt.GridBagLayout());

        lblName.setText(_("Name"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblName, gridBagConstraints);

        lblBudgetCosts.setText(_("Budget Costs"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblBudgetCosts, gridBagConstraints);

        lblFonds.setText(_("Fonds"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblFonds, gridBagConstraints);

        lblYear.setText(_("Year"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblYear, gridBagConstraints);

        txtName.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtName, gridBagConstraints);

        txtBudgetCosts.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtBudgetCosts, gridBagConstraints);

        txtFonds.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtFonds, gridBagConstraints);

        txtYear.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        add(txtYear, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBudgetCosts;
    private javax.swing.JLabel lblFonds;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblYear;
    private javax.swing.JTextField txtBudgetCosts;
    private javax.swing.JTextField txtFonds;
    private javax.swing.JTextField txtName;
    private javax.swing.JFormattedTextField txtYear;
    // End of variables declaration//GEN-END:variables

}