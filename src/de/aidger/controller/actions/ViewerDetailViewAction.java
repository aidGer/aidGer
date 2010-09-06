package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import de.aidger.view.UI;
import de.aidger.view.tabs.DetailViewerTab;
import de.aidger.view.tabs.ViewerTab;

/**
 * This action shows a model in the current tab in detail.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ViewerDetailViewAction extends AbstractAction implements
        MouseListener {

    /**
     * Initializes the action.
     */
    public ViewerDetailViewAction() {
        putValue(Action.NAME, _("View"));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
            "/de/aidger/res/icons/property.png")));
    }

    /**
     * Displays the selected entry in detail.
     */
    private void displayEntry() {
        ViewerTab tab = (ViewerTab) UI.getInstance().getCurrentTab();

        if (tab.getTable().getSelectedRow() == -1) {
            UI.displayError(_("Please select an entry from the table."));
        } else if (tab.getTable().getSelectedRowCount() > 1) {
            UI.displayError(_("Please select only one entry from the table."));
        } else {
            int index = tab.getTable().getRowSorter().convertRowIndexToModel(
                tab.getTable().getSelectedRow());

            UI.getInstance().replaceCurrentTab(
                new DetailViewerTab(tab.getType(), tab.getTableModel()
                    .getModel(index)));
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
        displayEntry();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getClickCount() == 2) {
            displayEntry();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
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
}