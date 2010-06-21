package de.aidger.view.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;

import de.aidger.model.AbstractModel;

@SuppressWarnings("serial")
public class ListModel extends DefaultListModel implements Observer {

    /**
     * The model before it was edited.
     */
    @SuppressWarnings("unchecked")
    private AbstractModel modelBeforeEdit;

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
    }
}
