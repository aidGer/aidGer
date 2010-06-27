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
import de.aidger.view.tabs.ActivityReportViewerTab;

/**
 * This action generates an activity report from the viewer panel.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ActivityReportGenerateAction extends AbstractAction {
    /**
     * Initializes the action.
     */
    public ActivityReportGenerateAction() {
        putValue(Action.NAME, _("Generate"));
        putValue(Action.SHORT_DESCRIPTION, _("Generate the activity report"));

        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
            "/de/aidger/view/icons/report.png")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        ActivityReportViewerTab viewerTab = (ActivityReportViewerTab) UI
            .getInstance().getCurrentTab();

        viewerTab.createReport(viewerTab.getSelectedAssistant());

        viewerTab.visualizeExportButton();
    }
}
