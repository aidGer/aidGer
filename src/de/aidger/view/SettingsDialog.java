package de.aidger.view;

import static de.aidger.utils.Translation._;

import java.awt.Frame;
import java.util.List;

import javax.swing.JDialog;

import de.aidger.controller.ActionNotFoundException;
import de.aidger.controller.ActionRegistry;
import de.aidger.controller.actions.DialogAbortAction;
import de.aidger.controller.actions.SaveSettingsAction;
import de.aidger.model.Runtime;
import de.unistuttgart.iste.se.adohive.util.tuple.Pair;

public class SettingsDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner activitiesSpinner;
    private javax.swing.JTextField historicTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox langComboBox;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JCheckBox openCheckBox;
    private javax.swing.JTextField pdfTextField;
    private javax.swing.JTextField pessimisticTextField;
    private javax.swing.JCheckBox saveCheckBox;
    // End of variables declaration//GEN-END:variables

    /**
     * @param owner
     */
    public SettingsDialog(Frame owner) {
        super(owner, true);
        initComponents();

        String lang = Runtime.getInstance().getOption("language");

        try {
            jButton2.setAction(ActionRegistry.getInstance().get(
                DialogAbortAction.class.getName()));
            jButton1.setAction(ActionRegistry.getInstance().get(
                SaveSettingsAction.class.getName()));
        } catch (ActionNotFoundException e) {
            UI.displayError(e.getMessage());
        }

        saveCheckBox.setSelected(Boolean.parseBoolean(
                Runtime.getInstance().getOption("auto-save")));
        openCheckBox.setSelected(Boolean.parseBoolean(
                Runtime.getInstance().getOption("auto-open")));
        nameTextField.setText(Runtime.getInstance().getOption("name"));
        pdfTextField.setText(Runtime.getInstance().getOption("pdf-viewer"));
        pessimisticTextField.setText(Runtime.getInstance().getOption(
                "pessimistic-factor"));
        historicTextField.setText(Runtime.getInstance().getOption(
                "historic-factor"));
        activitiesSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer
            .valueOf(Runtime.getInstance().getOption("activities")), null,
            null, Integer.valueOf(1)));

        /* Add all languages to the combobox and select the correct one */
        List<Pair<String, String>> langs = Runtime.getInstance().getLanguages();
        String[] longLangs = new String[langs.size()];
        int selected = -1;

        for (int i = 0; i < langs.size(); ++i) {
            longLangs[i] = langs.get(i).snd();
            if (langs.get(i).fst().equals(lang)) {
                selected = i;
            }
        }

        langComboBox.setModel(new javax.swing.DefaultComboBoxModel(longLangs));
        langComboBox.setSelectedIndex(selected);
    }

    /**
     * Get the currently entered username.
     * 
     * @return The username
     */
    public String getUsername() {
        return nameTextField.getText();
    }

    /**
     * Get the currently entered pdf viewer.
     * 
     * @return The pdf viewer
     */
    public String getPDFViewer() {
        return pdfTextField.getText();
    }

    /**
     * Get the pessimistic factor currently entered.
     *
     * @return The pessimistic factor
     */
    public String getPessimisticFactor() {
        return pessimisticTextField.getText();
    }

    /**
     * Get the historical factor currently entered.
     *
     * @return The historical factor
     */
    public String getHistoricFactor() {
        return historicTextField.getText();
    }

    /**
     * Get the number of activities currently entered
     * 
     * @return The number of activities
     */
    public int getNumOfActivities() {
        return (Integer) activitiesSpinner.getValue();
    }

    /**
     * Get whether the save checkbox is selected
     * 
     * @return True if the checkbox is selected
     */
    public boolean isSaveSelected() {
        return saveCheckBox.isSelected();
    }

    /**
     * Get whether the open checkbox is selected
     * 
     * @return True if the checkbox is selected
     */
    public boolean isOpenSelected() {
        return openCheckBox.isSelected();
    }

    /**
     * Get the index of the selected language.
     * 
     * @return The index of the selected language
     */
    public int getSelectedLanguage() {
        return langComboBox.getSelectedIndex();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        langComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        activitiesSpinner = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pdfTextField = new javax.swing.JTextField();
        openCheckBox = new javax.swing.JCheckBox();
        saveCheckBox = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pessimisticTextField = new javax.swing.JTextField();
        historicTextField = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(_("Settings"));

        jTabbedPane1.setName("null"); // NOI18N

        jLabel4.setText(_("Language:"));

        jLabel3.setText(_("Amount of activities:"));
        jLabel3.setToolTipText(_("The amount of activities shown in detailviews."));

        jLabel1.setText(_("Name:"));
        jLabel1.setToolTipText(_("The name used in activities."));

        nameTextField.setToolTipText(_("The name used in activities."));

        jLabel2.setText(_("PDF Viewer:"));

        openCheckBox.setText(_("Open reports automatically after generating"));

        saveCheckBox.setText(_("Save Tabs when quitting"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(langComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pdfTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                        .addComponent(activitiesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(openCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saveCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addGap(278, 278, 278))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pdfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(langComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activitiesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(openCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("General", jPanel1);

        jLabel5.setText(_("Pessimistic Factor:"));

        jLabel6.setText(_("Historical Factor:"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(historicTextField)
                    .addComponent(pessimisticTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(pessimisticTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(historicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(142, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reports", jPanel2);

        jButton2.setText("Speichern");

        jButton1.setText("Abbrechen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(256, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(15, 15, 15))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("null");

        pack();
    }// </editor-fold>//GEN-END:initComponents

}
