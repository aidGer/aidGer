package de.aidger.controller.actions.masterdata;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * This action removes a master data from the table and the database.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class MasterDataViewerDeleteAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public MasterDataViewerDeleteAction() {
        putValue(Action.NAME, _("Delete"));
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