package de.aidger.view.forms;

import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import de.aidger.view.models.GenericListModel;

/**
 * Form is the abstract class of all forms.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public abstract class Form extends JPanel {

    /**
     * The swing models of the form.
     */
    protected final List<GenericListModel> listModels = new Vector<GenericListModel>();

    /**
     * Updates the form. Do nothing by default.
     */
    public void update() {
    }

    /**
     * Returns the swing models of the form.
     * 
     * @return the swing models of the form.
     */
    public List<GenericListModel> getListModels() {
        return listModels;
    }
}
