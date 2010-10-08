package de.aidger.model.inspectors;

import java.util.List;

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
     * Flag whether the check requires an updated database.
     */
    protected boolean updatedDBRequired = false;

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

    /**
     * Returns whether one of the inspectors requires an updated database.
     * 
     * @param inspectors
     *            list of inspectors
     * @return whether one of the inspectors requires an updated database.
     */
    public static boolean isUpdatedDBRequired(List<Inspector> inspectors) {
        for (Inspector inspector : inspectors) {
            if (inspector.updatedDBRequired) {
                return true;
            }
        }

        return false;
    }
}
