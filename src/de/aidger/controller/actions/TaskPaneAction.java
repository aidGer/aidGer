package de.aidger.controller.actions;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingUtilities;

import de.aidger.view.TaskPane;
import de.aidger.view.UI;
import de.aidger.view.tabs.BalanceViewerTab;
import de.aidger.view.tabs.BudgetViewerTab;
import de.aidger.view.tabs.EditorTab;
import de.aidger.view.tabs.EmptyTab;
import de.aidger.view.tabs.ProtocolViewerTab;
import de.aidger.view.tabs.Tab;
import de.aidger.view.tabs.ViewerTab;
import de.aidger.view.tabs.ViewerTab.DataType;

/**
 * Base class for all task pane actions.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class TaskPaneAction extends AbstractAction implements MouseListener,
        Cloneable {
    /**
     * All possible tasks that can open tabs.
     * 
     * @author aidGer Team
     */
    public enum Task {
        ViewCourses, ViewAssistants, ViewFinancialCategories, ViewHourlyWages, ViewEmployments, CreateNewEmployment, ViewContracts, ViewActivities, CreateNewActivity, ExportActivities, ViewFullBalance, ViewAnnualBalance, ViewSemesterBalance, ViewBudgetCheck, ViewProtocol, ViewEmpty, Void
    }

    /**
     * The task of the action.
     */
    private final Task task;

    /**
     * The task pane of the action.
     */
    private TaskPane taskPane;

    /**
     * A flag whether the task pane link should be opened.
     */
    private boolean openLink = true;

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

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.AbstractAction#clone()
     */
    @Override
    public TaskPaneAction clone() {
        return new TaskPaneAction((String) getValue(Action.NAME), task);
    }

    /**
     * Sets the task pane of the action.
     * 
     * @param tp
     *            the task pane
     */
    public void setTaskPane(TaskPane tp) {
        taskPane = tp;
    }

    /**
     * The task pane link will not be opened after click.
     */
    public void dropLink() {
        openLink = false;
    }

    /**
     * Returns a new tab of the actions task. If there is no tab for the given
     * task, null will be returned.
     * 
     * @return the new tab
     */
    private Tab getNewTab() {
        switch (task) {
        case ViewCourses:
            return new ViewerTab(DataType.Course);
        case ViewAssistants:
            return new ViewerTab(DataType.Assistant);
        case ViewFinancialCategories:
            return new ViewerTab(DataType.FinancialCategory);
        case ViewHourlyWages:
            return new ViewerTab(DataType.HourlyWage);
        case ViewEmployments:
            return new ViewerTab(DataType.Employment);
        case CreateNewEmployment:
            return new EditorTab(DataType.Employment);
        case ViewContracts:
            return new ViewerTab(DataType.Contract);
        case ViewActivities:
            return new ViewerTab(DataType.Activity);
        case CreateNewActivity:
            return new EditorTab(DataType.Activity);
        case ViewFullBalance:
            return new BalanceViewerTab(1);
        case ViewAnnualBalance:
            return new BalanceViewerTab(2);
        case ViewSemesterBalance:
            return new BalanceViewerTab(3);
        case ViewBudgetCheck:
            return new BudgetViewerTab();
        case ViewProtocol:
            return new ProtocolViewerTab();
        case ViewEmpty:
        case ExportActivities: // TODO
            return new EmptyTab();
        case Void:
        default:
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            // handling for a click on the title bar
            if (taskPane != null) {
                if (!openLink || task == Task.Void) {
                    taskPane.setExpanded(!taskPane.isExpanded());

                    UI.getInstance().validate();

                    return;
                } else if (!taskPane.isExpanded()) {
                    taskPane.setExpanded(true);

                    UI.getInstance().validate();
                }
            }

            Tab tab = getNewTab();

            if (tab != null) {
                UI.getInstance().replaceCurrentTab(tab);
            }
        } else if (SwingUtilities.isMiddleMouseButton(e)) {
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
        e.getComponent().setCursor(
            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setCursor(Cursor.getDefaultCursor());
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // all task pane actions are handled by the mouse listener
    }
}
