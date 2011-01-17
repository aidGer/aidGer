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

import de.aidger.model.models.Course;
import de.aidger.view.tabs.ViewerTab.DataType;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * The UI course is used for prettier rendering of the model.
 * 
 * @author aidGer Team
 */
public class UICourse extends Course implements UIModel {

    /**
     * Initializes the Course class.
     */
    public UICourse() {
    }

    /**
     * Initializes the Course class with the given course model.
     * 
     * @param c
     *            the course model
     */
    public UICourse(ICourse c) {
        super(c);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.AbstractModel#toString()
     */
    @Override
    public String toString() {
        if (getDescription() == null) {
            return "";
        } else {
            return getDescription() + " (" + getSemester() + ", "
                    + getLecturer() + ")";
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.UIModel#getDataType()
     */
    @Override
    public DataType getDataType() {
        return DataType.Course;
    }
}
