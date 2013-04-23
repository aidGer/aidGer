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

/**
 * 
 */
package de.aidger.model.reports;

import java.util.ArrayList;

/**
 * This class has all the needed variables and methods for balance filters.
 * 
 * @author aidGer Team
 */
public class BalanceFilter {
    /**
     * The groups to be filtered.
     */
    private final ArrayList<String> groups;

    /**
     * The lecturers to be filtered.
     */
    private final ArrayList<String> lecturers;

    /**
     * The target audiences to be filtered.
     */
    private final ArrayList<String> targetAudiences;

    /**
     * Initializes a new BalanceFilter.
     */
    public BalanceFilter() {
        groups = new ArrayList<String>();
        lecturers = new ArrayList<String>();
        targetAudiences = new ArrayList<String>();
    }

    /**
     * Adds a group filter.
     * 
     * @param title
     *            The group to be added.
     */
    public void addGroup(String group) {
        groups.add(group);
    }

    /**
     * Adds a lecturer filter.
     * 
     * @param lecturer
     *            The filter to be added.
     */
    public void addLecturer(String lecturer) {
        lecturers.add(lecturer);
    }

    /**
     * Adds a target audience filter.
     * 
     * @param targetAudience
     *            The target audience to be added.
     */
    public void addTargetAudience(String targetAudience) {
        targetAudiences.add(targetAudience);
    }

    /**
     * Returns the groups filter vector.
     * 
     * @return groups the group filters to be returned.
     */
    public ArrayList<String> getGroups() {
        return groups;
    }

    /**
     * Returns the lecturer filter vector.
     * 
     * @return lecturers the lecturer filters to be returned.
     */
    public ArrayList<String> getLecturers() {
        return lecturers;
    }

    /**
     * Returns the target audience filter vector.
     * 
     * @return targetAudience the target audience filters to be returned.
     */
    public ArrayList<String> getTargetAudiences() {
        return targetAudiences;
    }

    /**
     * Removes a group filter.
     * 
     * @param group
     *            The group to be removed.
     */
    public void removeGroup(String group) {
        if (groups.contains(group)) {
            groups.remove(group);
        }
    }

    /**
     * Removes a lecturer filter.
     * 
     * @param lecturer
     *            The lecturer to be removed.
     */
    public void removeLecturer(String lecturer) {
        if (lecturers.contains(lecturer)) {
            lecturers.remove(lecturer);
        }
    }

    /**
     * Removes a target audience filter.
     * 
     * @param audience
     *            The target audience to be removed.
     */
    public void removeTargetAudience(String audience) {
        if (targetAudiences.contains(audience)) {
            targetAudiences.remove(audience);
        }
    }
}
