/**
 * 
 */
package de.aidger.model.reports;

import java.util.Vector;

/**
 * @author Phil
 * 
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
}
