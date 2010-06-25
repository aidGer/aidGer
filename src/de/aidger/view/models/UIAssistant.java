package de.aidger.view.models;

import de.aidger.model.models.Assistant;
import de.unistuttgart.iste.se.adohive.model.IAssistant;

/**
 * The UI assistant is used for prettier rendering of the model.
 * 
 * @author aidGer Team
 */
public class UIAssistant extends Assistant {

    /**
     * The total hours the assistant is employed for a course.
     */
    private double totalHours = 0.0;

    /**
     * Initializes the Assistant class.
     */
    public UIAssistant() {
    }

    /**
     * Initializes the Assistant class with the given assistant model.
     * 
     * @param c
     *            the assistant model
     */
    public UIAssistant(IAssistant a) {
        super(a);
    }

    /**
     * Sets the total hours for this assistant.
     * 
     * @param totalHours
     *            the total hours
     */
    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.AbstractModel#toString()
     */
    @Override
    public String toString() {
        String name = getFirstName() + " " + getLastName();

        if (totalHours == 0.0) {
            return name;
        } else {
            return name + " (" + totalHours + "h)";
        }
    }
}
