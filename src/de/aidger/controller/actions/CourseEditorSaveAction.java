package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.aidger.view.UI;
import de.aidger.view.tabs.MasterDataViewerTab;
import de.aidger.view.tabs.MasterDataViewerTab.MasterDataType;

/**
 * This action saves the course and replaces the current tab with the course
 * viewer tab.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class CourseEditorSaveAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public CourseEditorSaveAction() {
        putValue(Action.NAME, _("Save"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO save course..

        UI.getInstance().replaceCurrentTab(
                new MasterDataViewerTab(MasterDataType.Course));
    }
}