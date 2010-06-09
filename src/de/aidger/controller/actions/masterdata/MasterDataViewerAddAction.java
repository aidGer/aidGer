package de.aidger.controller.actions.masterdata;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

import de.aidger.view.UI;
import de.aidger.view.tabs.MasterDataEditorTab;
import de.aidger.view.tabs.MasterDataViewerTab;

/**
 * This action replaces the current tab with add-tab for each master data type.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class MasterDataViewerAddAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public MasterDataViewerAddAction() {
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
        JButton btnEdit = (JButton) e.getSource();
        JToolBar toolBar = (JToolBar) btnEdit.getParent();
        MasterDataViewerTab tab = (MasterDataViewerTab) toolBar.getParent();

        UI.getInstance().replaceCurrentTab(
            new MasterDataEditorTab(tab.getType()));
    }
}