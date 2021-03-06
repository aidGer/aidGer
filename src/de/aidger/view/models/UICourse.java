/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2013 The aidGer Team
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.aidger.model.models.Course;
import de.aidger.view.tabs.ViewerTab.DataType;

/**
 * The UI course is used for prettier rendering of the model.
 * 
 * @author aidGer Team
 */
public class UICourse extends Course implements UIModel, Comparable<UICourse> {

    /**
     * The pattern for a semester (SSXX, WSXXXX).
     */
    private final Pattern patternSemester = Pattern
        .compile("[SS|WS]([0-9]{2})[0-9]{0,2}");

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
    public UICourse(Course c) {
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

    /**
     * Returns the year of a given semester. If there was an error, the year 0
     * is returned.
     * 
     * @param semester
     *            the semester (SSXX, WSXXXX, XXXX)
     * @return the year in 4 digits
     */
    private Integer getYearOfSemester(String semester) {
        try {
            Matcher m = patternSemester.matcher(semester);
            return m.find() ? Integer.valueOf("20" + m.group(1)) : Integer
                .valueOf(semester);
        } catch (Exception e) {
            return 0;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(UICourse o) {
        int c = 0;

        if (getDescription() != null && o.getDescription() != null) {
            c = getDescription().compareTo(o.getDescription());
        }

        if (getSemester() == null || o.getSemester() == null) {
            return c;
        }

        Integer year = getYearOfSemester(getSemester()), oYear = getYearOfSemester(o
            .getSemester());

        if (year < oYear) {
            return 1;
        } else if (year > oYear) {
            return -1;
        } else {
            int c_ = getSemester().substring(0, 1).compareTo(
                o.getSemester().substring(0, 1));
            if (c_ == 0) {
                return c;
            } else {
                return -c_;
            }
        }
    }
}
