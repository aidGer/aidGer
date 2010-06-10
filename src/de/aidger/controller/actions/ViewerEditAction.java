package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.aidger.view.UI;
import de.aidger.view.tabs.EditorTab;
import de.aidger.view.tabs.ViewerTab;

/**
 * This action replaces the current tab with edit-tab for each model type.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ViewerEditAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ViewerEditAction() {
        putValue(Action.NAME, _("Edit"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ViewerTab tab = (ViewerTab) UI.getInstance().getCurrentTab();

        if (tab.getTable().getSelectedRow() > -1) {
            int index = tab.getTable().getRowSorter().convertRowIndexToModel(
                tab.getTable().getSelectedRow());

            UI.getInstance().replaceCurrentTab(
                new EditorTab(tab.getType(), tab.getTableModel()
                    .getModel(index)));
        } else {
            UI.displayError(_("Please select an entry from the table."));
        }
    }
}