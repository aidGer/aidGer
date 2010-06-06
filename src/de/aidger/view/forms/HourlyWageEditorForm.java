package de.aidger.view.forms;

import static de.aidger.utils.Translation._;

import javax.swing.JPanel;

import de.aidger.model.models.HourlyWage;

/**
 * A form used for editing / creating new hourly wages.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class HourlyWageEditorForm extends JPanel {

    public enum Qualification {
        u(_("Unchecked")), g(_("Checked")), b(_("Bachelor"));

        /**
         * The display name of an item.
         */
        private final String displayName;

        /**
         * Constructs a qualification item.
         * 
         * @param displayName
         *            the display name of the item
         */
        Qualification(final String displayName) {
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
     * Constructs a hourly wage editor form.
     * 
     * @param hw
     *            the hourly wage that will be edited
     */
    public HourlyWageEditorForm(HourlyWage hw) {
        initComponents();

        // add input filters
        InputPatternFilter.addFilter(txtWage, "[0-9]+[.,]?[0-9]{0,2}");

        if (hw != null) {
            cmbQualification.setSelectedItem(Qualification.valueOf(hw
                    .getQualification()));
            // spDate.setValue(hw.getMonth() + "." + hw.getYear());
            txtWage.setText(String.valueOf(hw.getWage()));
        }
    }

    /**
     * Get the month the wage is valid in.
     * 
     * @return The month the wage is valid in
     */
    public byte getMonth() {
        return 6; // TODO
    }

    /**
     * Get the qualification needed for the wage.
     * 
     * @return The qualification needed for the wage
     */
    public String getQualification() {
        return ((Qualification) cmbQualification.getSelectedItem()).name();
    }

    /**
     * Get the wage per hour.
     * 
     * @return The wage per hour
     */
    public String getWage() {
        return txtWage.getText();
    }

    /**
     * Get the year the wage is valid in.
     * 
     * @return The year the wage is valid in
     */
    public short getYear() {
        return 2010; // TODO
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
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblQualification = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblWage = new javax.swing.JLabel();
        cmbQualification = new javax.swing.JComboBox();
        spDate = new javax.swing.JSpinner();
        txtWage = new javax.swing.JTextField();

        setLayout(new java.awt.GridBagLayout());

        lblQualification.setText(_("Qualification"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblQualification, gridBagConstraints);

        lblDate.setText(_("Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblDate, gridBagConstraints);

        lblWage.setText(_("Wage"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblWage, gridBagConstraints);

        cmbQualification.setModel(new javax.swing.DefaultComboBoxModel(
                Qualification.values()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(cmbQualification, gridBagConstraints);

        spDate.setModel(new javax.swing.SpinnerDateModel());
        spDate
                .setEditor(new javax.swing.JSpinner.DateEditor(spDate,
                        "MM.yyyy"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(spDate, gridBagConstraints);

        txtWage.setMinimumSize(new java.awt.Dimension(200, 25));
        txtWage.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtWage, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbQualification;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblQualification;
    private javax.swing.JLabel lblWage;
    private javax.swing.JSpinner spDate;
    private javax.swing.JTextField txtWage;
    // End of variables declaration//GEN-END:variables

}
