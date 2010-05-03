package de.aidger.view;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;

import de.aidger.model.Runtime;

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
            jButton1 = new javax.swing.JButton();
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
            setTitle("Einstellungen");

            jButton1.setText("Abbrechen");

            jButton2.setText("Speichern");

            saveCheckBox.setText("Reiter bei Verlassen speichern");
            saveCheckBox.setSelected(Boolean.parseBoolean(save));

            openCheckBox.setText("Berichte automatisch nach Generierung öffnen");
            openCheckBox.setSelected(Boolean.parseBoolean(open));

            jLabel1.setText("Name:");
            jLabel1
                    .setToolTipText("Unter welchem Name sollen Vorgänge erfolgen?");

            nameTextField
                    .setToolTipText("Unter welchem Name sollen Vorgänge erfolgen?");
            nameTextField.setText(name);

            jLabel2.setText("PDF Anzeiger:");
            pdfTextField.setText(pdf);

            jLabel3.setText("Anzahl der Vorgänge:");
            jLabel3
                    .setToolTipText("Anzahl der Vorgänge die in Detailansichten angezeigt werden");

            refactorSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer
                    .valueOf(activities), null, null, Integer.valueOf(1)));

            jLabel4.setText("Sprache:");

            // TODO: Choose the correct language at start
            langComboBox.setModel(new javax.swing.DefaultComboBoxModel(
                    new String[] { "Deutsch", "English", "Francais" }));

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
