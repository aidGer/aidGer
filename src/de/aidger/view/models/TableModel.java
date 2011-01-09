package de.aidger.view.models;

import de.aidger.model.AbstractModel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.AbstractTableModel;

/**
 * The class represents the abstract table model.
 *
 * @author aidGer Team
 */
public abstract class TableModel extends AbstractTableModel implements Observer {

    /**
     * Array containing the names of all columns
     */
    private String[] columnNames;

    /**
     * The static map of models. Each data type has its own list of models.
     */
    protected static Map<String, Map<Integer, AbstractModel>> mapModels = new HashMap<String, Map<Integer, AbstractModel>>();

    /**
     * The data type specific models that are displayed on the table.
     */
    private Map<Integer, AbstractModel> models;

    /**
     * The model before it was edited.
     */
    private AbstractModel modelBeforeEdit;

    /**
     * Constructs the tablemodel.
     *
     * @param columnNames
     *              The names of all columns
     */
    public TableModel(String[] columnNames) {
        this.columnNames = columnNames;

        String className = this.getClass().getName();

        // get all models just once from database
        if (mapModels.get(className) == null) {
            models = new HashMap<Integer, AbstractModel>();
            mapModels.put(className, models);            
        } else {
            models = mapModels.get(className);
        }

        fireTableDataChanged();
    }

    /**
     * Get the value of the row for a specified model.
     *
     * @param model
     *          The model for wich the row value is needed
     * @param row
     *          The index of the row
     * @return The value of the row
     */
    protected abstract Object getRowValue(AbstractModel model, int row);

    /**
     * Get the model with the specified index.
     *
     * @param idx
     *          The index of the model
     * @return The model or null
     */
    protected abstract AbstractModel getModelFromDB(int idx);

        /**
     * Returns the model at the given index.
     *
     * @return the model at the given index
     */
    public AbstractModel getModel(int i) {
        return models.get(i);
    }

    /**
     * Sets the model before it was edited.
     *
     * @param m
     *            the model before it was edited
     */
    public void setModelBeforeEdit(AbstractModel m) {
        modelBeforeEdit = m;
    }

    /**
     * Returns the model before it was edited.
     *
     * @return the model before it was edited
     */
    public AbstractModel getModelBeforeEdit() {
        return modelBeforeEdit;
    }

    /**
     * Return the count of columns.
     *
     * @return The count of columns
     */
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Return the name of the column.
     *
     * @param column
     *          The index of the column
     * @return The name of the column
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Return the value at a specific position.
     *
     * @param col
     *          The column of the data
     * @param row
     *          The row of the data
     * @return The value at the position
     */
    public Object getValueAt(int col, int row) {
        AbstractModel model = null;
        if (models.containsKey(col)) {
            model = models.get(col);
        } else {
            model = getModelFromDB(col);
            models.put(col, model);
        }

        return getRowValue(model, row);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    public void update(Observable m, Object arg) {
        AbstractModel model = (AbstractModel) m;
        Boolean save = (Boolean) arg;
        int index = indexOf(model);

        if (save) { // the model was saved
            models.remove(index);
            models.put(index, model);
            fireTableRowsUpdated(index, index);
        } else { // the model was removed
            models.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }

    /**
     * Returns the row index of the given model on the table.
     *
     * @param model
     *            the model on the table
     * @return the row index and -1 if model was not found or is null
     */
    private int indexOf(AbstractModel model) {
        if (model == null) {
            return -1;
        }

        Iterator it = models.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (((AbstractModel) pair.getValue()).equals(model)) {
                return (Integer) pair.getKey();
            }
        }

        return -1;
    }

}
