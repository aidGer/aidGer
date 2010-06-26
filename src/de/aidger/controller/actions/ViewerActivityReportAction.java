package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.aidger.model.models.Assistant;
import de.aidger.view.UI;
import de.aidger.view.tabs.ViewerTab;

/**
 * This action shows the activity report for a given assistant.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ViewerActivityReportAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ViewerActivityReportAction() {
        putValue(Action.NAME, _("Activity Report"));
        // TODO
        //putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
        //        "/de/aidger/view/icons/")));
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

        if (tab.getTable().getSelectedRow() == -1) {
            UI.displayError(_("Please select an entry from the table."));
        } else if (tab.getTable().getSelectedRowCount() > 1) {
            UI.displayError(_("Please select only one entry from the table."));
        } else {
            int index = tab.getTable().getRowSorter().convertRowIndexToModel(
                tab.getTable().getSelectedRow());

            Assistant assistant = (Assistant) tab.getTableModel().getModel(
                index);

            // TODO
        }
    }
}