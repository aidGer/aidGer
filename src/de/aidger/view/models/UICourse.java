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
