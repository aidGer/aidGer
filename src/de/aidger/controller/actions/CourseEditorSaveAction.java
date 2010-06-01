package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.aidger.model.models.Course;
import de.aidger.view.UI;
import de.aidger.view.tabs.CourseEditorTab;
import de.aidger.view.tabs.MasterDataViewerTab;
import de.aidger.view.tabs.MasterDataViewerTab.MasterDataType;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * This action saves the course and replaces the current tab with the course
 * viewer tab.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class CourseEditorSaveAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public CourseEditorSaveAction() {
        putValue(Action.NAME, _("Save"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnSave = (JButton) e.getSource();
        JPanel buttons = (JPanel) btnSave.getParent();
        CourseEditorTab tab = (CourseEditorTab) buttons.getParent();

        Course course;

        if (tab.isEditMode()) {
            course = tab.getCourse();
        } else {
            course = new Course();
        }

        course.setDescription(tab.getDescription());
        course.setSemester(tab.getSemester());
        course.setLecturer(tab.getLecturer());
        course.setNumberOfGroups(tab.getNumberOfGroups());
        course.setTargetAudience(tab.getTargetAudience());
        course.setUnqualifiedWorkingHours(tab.getUnqualifiedWorkingHours());
        course.setScope(tab.getScope());
        course.setPart(tab.getPart());
        course.setGroup(tab.getGroup());
        course.setRemark(tab.getRemark());

        // TODO save financial category..

        try {
            course.save();
        } catch (AdoHiveException e1) {
            UI.displayError(_("Could not save the course."));
        }

        UI.getInstance().replaceCurrentTab(
                new MasterDataViewerTab(MasterDataType.Course));
    }
}