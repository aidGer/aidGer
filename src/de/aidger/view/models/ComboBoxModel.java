/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2011 The aidGer Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.aidger.view.models;

import java.util.Observable;

import javax.swing.DefaultComboBoxModel;

import de.aidger.model.AbstractModel;
import de.aidger.view.tabs.ViewerTab.DataType;
import de.aidger.view.utils.UIModelFactory;

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
    	//TODO: re-implement
    	/*
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
        */
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

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.DefaultComboBoxModel#addElement(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void addElement(Object element) {
        if (element instanceof Comparable) {
            insertElementAt(element, 0);
        } else {
            super.addElement(element);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.DefaultComboBoxModel#insertElementAt(java.lang.Object,
     * int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void insertElementAt(Object element, int index) {
        if (element instanceof Comparable) {
            int size = getSize();

            //  Determine where to insert element to keep model in sorted order

            for (index = 0; index < size; index++) {
                Comparable c = (Comparable) getElementAt(index);

                if (c.compareTo(element) > 0) {
                    break;
                }
            }
        }

        super.insertElementAt(element, index);
    }
}