package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.aidger.view.tabs.MasterDataViewerTab;

/**
 * This action shows a master data in the current tab.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class MasterDataViewAction extends AbstractAction {

    /**
     * The master data viewer tab where the action is performed.
     */
    private final MasterDataViewerTab tab;

    /**
     * Initializes the action.
     */
    public MasterDataViewAction(MasterDataViewerTab tab) {
        putValue(Action.NAME, _("View"));

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