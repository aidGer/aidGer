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

package de.aidger.view.forms;

import static de.aidger.utils.Translation._;

import javax.swing.JPanel;

import de.aidger.model.models.CostUnit;

/**
 * A form used for editing / creating new contracts.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class CostUnitViewerForm extends JPanel {

    /**
     * Constructs a cost unit viewer form.
     * 
     * @param costUnit
     *            the cost unit that will be displayed
     */
    public CostUnitViewerForm(CostUnit costUnit) {
        initComponents();

        this.costUnit.setText(costUnit.getCostUnit());
        funds.setText(costUnit.getFunds());
        tokenDB.setText(costUnit.getTokenDB());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblCostUnit = new javax.swing.JLabel();
        lblFunds = new javax.swing.JLabel();
        lblTokenDB = new javax.swing.JLabel();
        costUnit = new javax.swing.JLabel();
        funds = new javax.swing.JLabel();
        tokenDB = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        lblCostUnit.setText(_("Cost unit"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblCostUnit, gridBagConstraints);

        lblFunds.setText(_("Funds"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblFunds, gridBagConstraints);

        lblTokenDB.setText(_("Database token"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblTokenDB, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(costUnit, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(funds, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(tokenDB, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel costUnit;
    private javax.swing.JLabel funds;
    private javax.swing.JLabel lblCostUnit;
    private javax.swing.JLabel lblFunds;
    private javax.swing.JLabel lblTokenDB;
    private javax.swing.JLabel tokenDB;
    // End of variables declaration//GEN-END:variables

}
