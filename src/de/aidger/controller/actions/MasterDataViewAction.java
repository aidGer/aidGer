package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * This action shows a master data in the current tab.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class MasterDataViewAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public MasterDataViewAction() {
        putValue(Action.NAME, _("View"));
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