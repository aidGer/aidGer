package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import de.aidger.model.models.FinancialCategory;
import de.aidger.model.models.HourlyWage;
import de.aidger.view.UI;
import de.aidger.view.tabs.AssistantEditorTab;
import de.aidger.view.tabs.CourseEditorTab;
import de.aidger.view.tabs.FinancialCategoryEditorTab;
import de.aidger.view.tabs.HourlyWageEditorTab;
import de.aidger.view.tabs.MasterDataViewerTab;

/**
 * This action replaces the current tab with edit-tab for each master data type.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class MasterDataEditAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public MasterDataEditAction() {
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
        JButton btnEdit = (JButton) e.getSource();
        JToolBar toolBar = (JToolBar) btnEdit.getParent();
        MasterDataViewerTab tab = (MasterDataViewerTab) toolBar.getParent();

        if (tab.getTable().getSelectedRow() > -1) {
            int index = tab.getTable().getRowSorter().convertRowIndexToModel(
                    tab.getTable().getSelectedRow());

            switch (tab.getType()) {
            case Course:
                Course course = (Course) tab.getTableModel().getModel(index);

                UI.getInstance().replaceCurrentTab(new CourseEditorTab(course));
                break;
            case Assistant:
                Assistant assistant = (Assistant) tab.getTableModel().getModel(
                        index);

                UI.getInstance().replaceCurrentTab(new AssistantEditorTab());
                break;
            case FinancialCategory:
                FinancialCategory fc = (FinancialCategory) tab.getTableModel()
                        .getModel(index);

                UI.getInstance().replaceCurrentTab(
                        new FinancialCategoryEditorTab());
                break;
            case HourlyWage:
                HourlyWage hw = (HourlyWage) tab.getTableModel()
                        .getModel(index);

                UI.getInstance().replaceCurrentTab(new HourlyWageEditorTab());
                break;
            }
        } else {
            UI.displayError(_("Please select an entry from the table."));
        }
    }
}