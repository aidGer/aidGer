package de.aidger.view.models;

import java.util.Observable;

import javax.swing.DefaultComboBoxModel;

import de.aidger.model.AbstractModel;
import de.aidger.view.tabs.ViewerTab.DataType;

@SuppressWarnings("serial")
public class ComboBoxModel extends DefaultComboBoxModel implements
        GenericListModel {

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
    public ComboBoxModel(DataType dataType) {
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

    /**
     * Returns whether the model contains the object.
     * 
     * @param o
     *            the object
     * @return whether the model contains the object
     */
    public boolean contains(Object o) {
        return getIndexOf(o) > -1;
    }
}