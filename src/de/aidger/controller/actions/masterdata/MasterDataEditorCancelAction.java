package de.aidger.controller.actions.masterdata;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.aidger.view.UI;
import de.aidger.view.tabs.MasterDataEditorTab;
import de.aidger.view.tabs.MasterDataViewerTab;

/**
 * This action replaces the current tab with the master data viewer tab when
 * user cancelled his previous step.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class MasterDataEditorCancelAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public MasterDataEditorCancelAction() {
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
        JButton btnCancel = (JButton) e.getSource();
        JPanel buttons = (JPanel) btnCancel.getParent();
        MasterDataEditorTab tab = (MasterDataEditorTab) buttons.getParent();

        UI.getInstance().replaceCurrentTab(
                new MasterDataViewerTab(tab.getType()));
    }
}