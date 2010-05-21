package de.aidger.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingUtilities;

import de.aidger.view.UI;
import de.aidger.view.tabs.MasterDataViewerTab;
import de.aidger.view.tabs.Tab;

/**
 * Base class for all task pane actions.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class TaskPaneAction extends AbstractAction implements MouseListener {
    /**
     * All possible tasks that can open tabs.
     * 
     * @author aidGer Team
     */
    public enum Task {
        CoursesViewer, AssistantsViewer, FinancialCategoriesViewer, HourlyWagesViewer
    }

    /**
     * The task of the action.
     */
    private final Task task;

    /**
     * Constructs a task pane action.
     * 
     * @param name
     *            the action name
     * @param tabName
     *            the tab name that will be opened on click
     */
    public TaskPaneAction(String name, Task task) {
        putValue(Action.NAME, name);

        this.task = task;
    }

    /**
     * Returns a new tab of the actions task.
     * 
     * @return the new tab
     */
    private Tab getNewTab() {
        switch (task) {
        case CoursesViewer:
        case AssistantsViewer:
        case FinancialCategoriesViewer:
        case HourlyWagesViewer:
            return new MasterDataViewerTab();
        default:
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Tab tab = getNewTab();

        if (tab != null) {
            UI.getInstance().replaceCurrentTab(tab);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            Tab tab = getNewTab();

            if (tab != null) {
                UI.getInstance().addNewTab(tab);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
