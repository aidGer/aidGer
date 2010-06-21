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
     * Initializes the Assistant class with the given assistant model.
     * 
     * @param c
     *            the assistant model
     */
    public UIAssistant(IAssistant a) {
        super(a);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.AbstractModel#toString()
     */
    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }
}
