package de.aidger.view.models;

import de.aidger.model.models.FinancialCategory;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;

/**
 * The UI financial category is used for prettier rendering of the model.
 * 
 * @author aidGer Team
 */
public class UIFinancialCategory extends FinancialCategory {

    public UIFinancialCategory() { }

    /**
     * Initializes the Financial Category class with the given financial
     * category model.
     * 
     * @param c
     *            the assistant model
     */
    public UIFinancialCategory(IFinancialCategory fc) {
        super(fc);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.AbstractModel#toString()
     */
    @Override
    public String toString() {
        return getName() + " (" + getYear() + ")";
    }
}
