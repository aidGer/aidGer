package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

import de.aidger.view.UI;
import de.aidger.view.tabs.AssistantViewerTab;
import de.aidger.view.tabs.CourseViewerTab;
import de.aidger.view.tabs.EmptyTab;
import de.aidger.view.tabs.FinancialCategoryViewerTab;
import de.aidger.view.tabs.HourlyWageViewerTab;
import de.aidger.view.tabs.MasterDataViewerTab;
import de.aidger.view.tabs.Tab;

/**
 * This action shows a master data in the current tab.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class MasterDataViewAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public MasterDataViewAction() {
        putValue(Action.NAME, _("View"));
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
            newTab = new CourseViewerTab();
            break;
        case Assistant:
            newTab = new AssistantViewerTab();
            break;
        case FinancialCategory:
            newTab = new FinancialCategoryViewerTab();
            break;
        case HourlyWage:
            newTab = new HourlyWageViewerTab();
            break;
        default:
            newTab = new EmptyTab();
            break;
        }

        UI.getInstance().replaceCurrentTab(newTab);
    }
}