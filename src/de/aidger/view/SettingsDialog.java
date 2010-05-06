package de.aidger.view;

import static de.aidger.utils.Translation._;

import java.awt.Frame;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;

import de.aidger.controller.ActionRegistry;
import de.aidger.controller.actions.AbortAction;
import de.aidger.model.Runtime;
import de.unistuttgart.iste.se.adohive.util.tuple.Pair;

public class SettingsDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox saveCheckBox;
    private javax.swing.JCheckBox openCheckBox;
    private javax.swing.JComboBox langComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSpinner refactorSpinner;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField pdfTextField;

    /**
     * @param owner
     */
    public SettingsDialog(Frame owner) {
        super(owner);
        initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(350, 300);
        setContentPane(getJContentPane());
    }

    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {

            String name = Runtime.getInstance().getOption("name");
            String pdf = Runtime.getInstance().getOption("pdf-viewer");
            String lang = Runtime.getInstance().getOption("language");
            String activities = Runtime.getInstance().getOption("activities");
            String open = Runtime.getInstance().getOption("auto-open");
            String save = Runtime.getInstance().getOption("auto-save");

            jContentPane = new JPanel();
            jButton1 = new javax.swing.JButton(ActionRegistry.getInstance()
                    .get(AbortAction.class.getName()));
            jButton2 = new javax.swing.JButton();
            saveCheckBox = new javax.swing.JCheckBox();
            openCheckBox = new javax.swing.JCheckBox();
            jLabel1 = new javax.swing.JLabel();
            nameTextField = new javax.swing.JTextField();
            jLabel2 = new javax.swing.JLabel();
            pdfTextField = new javax.swing.JTextField();
            jLabel3 = new javax.swing.JLabel();
            refactorSpinner = new javax.swing.JSpinner();
            jLabel4 = new javax.swing.JLabel();
            langComboBox = new javax.swing.JComboBox();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle(_("Settings"));

            jButton2.setText(_("Save"));

            saveCheckBox.setText(_("Save Tabs when quitting"));
            saveCheckBox.setSelected(Boolean.parseBoolean(save));

            openCheckBox
                    .setText(_("Open reports automatically after generating"));
            openCheckBox.setSelected(Boolean.parseBoolean(open));

            jLabel1.setText(_("Name:"));
            jLabel1.setToolTipText(_("The name used in activities."));

            nameTextField.setToolTipText(_("The name used in activities."));
            nameTextField.setText(name);

            jLabel2.setText(_("PDF Viewer:"));
            pdfTextField.setText(pdf);

            jLabel3.setText(_("Amount of activities:"));
            jLabel3
                    .setToolTipText(_("The amount of activities shown in detailviews."));

            refactorSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer
                    .valueOf(activities), null, null, Integer.valueOf(1)));

            jLabel4.setText(_("Language:"));

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

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
                    jContentPane);
            jContentPane.setLayout(layout);

            layout
                    .setHorizontalGroup(layout
                            .createParallelGroup(
                                    javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                    layout
                                            .createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(
                                                    layout
                                                            .createParallelGroup(
                                                                    javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(
                                                                    javax.swing.GroupLayout.Alignment.TRAILING,
                                                                    layout
                                                                            .createSequentialGroup()
                                                                            .addComponent(
                                                                                    jButton2)
                                                                            .addPreferredGap(
                                                                                    javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(
                                                                                    jButton1))
                                                            .addGroup(
                                                                    javax.swing.GroupLayout.Alignment.TRAILING,
                                                                    layout
                                                                            .createParallelGroup(
                                                                                    javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(
                                                                                    openCheckBox)
                                                                            .addComponent(
                                                                                    saveCheckBox))
                                                            .addGroup(
                                                                    javax.swing.GroupLayout.Alignment.TRAILING,
                                                                    layout
                                                                            .createSequentialGroup()
                                                                            .addGroup(
                                                                                    layout
                                                                                            .createParallelGroup(
                                                                                                    javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(
                                                                                                    jLabel1)
                                                                                            .addComponent(
                                                                                                    jLabel2)
                                                                                            .addComponent(
                                                                                                    jLabel4))
                                                                            .addPreferredGap(
                                                                                    javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                    55,
                                                                                    Short.MAX_VALUE)
                                                                            .addGroup(
                                                                                    layout
                                                                                            .createParallelGroup(
                                                                                                    javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                    false)
                                                                                            .addComponent(
                                                                                                    langComboBox,
                                                                                                    0,
                                                                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                    Short.MAX_VALUE)
                                                                                            .addComponent(
                                                                                                    pdfTextField,
                                                                                                    javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(
                                                                                                    nameTextField,
                                                                                                    javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                    179,
                                                                                                    Short.MAX_VALUE)))
                                                            .addGroup(
                                                                    layout
                                                                            .createSequentialGroup()
                                                                            .addComponent(
                                                                                    jLabel3)
                                                                            .addPreferredGap(
                                                                                    javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                    125,
                                                                                    Short.MAX_VALUE)
                                                                            .addComponent(
                                                                                    refactorSpinner,
                                                                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                    60,
                                                                                    javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addContainerGap()));
            layout
                    .setVerticalGroup(layout
                            .createParallelGroup(
                                    javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                    javax.swing.GroupLayout.Alignment.TRAILING,
                                    layout
                                            .createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(
                                                    layout
                                                            .createParallelGroup(
                                                                    javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(
                                                                    jLabel1)
                                                            .addComponent(
                                                                    nameTextField,
                                                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                    javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(
                                                    javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(
                                                    layout
                                                            .createParallelGroup(
                                                                    javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(
                                                                    jLabel2)
                                                            .addComponent(
                                                                    pdfTextField,
                                                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                    javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(
                                                    javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(
                                                    layout
                                                            .createParallelGroup(
                                                                    javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(
                                                                    jLabel4)
                                                            .addComponent(
                                                                    langComboBox,
                                                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                    javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(
                                                    javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(
                                                    layout
                                                            .createParallelGroup(
                                                                    javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(
                                                                    jLabel3)
                                                            .addComponent(
                                                                    refactorSpinner,
                                                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                    javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(
                                                    javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    Short.MAX_VALUE)
                                            .addComponent(openCheckBox)
                                            .addPreferredGap(
                                                    javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(saveCheckBox)
                                            .addGap(18, 18, 18)
                                            .addGroup(
                                                    layout
                                                            .createParallelGroup(
                                                                    javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(
                                                                    jButton1)
                                                            .addComponent(
                                                                    jButton2))
                                            .addContainerGap()));

            pack();
        }
        return jContentPane;
    }
}
