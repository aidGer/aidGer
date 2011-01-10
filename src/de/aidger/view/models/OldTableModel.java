package de.aidger.view.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import de.aidger.model.AbstractModel;

/**
 * The class represents the abstract table model.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public abstract class OldTableModel extends DefaultTableModel implements Observer {

    /**
     * The static map of models. Each data type has its own list of models.
     */
    @SuppressWarnings("unchecked")
    protected static Map<String, List<AbstractModel>> mapModels = new HashMap<String, List<AbstractModel>>();

    /**
     * The data type specific models that are displayed on the table.
     */
    @SuppressWarnings("unchecked")
    protected List<AbstractModel> models;

    /**
     * The model before it was edited.
     */
    @SuppressWarnings("unchecked")
    private AbstractModel modelBeforeEdit;

    /**
     * Constructs the table model.
     */
    @SuppressWarnings("unchecked")
    public OldTableModel(String[] columnNames) {
        setColumnIdentifiers(columnNames);

        String className = this.getClass().getName();

        // get all models just once from database
        if (mapModels.get(className) == null) {
            models = new ArrayList<AbstractModel>();

            mapModels.put(className, models);

            getAllModels();
        } else {
            // models are already gotten
            models = mapModels.get(className);
        }

        refresh();
    }

    /**
     * Converts the model to a row.
     * 
     * @param model
     *            the model that will be converted
     * @return the row that is converted from the model
     */
    @SuppressWarnings("unchecked")
    protected abstract Object[] convertModelToRow(AbstractModel model);

    /**
     * Gets all models from database and stores them in the table model.
     */
    protected abstract void getAllModels();

    /**
     * Returns the model at the given index.
     * 
     * @return the model at the given index
     */
    @SuppressWarnings("unchecked")
    public AbstractModel getModel(int i) {
        return models.get(i);
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
     * Refreshes the table.
     */
    @SuppressWarnings("unchecked")
    private void refresh() {
        getDataVector().removeAllElements();

        fireTableDataChanged();

        for (AbstractModel model : models) {
            // each model is added as a row to the table
            addRow(convertModelToRow(model));
        }

        fireTableDataChanged();
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

        if (save) { // the model was saved

            models.remove(modelBeforeEdit);

            int index = indexOf(modelBeforeEdit);

            if (index != -1) {
                removeRow(index);
            }

            if (!models.contains(model)) {
                models.add(model);
            }

            addRow(convertModelToRow(model));
        } else { // the model was removed

            models.remove(model);

            int index = indexOf(model);

            if (index != -1) {
                removeRow(index);
            }
        }
    }

    /**
     * Returns the row index of the given model on the table.
     * 
     * @param model
     *            the model on the table
     * @return the row index and -1 if model was not found or is null
     */
    @SuppressWarnings("unchecked")
    private int indexOf(AbstractModel model) {
        if (model == null) {
            return -1;
        }

        Object[] row = new Object[] {};

        try {
            row = convertModelToRow(model);
        } catch (NullPointerException e) {
        }

        for (int i = 0; i < getDataVector().size(); ++i) {
            if (Arrays.equals(row, ((Vector) getDataVector().get(i)).toArray())) {
                return i;
            }
        }

        return -1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
