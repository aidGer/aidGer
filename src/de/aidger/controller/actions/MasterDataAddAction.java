package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

import de.aidger.view.UI;
import de.aidger.view.tabs.AssistantEditorTab;
import de.aidger.view.tabs.CourseEditorTab;
import de.aidger.view.tabs.EmptyTab;
import de.aidger.view.tabs.FinancialCategoryEditorTab;
import de.aidger.view.tabs.HourlyWageEditorTab;
import de.aidger.view.tabs.MasterDataViewerTab;
import de.aidger.view.tabs.Tab;

/**
 * This action replaces the current tab with add-tab for each master data type.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class MasterDataAddAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public MasterDataAddAction() {
        putValue(Action.NAME, _("Add"));
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

        Tab newTab;

        switch (tab.getType()) {
        case Course:
            newTab = new CourseEditorTab();
            break;
        case Assistant:
            newTab = new AssistantEditorTab();
            break;
        case FinancialCategory:
            newTab = new FinancialCategoryEditorTab();
            break;
        case HourlyWage:
            newTab = new HourlyWageEditorTab();
            break;
        default:
            newTab = new EmptyTab();
            break;
        }

        UI.getInstance().replaceCurrentTab(newTab);
    }
}