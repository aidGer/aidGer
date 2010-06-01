package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.aidger.view.UI;
import de.aidger.view.tabs.MasterDataViewerTab;
import de.aidger.view.tabs.MasterDataViewerTab.MasterDataType;

/**
 * This action replaces the current tab with the course viewer tab when user
 * cancelled his previous step.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class CourseEditorCancelAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public CourseEditorCancelAction() {
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
        UI.getInstance().replaceCurrentTab(
                new MasterDataViewerTab(MasterDataType.Course));
    }
}