package de.aidger.view.models;

import de.aidger.model.models.Course;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * The UI course is used for prettier rendering of the model.
 * 
 * @author aidGer Team
 */
public class UICourse extends Course {

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
        return getDescription() + " (" + getSemester() + ", " + getLecturer()
                + ")";
    }
}
