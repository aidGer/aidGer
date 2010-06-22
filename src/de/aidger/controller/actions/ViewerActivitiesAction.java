package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 * This action shows the activities for a given model.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ViewerActivitiesAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ViewerActivitiesAction() {
        putValue(Action.NAME, _("Activities"));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
                "/de/aidger/view/icons/party-hat.png")));
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