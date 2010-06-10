package de.aidger.controller.actions.masterdata;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import de.aidger.model.AbstractModel;
import de.aidger.view.UI;
import de.aidger.view.tabs.MasterDataViewerTab;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * This action removes a master data from the table and the database.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class MasterDataViewerDeleteAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public MasterDataViewerDeleteAction() {
        putValue(Action.NAME, _("Delete"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void actionPerformed(ActionEvent e) {
        MasterDataViewerTab tab = (MasterDataViewerTab) UI.getInstance()
            .getCurrentTab();

        if (tab.getTable().getSelectedRow() > -1) {
            int ret = JOptionPane.showConfirmDialog(tab,
                _("Do you really want to delete this entry?"), null,
                JOptionPane.YES_NO_OPTION);

            if (ret == JOptionPane.YES_OPTION) {
                int index = tab.getTable().getRowSorter()
                    .convertRowIndexToModel(tab.getTable().getSelectedRow());

                try {
                    AbstractModel model = tab.getTableModel().getModel(index);

                    model.remove();

                    tab.getTableModel().removeRow(index);
                } catch (AdoHiveException e1) {
                    UI.displayError(_("Could not remove model from database."));
                }
            }
        } else {
            UI.displayError(_("Please select an entry from the table."));
        }
    }
}