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

import javax.swing.DefaultListModel;

import de.aidger.model.AbstractModel;
import de.aidger.view.tabs.ViewerTab.DataType;
import de.aidger.view.utils.UIModelFactory;

@SuppressWarnings("serial")
public class ListModel extends DefaultListModel implements GenericListModel {

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
     * @see de.aidger.view.models.Model#getDataType()
     */
    public DataType getDataType() {
        return dataType;
    }
    
    /**
     * Removes the element with the given id from the list.
     * 
     * @param id
     *          The id of the model to remove from the list.
     */
    public void removeElementById(long id) {
        for(int i = 0; i < getSize(); i++) {
            if(((AbstractModel) getElementAt(i)).getId() != null && ((AbstractModel) getElementAt(i)).getId() == id) {
                removeElementAt(i);
                return;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.GenericListModel#addModel()
     */
    public void addModel(AbstractModel model) {
        Object modelUI = UIModelFactory.create(model);

        if (modelUI == null) {
            modelUI = model;
        }
        
        if(!contains(modelUI)) {
            addElement(modelUI);
        }
        
        fireContentsChanged(this, 0, getSize());
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.Observer#onSave()
     */
    public void onSave(AbstractModel model) {
        Object modelUI = UIModelFactory.create(model);

        if (modelUI == null) {
            modelUI = model;
        }

        removeElementById(model.getId());

        if (!contains(modelUI)) {
            addElement(modelUI);
        }

        fireContentsChanged(this, 0, getSize());
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.Observer#onRemove()
     */
    public void onRemove(AbstractModel model) {
        Object modelUI = UIModelFactory.create(model);

        if (modelUI == null) {
            modelUI = model;
        }

        removeElementById(model.getId());

        fireContentsChanged(this, 0, getSize());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.GenericListModel#removeModelById()
     */
    @Override
    public void removeModelById(long id) {
        removeElementById(id);

        fireContentsChanged(this, 0, getSize());
    }
}