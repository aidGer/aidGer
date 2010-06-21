package de.aidger.view.forms;

import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import de.aidger.view.models.ListModel;

/**
 * Form is the abstract class of all forms.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public abstract class Form extends JPanel {

    /**
     * The list models of the form.
     */
    protected final List<ListModel> listModels = new Vector<ListModel>();

    /**
     * Updates the form. Do nothing by default.
     */
    public void update() {
    }

    /**
     * Returns the list models of the form.
     * 
     * @return the list models of the form.
     */
    public List<ListModel> getListModels() {
        return listModels;
    }
}
