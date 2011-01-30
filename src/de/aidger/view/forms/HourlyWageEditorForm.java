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

package de.aidger.view.forms;

import static de.aidger.utils.Translation._;

import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import de.aidger.model.models.HourlyWage;
import de.aidger.view.tabs.EditorTab;
import de.aidger.view.utils.InputPatternFilter;
import de.aidger.view.utils.NumberFormat;

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

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                txtWage.requestFocusInWindow();
            }
        });

        // add input filters
        InputPatternFilter.addCurrencyFilter(txtWage);

        hlpWage.setToolTipText(MessageFormat
            .format(
                _("Only a valid currency format is allowed. Example: 8{0}40"),
                new Object[] { (new DecimalFormatSymbols())
                    .getDecimalSeparator() }));

        if (hw != null) {
            cmbQualification.setSelectedItem(Qualification.valueOf(hw
                .getQualification()));
            txtWage.setText(NumberFormat.getInstance().format(hw.getWage()));

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, hw.getMonth() - 1);
            cal.set(Calendar.YEAR, hw.getYear());
            spDate.setValue(cal.getTime());
            spDate.setEnabled(false);

            lblStartDate.setVisible(false);
            spStartDate.setVisible(false);
            lblFinishDate.setVisible(false);
            spFinishDate.setVisible(false);
        } else {
            lblDate.setVisible(false);
            spDate.setVisible(false);

            EditorTab.setTimeToNow(spStartDate);
            EditorTab.setTimeToNow(spFinishDate);
        }
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
     * Get the month the wage is valid in.
     * 
     * @return The month the wage is valid in
     */
    public byte getMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date) spDate.getValue());

        return (byte) (cal.get(Calendar.MONTH) + 1);
    }

    /**
     * Get the year the wage is valid in.
     * 
     * @return The year the wage is valid in
     */
    public short getYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date) spDate.getValue());

        return (short) cal.get(Calendar.YEAR);
    }

    /**
     * Get the start date the wage is valid in.
     * 
     * @return The start date the wage is valid in
     */
    public Date getStartDate() {
        return (Date) spStartDate.getValue();
    }

    /**
     * Get the finish date the wage is valid in.
     * 
     * @return The finish date the wage is valid in
     */
    public Date getFinishDate() {
        return (Date) spFinishDate.getValue();
    }

    /**
     * Returns if form is in edit mode.
     * 
     * @return if form is in edit mode.
     */
    public boolean isEditMode() {
        return spDate.isVisible();
    }

    /**
     * Get the wage per hour.
     * 
     * @return The wage per hour
     * @throws NumberFormatException
     */
    public double getWage() throws ParseException {
        return NumberFormat.getInstance().parse(txtWage.getText())
            .doubleValue();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblQualification = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblStartDate = new javax.swing.JLabel();
        lblFinishDate = new javax.swing.JLabel();
        lblWage = new javax.swing.JLabel();
        cmbQualification = new javax.swing.JComboBox();
        spDate = new javax.swing.JSpinner();
        spStartDate = new javax.swing.JSpinner();
        spFinishDate = new javax.swing.JSpinner();
        txtWage = new javax.swing.JTextField();
        hlpWage = new de.aidger.view.utils.HelpLabel();

        setLayout(new java.awt.GridBagLayout());

        lblQualification.setText(_("Qualification"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblQualification, gridBagConstraints);
        lblQualification.getAccessibleContext().setAccessibleDescription(
            "qualification");

        lblDate.setText(_("Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblDate, gridBagConstraints);
        lblDate.getAccessibleContext().setAccessibleDescription("date");

        lblStartDate.setText(_("Start date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblStartDate, gridBagConstraints);
        lblStartDate.getAccessibleContext().setAccessibleDescription(
            "startDate");

        lblFinishDate.setText(_("Finish date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblFinishDate, gridBagConstraints);
        lblFinishDate.getAccessibleContext().setAccessibleDescription(
            "finishDate");

        lblWage.setText(_("Wage"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblWage, gridBagConstraints);
        lblWage.getAccessibleContext().setAccessibleDescription("wage");

        cmbQualification.setModel(new javax.swing.DefaultComboBoxModel(
            Qualification.values()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(cmbQualification, gridBagConstraints);

        spDate.setModel(new javax.swing.SpinnerDateModel());
        spDate
            .setEditor(new javax.swing.JSpinner.DateEditor(spDate, "MM.yyyy"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(spDate, gridBagConstraints);

        spStartDate.setModel(new javax.swing.SpinnerDateModel());
        spStartDate.setEditor(new javax.swing.JSpinner.DateEditor(spStartDate,
            "MM.yyyy"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(spStartDate, gridBagConstraints);

        spFinishDate.setModel(new javax.swing.SpinnerDateModel());
        spFinishDate.setEditor(new javax.swing.JSpinner.DateEditor(
            spFinishDate, "MM.yyyy"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(spFinishDate, gridBagConstraints);

        txtWage.setMinimumSize(new java.awt.Dimension(200, 25));
        txtWage.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtWage, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        add(hlpWage, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbQualification;
    private de.aidger.view.utils.HelpLabel hlpWage;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblFinishDate;
    private javax.swing.JLabel lblQualification;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblWage;
    private javax.swing.JSpinner spDate;
    private javax.swing.JSpinner spFinishDate;
    private javax.swing.JSpinner spStartDate;
    private javax.swing.JTextField txtWage;
    // End of variables declaration//GEN-END:variables

}
