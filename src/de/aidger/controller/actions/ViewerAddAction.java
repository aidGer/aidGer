package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.aidger.view.UI;
import de.aidger.view.tabs.EditorTab;
import de.aidger.view.tabs.ViewerTab;

/**
 * This action replaces the current tab with add-tab for each model type.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ViewerAddAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ViewerAddAction() {
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
        ViewerTab tab = (ViewerTab) UI.getInstance().getCurrentTab();

        UI.getInstance().replaceCurrentTab(new EditorTab(tab.getType()));
    }
}