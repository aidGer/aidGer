/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2013 The aidGer Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

/*
 * ProtocolPanel.java
 * 
 * Created on 11.06.2010, 17:08:19
 */

package de.aidger.view.tabs;

import static de.aidger.utils.Translation._;

import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import de.aidger.controller.ActionNotFoundException;
import de.aidger.controller.ActionRegistry;
import de.aidger.controller.actions.ProtocolExportAction;
import de.aidger.model.reports.ProtocolCreator;
import de.aidger.view.UI;

/**
 * This tab displays the activity protocol in a table.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ProtocolViewerTab extends Tab {

    /**
     * The table model of the content table.
     */
    private final DefaultTableModel activityTableModel = new DefaultTableModel(
        null, new String[] { _("Affected assistant"), _("Affected course"),
                _("Type"), _("Date"), _("Content"), _("Initiator"),
                _("Processor"), _("Remark") }) {
        boolean[] canEdit = new boolean[] { false, false, false, false, false,
                false, false, false };

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    };

    /**
     * The protocol creator that gets the activities for this panel.
     */
    private final ProtocolCreator protocolCreator = new ProtocolCreator();

    /**
     * The activities of this panel.
     */
    private ArrayList<Object[]> activities = new ArrayList<Object[]>();

    /**
     * The number of days to display activities of.
     */
    private int days;

    /**
     * Initializes a new ProtocolViewerTab and registers a change listener to
     * the spinner.
     * 
     * @param protocolCreator
     *            The protocol creator that called this viewer.
     */
    public ProtocolViewerTab() {
        initComponents();

        daySpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if ((Integer) daySpinner.getValue() < 0) {
                    daySpinner.setValue(0);
                }
                days = (Integer) daySpinner.getValue();
                updateActivities(days);
            }
        });

        showSelectedDaysBtn.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (showSelectedDaysBtn.isSelected()) {
                    daySpinner.setEnabled(true);
                    showAllBtn.setSelected(false);
                    days = (Integer) daySpinner.getValue();
                    updateActivities(days);
                }
            }
        });

        showAllBtn.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (showAllBtn.isSelected()) {
                    daySpinner.setEnabled(false);
                    showSelectedDaysBtn.setSelected(false);
                    days = -1;
                    updateActivities(days);
                }
            }
        });

        try {
            exportProtocolButton.setAction(ActionRegistry.getInstance().get(
                ProtocolExportAction.class.getName()));
        } catch (ActionNotFoundException ex) {
            UI.displayError(ex.getMessage());
        }
        showAllBtn.setSelected(true);
    }

    /**
     * Updates the table of activities with the given number of days.
     * 
     * @param numberOfDays
     *            The number of days of which to display activities.
     */
    private void updateActivities(int numberOfDays) {
        clearTable();
        activities = protocolCreator.createProtocol(numberOfDays);
        for (Object activity : activities) {
            addActivity((Object[]) activity);
        }
        enableExport();
    }

    /**
     * Enables the button to export activities if there are entries in the
     * table.
     */
    private void enableExport() {
        if (activityTableModel.getRowCount() > 0) {
            exportProtocolButton.setEnabled(true);
        } else {
            exportProtocolButton.setEnabled(false);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.tabs.Tab#getTabName()
     */
    @Override
    public String getTabName() {
        return _("Export Activities");
    }

    /**
     * Get the amount of days selected.
     * 
     * @return The amount of days
     */
    public int getDays() {
        return days;
    }

    /**
     * Removes all the rows from the content table.
     */
    private void clearTable() {
        while (activityTableModel.getRowCount() > 0) {
            activityTableModel.removeRow(0);
        }
    }

    /**
     * Adds an activity to the table.
     * 
     * @param course
     *            The activity to be added to the table.
     */
    private void addActivity(Object[] activity) {
        activityTableModel.addRow(activity);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        exportProtocolButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        contentPanel = new javax.swing.JPanel();
        contentScrollPane = new javax.swing.JScrollPane();
        contentTable = new javax.swing.JTable();
        filterLabel = new javax.swing.JPanel();
        showAllBtn = new javax.swing.JRadioButton();
        showSelectedDaysBtn = new javax.swing.JRadioButton();
        daySpinner = new javax.swing.JSpinner();

        setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.add(jSeparator1);

        exportProtocolButton.setText(_("Export"));
        exportProtocolButton.setFocusable(false);
        exportProtocolButton
            .setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportProtocolButton
            .setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(exportProtocolButton);
        jToolBar1.add(jSeparator2);

        add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        contentPanel.setLayout(new java.awt.BorderLayout());

        contentTable.setModel(activityTableModel);
        contentScrollPane.setViewportView(contentTable);

        contentPanel.add(contentScrollPane, java.awt.BorderLayout.CENTER);

        filterLabel
            .setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        showAllBtn.setText(_("Show all activities"));
        filterLabel.add(showAllBtn);

        showSelectedDaysBtn
            .setText(_("Number of days before today to display: "));
        filterLabel.add(showSelectedDaysBtn);

        daySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                daySpinnerStateChanged(evt);
            }
        });
        filterLabel.add(daySpinner);

        contentPanel.add(filterLabel, java.awt.BorderLayout.PAGE_START);

        add(contentPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void daySpinnerStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_daySpinnerStateChanged
        daySpinner.setVisible(false);
        daySpinner.setVisible(true);
    }// GEN-LAST:event_daySpinnerStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane contentScrollPane;
    private javax.swing.JTable contentTable;
    private javax.swing.JSpinner daySpinner;
    private javax.swing.JButton exportProtocolButton;
    private javax.swing.JPanel filterLabel;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JRadioButton showAllBtn;
    private javax.swing.JRadioButton showSelectedDaysBtn;
    // End of variables declaration//GEN-END:variables

}
