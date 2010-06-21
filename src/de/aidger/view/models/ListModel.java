package de.aidger.view.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import de.aidger.model.AbstractModel;
import de.aidger.view.tabs.ViewerTab.DataType;

/**
 * The class represents a list model.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ListModel extends AbstractListModel implements ComboBoxModel,
        Observer {

    public enum ListModelType {
        List, ComboBox
    }

    /**
     * The internal list model.
     */
    private AbstractListModel listModel;

    /**
     * The type of the list model.
     */
    private final ListModelType type;

    /**
     * The type of the displayed data.
     */
    private final DataType dataType;

    /**
     * The model before it was edited.
     */
    @SuppressWarnings("unchecked")
    private AbstractModel modelBeforeEdit;

    /**
     * Constructs a new list model.
     * 
     * @param type
     *            the type of the list model
     */
    public ListModel(ListModelType type, DataType dataType) {
        this.type = type;
        this.dataType = dataType;

        switch (type) {
        case List:
            listModel = new DefaultListModel();
            break;
        case ComboBox:
            listModel = new DefaultComboBoxModel();
            break;
        }
    }

    /**
     * Sets the model before it was edited.
     * 
     * @param m
     *            the model before it was edited
     */
    @SuppressWarnings("unchecked")
    public void setModelBeforeEdit(AbstractModel m) {
        modelBeforeEdit = m;
    }

    /**
     * Returns the model before it was edited.
     * 
     * @return the model before it was edited
     */
    @SuppressWarnings("unchecked")
    public AbstractModel getModelBeforeEdit() {
        return modelBeforeEdit;
    }

    /**
     * Returns the type of the displayed data.
     * 
     * @return the type of the displayed data
     */
    public DataType getDataType() {
        return dataType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void update(Observable m, Object arg) {
        AbstractModel model = (AbstractModel) m;
        Boolean save = (Boolean) arg;

        Object modelUI = model;

        try {
            Class classUI = Class.forName("de.aidger.view.models.UI"
                    + model.getClass().getSimpleName());

            Class classInterface = Class
                .forName("de.unistuttgart.iste.se.adohive.model.I"
                        + model.getClass().getSimpleName());

            modelUI = classUI.getConstructor(classInterface).newInstance(
                classInterface.cast(model));
        } catch (Exception e) {
        }

        if (save) { // the model was saved

            removeElement(modelBeforeEdit);

            if (!contains(modelUI)) {
                addElement(modelUI);
            }
        } else { // the model was removed

            removeElement(modelUI);
        }

        fireContentsChanged(this, 0, getSize());
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.ListModel#getElementAt(int)
     */
    @Override
    public Object getElementAt(int index) {
        return listModel.getElementAt(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.ListModel#getSize()
     */
    @Override
    public int getSize() {
        return listModel.getSize();
    }

    /**
     * Adds an item at the end of the model.
     * 
     * @param o
     *            the object to be added
     */
    public void addElement(Object o) {
        if (type == ListModelType.List) {
            ((DefaultListModel) listModel).addElement(o);
        } else if (type == ListModelType.ComboBox) {
            ((DefaultComboBoxModel) listModel).addElement(o);
        }
    }

    /**
     * Removes an item from the model.
     * 
     * @param o
     *            the object to be removed
     */
    public void removeElement(Object o) {
        if (type == ListModelType.List) {
            ((DefaultListModel) listModel).removeElement(o);
        } else if (type == ListModelType.ComboBox) {
            ((DefaultComboBoxModel) listModel).removeElement(o);
        }
    }

    /**
     * Tests whether the specified object is a component in this list.
     * 
     * @param o
     *            an object
     * @return true if the specified object is the same as a component in this
     *         list
     */
    public boolean contains(Object o) {
        if (type == ListModelType.List) {
            return ((DefaultListModel) listModel).contains(o);
        } else if (type == ListModelType.ComboBox) {
            return ((DefaultComboBoxModel) listModel).getIndexOf(o) > -1;
        }

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.ComboBoxModel#getSelectedItem()
     */
    @Override
    public Object getSelectedItem() {
        if (type == ListModelType.ComboBox) {
            return ((DefaultComboBoxModel) listModel).getSelectedItem();
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.ComboBoxModel#setSelectedItem(java.lang.Object)
     */
    @Override
    public void setSelectedItem(Object o) {
        if (type == ListModelType.ComboBox) {
            ((DefaultComboBoxModel) listModel).setSelectedItem(o);
        }
    }
}
