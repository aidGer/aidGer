package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.aidger.view.UI;
import de.aidger.view.tabs.EditorTab;
import de.aidger.view.tabs.Tab;
import de.aidger.view.tabs.ViewerTab;

/**
 * This action replaces the current tab with the model viewer tab when user
 * cancelled his previous step in editor tab.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class EditorCancelAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public EditorCancelAction() {
        putValue(Action.NAME, _("Cancel"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        EditorTab tab = (EditorTab) UI.getInstance().getCurrentTab();

        Tab next = tab.getPredecessor();

        if (next == null) {
            next = new ViewerTab(tab.getType());
        }

        UI.getInstance().replaceCurrentTab(next);
    }
}