package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.aidger.model.models.Course;
import de.aidger.view.UI;
import de.aidger.view.tabs.CourseEditorTab;
import de.aidger.view.tabs.MasterDataViewerTab;

/**
 * This action replaces the current tab with edit-tab for each master data type.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class MasterDataEditAction extends AbstractAction {

    /**
     * The master data viewer tab where the action is performed.
     */
    private final MasterDataViewerTab tab;

    /**
     * Initializes the action.
     */
    public MasterDataEditAction(MasterDataViewerTab tab) {
        putValue(Action.NAME, _("Edit"));

        this.tab = tab;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (tab.getTable().getSelectedRow() > -1) {
            int index = tab.getTable().getRowSorter().convertRowIndexToModel(
                    tab.getTable().getSelectedRow());

            switch (tab.getType()) {
            case Course:
                Course course = (Course) tab.getTableModel().getModel(index);

                UI.getInstance().replaceCurrentTab(new CourseEditorTab());
                break;
            case Assistant:
                break;
            case FinancialCategory:
                break;
            case HourlyWage:
                break;
            }
        } else {
            UI.displayError(_("Please select an entry from the table."));
        }
    }
}