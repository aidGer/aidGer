package de.aidger.view.models;

import javax.swing.table.DefaultTableModel;

import de.aidger.model.AbstractModel;

/**
 * The class represents the abstract table model for the master data.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public abstract class MasterDataTableModel extends DefaultTableModel {

    /**
     * Constructs the abstract master data table model.
     */
    public MasterDataTableModel(String[] columnNames) {
        setColumnIdentifiers(columnNames);

        refresh();
    }

    /**
     * Refreshs the whole table.
     */
    public abstract void refresh();

    /**
     * Returns the model at the given index.
     * 
     * @return the model at the given index
     */
    @SuppressWarnings("unchecked")
    public abstract AbstractModel getModel(int i);
}
