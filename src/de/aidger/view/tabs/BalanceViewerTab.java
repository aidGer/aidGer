package de.aidger.view.tabs;

import static de.aidger.utils.Translation._;

/**
 * A tab for viewing balance reports.
 * 
 * @author aidGer Team
 */
public class BalanceViewerTab extends Tab {

    /**
     * Initializes a new BalanceViewerTab, which will have the created Semesters
     * added to it.
     */
    public BalanceViewerTab() {
        initComponents();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.tabs.Tab#getTabName()
     */
    @Override
    public String getTabName() {
        return _("Balance Viewing");
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

        jTextField3 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();

        jTextField3.setText("jTextField3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
            jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 100,
            Short.MAX_VALUE));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 100,
            Short.MAX_VALUE));

        setLayout(new java.awt.GridLayout(0, 1));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

}
