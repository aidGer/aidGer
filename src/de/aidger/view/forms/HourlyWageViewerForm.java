package de.aidger.view.forms;

import static de.aidger.utils.Translation._;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JPanel;

import de.aidger.model.models.HourlyWage;
import de.aidger.view.forms.HourlyWageEditorForm.Qualification;
import de.aidger.view.utils.NumberFormat;

/**
 * A form used for viewing hourly wages.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class HourlyWageViewerForm extends JPanel {

    /**
     * Constructs a hourly wage viewer form.
     * 
     * @param hw
     *            the hourly wage that will be displayed
     */
    public HourlyWageViewerForm(HourlyWage hw) {
        initComponents();

        qualification.setText(Qualification.valueOf(hw.getQualification())
            .toString());

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, hw.getMonth() - 1);
        cal.set(Calendar.YEAR, hw.getYear());
        date.setText(new SimpleDateFormat("MM.yyyy").format(cal.getTime()));

        wage
            .setText(NumberFormat.getInstance().format(hw.getWage()) + "\u20ac");
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
        lblWage = new javax.swing.JLabel();
        qualification = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        wage = new javax.swing.JLabel();
        filler = new javax.swing.JLabel();

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(qualification, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(date, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(wage, gridBagConstraints);

        filler.setPreferredSize(new java.awt.Dimension(200, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        add(filler, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date;
    private javax.swing.JLabel filler;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblQualification;
    private javax.swing.JLabel lblWage;
    private javax.swing.JLabel qualification;
    private javax.swing.JLabel wage;
    // End of variables declaration//GEN-END:variables

}
