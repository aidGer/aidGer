package de.aidger.view.models;

import java.util.Observer;

import de.aidger.model.AbstractModel;
import de.aidger.view.tabs.ViewerTab.DataType;

/**
 * An interface for all swing models.
 * 
 * @author aidGer Team
 */
public interface GenericListModel extends Observer {

    /**
     * Sets the model before it was edited.
     * 
     * @param m
     *            the model before it was edited
     */
    @SuppressWarnings("unchecked")
    public void setModelBeforeEdit(AbstractModel m);

    /**
     * Returns the type of the displayed data.
     * 
     * @return the type of the displayed data
     */
    public DataType getDataType();
}
