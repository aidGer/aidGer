/**
 * 
 */
package de.aidger.model.reports;

import java.util.Vector;

/**
 * This class has all the needed variables and methods for balance filters.
 * 
 * @author aidGer Team
 */
public class BalanceFilter {
    /**
     * The groups to be filtered.
     */
    private final Vector groups;

    /**
     * The lecturers to be filtered.
     */
    private final Vector lecturers;

    /**
     * The target audiences to be filtered.
     */
    private final Vector targetAudiences;

    /**
     * Initializes a new BalanceFilter.
     */
    public BalanceFilter() {
        groups = new Vector();
        lecturers = new Vector();
        targetAudiences = new Vector();
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
    public Vector getGroups() {
        return groups;
    }

    /**
     * Returns the lecturer filter vector.
     * 
     * @return lecturers the lecturer filters to be returned.
     */
    public Vector getLecturers() {
        return lecturers;
    }

    /**
     * Returns the target audience filter vector.
     * 
     * @return targetAudience the target audience filters to be returned.
     */
    public Vector getTargetAudiences() {
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
