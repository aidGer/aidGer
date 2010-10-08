/**
 * 
 */
package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import de.aidger.view.UI;
import de.aidger.view.tabs.ControllingViewerTab;

/**
 * This action generates a controlling report.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ControllingGenerateAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ControllingGenerateAction() {
        putValue(Action.NAME, _("Generate"));
        putValue(Action.SHORT_DESCRIPTION, _("Generate the controlling report"));

        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
            "/de/aidger/res/icons/calculator.png")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        ControllingViewerTab viewerTab = (ControllingViewerTab) UI
            .getInstance().getCurrentTab();
        viewerTab.createTable();
        viewerTab.enableExport();
    }

}
