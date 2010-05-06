package de.aidger.view;

import static de.aidger.utils.Translation._;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;

import de.aidger.controller.ActionRegistry;
import de.aidger.controller.actions.AbortAction;

public class AboutDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;

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
        this.setSize(480, 145);
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
            jButton1 = new javax.swing.JButton(ActionRegistry.getInstance()
                    .get(AbortAction.class.getName()));
            jLabel3 = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

            jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel1.setText("aidGer 0.1");

            jLabel2
                    .setText(_("Authors: Christian Buchgraber, Philipp Gildein, Philipp Pirrung"));

            jButton1.setText(_("Close"));

            jLabel3.setText(_("Homepage: http://www.aidger.de"));

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
                                                                    jLabel1,
                                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                    459,
                                                                    Short.MAX_VALUE)
                                                            .addComponent(
                                                                    jLabel2)
                                                            .addGroup(
                                                                    javax.swing.GroupLayout.Alignment.TRAILING,
                                                                    layout
                                                                            .createSequentialGroup()
                                                                            .addComponent(
                                                                                    jLabel3)
                                                                            .addPreferredGap(
                                                                                    javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                    208,
                                                                                    Short.MAX_VALUE)
                                                                            .addComponent(
                                                                                    jButton1)))
                                            .addContainerGap()));
            layout
                    .setVerticalGroup(layout
                            .createParallelGroup(
                                    javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                    layout
                                            .createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(
                                                    javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(
                                                    jLabel2,
                                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                                    26,
                                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(
                                                    javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(
                                                    layout
                                                            .createParallelGroup(
                                                                    javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(
                                                                    jButton1)
                                                            .addComponent(
                                                                    jLabel3))
                                            .addContainerGap(
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    Short.MAX_VALUE)));

            pack();
        }
        return jContentPane;
    }
}
