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

import de.aidger.model.Observer;
import de.aidger.model.AbstractModel;
import de.aidger.view.tabs.ViewerTab.DataType;

/**
 * An interface for all swing models.
 * 
 * @author aidGer Team
 */
public interface GenericListModel extends Observer {

    /**
     * Returns the type of the displayed data.
     * 
     * @return the type of the displayed data
     */
    public DataType getDataType();
}
