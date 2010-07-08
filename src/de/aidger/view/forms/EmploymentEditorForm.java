package de.aidger.view.forms;

import static de.aidger.utils.Translation._;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.FinancialCategory;
import de.aidger.view.UI;
import de.aidger.view.forms.HourlyWageEditorForm.Qualification;
import de.aidger.view.models.ComboBoxModel;
import de.aidger.view.models.GenericListModel;
import de.aidger.view.models.UIAssistant;
import de.aidger.view.models.UIContract;
import de.aidger.view.models.UICourse;
import de.aidger.view.tabs.EditorTab;
import de.aidger.view.tabs.Tab;
import de.aidger.view.tabs.ViewerTab.DataType;
import de.aidger.view.utils.AutoCompletion;
import de.aidger.view.utils.HelpLabel;
import de.aidger.view.utils.InputPatternFilter;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.IContract;
import de.unistuttgart.iste.se.adohive.model.ICourse;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;

/**
 * A form used for editing / creating new employments.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class EmploymentEditorForm extends JPanel {

    /**
     * A flag whether the form is in edit mode.
     */
    private boolean editMode = false;

    /**
     * Constructs an employment editor form.
     * 
     * @param employment
     *            The employment that will be edited
     * @param listModels
     *            the list models of the parent editor tab
     */
    @SuppressWarnings("unchecked")
    public EmploymentEditorForm(Employment employment,
            List<GenericListModel> listModels) {
        if (employment != null) {
            editMode = true;
        }

        initComponents();

        btnContractAdd.setIcon(new ImageIcon(getClass().getResource(
            "/de/aidger/view/icons/plus-small.png")));
        btnContractAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Tab current = UI.getInstance().getCurrentTab();

                EditorTab next = new EditorTab(DataType.Contract);

                if (cmbAssistant.getSelectedItem() != null) {
                    ((ContractEditorForm) next.getEditorForm())
                        .setAssistant(((Assistant) cmbAssistant
                            .getSelectedItem()));
                }

                current.markAsPredecessor();
                next.markAsNoPredecessor();

                UI.getInstance().replaceCurrentTab(next);

                current.markAsNoPredecessor();
            }
        });

        AutoCompletion.enable(cmbAssistant);
        AutoCompletion.enable(cmbCourse);
        AutoCompletion.enable(cmbContract);
        AutoCompletion.enable(cmbFunds);

        InputPatternFilter.addFilter(txtCostUnit, ".{0,10}");

        hlpCostUnit
            .setToolTipText(_("Only a maximum length of 10 is allowed."));

        cmbAssistant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshQualification((Assistant) cmbAssistant.getSelectedItem());
            }
        });

        cmbCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshFunds((Course) cmbCourse.getSelectedItem());
            }
        });

        try {
            List<IAssistant> assistants = (new Assistant()).getAll();

            ComboBoxModel cmbAssistantModel = new ComboBoxModel(
                DataType.Assistant);

            for (IAssistant a : assistants) {
                Assistant assistant = new UIAssistant(a);

                cmbAssistantModel.addElement(assistant);

                if (employment != null
                        && assistant.getId() == employment.getAssistantId()) {
                    cmbAssistantModel.setSelectedItem(assistant);
                }
            }

            List<ICourse> courses = (new Course()).getAll();

            ComboBoxModel cmbCourseModel = new ComboBoxModel(DataType.Course);

            for (ICourse c : courses) {
                Course course = new UICourse(c);

                cmbCourseModel.addElement(course);

                if (employment != null
                        && course.getId() == employment.getCourseId()) {
                    cmbCourseModel.setSelectedItem(course);
                }
            }

            List<IContract> contracts = (new Contract()).getAll();

            ComboBoxModel cmbContractModel = new ComboBoxModel(
                DataType.Contract);

            for (IContract c : contracts) {
                Contract contract = new UIContract(c);

                cmbContractModel.addElement(contract);

                if (employment != null
                        && contract.getId() == employment.getContractId()) {
                    cmbContractModel.setSelectedItem(contract);
                }
            }

            ComboBoxModel cmbFundsModel = new ComboBoxModel(
                DataType.FinancialCategory);

            cmbAssistant.setModel(cmbAssistantModel);
            cmbCourse.setModel(cmbCourseModel);
            cmbContract.setModel(cmbContractModel);
            cmbFunds.setModel(cmbFundsModel);

            listModels.add(cmbAssistantModel);
            listModels.add(cmbCourseModel);
            listModels.add(cmbContractModel);

            if (cmbAssistant.getSelectedItem() != null) {
                refreshQualification((Assistant) cmbAssistant.getSelectedItem());
            }

            if (cmbCourse.getSelectedItem() != null) {
                refreshFunds((Course) cmbCourse.getSelectedItem());
            }

            addNewDate();

            if (editMode) {
                cmbFunds.setSelectedItem(String.valueOf(employment.getFunds()));
                txtCostUnit.setText(employment.getCostUnit());
                cmbQualification.setSelectedItem(Qualification
                    .valueOf(employment.getQualification()));
                txtRemark.setText(employment.getRemark());

                DateLine dl = dateLines.get(0);

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.MONTH, employment.getMonth() - 1);
                cal.set(Calendar.YEAR, employment.getYear());
                dl.spDate.setValue(cal.getTime());

                dl.txtHourCount.setText(String.valueOf(employment
                    .getHourCount()));
            }

        } catch (AdoHiveException e) {
        }
    }

    /**
     * Refreshs the qualification model.
     * 
     * @param assistant
     *            the new assistant
     */
    private void refreshQualification(Assistant assistant) {
        if (assistant == null) {
            return;
        }

        cmbQualification.setSelectedItem(Qualification.valueOf(assistant
            .getQualification()));
    }

    /**
     * Refreshs the funds model.
     * 
     * @param course
     *            the new course
     */
    private void refreshFunds(Course course) {
        if (course == null) {
            return;
        }

        try {
            IFinancialCategory fc = (new FinancialCategory()).getById(course
                .getFinancialCategoryId());

            ComboBoxModel cmbFundsModel = (ComboBoxModel) cmbFunds.getModel();

            cmbFundsModel.removeAllElements();

            for (int funds : fc.getFunds()) {
                cmbFundsModel.addElement(String.valueOf(funds));
            }
        } catch (AdoHiveException e) {
        }
    }

    /**
     * Get the id referencing the assistant.
     * 
     * @return The id of the assistant
     */
    public Assistant getAssistant() {
        return (Assistant) cmbAssistant.getSelectedItem();
    }

    /**
     * Get the id referencing the contract.
     * 
     * @return The id of the contract
     */
    public int getContractId() {
        if (cmbContract.getSelectedItem() != null) {
            return ((Contract) cmbContract.getSelectedItem()).getId();
        }

        return 0;
    }

    /**
     * Get the cost unit of the contract.
     * 
     * @return The cost unit of the contract
     */
    public String getCostUnit() {
        return txtCostUnit.getText();
    }

    /**
     * Get the id referencing the course.
     * 
     * @return The id of the course
     */
    public Course getCourse() {
        return (Course) cmbCourse.getSelectedItem();
    }

    /**
     * Get the funds of the employment.
     * 
     * @return The funds of the employment
     * @throws NumberFormatException
     */
    public int getFunds() throws NumberFormatException {
        return Integer.valueOf((String) cmbFunds.getSelectedItem());
    }

    /**
     * Get the qualification of the employment.
     * 
     * @return The qualification of the employment
     */
    public String getQualification() {
        return ((Qualification) cmbQualification.getSelectedItem()).name();
    }

    /**
     * Get the remarks regarding the employment.
     * 
     * @return The remarks regarding the employment
     */
    public String getRemark() {
        return txtRemark.getText();
    }

    /**
     * Get the dates of the employment.
     * 
     * @return The dates of the employment
     */
    public List<Date> getDates() {
        List<Date> dates = new Vector<Date>();

        for (DateLine dl : dateLines) {
            dates.add((Date) dl.spDate.getValue());
        }

        return dates;
    }

    /**
     * Get the hour counts worked during the employment.
     * 
     * @return The hour counts worked during the employment
     * @throws NumberFormatException
     */
    public List<Double> getHourCounts() throws ParseException {
        List<Double> hcs = new Vector<Double>();

        for (DateLine dl : dateLines) {
            hcs.add(NumberFormat.getInstance().parse(dl.txtHourCount.getText())
                .doubleValue());
        }

        return hcs;
    }

    /**
     * Adds a new date line to the form.
     */
    private void addNewDate() {
        GridBagConstraints gridBagConstraints;

        JLabel lblDate = new JLabel();
        lblDate.setText(_("Date"));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblDate, gridBagConstraints);

        JSpinner spDate = new JSpinner();
        spDate.setModel(new SpinnerDateModel());
        spDate.setEditor(new JSpinner.DateEditor(spDate, "MM.yyyy"));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(spDate, gridBagConstraints);

        JLabel lblHourCount = new JLabel();
        lblHourCount.setText(_("Hour count"));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 10, 10);
        add(lblHourCount, gridBagConstraints);

        JTextField txtHourCount = new JTextField();
        txtHourCount.setMinimumSize(new java.awt.Dimension(200, 25));
        txtHourCount.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtHourCount, gridBagConstraints);

        InputPatternFilter.addDoubleFilter(txtHourCount);

        HelpLabel hlpHourCount = new HelpLabel();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        add(hlpHourCount, gridBagConstraints);

        hlpHourCount.setToolTipText(_("Only a decimal number is allowed."));

        JButton btnPlusMinus = new JButton();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;

        DateLine dl = new DateLine(lblDate, spDate, lblHourCount, txtHourCount,
            hlpHourCount, btnPlusMinus);

        if (dateLines.isEmpty()) {
            EditorTab.setTimeToNow(spDate);

            btnPlusMinus.setIcon(new ImageIcon(getClass().getResource(
                "/de/aidger/view/icons/plus-small.png")));

            gridBagConstraints.gridy = 4;

            btnPlusMinus.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    addNewDate();
                }
            });

            if (editMode) {
                btnPlusMinus.setVisible(false);
            }
        } else {
            gridBagConstraints.gridy = GridBagConstraints.RELATIVE;

            btnPlusMinus.setAction(new RemoveDateAction(dl));

            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.setTime((Date) dateLines.get(dateLines.size() - 1).spDate
                .getValue());
            cal.add(Calendar.MONTH, 1);

            spDate.setValue(cal.getTime());

            txtHourCount
                .setText(dateLines.get(dateLines.size() - 1).txtHourCount
                    .getText());
        }

        add(btnPlusMinus, gridBagConstraints);

        dateLines.add(dl);
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
        lblCourse = new javax.swing.JLabel();
        lblContract = new javax.swing.JLabel();
        lblFunds = new javax.swing.JLabel();
        lblCostUnit = new javax.swing.JLabel();
        lblQualification = new javax.swing.JLabel();
        lblRemark = new javax.swing.JLabel();
        cmbAssistant = new javax.swing.JComboBox();
        cmbCourse = new javax.swing.JComboBox();
        cmbContract = new javax.swing.JComboBox();
        btnContractAdd = new javax.swing.JButton();
        cmbFunds = new javax.swing.JComboBox();
        txtCostUnit = new javax.swing.JTextField();
        cmbQualification = new javax.swing.JComboBox();
        txtRemark = new javax.swing.JTextField();
        filler = new javax.swing.JLabel();
        hlpCostUnit = new de.aidger.view.utils.HelpLabel();

        setLayout(new java.awt.GridBagLayout());

        lblAssistant.setText(_("Assistant"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblAssistant, gridBagConstraints);

        lblCourse.setText(_("Course"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblCourse, gridBagConstraints);

        lblContract.setText(_("Contract"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 10, 10);
        add(lblContract, gridBagConstraints);

        lblFunds.setText(_("Funds"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 10, 10);
        add(lblFunds, gridBagConstraints);

        lblCostUnit.setText(_("Cost unit"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblCostUnit, gridBagConstraints);

        lblQualification.setText(_("Qualification"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 10, 10);
        add(lblQualification, gridBagConstraints);

        lblRemark.setText(_("Remark"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblRemark, gridBagConstraints);

        cmbAssistant.setMinimumSize(new java.awt.Dimension(200, 25));
        cmbAssistant.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(cmbAssistant, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(cmbCourse, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(cmbContract, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        add(btnContractAdd, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(cmbFunds, gridBagConstraints);

        txtCostUnit.setMinimumSize(new java.awt.Dimension(200, 25));
        txtCostUnit.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtCostUnit, gridBagConstraints);

        cmbQualification.setModel(new javax.swing.DefaultComboBoxModel(
            Qualification.values()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(cmbQualification, gridBagConstraints);

        txtRemark.setMinimumSize(new java.awt.Dimension(200, 25));
        txtRemark.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtRemark, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(filler, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        add(hlpCostUnit, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContractAdd;
    private javax.swing.JComboBox cmbAssistant;
    private javax.swing.JComboBox cmbContract;
    private javax.swing.JComboBox cmbCourse;
    private javax.swing.JComboBox cmbFunds;
    private javax.swing.JComboBox cmbQualification;
    private javax.swing.JLabel filler;
    private de.aidger.view.utils.HelpLabel hlpCostUnit;
    private javax.swing.JLabel lblAssistant;
    private javax.swing.JLabel lblContract;
    private javax.swing.JLabel lblCostUnit;
    private javax.swing.JLabel lblCourse;
    private javax.swing.JLabel lblFunds;
    private javax.swing.JLabel lblQualification;
    private javax.swing.JLabel lblRemark;
    private javax.swing.JTextField txtCostUnit;
    private javax.swing.JTextField txtRemark;
    // End of variables declaration//GEN-END:variables

    private final List<DateLine> dateLines = new Vector<DateLine>();

    /**
     * This class represents a date line in the form.
     * 
     * @author aidGer Team
     */
    private class DateLine {
        public JLabel lblDate;
        public JSpinner spDate;
        public JLabel lblHourCount;
        public JTextField txtHourCount;
        public HelpLabel hlpHourCount;
        public JButton btnPlusMinus;

        /**
         * Initializes a date line.
         * 
         */
        public DateLine(JLabel lblDate, JSpinner spDate, JLabel lblHourCount,
                JTextField txtHourCount, HelpLabel hlpHourCount,
                JButton btnPlusMinus) {
            this.lblDate = lblDate;
            this.spDate = spDate;
            this.lblHourCount = lblHourCount;
            this.txtHourCount = txtHourCount;
            this.hlpHourCount = hlpHourCount;
            this.btnPlusMinus = btnPlusMinus;
        }
    }

    /**
     * Removes a date line from the form by clicking on "-" button.
     * 
     * @author aidGer Team
     */
    private class RemoveDateAction extends AbstractAction {
        /**
         * The date line that will be removed.
         */
        private final DateLine dateLine;

        /**
         * Initializes the action.
         */
        public RemoveDateAction(DateLine dateLine) {
            putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
                "/de/aidger/view/icons/minus-small.png")));

            this.dateLine = dateLine;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
         * )
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            remove(dateLine.lblDate);
            remove(dateLine.spDate);
            remove(dateLine.lblHourCount);
            remove(dateLine.txtHourCount);
            remove(dateLine.hlpHourCount);
            remove(dateLine.btnPlusMinus);

            dateLines.remove(dateLine);

            revalidate();
        }
    }

}
