package de.aidger.view.models;

import java.text.SimpleDateFormat;

import de.aidger.model.models.Contract;
import de.unistuttgart.iste.se.adohive.model.IContract;

/**
 * The UI contract is used for prettier rendering of the model.
 * 
 * @author aidGer Team
 */
public class UIContract extends Contract {

    /**
     * Initializes the Contract class with the given contract model.
     * 
     * @param c
     *            the contract model
     */
    public UIContract(IContract c) {
        super(c);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.AbstractModel#toString()
     */
    @Override
    public String toString() {
        return getType()
                + " ("
                + (new SimpleDateFormat("dd.MM.yy"))
                    .format(getCompletionDate()) + ")";
    }
}
