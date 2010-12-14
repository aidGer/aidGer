/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DatabaseCheck.java
 *
 * Created on Nov 30, 2010, 2:29:12 PM
 */

package de.aidger.view.wizard;

import de.aidger.controller.ActionNotFoundException;
import de.aidger.controller.ActionRegistry;
import de.aidger.controller.actions.DatabaseCheckFinishAction;
import de.aidger.model.Runtime;
import de.aidger.model.models.Activity;
import static de.aidger.utils.Translation._;
import de.aidger.view.UI;
import de.aidger.view.WizardPanel;
import de.aidger.view.utils.MultiLineLabelUI;
import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import java.awt.Color;
import java.util.List;
import javax.swing.AbstractAction;

/**
 * Check the previously entered database connection
 *
 * @author rmbl
 */
public class DatabaseCheck extends WizardPanel {

    private boolean hasConnected = false;

    /** Creates new form DatabaseCheck */
    public DatabaseCheck() {
        initComponents();
        try {
            setNextAction((AbstractAction) ActionRegistry.getInstance().get(DatabaseCheckFinishAction.class.getName()));
        } catch (ActionNotFoundException ex) {
            UI.displayError(ex.getMessage());
        }

        jLabel1.setUI(MultiLineLabelUI.labelUI);
    }

    /**
     * Prepare the panel before showing it.
     */
    @Override
    public void preparePanel() {
        jLabel3.setForeground(Color.red);
        jLabel3.setText(_("Trying to connect ..."));
        
        AdoHiveController.setConnectionString(Runtime.getInstance().getOption("database-uri"));
        AdoHiveController.setDriver(Runtime.getInstance().getOption("database-driver"));
        AdoHiveController.resetInstance();

        BackgroundThread thread = new BackgroundThread();
        thread.start();
    }

    /**
     * Return true if the connection was successfully established.
     *
     * @return True if the connection was successfully established
     */
    public boolean hasConnected() {
        return hasConnected;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(500, 300));

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 0, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/aidger/res/icons/aidger-icon.png"))); // NOI18N
        jLabel2.setText("aidGer");

        jLabel1.setText(_("aidGer will now try to establish a connection to the database and configure the database if necessary.\n\nIf the text below changes and becomes green, it is save to finish the wizard and start working with aidGer. Otherwise you should go back to the previous page and configure your database correctly."));
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel3.setForeground(java.awt.Color.red);
        jLabel3.setText(_("Trying to connect ..."));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap(68, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    /**
     * Try to connect to the database in a separate thread.
     */
    private class BackgroundThread extends Thread {

        @Override
        public void run() {
            List lst = null;
            try {
                 lst = (new Activity()).getAll();
            } catch (Exception ex) {
            }

            if (lst == null) {
                jLabel3.setText(_("Connection failed. Please check with your Administrator"));
            } else {
                jLabel3.setForeground(Color.green);
                jLabel3.setText(_("Connection successful."));
                hasConnected = true;
            }
        }
    }



}