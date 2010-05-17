package de.aidger.view;

import static de.aidger.utils.Translation._;

import java.awt.Frame;
import java.util.List;

import javax.swing.JDialog;

import de.aidger.controller.ActionNotFoundException;
import de.aidger.controller.ActionRegistry;
import de.aidger.controller.actions.AbortAction;
import de.aidger.controller.actions.SaveSettingsAction;
import de.aidger.model.Runtime;
import de.unistuttgart.iste.se.adohive.util.tuple.Pair;

public class SettingsDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner activitiesSpinner;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox langComboBox;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JCheckBox openCheckBox;
    private javax.swing.JTextField pdfTextField;
    private javax.swing.JCheckBox saveCheckBox;
    // End of variables declaration//GEN-END:variables

    /**
     * @param owner
     */
    public SettingsDialog(Frame owner) {
        super(owner, true);
        initComponents();

        String name = Runtime.getInstance().getOption("name");
        String pdf = Runtime.getInstance().getOption("pdf-viewer");
        String lang = Runtime.getInstance().getOption("language");
        String activities = Runtime.getInstance().getOption("activities");
        String open = Runtime.getInstance().getOption("auto-open");
        String save = Runtime.getInstance().getOption("auto-save");

        try {
            jButton1.setAction(ActionRegistry.getInstance()
                    .get(AbortAction.class.getName()));
            jButton2.setAction(ActionRegistry.getInstance()
                    .get(SaveSettingsAction.class.getName()));
        } catch (ActionNotFoundException e) {
            UI.displayError(e.getMessage());
        }

        saveCheckBox.setSelected(Boolean.parseBoolean(save));
        openCheckBox.setSelected(Boolean.parseBoolean(open));
        nameTextField.setText(name);
        pdfTextField.setText(pdf);
        activitiesSpinner
                .setModel(new javax.swing.SpinnerNumberModel(Integer
                        .valueOf(activities), null, null, Integer
                        .valueOf(1)));

        /* Add all languages to the combobox and select the correct one */
        List<Pair<String, String>> langs = Runtime.getInstance()
                .getLanguages();
        String[] longLangs = new String[langs.size()];
        int selected = -1;

        for (int i = 0; i < langs.size(); ++i) {
            longLangs[i] = langs.get(i).snd();
            if (langs.get(i).fst().equals(lang)) {
                selected = i;
            }
        }

        langComboBox.setModel(new javax.swing.DefaultComboBoxModel(
                longLangs));
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        saveCheckBox = new javax.swing.JCheckBox();
        openCheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pdfTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        activitiesSpinner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        langComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(_("Settings"));

        jButton1.setText("Abbrechen");

        jButton2.setText("Speichern");

        saveCheckBox.setText(_("Save Tabs when quitting"));

        openCheckBox.setText(_("Open reports automatically after generating"));

        jLabel1.setText(_("Name:"));
        jLabel1.setToolTipText(_("The name used in activities."));

        nameTextField.setToolTipText(_("The name used in activities."));

        jLabel2.setText(_("PDF Viewer:"));

        jLabel3.setText(_("Amount of activities:"));
        jLabel3.setToolTipText(_("The amount of activities shown in detailviews."));

        jLabel4.setText(_("Language:"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(langComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pdfTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                        .addComponent(activitiesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(openCheckBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                    .addComponent(saveCheckBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pdfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(langComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(activitiesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(openCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveCheckBox)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

}
