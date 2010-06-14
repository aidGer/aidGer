package de.aidger.view.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.DefaultTableModel;

import de.aidger.model.AbstractModel;

/**
 * The class represents the abstract table model.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public abstract class TableModel extends DefaultTableModel implements Observer {

    /**
     * The models that are displayed on the table.
     */
    @SuppressWarnings("unchecked")
    protected List<AbstractModel> models = new ArrayList<AbstractModel>();

    /**
     * Constructs the abstract table model.
     */
    public TableModel(String[] columnNames) {
        setColumnIdentifiers(columnNames);

        refresh();
    }

    /**
     * Refreshs the whole table.
     */
    public void refresh() {
        models.clear();

        setRowCount(0);
    }

    /**
     * Returns the model at the given index.
     * 
     * @return the model at the given index
     */
    @SuppressWarnings("unchecked")
    public AbstractModel getModel(int i) {
        return models.get(i);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable model, Object o) {
        removeRow(models.indexOf(model));

        models.remove(model);
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
