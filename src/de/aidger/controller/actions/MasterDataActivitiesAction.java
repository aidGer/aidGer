package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.aidger.view.tabs.MasterDataViewerTab;

/**
 * This action shows the activities for a given master data.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class MasterDataActivitiesAction extends AbstractAction {

    /**
     * The master data viewer tab where the action is performed.
     */
    private final MasterDataViewerTab tab;

    /**
     * Initializes the action.
     */
    public MasterDataActivitiesAction(MasterDataViewerTab tab) {
        putValue(Action.NAME, _("Activities"));

        this.tab = tab;
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