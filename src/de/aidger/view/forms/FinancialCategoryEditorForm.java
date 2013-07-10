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

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import de.aidger.model.Runtime;
import de.aidger.model.models.CostUnit;
import de.aidger.model.models.FinancialCategory;
import de.aidger.view.models.ComboBoxModel;
import de.aidger.view.models.GenericListModel;
import de.aidger.view.models.UICostUnit;
import de.aidger.view.tabs.EditorTab;
import de.aidger.view.tabs.ViewerTab.DataType;
import de.aidger.view.utils.HelpLabel;
import de.aidger.view.utils.InputPatternFilter;
import de.aidger.view.utils.InvalidLengthException;

/**
 * A form used for editing / creating new financial categories.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class FinancialCategoryEditorForm extends JPanel {
    
    EditorTab tab;

    /**
     * Constructs a financial category editor form.
     * 
     * @param fc
     *            the financial category that will be edited
     */
    public FinancialCategoryEditorForm(FinancialCategory fc, EditorTab tab) {
        this.tab = tab;
        
        initComponents();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                txtName.requestFocusInWindow();
            }
        });

        // add input filters
        InputPatternFilter.addFilter(txtYear, "[0-9]{0,4}");

        hlpYear.setToolTipText(_("Only a year in 4 digits is allowed."));

        if (fc != null) {
            txtName.setText(fc.getName());
            txtYear.setText(String.valueOf(fc.getYear()));

            for (int i = 0; i < fc.getCostUnits().length; ++i) {
                addNewCostUnit();

                CostUnitLine fl = costUnitLines.get(i);
                fl.cmbCostUnit.setSelectedItem(new CostUnit().getCostUnit(fc.getCostUnits()[i]));
                fl.txtBudgetCosts.setText(String.valueOf(fc.getBudgetCosts()[i]));
            }
        } else {
            addNewCostUnit();
        }
    }

    /**
     * Sorts the cost units.
     */
    public void sortCostUnits() {
        Collections.sort(costUnitLines, new Comparator<CostUnitLine>() {
            @Override
            public int compare(CostUnitLine f, CostUnitLine s) {
            	try {
	                Integer first = Integer.valueOf(((CostUnit) f.cmbCostUnit.getSelectedItem()).getCostUnit());
	                Integer second = Integer.valueOf(((CostUnit) s.cmbCostUnit.getSelectedItem()).getCostUnit());
	
	                if (first < second) {
	                    return -1;
	                } else if (first > second) {
	                    return 1;
	                } else {
	                    return 0;
	                }
            	} catch (NumberFormatException e) {
            		return 0;
            	} catch (NullPointerException e) {
            	    return 0;
            	}
            }
        });
    }

    /**
     * Get the budget costs of the category.
     * 
     * @return The budget costs of the category
     * @throws NumberFormatException
     */
    public Integer[] getBudgetCosts() throws NumberFormatException {
        Integer[] budgetCosts = new Integer[costUnitLines.size()];

        for (int i = 0; i < costUnitLines.size(); ++i) {
            budgetCosts[i] = Integer.valueOf(costUnitLines.get(i).txtBudgetCosts.getText());
        }

        return budgetCosts;
    }

    /**
     * Get the cost units of the category.
     * 
     * @return the cost units of the category
     * @throws NumberFormatException
     *             InvaludLengthException
     */
    public Integer[] getCostUnits() throws NumberFormatException, InvalidLengthException {
        Integer[] costUnits = new Integer[costUnitLines.size()];

        for (int i = 0; i < costUnitLines.size(); ++i) {
            CostUnit costUnit = (CostUnit) costUnitLines.get(i).cmbCostUnit.getSelectedItem();
            if(costUnit != null) {
                costUnits[i] = Integer.valueOf(costUnit.getCostUnit());
    
                if (costUnit.getCostUnit().length() != 8) {
                    throw new InvalidLengthException();
                }
            } else {
                costUnits[i] = null;
            }
        }

        return costUnits;
    }

    /**
     * Get the name of the category.
     * 
     * @return The name of the category
     */
    public String getFCName() {
        return txtName.getText();
    }

    /**
     * Get the year the category is valid.
     * 
     * @return The year the category is valid
     * @throws NumberFormatException
     */
    public short getYear() throws NumberFormatException {
        return Short.valueOf(txtYear.getText());
    }

    /**
     * Adds a new cost unit line to the form.
     */
    private void addNewCostUnit() {
        GridBagConstraints gridBagConstraints;

        JLabel lblCostUnit = new JLabel();
        lblCostUnit.setText(_("Cost unit"));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblCostUnit, gridBagConstraints);
        lblCostUnit.getAccessibleContext()
            .setAccessibleDescription("costUnits");

        ComboBoxModel costUnitModel = new ComboBoxModel(DataType.CostUnit);
        List<CostUnit> costUnits = (new CostUnit()).getAll();
        for(CostUnit costUnit : costUnits) {
            costUnitModel.addModel(costUnit);
        }
        
        JComboBox cmbCostUnit = new JComboBox();
        cmbCostUnit.setModel(costUnitModel);
        if(cmbCostUnit.getItemCount() > 0)
            cmbCostUnit.setSelectedItem(null);
        tab.getListModels().add(costUnitModel);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(cmbCostUnit, gridBagConstraints);

        JLabel lblBudgetCosts = new JLabel();
        lblBudgetCosts.setText(_("Budget Costs"));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 10, 10);
        add(lblBudgetCosts, gridBagConstraints);
        lblBudgetCosts.getAccessibleContext().setAccessibleDescription(
            "budgetCosts");

        JTextField txtBudgetCosts = new JTextField();
        txtBudgetCosts.setMinimumSize(new java.awt.Dimension(200, 25));
        txtBudgetCosts.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtBudgetCosts, gridBagConstraints);

        InputPatternFilter.addFilter(txtBudgetCosts, "[0-9]+");

        HelpLabel hlpBudgetCosts = new HelpLabel();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        add(hlpBudgetCosts, gridBagConstraints);

        hlpBudgetCosts.setToolTipText(_("Only a number is allowed."));

        JButton btnPlusMinus = new JButton();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;

        CostUnitLine fl = new CostUnitLine(lblCostUnit, cmbCostUnit,
            lblBudgetCosts, txtBudgetCosts, hlpBudgetCosts, btnPlusMinus);

        if (costUnitLines.isEmpty()) {
            btnPlusMinus.setIcon(new ImageIcon(getClass().getResource(
                "/de/aidger/res/icons/plus-small.png")));

            gridBagConstraints.gridy = 1;

            btnPlusMinus.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    addNewCostUnit();
                }
            });
        } else {
            gridBagConstraints.gridy = GridBagConstraints.RELATIVE;

            btnPlusMinus.setAction(new RemoveCostUnitAction(fl));
        }

        add(btnPlusMinus, gridBagConstraints);

        costUnitLines.add(fl);
    }

    /**
     * Initializes the components.
     */
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblName = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtYear = new javax.swing.JTextField();
        hlpYear = new HelpLabel();

        costUnitLines = new ArrayList<CostUnitLine>();

        setLayout(new java.awt.GridBagLayout());

        lblName.setText(_("Name"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblName, gridBagConstraints);
        lblName.getAccessibleContext().setAccessibleDescription("name");

        lblYear.setText(_("Year"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 10, 10);
        add(lblYear, gridBagConstraints);
        lblYear.getAccessibleContext().setAccessibleDescription("year");

        txtName.setMinimumSize(new java.awt.Dimension(200, 25));
        txtName.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtName, gridBagConstraints);

        txtYear.setMinimumSize(new java.awt.Dimension(200, 25));
        txtYear.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtYear, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        add(hlpYear, gridBagConstraints);
    }

    private JLabel lblName;
    private JLabel lblYear;
    private JTextField txtName;
    private JTextField txtYear;
    private HelpLabel hlpYear;

    private List<CostUnitLine> costUnitLines;

    /**
     * This class represents a funds line in the form.
     * 
     * @author aidGer Team
     */
    private class CostUnitLine {
        public JLabel lblCostUnit;
        public JComboBox cmbCostUnit;
        public JLabel lblBudgetCosts;
        public JTextField txtBudgetCosts;
        public HelpLabel hlpBudgetCosts;
        public JButton btnPlusMinus;

        /**
         * Initializes a cost unit line.
         * 
         */
        public CostUnitLine(JLabel lblCostUnit, JComboBox cmbCostUnit,
                JLabel lblBudgetCosts, JTextField txtBudgetCosts,
                HelpLabel hlpBudgetCosts, JButton btnPlusMinus) {
            this.lblCostUnit = lblCostUnit;
            this.cmbCostUnit = cmbCostUnit;
            this.lblBudgetCosts = lblBudgetCosts;
            this.txtBudgetCosts = txtBudgetCosts;
            this.hlpBudgetCosts = hlpBudgetCosts;
            this.btnPlusMinus = btnPlusMinus;
        }
    }

    /**
     * Removes a cost unit line from the form by clicking on "-" button.
     * 
     * @author aidGer Team
     */
    private class RemoveCostUnitAction extends AbstractAction {
        /**
         * The funds line that will be removed.
         */
        private final CostUnitLine costUnitLine;

        /**
         * Initializes the action.
         */
        public RemoveCostUnitAction(CostUnitLine costUnitLine) {
            putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
                "/de/aidger/res/icons/minus-small.png")));

            this.costUnitLine = costUnitLine;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
         * )
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            tab.getListModels().remove(costUnitLine.cmbCostUnit.getModel());
            remove(costUnitLine.lblCostUnit);
            remove(costUnitLine.cmbCostUnit);
            remove(costUnitLine.lblBudgetCosts);
            remove(costUnitLine.txtBudgetCosts);
            remove(costUnitLine.hlpBudgetCosts);
            remove(costUnitLine.btnPlusMinus);

            costUnitLines.remove(costUnitLine);

            repaint();
            revalidate();
        }
    }

}
