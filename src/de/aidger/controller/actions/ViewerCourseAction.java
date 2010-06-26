package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.view.UI;
import de.aidger.view.tabs.DetailViewerTab;
import de.aidger.view.tabs.ViewerTab;
import de.aidger.view.tabs.ViewerTab.DataType;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * This action shows the course for a given model.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ViewerCourseAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public ViewerCourseAction() {
        putValue(Action.NAME, _("Course"));
        // TODO
        //putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
        //        "/de/aidger/view/icons/")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ViewerTab tab = (ViewerTab) UI.getInstance().getCurrentTab();

        if (tab.getTable().getSelectedRow() == -1) {
            UI.displayError(_("Please select an entry from the table."));
        } else if (tab.getTable().getSelectedRowCount() > 1) {
            UI.displayError(_("Please select only one entry from the table."));
        } else {
            int index = tab.getTable().getRowSorter().convertRowIndexToModel(
                tab.getTable().getSelectedRow());

            Employment employment = (Employment) tab.getTableModel().getModel(
                index);

            try {
                Course course = new Course((new Course()).getById(employment
                    .getCourseId()));

                UI.getInstance().replaceCurrentTab(
                    new DetailViewerTab(DataType.Course, course));
            } catch (AdoHiveException e1) {
                UI
                    .displayError(_("The related course could not be displayed."));
            }
        }
    }
}