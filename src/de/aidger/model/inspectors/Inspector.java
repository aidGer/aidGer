package de.aidger.model.inspectors;


/**
 * Basic inspector class for checks before saving models.
 * 
 * @author aidGer Team
 */
public abstract class Inspector {
    /**
     * The result of the check.
     */
    protected String result = "";

    /**
     * Main work for all inspectors is done here by checking the models.
     */
    public abstract void check();

    /**
     * Returns whether the check has failed.
     * 
     * @return whether the check has failed
     */
    public boolean isFail() {
        return !result.isEmpty();
    }

    /**
     * Returns the result of the check.
     * 
     * @return the result of the check
     */
    public String getResult() {
        return result;
    }
}
