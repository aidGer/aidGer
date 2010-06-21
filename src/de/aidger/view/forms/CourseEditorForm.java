package de.aidger.view.forms;

import static de.aidger.utils.Translation._;

import java.util.List;

import de.aidger.model.models.Course;
import de.aidger.model.models.FinancialCategory;
import de.aidger.utils.Logger;
import de.aidger.view.models.UIFinancialCategory;
import de.aidger.view.utils.InputPatternFilter;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;

/**
 * A form used for editing / creating new courses.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class CourseEditorForm extends Form {

    /**
     * Constructs a course editor form.
     * 
     * @param course
     *            The course that will be edited
     */
    @SuppressWarnings("unchecked")
    public CourseEditorForm(Course course) {
        initComponents();

        // add input filters
        InputPatternFilter.addFilter(txtGrantedAWH, "[0-9]+[.,]?[0-9]*");
        InputPatternFilter.addFilter(txtPart, ".?");
        InputPatternFilter.addFilter(txtSemester,
            "([0-9]{0,4})|(WS?|WS[0-9]{0,4})|(SS?|SS[0-9]{0,2})");

        List<IFinancialCategory> fcs = null;

        try {
            fcs = (new FinancialCategory()).getAll();
        } catch (AdoHiveException e) {
            Logger.error(e.getMessage());
        }

        for (IFinancialCategory fc : fcs) {
            cmbFinancialCategory.addItem(new UIFinancialCategory(fc));
        }

        if (course != null) {
            txtDescription.setText(course.getDescription());
            txtSemester.setText(course.getSemester());
            txtLecturer.setText(course.getLecturer());
            txtAdvisor.setText(course.getAdvisor());
            txtTargetAudience.setText(course.getTargetAudience());
            spNumberOfGroups.setValue(course.getNumberOfGroups());
            txtGrantedAWH.setText(String.valueOf(course
                .getUnqualifiedWorkingHours()));
            txtScope.setText(course.getScope());
            txtPart.setText(String.valueOf(course.getPart()));
            txtGroup.setText(course.getGroup());
            txtRemark.setText(course.getRemark());

            try {
                IFinancialCategory fc = (new FinancialCategory())
                    .getById(course.getFinancialCategoryId());

                cmbFinancialCategory.setSelectedItem(new FinancialCategory(fc));
            } catch (AdoHiveException e) {
                Logger.error(e.getMessage());
            }
        }
    }

    /**
     * Get the description of the course
     * 
     * @return The description of the course
     */
    public String getDescription() {
        return txtDescription.getText();
    }

    /**
     * Get the id referencing the category.
     * 
     * @return The id of the category
     */
    public int getFinancialCategoryId() {
        return ((FinancialCategory) cmbFinancialCategory.getSelectedItem())
            .getId();
    }

    /**
     * Get the group of the course.
     * 
     * @return The group of the course
     */
    public String getGroup() {
        return txtGroup.getText();
    }

    /**
     * Get the lecturer of the course.
     * 
     * @return The lecturer of the course
     */
    public String getLecturer() {
        return txtLecturer.getText();
    }

    /**
     * Get the advisor of the course.
     * 
     * @return The advisor of the course
     */
    public String getAdvisor() {
        return txtAdvisor.getText();
    }

    /**
     * Get the number of groups in the course.
     * 
     * @return The number of groups
     */
    public int getNumberOfGroups() {
        return (Integer) spNumberOfGroups.getValue();
    }

    /**
     * Get the part of the course.
     * 
     * @return The part of the course
     * @throws StringIndexOutOfBoundsException
     */
    public char getPart() throws StringIndexOutOfBoundsException {
        return txtPart.getText().charAt(0);
    }

    /**
     * Get remarks regarding the course.
     * 
     * @return The remarks
     */
    public String getRemark() {
        return txtRemark.getText();
    }

    /**
     * Get the scope of the course.
     * 
     * @return The scope of the course
     */
    public String getScope() {
        return txtScope.getText();
    }

    /**
     * Get the semester of the course.
     * 
     * @return The semester
     */
    public String getSemester() {
        return txtSemester.getText();
    }

    /**
     * Get the target audience of the course.
     * 
     * @return The target audience
     */
    public String getTargetAudience() {
        return txtTargetAudience.getText();
    }

    /**
     * Get the amount of unqualified working hours granted.
     * 
     * @return The amount of UWHs
     * @throws NumberFormatException
     */
    public double getUnqualifiedWorkingHours() throws NumberFormatException {
        return Double.valueOf(txtGrantedAWH.getText());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblDescription = new javax.swing.JLabel();
        lblSemester = new javax.swing.JLabel();
        lblLecturer = new javax.swing.JLabel();
        lblAdvisor = new javax.swing.JLabel();
        lblNumberOfGroups = new javax.swing.JLabel();
        lblTargetAudience = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        txtSemester = new javax.swing.JTextField();
        txtLecturer = new javax.swing.JTextField();
        txtAdvisor = new javax.swing.JTextField();
        spNumberOfGroups = new javax.swing.JSpinner();
        txtTargetAudience = new javax.swing.JTextField();
        lblGrantedAWH = new javax.swing.JLabel();
        lblScope = new javax.swing.JLabel();
        lblPart = new javax.swing.JLabel();
        lblGroup = new javax.swing.JLabel();
        lblRemark = new javax.swing.JLabel();
        lblFinancialCategory = new javax.swing.JLabel();
        txtGrantedAWH = new javax.swing.JTextField();
        txtScope = new javax.swing.JTextField();
        txtPart = new javax.swing.JTextField();
        txtGroup = new javax.swing.JTextField();
        txtRemark = new javax.swing.JTextField();
        cmbFinancialCategory = new javax.swing.JComboBox();

        setLayout(new java.awt.GridBagLayout());

        lblDescription.setText(_("Description"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblDescription, gridBagConstraints);

        lblSemester.setText(_("Semester"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblSemester, gridBagConstraints);

        lblLecturer.setText(_("Lecturer"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblLecturer, gridBagConstraints);

        lblAdvisor.setText(_("Advisor"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblAdvisor, gridBagConstraints);

        lblNumberOfGroups.setText(_("Number of Groups"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblNumberOfGroups, gridBagConstraints);

        lblTargetAudience.setText(_("Target Audience"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblTargetAudience, gridBagConstraints);

        txtDescription.setMinimumSize(new java.awt.Dimension(200, 25));
        txtDescription.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtDescription, gridBagConstraints);

        txtSemester.setMinimumSize(new java.awt.Dimension(200, 25));
        txtSemester.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtSemester, gridBagConstraints);

        txtLecturer.setMinimumSize(new java.awt.Dimension(200, 25));
        txtLecturer.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtLecturer, gridBagConstraints);

        txtAdvisor.setMinimumSize(new java.awt.Dimension(200, 25));
        txtAdvisor.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtAdvisor, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(spNumberOfGroups, gridBagConstraints);

        txtTargetAudience.setMinimumSize(new java.awt.Dimension(200, 25));
        txtTargetAudience.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtTargetAudience, gridBagConstraints);

        lblGrantedAWH.setText(_("Granted AWH"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 10);
        add(lblGrantedAWH, gridBagConstraints);

        lblScope.setText(_("Scope"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 10);
        add(lblScope, gridBagConstraints);

        lblPart.setText(_("Part"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 10);
        add(lblPart, gridBagConstraints);

        lblGroup.setText(_("Group"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 10);
        add(lblGroup, gridBagConstraints);

        lblRemark.setText(_("Remark"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 10);
        add(lblRemark, gridBagConstraints);

        lblFinancialCategory.setText(_("Financial Category"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 10);
        add(lblFinancialCategory, gridBagConstraints);

        txtGrantedAWH.setMinimumSize(new java.awt.Dimension(200, 25));
        txtGrantedAWH.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtGrantedAWH, gridBagConstraints);

        txtScope.setMinimumSize(new java.awt.Dimension(200, 25));
        txtScope.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtScope, gridBagConstraints);

        txtPart.setMinimumSize(new java.awt.Dimension(200, 25));
        txtPart.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtPart, gridBagConstraints);

        txtGroup.setMinimumSize(new java.awt.Dimension(200, 25));
        txtGroup.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtGroup, gridBagConstraints);

        txtRemark.setMinimumSize(new java.awt.Dimension(200, 25));
        txtRemark.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtRemark, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(cmbFinancialCategory, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbFinancialCategory;
    private javax.swing.JLabel lblAdvisor;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblFinancialCategory;
    private javax.swing.JLabel lblGrantedAWH;
    private javax.swing.JLabel lblGroup;
    private javax.swing.JLabel lblLecturer;
    private javax.swing.JLabel lblNumberOfGroups;
    private javax.swing.JLabel lblPart;
    private javax.swing.JLabel lblRemark;
    private javax.swing.JLabel lblScope;
    private javax.swing.JLabel lblSemester;
    private javax.swing.JLabel lblTargetAudience;
    private javax.swing.JSpinner spNumberOfGroups;
    private javax.swing.JTextField txtAdvisor;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtGrantedAWH;
    private javax.swing.JTextField txtGroup;
    private javax.swing.JTextField txtLecturer;
    private javax.swing.JTextField txtPart;
    private javax.swing.JTextField txtRemark;
    private javax.swing.JTextField txtScope;
    private javax.swing.JTextField txtSemester;
    private javax.swing.JTextField txtTargetAudience;
    // End of variables declaration//GEN-END:variables

}
