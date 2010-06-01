package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.aidger.view.UI;

/**
 * This action opens aidger.de in the default browser.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class HomepageAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public HomepageAction() {
        putValue(Action.NAME, _("Homepage"));
        putValue(Action.SHORT_DESCRIPTION, _("Open homepage"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Only works with Java >= 6
            java.awt.Desktop.getDesktop().browse(
                    new java.net.URI("http://aidger.de"));
        } catch (Exception ex) {
            UI.displayError(ex.getMessage());
        }
    }
}