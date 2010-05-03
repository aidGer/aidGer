package de.aidger.view;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class SettingsDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;

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
            jContentPane = new JPanel();
            jButton1 = new javax.swing.JButton();
            jButton2 = new javax.swing.JButton();
            jCheckBox1 = new javax.swing.JCheckBox();
            jCheckBox2 = new javax.swing.JCheckBox();
            jLabel1 = new javax.swing.JLabel();
            jTextField1 = new javax.swing.JTextField();
            jLabel2 = new javax.swing.JLabel();
            jTextField2 = new javax.swing.JTextField();
            jLabel3 = new javax.swing.JLabel();
            jSpinner1 = new javax.swing.JSpinner();
            jLabel4 = new javax.swing.JLabel();
            jComboBox1 = new javax.swing.JComboBox();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("Einstellungen");

            jButton1.setText("Abbrechen");

            jButton2.setText("Speichern");

            jCheckBox1.setText("Reiter bei Verlassen speichern");

            jCheckBox2.setText("Berichte automatisch nach Generierung öffnen");

            jLabel1.setText("Name:");
            jLabel1
                    .setToolTipText("Unter welchem Name sollen Vorgänge erfolgen?");

            jTextField1
                    .setToolTipText("Unter welchem Name sollen Vorgänge erfolgen?");

            jLabel2.setText("PDF Anzeiger:");

            jLabel3.setText("Anzahl der Vorgänge:");
            jLabel3
                    .setToolTipText("Anzahl der Vorgänge die in Detailansichten angezeigt werden");

            jSpinner1.setModel(new javax.swing.SpinnerNumberModel(Integer
                    .valueOf(10), null, null, Integer.valueOf(1)));

            jLabel4.setText("Sprache:");

            jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(
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
                                                                                    jCheckBox2)
                                                                            .addComponent(
                                                                                    jCheckBox1))
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
                                                                                                    jComboBox1,
                                                                                                    0,
                                                                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                    Short.MAX_VALUE)
                                                                                            .addComponent(
                                                                                                    jTextField2,
                                                                                                    javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(
                                                                                                    jTextField1,
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
                                                                                    jSpinner1,
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
                                                                    jTextField1,
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
                                                                    jTextField2,
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
                                                                    jComboBox1,
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
                                                                    jSpinner1,
                                                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                    javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(
                                                    javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    Short.MAX_VALUE)
                                            .addComponent(jCheckBox2)
                                            .addPreferredGap(
                                                    javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jCheckBox1)
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
