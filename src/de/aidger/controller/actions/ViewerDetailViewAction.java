package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import de.aidger.view.UI;
import de.aidger.view.tabs.DetailViewerTab;
import de.aidger.view.tabs.ViewerTab;

/**
 * This action shows a model in the current tab in detail.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ViewerDetailViewAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ViewerDetailViewAction() {
        putValue(Action.NAME, _("View"));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
            "/de/aidger/view/icons/property.png")));
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
                new DetailViewerTab(tab.getType(), tab.getTableModel()
                    .getModel(index)));
        } else {
            UI.displayError(_("Please select an entry from the table."));
        }
    }
}