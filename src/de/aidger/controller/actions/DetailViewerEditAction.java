package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.aidger.view.UI;
import de.aidger.view.tabs.DetailViewerTab;
import de.aidger.view.tabs.EditorTab;
import de.aidger.view.tabs.Tab;

/**
 * This action replaces the current tab with the model editor tab when user
 * wants to edit the model displayed in detail viewer tab.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class DetailViewerEditAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public DetailViewerEditAction() {
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
        DetailViewerTab tab = (DetailViewerTab) UI.getInstance()
            .getCurrentTab();

        Tab next = new EditorTab(tab.getType(), tab.getModel());
        next.markAsNoPredecessor();

        UI.getInstance().replaceCurrentTab(next);
    }
}