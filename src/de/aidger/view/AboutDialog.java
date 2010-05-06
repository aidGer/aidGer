package de.aidger.view;

import static de.aidger.utils.Translation._;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class AboutDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;

    /**
     * @param owner
     */
    public AboutDialog(Frame owner) {
        super(owner);
        initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(460, 110);
        setTitle(_("About aidGer"));
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
            jLabel1 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            // TODO: Add link to homepage

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

            jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel1.setText("aidGer 0.1");

            jLabel2
                    .setText(_("Authors: Christian Buchgraber, Philipp Gildein, Philipp Pirrung"));

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
                                                            .addComponent(
                                                                    jLabel2)
                                                            .addComponent(
                                                                    jLabel1,
                                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                    438,
                                                                    Short.MAX_VALUE))
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
                                            .addComponent(jLabel1)
                                            .addPreferredGap(
                                                    javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                    23, Short.MAX_VALUE)
                                            .addComponent(jLabel2)
                                            .addContainerGap()));

            pack();
        }
        return jContentPane;
    }

}
