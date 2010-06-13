package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import de.aidger.view.UI;
import de.aidger.view.tabs.AssistantViewerTab;
import de.aidger.view.tabs.CourseViewerTab;
import de.aidger.view.tabs.EmptyTab;
import de.aidger.view.tabs.FinancialCategoryViewerTab;
import de.aidger.view.tabs.HourlyWageViewerTab;
import de.aidger.view.tabs.Tab;
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