package de.aidger.view.tabs;

import static de.aidger.utils.Translation._;
import de.aidger.controller.ActionNotFoundException;
import de.aidger.controller.ActionRegistry;
import de.aidger.controller.actions.AbortAction;
import de.aidger.view.UI;

/**
 * A tab for viewing Financial Categories.
 * 
 * @author aidGer Team
 */
public class FinancialCategoryViewerTab extends Tab {

    /**
     * Initializes a new FinancialCategoryViewerTab with all the fields for
     * displaying the relevant data.
     */
    public FinancialCategoryViewerTab() {
        initComponents();
        try {
            jButton1.setAction(ActionRegistry.getInstance().get(
                    AbortAction.class.getName()));
        } catch (ActionNotFoundException e) {
            UI.displayError(e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.tabs.Tab#getName()
     */
    @Override
    public String getName() {
        return _("Financial Category Viewing");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel3.setBorder(javax.swing.BorderFactory
                .createTitledBorder(_("Data")));

        jLabel1.setText(_("Name") + ":");

        jLabel2.setText(_("Budgetcosts") + (":"));

        jLabel3.setText(_("Fund") + ":");

        jLabel4.setText(_("Year") + (":"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
                jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                jPanel1Layout.createSequentialGroup().addGroup(
                        jPanel1Layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1).addComponent(jLabel2)
                                .addComponent(jLabel3).addComponent(jLabel4))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)));
        jPanel1Layout
                .setVerticalGroup(jPanel1Layout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                jPanel1Layout
                                        .createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel4)
                                        .addContainerGap()));

        jLabel1.getAccessibleContext().setAccessibleName(_("Name") + ":");

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        jLabel7.setText("jLabel7");

        jLabel8.setText("jLabel8");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
                jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel5).addComponent(jLabel6).addComponent(
                        jLabel7).addComponent(jLabel8));
        jPanel2Layout
                .setVerticalGroup(jPanel2Layout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                jPanel2Layout
                                        .createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
                jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout
                .setHorizontalGroup(jPanel3Layout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                jPanel3Layout
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(
                                                jPanel1,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(
                                                jPanel2,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)));
        jPanel3Layout
                .setVerticalGroup(jPanel3Layout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                jPanel3Layout
                                        .createSequentialGroup()
                                        .addGroup(
                                                jPanel3Layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(
                                                                jPanel1,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(
                                                                jPanel2,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap()));

        jButton1.setText("Abbrechen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Bearbeiten");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                        layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel3,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(
                                        layout.createSequentialGroup()
                                                .addComponent(jButton2).addGap(
                                                        10, 10, 10)
                                                .addComponent(jButton1)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)));
        layout
                .setVerticalGroup(layout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(
                                                jPanel3,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                131,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButton1)
                                                        .addComponent(jButton2))
                                        .addContainerGap(
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

}
