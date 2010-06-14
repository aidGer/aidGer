package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import de.aidger.model.AbstractModel;
import de.aidger.view.UI;
import de.aidger.view.tabs.ViewerTab;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * This action removes a model from the table and the database.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ViewerDeleteAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ViewerDeleteAction() {
        putValue(Action.NAME, _("Delete"));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
            "/de/aidger/view/icons/minus.png")));
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
        ViewerTab tab = (ViewerTab) UI.getInstance().getCurrentTab();

        if (tab.getTable().getSelectedRow() > -1) {
            int[] rows = tab.getTable().getSelectedRows();

            String confirmMsg = _("Do you really want to delete this entry?");
            if (rows.length > 1) {
                confirmMsg = _("Do you really want to delete the selected entries?");
            }

            int ret = JOptionPane.showConfirmDialog(tab, confirmMsg, null,
                JOptionPane.YES_NO_OPTION);

            if (ret == JOptionPane.YES_OPTION) {
                try {
                    for (int row : rows) {
                        int index = tab.getTable().convertRowIndexToModel(row);

                        AbstractModel model = tab.getTableModel().getModel(
                            index);

                        model.remove();
                    }
                } catch (AdoHiveException e1) {
                    UI
                        .displayError(_("A database error occurred during removing."));
                }
            }
        } else {
            UI.displayError(_("Please select an entry from the table."));
        }
    }
}