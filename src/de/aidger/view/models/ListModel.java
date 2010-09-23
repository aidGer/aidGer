package de.aidger.view.models;

import java.util.Observable;

import javax.swing.DefaultListModel;

import de.aidger.model.AbstractModel;
import de.aidger.view.tabs.ViewerTab.DataType;
import de.aidger.view.utils.UIModelFactory;

@SuppressWarnings("serial")
public class ListModel extends DefaultListModel implements GenericListModel {

    /**
     * The model before it was edited.
     */
    @SuppressWarnings("unchecked")
    private AbstractModel modelBeforeEdit;

    /**
     * The type of the displayed data.
     */
    private final DataType dataType;

    /**
     * Constructs a new list model.
     * 
     * @param dateType
     *            the type of the displayed data
     */
    public ListModel(DataType dataType) {
        this.dataType = dataType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.aidger.view.models.Model#setModelBeforeEdit(de.aidger.model.AbstractModel
     * )
     */
    @SuppressWarnings("unchecked")
    public void setModelBeforeEdit(AbstractModel m) {
        modelBeforeEdit = m;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.Model#getDataType()
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

        Object modelUI = UIModelFactory.create(model);

        if (modelUI == null) {
            modelUI = model;
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
}