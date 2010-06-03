package de.aidger.controller.actions.masterdata;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * This action shows the activities for a given master data.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class MasterDataViewerActivitiesAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public MasterDataViewerActivitiesAction() {
        putValue(Action.NAME, _("Activities"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}