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

import de.aidger.model.Runtime;
import de.aidger.model.models.FinancialCategory;
import de.aidger.view.utils.HelpLabel;
import de.aidger.view.utils.InputPatternFilter;
import de.aidger.view.utils.InvalidLengthException;
import de.aidger.view.utils.UIFund;

/**
 * A form used for editing / creating new financial categories.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class FinancialCategoryEditorForm extends JPanel {

    /**
     * Constructs a financial category editor form.
     * 
     * @param fc
     *            the financial category that will be edited
     */
    public FinancialCategoryEditorForm(FinancialCategory fc) {
        initComponents();

        // add input filters
        InputPatternFilter.addFilter(txtYear, "[0-9]{0,4}");

        hlpYear.setToolTipText(_("Only a year in 4 digits is allowed."));

        if (fc != null) {
            txtName.setText(fc.getName());
            txtYear.setText(String.valueOf(fc.getYear()));

            for (int i = 0; i < fc.getFunds().length; ++i) {
                addNewFunds();

                FundsLine fl = fundsLines.get(i);
                fl.cmbFunds.setSelectedItem(UIFund.valueOf(fc.getFunds()[i]));
                fl.txtBudgetCosts.setText(String
                    .valueOf(fc.getBudgetCosts()[i]));
            }
        } else {
            addNewFunds();
        }
    }

    /**
     * Sorts the funds.
     */
    public void sortFunds() {
        Collections.sort(fundsLines, new Comparator<FundsLine>() {
            @Override
            public int compare(FundsLine f, FundsLine s) {
                Integer first = Integer.valueOf((String) f.cmbFunds
                    .getSelectedItem());
                Integer second = Integer.valueOf((String) s.cmbFunds
                    .getSelectedItem());

                if (first < second) {
                    return -1;
                } else if (first > second) {
                    return 1;
                } else {
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
        Integer[] budgetCosts = new Integer[fundsLines.size()];

        for (int i = 0; i < fundsLines.size(); ++i) {
            budgetCosts[i] = Integer.valueOf(fundsLines.get(i).txtBudgetCosts
                .getText());
        }

        return budgetCosts;
    }

    /**
     * Get the funds of the category.
     * 
     * @return The funds of the category
     * @throws NumberFormatException
     *             InvaludLengthException
     */
    public Integer[] getFunds() throws NumberFormatException,
            InvalidLengthException {
        Integer[] funds = new Integer[fundsLines.size()];

        for (int i = 0; i < fundsLines.size(); ++i) {
            String fund = (String) fundsLines.get(i).cmbFunds.getSelectedItem();
            funds[i] = Integer.valueOf(fund);

            if (fund.length() != 8) {
                throw new InvalidLengthException();
            }
        }

        return funds;
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
     * Adds a new funds line to the form.
     */
    private void addNewFunds() {
        GridBagConstraints gridBagConstraints;

        JLabel lblFunds = new JLabel();
        lblFunds.setText(_("Cost unit"));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblFunds, gridBagConstraints);

        JComboBox cmbFunds = new JComboBox();
        cmbFunds.setEditable(true);
        cmbFunds.setModel(new DefaultComboBoxModel(Runtime.getInstance()
            .getCostUnitMap().getCostUnits()));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(cmbFunds, gridBagConstraints);

        HelpLabel hlpFunds = new HelpLabel();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        add(hlpFunds, gridBagConstraints);

        hlpFunds.setToolTipText(_("Only a number with 8 digits is allowed."));

        JLabel lblBudgetCosts = new JLabel();
        lblBudgetCosts.setText(_("Budget Costs"));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 10, 10);
        add(lblBudgetCosts, gridBagConstraints);

        JTextField txtBudgetCosts = new JTextField();
        txtBudgetCosts.setMinimumSize(new java.awt.Dimension(200, 25));
        txtBudgetCosts.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtBudgetCosts, gridBagConstraints);

        InputPatternFilter.addFilter(txtBudgetCosts, "[0-9]+");

        HelpLabel hlpBudgetCosts = new HelpLabel();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        add(hlpBudgetCosts, gridBagConstraints);

        hlpBudgetCosts.setToolTipText(_("Only a number is allowed."));

        JButton btnPlusMinus = new JButton();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;

        FundsLine fl = new FundsLine(lblFunds, cmbFunds, hlpFunds,
            lblBudgetCosts, txtBudgetCosts, hlpBudgetCosts, btnPlusMinus);

        if (fundsLines.isEmpty()) {
            btnPlusMinus.setIcon(new ImageIcon(getClass().getResource(
                "/de/aidger/res/icons/plus-small.png")));

            gridBagConstraints.gridy = 1;

            btnPlusMinus.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    addNewFunds();
                }
            });
        } else {
            gridBagConstraints.gridy = GridBagConstraints.RELATIVE;

            btnPlusMinus.setAction(new RemoveFundsAction(fl));
        }

        add(btnPlusMinus, gridBagConstraints);

        fundsLines.add(fl);
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
        filler = new javax.swing.JLabel();

        fundsLines = new ArrayList<FundsLine>();

        setLayout(new java.awt.GridBagLayout());

        lblName.setText(_("Name"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblName, gridBagConstraints);

        lblYear.setText(_("Year"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 10, 10);
        add(lblYear, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(filler, gridBagConstraints);

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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(txtYear, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
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
    private JLabel filler;

    private List<FundsLine> fundsLines;

    /**
     * This class represents a funds line in the form.
     * 
     * @author aidGer Team
     */
    private class FundsLine {
        public JLabel lblFunds;
        public JComboBox cmbFunds;
        public HelpLabel hlpFunds;
        public JLabel lblBudgetCosts;
        public JTextField txtBudgetCosts;
        public HelpLabel hlpBudgetCosts;
        public JButton btnPlusMinus;

        /**
         * Initializes a funds line.
         * 
         */
        public FundsLine(JLabel lblFunds, JComboBox cmbFunds,
                HelpLabel hlpFunds, JLabel lblBudgetCosts,
                JTextField txtBudgetCosts, HelpLabel hlpBudgetCosts,
                JButton btnPlusMinus) {
            this.lblFunds = lblFunds;
            this.cmbFunds = cmbFunds;
            this.hlpFunds = hlpFunds;
            this.lblBudgetCosts = lblBudgetCosts;
            this.txtBudgetCosts = txtBudgetCosts;
            this.hlpBudgetCosts = hlpBudgetCosts;
            this.btnPlusMinus = btnPlusMinus;
        }
    }

    /**
     * Removes a funds line from the form by clicking on "-" button.
     * 
     * @author aidGer Team
     */
    private class RemoveFundsAction extends AbstractAction {
        /**
         * The funds line that will be removed.
         */
        private final FundsLine fundsLine;

        /**
         * Initializes the action.
         */
        public RemoveFundsAction(FundsLine fundsLine) {
            putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
                "/de/aidger/res/icons/minus-small.png")));

            this.fundsLine = fundsLine;
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
            remove(fundsLine.lblFunds);
            remove(fundsLine.cmbFunds);
            remove(fundsLine.hlpFunds);
            remove(fundsLine.lblBudgetCosts);
            remove(fundsLine.txtBudgetCosts);
            remove(fundsLine.hlpBudgetCosts);
            remove(fundsLine.btnPlusMinus);

            fundsLines.remove(fundsLine);

            repaint();
            revalidate();
        }
    }

}
